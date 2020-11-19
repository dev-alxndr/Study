package io.alxndr.demospringevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Component
public class OrderRunner implements ApplicationRunner {

    @Autowired
    OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("START RUNNER ON THREAD : " + Thread.currentThread());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 주문 요총하는척...
        System.out.println("REQUEST ORDER");
        orderService.save();

        stopWatch.stop();
        System.out.println("Runner Task Time : " + stopWatch.getTotalTimeSeconds());

        System.out.println("FINISHED");
    }
}
