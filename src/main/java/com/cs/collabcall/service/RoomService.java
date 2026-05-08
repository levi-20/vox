package com.cs.collabcall.service;

import com.cs.collabcall.dto.RoomResponse;
import com.cs.collabcall.entity.Room;
import com.cs.collabcall.entity.User;
import com.cs.collabcall.repository.RoomRepository;
import com.cs.collabcall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;
    private final UserRepository userRepository;

    public Optional<RoomResponse> searchRoomByName(String roomName) {

        log.info("Search by room name: {}", roomName);
        return repository.findByName(roomName)
            .map(this::roomResponse);
    }

    public List<RoomResponse> listRooms() {

        log.info("Querying all rooms");
        return repository.findAll()
            .stream()
            .map(this::roomResponse)
            .toList();
    }

    public RoomResponse createRoom(Room room) {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        room.setCreatedBy(user.getId());
        log.info("Creating room with name: {}", room.getName());
        return roomResponse(repository.save(room));
    }

    public void deleteRoomById(UUID id) {

        log.info("Deleting room: {}", id);
        repository.deleteById(id);
    }

    public Optional<RoomResponse> roomById(UUID id) {

        return repository.findById(id)
            .map(this::roomResponse);
    }

    public List<RoomResponse> searchRooms(String searchTerm) {

        return repository.findByNameContainingIgnoreCase(searchTerm)
            .stream()
            .map(this::roomResponse)
            .toList();
    }

    public RoomResponse roomResponse(Room room) {
        return new RoomResponse(
            room.getId(),
            room.getName(),
            room.getDescription(),
            room.getCreatedAt(),
            room.getCreatedBy()
        );
    }

    public List<RoomResponse> searchRoomsByUser(UUID userId) {

        return repository.findRoomsByCreatedBy(userId)
            .stream()
            .map(this::roomResponse)
            .toList();
    }
}
