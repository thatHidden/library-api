package com.example.library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ResponseBookDto(
        String title,

        String author,

        @JsonProperty("publish_date")
        LocalDate publishDate
) {
}
