package me.alxndr.demowebsocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Alexander Choi
 * @date : 2021/11/24
 */
@Controller
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/chat")
    public String startChat() {

        return "chat";
    }

}
