package com.harry.user.service.impl;

import com.harry.security.jwt.JwtTokenUtil;
import com.harry.user.mapper.UserMapper;
import com.harry.user.model.po.User;
import com.harry.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String loginId, String rawPassword) {
        // 1. Find user by username / phone / email
        User user = userMapper.findByUsername(loginId);
        if (user == null) user = userMapper.findByPhone(loginId);
        if (user == null) user = userMapper.findByEmail(loginId);

        if (user == null) {
            log.warn("Login failed, no such user: {}", loginId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // 2. Decode raw password
        // TODO

        // 3. Verify password
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            log.warn("Invalid password attempt for user: {}", loginId);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        log.info("User [{}] login success", user.getUsername());
        return user;
    }
}
