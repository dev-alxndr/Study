package io.alxndr.jpashop.api;

import io.alxndr.jpashop.domain.Order;
import io.alxndr.jpashop.repository.OrderRepository;
import io.alxndr.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * XToOne (ManyToOne, OneToOne)
 *
 * ORDER
 * ORDER -> Member
 * ORDER -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * 무한루프에 빠지게됨.
     *
     * 1. 양방향 맵핑시 한곳에는 @JsonIgnore해줘야함
     * 2. HttpMessageConversionException: Type definition error 발생
     *      1. Order -> Member는 지연로딩이기때문에 프록시 객체임
     *      2. Jackson은 프록시 객체를 변환하지 못함.
     * 3. Hibernate5Module로 해결할 수 있지만 Lazy Loading은 Null로 나오게됨
     */

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        return all;
    }


}
