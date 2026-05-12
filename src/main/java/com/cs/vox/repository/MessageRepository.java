package com.cs.vox.repository;

import com.cs.vox.dto.RoomMessage;
import com.cs.vox.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByRoomId(UUID roomId, Pageable pageable);
}
