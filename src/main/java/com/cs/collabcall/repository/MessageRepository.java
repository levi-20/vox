package com.cs.collabcall.repository;

import com.cs.collabcall.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByRoomId(UUID roomId);
}
