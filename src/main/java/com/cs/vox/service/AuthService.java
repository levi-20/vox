package com.cs.vox.service;

import com.cs.vox.dto.AuthResponse;
import com.cs.vox.dto.UserResponse;
import com.cs.vox.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserResponse register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userService.createUser(user);
    }

    public AuthResponse login(String username, String password) {

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return new AuthResponse(this.jwtService.generateToken(username));
    }
}
