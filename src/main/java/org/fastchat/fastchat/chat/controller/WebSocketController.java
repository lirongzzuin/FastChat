package org.fastchat.fastchat.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.fastchat.fastchat.chat.service.KafkaProducer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("WebSocket 메시지 수신: {}", chatMessage);
        kafkaProducer.sendMessage(chatMessage);
    }
}
