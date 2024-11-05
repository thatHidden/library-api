package com.example.library.service;

import com.example.library.exception.EntityNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    public Book findOne(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book create(Book book) {
        bookValidator.validate(book);
        book.setPublishDate(LocalDate.now());
        return bookRepository.save(book);
    }

    public Book update(Book book, UUID id) {
        bookValidator.validate(book);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        return bookRepository.save(existingBook);
    }

    public void delete(UUID id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        bookRepository.delete(existingBook);
    }

}
