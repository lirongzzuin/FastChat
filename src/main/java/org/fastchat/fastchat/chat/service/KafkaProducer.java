package org.fastchat.fastchat.chat.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson = new Gson(); // Gson으로 DTO -> JSON 문자열 변환

    public void sendMessage(ChatMessageDTO messageDTO) {
        String jsonMessage = gson.toJson(messageDTO);
        kafkaTemplate.send("fastchat-messages", jsonMessage);
    }
}
