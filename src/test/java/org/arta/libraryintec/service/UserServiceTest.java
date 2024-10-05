package org.arta.libraryintec.service;

import org.arta.libraryintec.database.entity.Book;
import org.arta.libraryintec.database.entity.User;
import org.arta.libraryintec.database.repository.BookRepository;
import org.arta.libraryintec.database.repository.UserRepository;
import org.arta.libraryintec.dto.UserCreateDto;
import org.arta.libraryintec.dto.UserReadDto;
import org.arta.libraryintec.mapper.UserCreateMapper;
import org.arta.libraryintec.mapper.UserReadMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserReadMapper userReadMapper;
    @Mock
    private UserCreateMapper userCreateMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers() {
        User user1 = new User(1, "Artem", null);
        User user2 = new User(2, "Leonid", null);

        UserReadDto userReadDto1 = new UserReadDto(1, "Artem");
        UserReadDto userReadDto2 = new UserReadDto(1, "Leonid");
        List<UserReadDto> expected = List.of(userReadDto1, userReadDto2);

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        when(userReadMapper.map(user1)).thenReturn(userReadDto1);
        when(userReadMapper.map(user2)).thenReturn(userReadDto2);

        List<UserReadDto> actual = userService.getAllUsers();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    void addUser() {
        User user1 = new User(1, "Artem", null);
        UserCreateDto userCreateDto = new UserCreateDto("Artem");

        UserReadDto expected = new UserReadDto(1, "Artem");

        when(userCreateMapper.map(userCreateDto)).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);
        when(userReadMapper.map(user1)).thenReturn(new UserReadDto(1, "Artem"));

        Optional<UserReadDto> actual = userService.addUser(userCreateDto);

        assertTrue(actual.isPresent());
        actual.ifPresent(res -> assertEquals(expected.id(), res.id()));
    }

    @Test
    void borrowBook() {
        User user1 = User.builder()
                .id(1)
                .name("Artem")
                .build();
        Book book1 = new Book(1, "Рога и копыта", "author1", true);

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));

        assertTrue(userService.borrowBook(1, 1));
        assertEquals(user1.getBorrowedBooks().getFirst(), book1);
        assertFalse(book1.isAvailable());
    }

    @Test
    void returnBook() {
        Book book1 = new Book(1, "Рога и копыта", "author1", false);
        User user1 = User.builder()
                .id(1)
                .name("Artem")
                .borrowedBooks(new ArrayList<>(List.of(book1)))
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));

        assertTrue(userService.returnBook(1, 1));
        assertFalse(user1.getBorrowedBooks().contains(book1));
        assertTrue(book1.isAvailable());
    }
}