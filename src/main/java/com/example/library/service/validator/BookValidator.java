package com.example.library.service.validator;

import com.example.library.exception.BadRequestException;
import com.example.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    public void validate(Book book) {
        checkTitle(book.getTitle());
        checkAuthor(book.getAuthor());
    }

    private void checkTitle(String title) {
        if (title == null || title.isBlank() || title.length() > 50) {
            throw new BadRequestException("bad title");
        }
    }

    private void checkAuthor(String author) {
        if (author == null || author.isBlank() || author.length() > 40) {
            throw new BadRequestException("bad author");
        }
    }
}
