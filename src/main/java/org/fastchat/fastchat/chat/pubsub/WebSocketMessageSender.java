package org.fastchat.fastchat.chat.pubsub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastchat.fastchat.chat.dto.ChatMessageDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketMessageSender {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendToClient(String roomId, ChatMessageDTO message) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + roomId, message);
        log.info("WebSocket 전송 완료: /sub/chat/room/{}", roomId);
    }
}
