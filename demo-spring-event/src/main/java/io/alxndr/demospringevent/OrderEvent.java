package io.alxndr.demospringevent;


import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {

    private Order data;

    public OrderEvent(Object source) {
        super(source);
    }

    public OrderEvent(Object source, Order data) {
        super(source);
        this.data = data;
    }

    public Order getData() {
        return data;
    }
}
