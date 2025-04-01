package org.fastchat.fastchat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private String username;
    private String message;
    private String roomId;
}
