package com.harry.user.controller;

import com.harry.security.jwt.JwtTokenUtil;
import com.harry.user.model.LoginRequest;
import com.harry.user.model.LoginResponse;
import com.harry.user.model.po.User;
import com.harry.user.model.vo.UserVO;
import com.harry.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        User user = authService.login(request.getLoginId(), request.getPassword());

        // Generate JWT after successful login
        String token = jwtTokenUtil.generateToken(user.getId(), user.getUsername());

        LoginResponse response = LoginResponse.builder()
                .token(token)
                .user(UserVO.builder()
                        .userNo(user.getUserNo())
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }
}
