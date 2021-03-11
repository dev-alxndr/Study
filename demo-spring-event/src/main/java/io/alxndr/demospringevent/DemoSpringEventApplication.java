package io.alxndr.demospringevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @see https://ivvve.github.io/2019/06/02/java/Spring/event-listener-2/
 */
@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class DemoSpringEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringEventApplication.class, args);
    }

}
