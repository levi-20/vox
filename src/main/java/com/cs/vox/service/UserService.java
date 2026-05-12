package com.cs.vox.service;


import com.cs.vox.dto.UserResponse;
import com.cs.vox.entity.User;
import com.cs.vox.exceptions.UserNotFoundException;
import com.cs.vox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // GET    /api/users/{id} → user by UUID
    public Optional<UserResponse> getUserById(UUID uuid) throws UserNotFoundException {

        return Optional.of(userRepository.findById(uuid)
            .map(this::toUserResponse)
            .orElseThrow(UserNotFoundException::new));
    }

    // GET    /api/users/username/{username} → exact username lookup
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(this::toUserResponse);
    }

    // GET    /api/users → all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(this::toUserResponse)
            .toList();
    }

    // GET    /api/users/search?name=        → search by name
    public List<UserResponse> searchWithNames(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
            .stream()
            .map(this::toUserResponse)
            .toList();
    }

    // GET    /api/users/search?user=        → search by username
    public List<UserResponse> searchWithUsernames(String username) {

        return userRepository.findByUsernameContainingIgnoreCase(username)
            .stream()
            .map(this::toUserResponse)
            .toList();
    }

    // POST   /api/users                     → create user
    public UserResponse createUser(User user) {

        return toUserResponse(userRepository.save(user));
    }

    // PUT    /api/users/{id}                → update user (username, email)
    public UserResponse updateUser(UUID id, String email, String name) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setEmail(email);
        user.setName(name);
        return toUserResponse(userRepository.save(user));
    }

    // DELETE /api/users/{id}                → delete user
    public void deleteUser(UUID userId) {

        userRepository.deleteById(userId);
    }

    private UserResponse toUserResponse(User user) {

        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}


