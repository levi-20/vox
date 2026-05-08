package com.cs.collabcall.controller;

import com.cs.collabcall.dto.MessageRequest;
import com.cs.collabcall.dto.MessageResponse;
import com.cs.collabcall.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable UUID roomId) {

        return ResponseEntity.status(HttpStatus.OK).body(this.messageService.getMessages(roomId));
    }

    @PostMapping("/{roomId}/messages")
    public ResponseEntity<MessageResponse> saveMessage(@PathVariable UUID roomId, @RequestBody MessageRequest messageRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.save(roomId, messageRequest));
    }
}
