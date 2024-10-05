package org.arta.libraryintec.mapper;

import org.arta.libraryintec.database.entity.Book;
import org.arta.libraryintec.dto.BookReadDto;
import org.springframework.stereotype.Component;

@Component
public class BookReadMapper implements Mapper<Book, BookReadDto>{
    @Override
    public BookReadDto map(Book object) {
        return new BookReadDto(
                object.getId(),
                object.getTitle(),
                object.getAuthor(),
                object.isAvailable()
        );
    }
}
