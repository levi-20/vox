package com.cs.vox.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UserResponse(

    UUID id,
    String username,
    String email,
    String name,
    ZonedDateTime createdAt,
    ZonedDateTime updatedAt
) {
}
