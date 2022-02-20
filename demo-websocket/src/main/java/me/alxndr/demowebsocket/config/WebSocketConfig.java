package me.alxndr.demowebsocket.config;

import lombok.RequiredArgsConstructor;
import me.alxndr.demowebsocket.ChatHandler;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * https://dev-gorany.tistory.com/224?category=901854
 *
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
//@Configuration
@RequiredArgsConstructor
//@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ChatHandler chatHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/example").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/test");
        registry.enableStompBrokerRelay("/topic", "/queue");
    }
}
