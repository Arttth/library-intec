package org.arta.libraryintec.service;

import lombok.RequiredArgsConstructor;
import org.arta.libraryintec.database.repository.BookRepository;
import org.arta.libraryintec.dto.BookCreateDto;
import org.arta.libraryintec.dto.BookReadDto;
import org.arta.libraryintec.mapper.BookCreateMapper;
import org.arta.libraryintec.mapper.BookReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final BookCreateMapper bookCreateMapper;

    public List<BookReadDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public Optional<BookReadDto> getBookById(Integer id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map);
    }

    public BookReadDto addBook(BookCreateDto bookCreateDto) {
        return Optional.of(bookCreateDto)
                .map(bookCreateMapper::map)
                .map(bookRepository::save)
                .map(bookReadMapper::map)
                .orElseThrow();
    }

    public List<BookReadDto> findBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public List<BookReadDto> findBookByAuthor(String author) {
        return bookRepository.findByAuthorLike(author)
                .stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public boolean deleteBook(Integer id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return true;
                }).orElse(false);
    }
}
