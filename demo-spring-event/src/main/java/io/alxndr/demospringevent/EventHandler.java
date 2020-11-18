package io.alxndr.demospringevent;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Component
public class EventHandler implements ApplicationListener<MyEvent> {

    @Async
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("data = " + event.getData());
    }
}
