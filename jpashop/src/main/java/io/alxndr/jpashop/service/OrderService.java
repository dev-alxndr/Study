package io.alxndr.jpashop.service;


import io.alxndr.jpashop.domain.Delivery;
import io.alxndr.jpashop.domain.Member;
import io.alxndr.jpashop.domain.Order;
import io.alxndr.jpashop.domain.OrderItem;
import io.alxndr.jpashop.domain.item.Item;
import io.alxndr.jpashop.repository.ItemRepository;
import io.alxndr.jpashop.repository.MemberRepository;
import io.alxndr.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        /*
        주문 저장
        Cascade Option을 ALL을 해줬기 때문에 Order만 Persist해도 포함된 Entity도 persist
         */
        orderRepository.save(order);
        return order.getId();

    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 조회
        Order findOrder = orderRepository.findOne(orderId);
        // 주문 취소
        findOrder.cancel();
    }

    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
