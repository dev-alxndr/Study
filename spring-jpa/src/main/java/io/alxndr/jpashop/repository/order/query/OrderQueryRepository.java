package io.alxndr.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> orders = findOrders();

        orders.forEach(o -> {

        });

    }

    private List<OrderQueryDto> findOrders() {
        em.createQuery("select new io.alxndr.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o " +
                "join o.member m " +
                "join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
