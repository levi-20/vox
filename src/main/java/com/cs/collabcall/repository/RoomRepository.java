package com.cs.collabcall.repository;

import com.cs.collabcall.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    Optional<Room> findByName(String name);

    List<Room> findByNameContainingIgnoreCase(String nameString);

    List<Room> findRoomsByCreatedBy(UUID createdBy);
}
