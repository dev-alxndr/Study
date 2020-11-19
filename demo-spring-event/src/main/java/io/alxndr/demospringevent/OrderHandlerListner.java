package io.alxndr.demospringevent;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Component
public class OrderHandlerListner implements ApplicationListener<OrderEvent> {

    @Autowired
    MessageService messageService;

    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println("EVENT!!");
        messageService.sendKakao();
    }
}
