package com.cs.vox.dto;

import java.util.List;
import java.util.UUID;

public record RoomMessageResponse(
    UUID roomId,
    List<RoomMessage> messages
) {
}
