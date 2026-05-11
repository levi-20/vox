package com.cs.collabcall.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RoomCreateRequest(
    @Size(max = 100, message = "Room name is must be under 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9][a-zA-Z0-9 _,.-]*$",
        message = "Room name must start with a letter or digit and can only contain letters, digits, spaces, underscores, commas, dots, and hyphens"
    )
    String name,

    @Size(max = 500, message = "Description too long")
    String description
) {
}
