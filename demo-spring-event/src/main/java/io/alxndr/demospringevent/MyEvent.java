package io.alxndr.demospringevent;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    private Event data;

    public MyEvent(Object source) {
        super(source);
    }

    public MyEvent(Object source, Event data) {
        super(source);
        this.data = data;
    }

    public Event getData() {
        return data;
    }
}
