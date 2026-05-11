package com.cs.collabcall.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record MessageResponse(
    UUID id,
    String message,
    UUID room,
    UUID sender,
    ZonedDateTime sentAt
) {
}
