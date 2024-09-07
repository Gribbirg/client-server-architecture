package ru.mirea.client_server_architecture.practice.practice4.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/topic/return")
    public String processMessage(String message) {
        return message;
    }
}

