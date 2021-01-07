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
