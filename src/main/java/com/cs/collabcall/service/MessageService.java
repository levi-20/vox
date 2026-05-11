package com.cs.collabcall.service;

import com.cs.collabcall.dto.MessageRequest;
import com.cs.collabcall.dto.MessageResponse;
import com.cs.collabcall.entity.Message;
import com.cs.collabcall.entity.Room;
import com.cs.collabcall.entity.User;
import com.cs.collabcall.exceptions.RoomNotFoundException;
import com.cs.collabcall.repository.MessageRepository;
import com.cs.collabcall.repository.RoomRepository;
import com.cs.collabcall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private static final int PAGESIZE = 20;

    public MessageResponse save(UUID roomId, MessageRequest messageRequest) {

        // Get User
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        // Get room
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RoomNotFoundException("room not found"));

        Message message = new Message();
        message.setMessage(messageRequest.message());
        message.setSender(user);
        message.setRoom(room);
        return toMessageResponse(messageRepository.save(message));
    }

    public List<MessageResponse> getMessages(UUID roomId, int page) {

        Pageable pageable = PageRequest.of(page, PAGESIZE, Sort.Direction.DESC, "sentAt");
        return messageRepository.findByRoomId(roomId, pageable)
            .stream()
            .map(this::toMessageResponse)
            .toList();
    }

    private MessageResponse toMessageResponse(Message message) {
        return new MessageResponse(
            message.getId(),
            message.getMessage(),
            message.getRoom().getId(),
            message.getSender().getId(),
            message.getSentAt()
        );
    }
}
