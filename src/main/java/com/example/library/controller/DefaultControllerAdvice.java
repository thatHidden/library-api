package com.example.library.controller;

import com.example.library.dto.response.ErrorResponseDto;
import com.example.library.exception.BadRequestException;
import com.example.library.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(request, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationException(HttpServletRequest request, BadRequestException e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(request, e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBaseException(HttpServletRequest request, Throwable e) {
        String message = "unexpected error";
        log.error(message, e);
        return buildErrorResponse(request, message);
    }

    private ErrorResponseDto buildErrorResponse(HttpServletRequest request, String message) {
        return ErrorResponseDto.builder()
                .path(request.getServletPath())
                .method(request.getMethod())
                .timeStamp(Timestamp.from(Instant.now()).toString())
                .message(message)
                .build();
    }
}

