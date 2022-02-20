package me.alxndr.demowebsocket;

import lombok.RequiredArgsConstructor;
import me.alxndr.demowebsocket.dto.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}
