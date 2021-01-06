# 간단한 주문 조회 v4: JPA에서 DTO로 바로 조회

- OrderSimpleController
```java
@GetMapping("/api/v4/simple-orders")
public List<OrderSimpleQueryDto> ordersV4() {
    List<OrderSimpleQueryDto> orderDtos = orderRepository.findOrderDtos();

    return orderDtos;
}
```

- OrderSimpleQueryDto
```java
@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
```

- OrderRepository
```java
/*
* return as DTO
* */
public List<OrderSimpleQueryDto> findOrderDtos() {
    return em.createQuery("select new io.alxndr.jpashop.repository.OrderSimpleQueryDto(o.id, o.member.name, o.orderDate, o.status, o.delivery.address) " +
                "from Order o " +
                "join o.member m " +
                "join o.delivery d ", OrderSimpleQueryDto.class)
            .getResultList();
}
```
> new operation으로 패키지명부터 적어야한다.
- 일반적인 SQL을 사용할 때 처럼 원하는 값을 선택해서 조회
- JPQL 결과를 DTO로 즉시 반환
- `select`절에서 원하는 데이터를 직접 선택하므로 DB -> 어플리케이션 network 용량 최적화 (미비한 차이)

### 기존 V3 vs V4 비교

- v3 
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
모든 컬럼을 가지고옴


- v4
```sql
select
        order0_.order_id as col_0_0_,
        member1_.name as col_1_0_,
        order0_.order_date as col_2_0_,
        order0_.status as col_3_0_,
        delivery2_.city as col_4_0_,
        delivery2_.street as col_4_1_,
        delivery2_.zipcode as col_4_2_ 
    from
        orders order0_ 
    inner join
        member member1_ 
            on order0_.member_id=member1_.member_id 
    inner join
        delivery delivery2_ 
            on order0_.delivery_id=delivery2_.delivery_id
```
내가 선택한 컬럼만 select 해옴

## v3 와 v4 장단점

### v3
- 장점
    - 실제 Entity를 가져오기 때문에 활용성이 높음
- 단점 
    - 불필요한 컬럼들까지 데이터를 가져옴
### v4
- 장점
    - 선택한 컬럼만 가져오기 때문에 아주 작은 최적화
- 단점
    - 코드가 지저분해지고, 스펙을 고정해뒀기 때문에 재활용성 down


### 정리 
개인적인 생각으로는 v3의 코드가 낫다고 본다.
v3와 v4 성능상의 큰 차이 나지않고   
v4의 코드가 지저분하고 재활용성이 떨어지므로 v3 버전을   
사용할 것 같다. (극한의 성능 최적화를 하려면 사용하겠지만...)