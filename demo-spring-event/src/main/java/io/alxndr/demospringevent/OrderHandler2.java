package io.alxndr.demospringevent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StopWatch;

@Component
public class OrderHandler2 {

    @Autowired
    private OrderService eventService;

    @Autowired
    private MessageService messageService;

    @EventListener
    public void actionAfterOrder(Order order) {
        System.out.println("data = " + order.toString());
        messageService.sendKakao();
    }


}
