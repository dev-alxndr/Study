package me.alxndr.demowebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* https://daddyprogrammer.org/post/4731/spring-websocket-chatting-server-redis-pub-sub/
* */

@SpringBootApplication
public class DemoWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebsocketApplication.class, args);
    }

}
