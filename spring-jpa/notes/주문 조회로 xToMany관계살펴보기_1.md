# 주문 조회로 xToMany 관계살펴보기_1.md

## Collection(일대다) 조회 최적화

OneToOne, ManyToOne 조회는 Fetch Join으로 쉽게 해결했지만,
ManyToMany, OneToMany를 조인을 하게되면 데이터가 뻥튀기되기 때문에 문제가 발생한다. 해결하는 방법을 순차적으로 알아본다.


- OrderApiController.class
```java
@GetMapping("/api/v1/orders")
public List<Order> ordersV1() {
    List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
    for (Order order : all) {
        order.getMember().getName();
        order.getDelivery().getStatus();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(oi -> oi.getItem().getName());
    }

    return all;
}
```

Order 조회하면서 Member, Delivery, OrderItems를 출력하기 위해 루프를 돌면서 강제로 초기화를 해줬다.

> v1 방법은 Entity를 직접 노출하기 떄문에 사용하면 안된다!
