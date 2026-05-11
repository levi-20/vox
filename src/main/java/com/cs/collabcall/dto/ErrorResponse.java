package com.cs.collabcall.dto;

import java.time.ZonedDateTime;

public record ErrorResponse(
    String error,
    String message,
    int status,
    ZonedDateTime timestamp
) {
}
