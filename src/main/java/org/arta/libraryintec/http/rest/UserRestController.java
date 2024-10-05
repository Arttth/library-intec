package org.arta.libraryintec.http.rest;

import lombok.RequiredArgsConstructor;
import org.arta.libraryintec.dto.UserCreateDto;
import org.arta.libraryintec.dto.UserReadDto;
import org.arta.libraryintec.mapper.UserReadMapper;
import org.arta.libraryintec.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    private final String USERS_URL = "/users";
    private final String BORROW_URL = "/borrow";
    private final String RETURN_URL = "/return";
    @GetMapping(USERS_URL)
    public List<UserReadDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = USERS_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto addUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.addUser(userCreateDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(BORROW_URL + "/{userId}/{bookId}")
    public void borrowBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        userService.borrowBook(userId, bookId);
    }

    @PostMapping(RETURN_URL + "/{userId}/{bookId}")
    public void returnBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        userService.returnBook(userId, bookId);
    }



}
