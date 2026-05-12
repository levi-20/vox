package com.cs.vox.controller;

import com.cs.vox.dto.AuthResponse;
import com.cs.vox.dto.LoginRequest;
import com.cs.vox.dto.RegisterRequest;
import com.cs.vox.dto.UserResponse;
import com.cs.vox.entity.User;
import com.cs.vox.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        log.info("Registering User: {}", registerRequest);
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(registerRequest.password());
        user.setUsername(registerRequest.username());
        user.setName(registerRequest.name());
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.login(loginRequest.username(), loginRequest.password()));
    }
}
