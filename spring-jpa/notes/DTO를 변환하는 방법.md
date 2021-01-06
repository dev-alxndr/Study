# 간단한 주문 조회 v1: Entity를 직접노출   

Last commit Point `24166ce`   

Entity를 외부로 노출하게되면 생기는 문제점과 해결법
무한루프에 빠지게됨.
- 양방향 맵핑시 한곳에는 @JsonIgnore해줘야함
- HttpMessageConversionException: Type definition error 발생
     1. Order -> Member는 지연로딩이기때문에 프록시 객체임
     2. Jackson은 프록시 객체를 변환하지 못함.
- Hibernate5Module로 해결할 수 있지만 Lazy Loading은 Null로 나오게됨
- hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true) 이렇게해서 강제 로딩할 수 있음 (비추천)


### Hibernate5Module

```gradle
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-hibernate5'
```

```java
@Bean
Hibernate5Module hibernate5Module() {
    Hibernate5Module hibernate5Module = new Hibernate5Module();
    hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
    return hibernate5Module;
}
```

---
   
# 간단한 주문 조회 v2: 엔티티를 DTO로 변환

Start Commit `a8dd726`

Inner Class로 DTO를 만들어준다.
```java
@Data
private static class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }
}
```

```java
@GetMapping("/api/v2/simple-orders")
public List<SimpleOrderDto> ordersV2() {
    List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
    return orders.stream()
            .map(SimpleOrderDto::new)
            .collect(Collectors.toList());
}
```
Entity를 Stream을 활용해서 SimpleOrderDto로 변환하여 Return한다.


### v1, v2가 가지는 문제점
- Lazy Loading으로 인한 쿼리 조회가 너무 많음.
    - 주문 한개당 총 3번(회원, 주문, 배송) 쿼리 발생
    - 100명이면 100 * 3번의 쿼리 발생 (n + 1) 문제 ..(내 생각도 1+n이라고 칭하는게 맞지않을까?..)   

order 조회 1번   
order -> member 지연로딩 조회 N번   
order -> delivery 지연로딩 조회 N번   
예) order의 결과가 2개면 1 + 2 + 2번 실행된다 (최악의 경우)

* 만약 2개의 주문을 같은 회원이 주문했다면 Member객체는 한번만 실행된다. -> 영속성컨텍스트를 체크를 하기 때문에 같은 Member는 한번만 실행된다.

---
### 다음으로는 N+1문제를 `fetch join`으로 해결하겠습니다.


# 간단한 주문 조회 V3: 엔티티를 DTO로 변환 - 페치 조인 최적화

`08f8eac`

#### 기존의 N+1문제를 Fetch join으로 해결하기
- OrderRepository.class
```java
public List<Order> findAllWithMemberAndDelivery() {
    return em.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d", Order.class)
            .getResultList();
}
```
- OrderController.class
```java
@GetMapping("/api/v3/simple-orders")
public List<SimpleOrderDto> ordersV3() {
    List<Order> list = orderRepository.findAllWithMemberAndDelivery();
    return list.stream()
            .map(SimpleOrderDto::new)
            .collect(Collectors.toList());
}
```
`join fetch o.member m`로 한번에 가져오게됨.

- 실제 쿼리
```sql
select
    order0_.order_id as order_id1_6_0_,
    member1_.member_id as member_i1_4_1_,
    delivery2_.delivery_id as delivery1_2_2_,
    order0_.delivery_id as delivery4_6_0_,
    order0_.member_id as member_i5_6_0_,
    order0_.order_date as order_da2_6_0_,
    order0_.status as status3_6_0_,
    member1_.city as city2_4_1_,
    member1_.street as street3_4_1_,
    member1_.zipcode as zipcode4_4_1_,
    member1_.name as name5_4_1_,
    delivery2_.city as city2_2_2_,
    delivery2_.street as street3_2_2_,
    delivery2_.zipcode as zipcode4_2_2_,
    delivery2_.status as status5_2_2_ 
from
    orders order0_ 
inner join
    member member1_ 
        on order0_.member_id=member1_.member_id 
inner join
    delivery delivery2_ 
        on order0_.delivery_id=delivery2_.delivery_id

```