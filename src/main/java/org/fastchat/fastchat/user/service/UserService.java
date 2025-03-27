package org.fastchat.fastchat.user.service;

import lombok.RequiredArgsConstructor;
import org.fastchat.fastchat.common.jwt.JwtUtil;
import org.fastchat.fastchat.user.dto.UserRequestDTO;
import org.fastchat.fastchat.user.dto.UserResponseDTO;
import org.fastchat.fastchat.user.model.User;
import org.fastchat.fastchat.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public void register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .username(username)
                .password(encodedPassword)
                .build();
        userRepository.save(user);
    }

    // 로그인
    public UserResponseDTO login(UserRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtUtil.createToken(user.getUsername());
        return new UserResponseDTO(user.getUsername(), token);
    }
}
