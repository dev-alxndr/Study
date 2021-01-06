package io.alxndr.jpashop.repository;

import io.alxndr.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m ";

        return em.createQuery(jpql, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)    // 최대 1000건
                .getResultList();

    }


    /**
     * JPA Criteria NOT RECOMMEND
     *
     * 단점
     * 1. 가독성이 떨어짐
     * 2. 어떤 쿼리가 생성되는지 정확히 알기 힘듦.
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Join<Object, Object> member = root.join("member", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        // 주문 상태
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(root.get("status"), orderSearch.getOrderStatus());
            predicates.add(status);
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(member.get("name"), "%" + orderSearch.getMemberName() + "%");
            predicates.add(name);
        }

        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Order> tq = em.createQuery(query).setMaxResults(1000);

        return tq.getResultList();
    }

    /*
    * Fetch Join으로 N+1 문제 해결
    * */
    public List<Order> findAllWithMemberAndDelivery() {
        return em.createQuery("select o from Order o " +
                    "join fetch o.member m " +
                    "join fetch o.delivery d", Order.class)
                .getResultList();
    }

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

    public List<Order> findAllWithItem() {
        return em.createQuery(
                "select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery d " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item", Order.class)
                .getResultList();

    }
}
