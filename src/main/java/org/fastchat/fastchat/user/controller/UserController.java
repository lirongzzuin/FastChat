package org.fastchat.fastchat.user.controller;

import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.user.dto.UserRequestDTO;
import org.fastchat.fastchat.user.dto.UserResponseDTO;
import org.fastchat.fastchat.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO requestDTO) {
        userService.register(requestDTO.getUsername(), requestDTO.getPassword());
        return ResponseEntity.ok("✅ 회원가입 완료");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.login(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
