# 주문 조회로 xToMany 관계살펴보기_3.md
## 페이징과 한계 돌파

- 컬렉션을 패치조인하면 페이징이 불가하다.
    - 컬렉션을 패치 조인하면 일대다 조인이 발생하므로 데이터가 예측할 수 없이 증가한다.
    - 일대다에서 일(1)을 기준으로 페이징을 하는 것이 목적이다. 그런데 데이터는 다(N) 기준으로 Row가 생성된다.
- 페이징을 시도하면 하이버네이트는 경고 로그를 남기로 메모리에서 페이징을 한다.


## 한계 돌파
- 먼저 ToOne관계를 모두 패치조인한다. ToOne관계는 Row를 증가시키지 않으므로 페이장에 영향이 없다.
- 컬렉션은 지연로딩으로 조회한다.
- 지연 로딩 성능 최적화를 위해 `hibernate.default_batch_size`, `@BatchSize`를 적용한다.
    - `hibernate.default_batch_size` Global setting
    - `@BatchSize`  개별 세팅


- OrderApiController.java
```java
// Collection 조회 한계 돌파
@GetMapping("/api/v3_1/orders")
public List<OrderDto> ordersV3_page() {
    List<Order> allWithItem = orderRepository.findAllWithMemberAndDelivery();
    List<OrderDto> orders = allWithItem.stream()
            .map(o -> new OrderDto(o))
            .collect(Collectors.toList());
    return orders;
}
```

### sql 실행
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
Fetch Join으로 `Member`, `Delivery`는 한번에 가지고 온다.
OrderItems = 2개의 데이터가 있다.

OrderItems를 조회한다.
```sql
select
    orderitems0_.order_id as order_id5_5_0_,
    orderitems0_.order_item_id as order_it1_5_0_,
    orderitems0_.order_item_id as order_it1_5_1_,
    orderitems0_.count as count2_5_1_,
    orderitems0_.item_id as item_id4_5_1_,
    orderitems0_.order_id as order_id5_5_1_,
    orderitems0_.order_price as order_pr3_5_1_ 
from
    order_item orderitems0_ 
where
    orderitems0_.order_id=?
```

그다음 OrderItem에 있는 item을 지연 로딩한다.
```sql
select
    item0_.item_id as item_id2_3_0_,
    item0_.name as name3_3_0_,
    item0_.price as price4_3_0_,
    item0_.stock_quantity as stock_qu5_3_0_,
    item0_.artist as artist6_3_0_,
    item0_.etc as etc7_3_0_,
    item0_.author as author8_3_0_,
    item0_.isbn as isbn9_3_0_,
    item0_.actor as actor10_3_0_,
    item0_.director as directo11_3_0_,
    item0_.dtype as dtype1_3_0_ 
from
    item item0_ 
where
    item0_.item_id=?
```

> ToOne관계만 Fetch Join이고   
> ToMany관계가 지연로딩으로 되기떄문에 페이징에 문제는 없다.

ToMany관계의 N+1문제를 해결한다.

아래의 옵션을 추가해준다.
- application.yml
```yml
spring:
    jpa:
        properties:
            hibernate:
                default_batch_fetch_size: 100
```

옵션을 적용하고
`/api/v3_1/orders`를 호출해보면 아래와 같은 sql이 실행된다.

- Order entity
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
        on order0_.delivery_id=delivery2_.delivery_id limit ?
```

- OrderItem Entity
```sql
select
    orderitems0_.order_id as order_id5_5_1_,
    orderitems0_.order_item_id as order_it1_5_1_,
    orderitems0_.order_item_id as order_it1_5_0_,
    orderitems0_.count as count2_5_0_,
    orderitems0_.item_id as item_id4_5_0_,
    orderitems0_.order_id as order_id5_5_0_,
    orderitems0_.order_price as order_pr3_5_0_ 
from
    order_item orderitems0_ 
where
    orderitems0_.order_id in (
        ?, ?
    )
```
위 Query를 살펴보면 where절에 `order_id`를 in 쿼리로 한번에 가지고 온다.
in의 들어갈 갯수는 위 yml에 설정한 수 만큼 들어간다.

> 1 + N + M 쿼리가 실행되던게 1 + 1 + 1 쿼리로 바뀌었다.