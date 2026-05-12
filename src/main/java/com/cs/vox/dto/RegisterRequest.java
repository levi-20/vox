package com.cs.vox.dto;

public record RegisterRequest(
    String username,
    String email,
    String name,
    String password
) {
}
