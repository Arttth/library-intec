package org.arta.libraryintec.service;

import lombok.RequiredArgsConstructor;
import org.arta.libraryintec.database.entity.Book;
import org.arta.libraryintec.database.entity.User;
import org.arta.libraryintec.database.repository.BookRepository;
import org.arta.libraryintec.database.repository.UserRepository;
import org.arta.libraryintec.dto.UserCreateDto;
import org.arta.libraryintec.dto.UserReadDto;
import org.arta.libraryintec.mapper.UserCreateMapper;
import org.arta.libraryintec.mapper.UserReadMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    public List<UserReadDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userReadMapper::map)
                .toList();
    }

    // TODO: return value
    public Optional<UserReadDto> addUser(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map);
    }

    public boolean borrowBook(Integer userId, Integer bookId) {
        return userRepository.findById(userId)
                .map(user -> {
                    return bookRepository.findById(bookId)
                            .map(book -> {
                                if (book.isAvailable()) {
                                    user.addBook(book);
                                    book.setAvailable(false);
                                    return true;
                                }
                                return false;
                            }).orElse(false);
                }).orElse(false);
    }

    public boolean returnBook(Integer userId, Integer bookId) {
        return bookRepository.findById(bookId).
                map(book -> {
                    return userRepository.findById(userId)
                            .map(user -> {
                                user.removeBook(book);
                                book.setAvailable(true);
                                return true;
                            }).orElse(false);
                }).orElse(false);
    }


}
