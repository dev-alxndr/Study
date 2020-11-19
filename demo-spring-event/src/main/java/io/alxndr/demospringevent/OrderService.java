package io.alxndr.demospringevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void save() {
        System.out.println("ORDER PROCESSING...");

        Order order = new Order();
        order.setOrderId(1L);
        order.setName("ALXNDR");
        order.setPrice("10000");
        order.setMethod("CREDIT");

        applicationEventPublisher.publishEvent(order);
        System.out.println("ORDER SUCCESS");
    }
}
