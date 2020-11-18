package io.alxndr.demospringevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void save() {
        System.out.println("ORDER PROCESSING...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.setOrderId(1L);
        order.setName("ALXNDR");
        order.setPrice("10000");
        order.setMethod("CREDIT");

        System.out.println("ORDER SUCCESS");

        applicationEventPublisher.publishEvent(new OrderEvent(this, order));
    }
}
