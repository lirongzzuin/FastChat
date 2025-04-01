package org.fastchat.fastchat.chat.service;

import jakarta.annotation.PostConstruct;
import org.fastchat.fastchat.chat.model.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatRoomService {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    public void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    // 채팅방 전체 목록 반환
    public List<ChatRoom> findAllRooms() {
        List<ChatRoom> rooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(rooms); // 최근 생성순 정렬
        return rooms;
    }

    // roomId 기준으로 채팅방 반환
    public ChatRoom findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    // 채팅방 생성
    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom room = new ChatRoom(roomId, name);
        chatRoomMap.put(roomId, room);
        return room;
    }
}
