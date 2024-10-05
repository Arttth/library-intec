package org.arta.libraryintec.http.rest;

import lombok.RequiredArgsConstructor;
import org.arta.libraryintec.dto.BookCreateDto;
import org.arta.libraryintec.dto.BookReadDto;
import org.arta.libraryintec.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookRestController {
    private final BookService bookService;
    @GetMapping
    List<BookReadDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    BookReadDto getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    BookReadDto addBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.addBook(bookCreateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        if (!bookService.deleteBook(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    List<BookReadDto> findBookByTitle(String title) {
        return bookService.findBookByTitle(title);
    }

    List<BookReadDto> findBookByAuthor(String author) {
        return bookService.findBookByAuthor(author);
    }

}
