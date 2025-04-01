package org.fastchat.fastchat.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.fastchat.fastchat.chat.service.KafkaProducer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final KafkaProducer kafkaProducer;

    /**
     * 클라이언트에서 /pub/chat/message 로 전송한 메시지를 수신
     * Kafka에 메시지를 발행
     */
    @MessageMapping("/chat/message")
    public void handleMessage(ChatMessageDTO message) {
        log.info("WebSocket 수신 메시지: {}", message);
        kafkaProducer.sendMessage(message); // Kafka로 전달
    }
}
