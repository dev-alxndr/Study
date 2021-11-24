package me.alxndr.demowebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sockets = new LinkedList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        for(WebSocketSession sess: sockets) {
            sess.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sockets.add(session);
        log.info(session.getId() + " Enter");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sockets.remove(session);
        log.info(session + " Exit");
    }
}
