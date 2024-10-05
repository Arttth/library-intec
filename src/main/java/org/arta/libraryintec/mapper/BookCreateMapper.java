package org.arta.libraryintec.mapper;

import org.arta.libraryintec.database.entity.Book;
import org.arta.libraryintec.dto.BookCreateDto;
import org.springframework.stereotype.Component;

@Component
public class BookCreateMapper implements Mapper<BookCreateDto, Book>{
    @Override
    public Book map(BookCreateDto object) {
        return Book.builder()
                .title(object.title())
                .author(object.author())
                .build();
    }
}
