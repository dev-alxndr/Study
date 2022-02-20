package me.alxndr.demowebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

//
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void redisServer() {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        if (redisServer != null) {
//            redisServer.stop();
//        }
//    }

}
