# JPA에서 DTO로 직접 반환 (Collection)

`37e7f9b`

### oneToMany 관계 DTO 변환
- OrderApiController.java
```java
@GetMapping("/api/v4/orders")
public List<OrderQueryDto> ordersV4() {
    return orderQueryRepository.findOrderQueryDtos();
}
```
OrderQueryRepository.java
```java
public List<OrderQueryDto> findOrderQueryDtos() {
    List<OrderQueryDto> orders = findOrders(); // Query 1번

    orders.forEach(o -> {
        List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); // Query N번
        o.setOrderItems(orderItems);
    });
    return orders;
}


// OrderItems Query
private List<OrderItemQueryDto> findOrderItems(Long orderId) {
    return em.createQuery("select new io.alxndr.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) from OrderItem oi " +
                "join oi.item i " +
                "where oi.order.id = :orderId", OrderItemQueryDto.class)
            .setParameter("orderId", orderId)
            .getResultList();
}

// Order Query
private List<OrderQueryDto> findOrders() {
        return em.createQuery("select new io.alxndr.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o " +
                    "join o.member m " +
                    "join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

```
> `findOrderQueryDtos()` 에서 주문을 가져온 후 loop를 돌면서   
> orderId에 해당하는 OrderItem을 찾아서 set해주게 됩니다.

결과적으로 주문10개라면 있다면 `1+10` 번의 쿼리가 실행됩니다.

---

# JPA에서 DTO 직접 조회 - Collection 최적화

`ed07a74`

- OrderApiController.java   
```java
@GetMapping("/api/v5/orders")
public List<OrderQueryDto> ordersV5() {
    return orderQueryRepository.findAllByDto_optimization();
}
```

- OrderQueryRepository.java
```java
public List<OrderQueryDto> findAllByDto_optimization() {
    List<OrderQueryDto> orders = findOrders();

    List<Long> orderIds = toOrderIds(orders);

    List<OrderItemQueryDto> orderItems = getOrderItems(orderIds);   // in절로 한번에 조회

    Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
            .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));     // List를 Map으로 변환

    orders.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

    return orders;
}


private List<Long> toOrderIds(List<OrderQueryDto> orders) {
    List<Long> orderIds = orders.stream()
            .map(o -> o.getOrderId())
            .collect(Collectors.toList());  // 주문에서 OrderId를 뽑아서 collect
    return orderIds;
}


private List<OrderItemQueryDto> getOrderItems(List<Long> orderIds) {
    return em.createQuery("select new io.alxndr.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) from OrderItem oi " +
            "join oi.item i " +
            "where oi.order.id in :orderIds", OrderItemQueryDto.class)
            .setParameter("orderIds", orderIds)
            .getResultList();
}
```

V4와의 차이점은 가져온 Orders를 `toOrderIds()`에서 loop돌며 Id만 List에 담아서 리턴하고 `getOrderItems()`에서 orderIds를 `IN절`로 여러 개의 주문에 해당하는 상품을 검색한다.   
그 다음 
```java
orderItems.stream()
        .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
```
`List`를 `Map`으로 바꿔준다.   

다음 orders를 `forEach` 돌면서 `Map`에서 id값으로 키를 찾아서 set해준다.

### 정리
- Query : Order 1번, 컬렉션 1번

#### v6는 제외. 너무 복잡해짐
- 쿼리는 한번이지만 중복이 늘어나기때문에 v5보다 느려질 수 있음.
- 페이징 불가능 (이것만해도 안할 이유..)