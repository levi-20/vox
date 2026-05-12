package com.cs.vox.controller;

import com.cs.vox.dto.UpdateUserRequest;
import com.cs.vox.dto.UserResponse;
import com.cs.vox.entity.User;
import com.cs.vox.exceptions.UserNotFoundException;
import com.cs.vox.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<ResponseEntity<UserResponse>> getUserById(@PathVariable UUID id) throws UserNotFoundException {

        return userService.getUserById(id)
            .map(ResponseEntity::ok);
    }

    @GetMapping("/username/{username}")
    public Optional<ResponseEntity<UserResponse>> getUserByUsername(@PathVariable String username) {

        return userService.getUserByUsername(username)
            .map(ResponseEntity::ok);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.status(200)
            .body(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String username) {

        if (username == null && name == null) {
            throw new IllegalArgumentException("At least one search param required: name or username");
        }

        if (username != null) {
            return ResponseEntity.status(200)
                .body(userService.searchWithUsernames(username));
        }
        return ResponseEntity.status(200)
            .body(userService.searchWithNames(name));

    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody User user) {

        UserResponse userResponse = userService.createUser(user);
        return ResponseEntity.status(201).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) throws UserNotFoundException {

        return ResponseEntity.status(200)
            .body(userService.updateUser(
                id,
                updateUserRequest.email(),
                updateUserRequest.name()
            ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
