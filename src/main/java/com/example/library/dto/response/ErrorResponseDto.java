package com.example.library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ErrorResponseDto(
        @JsonProperty(value = "time_stamp", index = 1)
        String timeStamp,

        String path,

        String method,

        String message
) {
}

