package org.fastchat.fastchat.chat.pubsub;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final StringRedisTemplate redisTemplate;
    private final Gson gson = new Gson();

    public void publish(String roomId, ChatMessageDTO message) {
        String json = gson.toJson(message);
        redisTemplate.convertAndSend("chatroom-" + roomId, json);
    }
}
