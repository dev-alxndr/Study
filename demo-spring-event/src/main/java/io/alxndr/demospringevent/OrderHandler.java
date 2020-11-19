package io.alxndr.demospringevent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StopWatch;

@Component
public class OrderHandler {

    @Autowired
    private MessageService messageService;

    @Async
    @EventListener
    public void sendKakaoEvent(Order order) {
        StopWatch sw = new StopWatch();
        sw.start();

        messageService.sendKakao();

        sw.stop();
        System.out.println("KAKAO Send  " + sw.getTotalTimeSeconds() + " |  Thread = " + Thread.currentThread());
    }

    @Async
    @EventListener
    public void sendEmailEvent(Order order) {
        StopWatch sw = new StopWatch();
        sw.start();
        messageService.sendEmail();
        sw.stop();
        System.out.println("Email Send  " + sw.getTotalTimeSeconds() + " |  Thread = " + Thread.currentThread());
    }

    @TransactionalEventListener
    public void sendPushEvent(Order order) {
        messageService.sendPushMessage();
    }

}
