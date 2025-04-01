package org.fastchat.fastchat.chat.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.fastchat.fastchat.chat.pubsub.RedisPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final RedisCacheService redisCacheService;
    private final RedisPublisher redisPublisher;
    private final Gson gson = new Gson();

    @KafkaListener(topics = "fastchat-messages", groupId = "fastchat-group")
    public void consume(String message) {
        try {
            ChatMessageDTO chatMessage = gson.fromJson(message, ChatMessageDTO.class);
            log.info("Kafka 수신 메시지: {}", chatMessage);

            // Redis에 메시지 캐싱
            redisCacheService.cacheMessage(chatMessage.getRoomId(), chatMessage);
            log.info("Redis 캐싱 완료: roomId={}, message={}", chatMessage.getRoomId(), chatMessage.getMessage());

            // Redis Pub/Sub 발행
            redisPublisher.publish(chatMessage.getRoomId(), chatMessage);
            log.info("Redis Pub/Sub 발행 완료: roomId={}, message={}", chatMessage.getRoomId(), chatMessage.getMessage());

        } catch (Exception e) {
            log.error("Kafka 메시지 처리 중 오류 발생", e);
        }
    }
}
