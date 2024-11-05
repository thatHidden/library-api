package com.example.library.dto.request;

import lombok.Builder;

@Builder
public record CommandBookDto(
        String title,
        String author
) {
}
