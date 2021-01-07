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

---

# 정리

+ Entity
    - 컬렉션 페이징과 한계돌파 v3.1
        - 컬렉션은 패치조인시 페이징 불가
        - ToOne관계는 패치 조인으로 쿼리수 최적화
        - 컬렉션은 패치 조인 대신에 지연로딩을 유지하고 `hibernate.default_batch_fetch_size`, `@BatchSize`로 최적화
+ DTO 직접 조회
    - JPA에서 DTO를 직접 조회
    - 컬렉션 조회 최적화 : 일대다 관계인 컬렉션은 IN절을 활용해서 메모리에 미리 조회해서 최적화 v5
    - 

### 권장 최적화 순서
+ 엔티티 조회 방식으로 우선 접근
    - 패치조인으로 쿼리 수 최적화
    - 컬렉션 최적화
        * 페이징 필요시 `hibernate.default_batch_fetch_size`, `@BatchSize`로 최적화
        * 페이징 필요 없을시 -> `fetch join`
+ 엔티티 조회방식으로 해결이 안된다면 DTO 조회 방식 사용
+ DTO 조회방식으로 안되면 NativeSQL or 스프링 JDBC Template 사용

> 엔티티 조회방식은 패치 조인이나 `hibernate.default_batch_fetch_size`, `@BatchSize`같이 코드를 거의 수정하지 않고, 옵션만 약간 변경하여 다양한 성능 최적화를 시도할 수 있다. 반면에 DTO를 직접 조회하는 방식은 성능을 최적화하거나 성능 최적화 방식을 변경할 때 많은 코드를 수정해야한다.

> ## v3.1(Entity), V5(DTO) 선택해서 하세요.👍