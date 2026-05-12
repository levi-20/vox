package com.cs.vox.service;

import com.cs.vox.dto.*;
import com.cs.vox.entity.Message;
import com.cs.vox.entity.Room;
import com.cs.vox.entity.User;
import com.cs.vox.exceptions.RoomNotFoundException;
import com.cs.vox.repository.MessageRepository;
import com.cs.vox.repository.RoomRepository;
import com.cs.vox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomService roomService;
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
        message.setContent(messageRequest.message());
        message.setSender(user);
        message.setRoom(room);
        return toMessageResponse(messageRepository.save(message));
    }

    public RoomMessageResponse getMessages(UUID roomId, int page) {

        // if room doesn't exit throw RoomNotFoundException
        roomService.roomById(roomId)
            .orElseThrow(() -> new RoomNotFoundException("Room id not found"));


        Pageable pageable = PageRequest.of(page, PAGESIZE, Sort.Direction.DESC, "sentAt");
        log.debug("Getting messages for room {} {} {}", roomId, pageable, page);

        return new RoomMessageResponse(
            roomId,
            messageRepository.findByRoomId(roomId, pageable)
                .stream()
                .map(this::toRoomMessage)
                .toList()
        );
    }

    private MessageResponse toMessageResponse(Message message) {
        return new MessageResponse(
            message.getId(),
            message.getContent(),
            message.getRoom().getId(),
            message.getSender().getId(),
            message.getSentAt()
        );
    }

    private RoomMessage toRoomMessage(Message message) {
        return new RoomMessage(
            message.getId(),
            message.getContent(),
            message.getSender().getId(),
            message.getSentAt()
        );
    }
}
