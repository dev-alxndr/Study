package me.alxndr.demowebsocket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Alexander Choi
 * @date : 2021/11/26
 */
@Getter
@Setter
public class ChatMessageDto {

    private String roomId;

    private String writer;

    private String message;

}
