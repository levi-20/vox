package com.cs.vox.controller;

import com.cs.vox.dto.MessageRequest;
import com.cs.vox.dto.MessageResponse;
import com.cs.vox.dto.RoomMessageResponse;
import com.cs.vox.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<RoomMessageResponse> getMessages(@Valid @PathVariable UUID roomId, @RequestParam(defaultValue = "0") int page) {

        if (page <= 0) {
            throw new IllegalArgumentException("Page number must be greater than 0(zero)");
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessages(roomId, page - 1));
    }

    @PostMapping("/{roomId}/messages")
    public ResponseEntity<MessageResponse> saveMessage(@PathVariable UUID roomId, @RequestBody MessageRequest messageRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.save(roomId, messageRequest));
    }
}
