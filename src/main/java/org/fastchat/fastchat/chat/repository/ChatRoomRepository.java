package org.fastchat.fastchat.chat.repository;

import org.fastchat.fastchat.chat.model.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private final Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();

    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom(roomId, name);
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }

    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }
}
