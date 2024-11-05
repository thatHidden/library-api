package com.example.library.controller;

import com.example.library.dto.request.CommandBookDto;
import com.example.library.dto.response.ResponseBookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    private ResponseBookDto getBook(@PathVariable("id") UUID id) {
        return bookMapper.toDto(bookService.findOne(id));
    }

    @GetMapping()
    private Page<ResponseBookDto> getAllBooks(@PageableDefault Pageable pageable) {
        return bookService.findAll(pageable).map(bookMapper::toDto);
    }

    @PostMapping()
    public ResponseBookDto createBook(@RequestBody CommandBookDto dto) {
        return bookMapper.toDto(bookService.create(bookMapper.toModel(dto)));
    }

    @PostMapping("/{id}")
    public ResponseBookDto updateBook(@RequestBody CommandBookDto dto,
                                      @PathVariable UUID id) {
        return bookMapper.toDto(bookService.update(bookMapper.toModel(dto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.delete(id);
    }
}
