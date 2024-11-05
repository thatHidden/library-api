package com.example.library.dto.request;

import com.example.library.util.Operation;

import java.util.UUID;

public record MessageBookDto(
    Operation operation,
    UUID id,
    String title,
    String author
) {
}
