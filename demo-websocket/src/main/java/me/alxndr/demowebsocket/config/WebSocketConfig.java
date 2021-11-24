package me.alxndr.demowebsocket.config;

import lombok.RequiredArgsConstructor;
import me.alxndr.demowebsocket.ChatHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * https://dev-gorany.tistory.com/224?category=901854
 *
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(chatHandler, "ws/chat")
                .setAllowedOriginPatterns("*");

    }
}
