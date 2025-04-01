package org.fastchat.fastchat.chat.controller;

import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.chat.model.ChatRoom;
import org.fastchat.fastchat.chat.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 모든 채팅방 조회
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        return ResponseEntity.ok(chatRoomService.findAllRooms());
    }

    // 특정 채팅방 조회
    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoom> getRoom(@PathVariable String roomId) {
        return ResponseEntity.ok(chatRoomService.findRoomById(roomId));
    }

    // 새로운 채팅방 생성
    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name) {
        return ResponseEntity.ok(chatRoomService.createRoom(name));
    }
}
