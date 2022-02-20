package me.alxndr.demowebsocket.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author : Alexander Choi
 * @date : 2021/11/26
 */
@Getter
@Setter
public class ChatRoomDto {

    private String roomId;

    private String name;

    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomDto create(String name) {
        ChatRoomDto room = new ChatRoomDto();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;

        return room;
    }

}
