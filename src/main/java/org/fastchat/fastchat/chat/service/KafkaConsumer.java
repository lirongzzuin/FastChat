package org.fastchat.fastchat.chat.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final RedisCacheService redisCacheService;
    private final Gson gson = new Gson();

    @KafkaListener(topics = "fastchat-messages", groupId = "fastchat-group")
    public void consume(String message) {
        ChatMessageDTO chatMessage = gson.fromJson(message, ChatMessageDTO.class);
        log.info("Kafka 수신 메시지: {}", chatMessage);
        redisCacheService.cacheMessage(chatMessage.getRoomId(), chatMessage.getMessage());
        log.info("Redis 캐싱 완료: roomId={}, message={}", chatMessage.getRoomId(), chatMessage.getMessage());
    }
}
