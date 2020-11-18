package io.alxndr.demospringevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Component
public class EventRunner implements ApplicationRunner {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("START RUNNER ON THREAD : " + Thread.currentThread());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        applicationEventPublisher.publishEvent(new MyEvent(this, new Event()));
        Thread.sleep(3000);

        stopWatch.stop();
        System.out.println("Runner Task Time : " + stopWatch.getTotalTimeSeconds());

        System.out.println("FINISHED");
    }
}
