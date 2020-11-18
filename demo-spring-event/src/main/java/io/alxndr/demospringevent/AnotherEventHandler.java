package io.alxndr.demospringevent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StopWatch;

@Component
public class AnotherEventHandler {

    @Autowired
    private EventService eventService;

    @Async
    @EventListener
    public void eventAfterSave(MyEvent event) {
        System.out.println("START FIRST EVENT ON THREAD : " + Thread.currentThread());
        eventService.print();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();

        System.out.println("END OF Fist @EventListner / " + stopWatch.getTotalTimeSeconds());
    }


    @Async
    @EventListener
    public void eventAfterSave2(MyEvent event) {
        System.out.println("START SECOND EVENT ON THREAD : " + Thread.currentThread());
        eventService.print();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();

        System.out.println("END OF Second @EventListner / " + stopWatch.getTotalTimeSeconds());
    }

    @Async
    @TransactionalEventListener
    public void errorOccured(MyEvent event) {
        throw new RuntimeException("Error");
    }



}
