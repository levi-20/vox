package com.cs.collabcall.controller;


import com.cs.collabcall.dto.RoomResponse;
import com.cs.collabcall.entity.Room;

import com.cs.collabcall.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {

        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable UUID id) {

        return roomService.roomById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<RoomResponse> getRoomByName(@PathVariable String name) {

        return roomService.searchRoomByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<RoomResponse>> searchRooms(
        @RequestParam(name = "q", required = false, defaultValue = "")
        @Pattern(regexp = "^[a-zA-Z0-9 _-]*$")
        String searchTerm) {

        return ResponseEntity.ok()
            .body(roomService.searchRooms(searchTerm));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoomResponse>> findRoomsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok()
            .body(roomService.searchRoomsByUser(userId));
    }


    @GetMapping
    public ResponseEntity<List<RoomResponse>> getRooms() {

        return ResponseEntity.status(200)
            .body(roomService.listRooms());
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody Room room) {

        return ResponseEntity.ok().body(roomService.createRoom(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {

        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }
}
