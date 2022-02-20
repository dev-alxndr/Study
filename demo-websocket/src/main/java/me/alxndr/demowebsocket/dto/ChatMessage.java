package me.alxndr.demowebsocket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Getter
@Setter
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
