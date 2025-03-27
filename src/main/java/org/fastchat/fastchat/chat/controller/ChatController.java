package org.fastchat.fastchat.chat.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.fastchat.fastchat.chat.service.KafkaProducer;
import org.fastchat.fastchat.chat.service.RedisCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@SecurityRequirement(name = "jwtAuth")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducer kafkaProducer;
    private final RedisCacheService redisCacheService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(
            @RequestParam String roomId,
            @RequestBody String message,
            Principal principal
    ) {
        String username = principal.getName();
        ChatMessageDTO dto = new ChatMessageDTO(username, message);
        kafkaProducer.sendMessage(dto); // ✅ ChatMessageDTO 전달
        redisCacheService.cacheMessage(roomId, dto);
        return ResponseEntity.ok("✅ 메시지 전송 및 캐싱 완료");
    }

    @GetMapping("/history/{roomId}")
    public ResponseEntity<List<String>> getChatHistory(@PathVariable String roomId) {
        List<String> messages = redisCacheService.getRecentMessages(roomId, 10);
        return ResponseEntity.ok(messages);
    }
}