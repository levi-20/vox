package com.cs.vox.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record RoomMessage(
    UUID id,
    String message,
    UUID sender,
    ZonedDateTime sentAt
) {
}
