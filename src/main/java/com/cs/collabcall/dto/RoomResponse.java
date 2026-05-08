package com.cs.collabcall.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record RoomResponse(
    UUID id,
    String name,
    String description,
    LocalDateTime createAt,
    UUID createdBy
) {
}
