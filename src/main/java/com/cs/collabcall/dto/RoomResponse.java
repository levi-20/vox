package com.cs.collabcall.dto;

import java.time.ZonedDateTime;
import java.util.UUID;


public record RoomResponse(
    UUID id,
    String name,
    String description,
    ZonedDateTime createAt,
    UUID createdBy
) {
}
