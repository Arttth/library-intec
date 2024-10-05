package org.arta.libraryintec.service;

import org.arta.libraryintec.LibraryIntecApplication;
import org.arta.libraryintec.database.entity.Book;
import org.arta.libraryintec.database.repository.BookRepository;
import org.arta.libraryintec.dto.BookCreateDto;
import org.arta.libraryintec.dto.BookReadDto;
import org.arta.libraryintec.mapper.BookCreateMapper;
import org.arta.libraryintec.mapper.BookReadMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookReadMapper bookReadMapper;
    @Mock
    private BookCreateMapper bookCreateMapper;
    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "1", "author1", true);
        Book book2 = new Book(2, "2", "author2", true);

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        when(bookReadMapper.map(book1)).thenReturn(new BookReadDto(1, "1", "author1", true));
        when(bookReadMapper.map(book2)).thenReturn(new BookReadDto(2, "2", "author2", true));

        List<BookReadDto> actual = bookService.getAllBooks();

        assertEquals(2, actual.size());
        assertEquals(1, actual.get(0).id());
        assertEquals(2, actual.get(1).id());
    }

    @Test
    void getBookById() {
        Book book1 = new Book(1, "1", "author1", true);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));
        when(bookReadMapper.map(book1)).thenReturn(new BookReadDto(1, "1", "author1", true));

        Optional<BookReadDto> actual = bookService.getBookById(1);
        actual.ifPresent(res -> assertEquals(1, res.id()));
    }

    @Test
    void addBook() {
        Book book1 = new Book(1, "1", "author1", true);
        BookCreateDto bookCreateDto = new BookCreateDto("Рога и копыта", "Васильев");

        BookReadDto expected = new BookReadDto(1, "Рога и копыта", "Васильев", true);

        when(bookCreateMapper.map(bookCreateDto)).thenReturn(book1);
        when(bookRepository.save(book1)).thenReturn(book1);
        when(bookReadMapper.map(book1)).thenReturn(expected);

        BookReadDto actual = bookService.addBook(bookCreateDto);

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.title(), actual.title());
        assertEquals(expected.author(), actual.author());
        assertEquals(expected.available(), actual.available());

        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void findBookByTitle() {
        Book book1 = new Book(1, "Рога и копыта", "author1", true);
        Book book2 = new Book(2, "Рога и рога", "author1", true);

        final String FIND_TITLE = "Рога";
        BookReadDto bookReadDto1 = new BookReadDto(1, "Рога и копыта", "author1", true);
        BookReadDto bookReadDto2 = new BookReadDto(2, "Рога и рога", "author1", true);
        List<BookReadDto> expected = List.of(bookReadDto1, bookReadDto2);

        when(bookRepository.findByTitleLike(FIND_TITLE)).thenReturn(List.of(book1, book2));
        when(bookReadMapper.map(book1)).thenReturn(bookReadDto1);
        when(bookReadMapper.map(book1)).thenReturn(bookReadDto2);

        List<BookReadDto> actual = bookService.findBookByTitle(FIND_TITLE);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    void findBookByAuthor() {
        Book book1 = new Book(1, "Рога и копыта", "author1", true);
        Book book2 = new Book(2, "Рога и рога", "author1", true);

        final String FIND_AUTHOR = "author1";
        BookReadDto bookReadDto1 = new BookReadDto(1, "Рога и копыта", "author1", true);
        BookReadDto bookReadDto2 = new BookReadDto(2, "Рога и рога", "author1", true);
        List<BookReadDto> expected = List.of(bookReadDto1, bookReadDto2);

        when(bookRepository.findByTitleLike(FIND_AUTHOR)).thenReturn(List.of(book1, book2));
        when(bookReadMapper.map(book1)).thenReturn(bookReadDto1);
        when(bookReadMapper.map(book1)).thenReturn(bookReadDto2);

        List<BookReadDto> actual = bookService.findBookByTitle(FIND_AUTHOR);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    void deleteBook() {
        Book book1 = new Book(1, "Рога и копыта", "author1", true);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));

        assertEquals(true, bookService.deleteBook(1));
    }
}