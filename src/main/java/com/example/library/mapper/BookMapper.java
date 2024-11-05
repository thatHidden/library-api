package com.example.library.mapper;

import com.example.library.dto.request.CommandBookDto;
import com.example.library.dto.request.MessageBookDto;
import com.example.library.dto.response.ResponseBookDto;
import com.example.library.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    ResponseBookDto toDto(Book model);

    Book toModel(CommandBookDto dto);

    Book messageToModel(MessageBookDto dto);
}
