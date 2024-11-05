package com.example.library.messaging;

import com.example.library.dto.request.MessageBookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitConsumer {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @RabbitListener(queues = "${messaging.consumer.book-operations.queue}")
    public void consumeBookMessage(@Payload MessageBookDto messageBookDto) {
        try {
            switch (messageBookDto.operation()) {
                case CREATE -> bookService.create(bookMapper.messageToModel(messageBookDto));
                case UPDATE -> bookService.update(bookMapper.messageToModel(messageBookDto), messageBookDto.id());
                case DELETE -> bookService.delete(messageBookDto.id());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
