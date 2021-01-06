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