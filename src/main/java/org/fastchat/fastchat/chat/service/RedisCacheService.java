package org.fastchat.fastchat.chat.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisCacheService {

    private final StringRedisTemplate redisTemplate;
    private final Gson gson = new Gson();

    public void cacheMessage(String roomId, ChatMessageDTO messageDTO) {
        String jsonMessage = gson.toJson(messageDTO);
        redisTemplate.opsForList().rightPush("chat:" + roomId, jsonMessage);
    }

    public List<String> getRecentMessages(String roomId, int limit) {
        String key = "chat:" + roomId;
        long size = redisTemplate.opsForList().size(key);
        long start = Math.max(size - limit, 0);
        return redisTemplate.opsForList().range(key, start, size);
    }
}
