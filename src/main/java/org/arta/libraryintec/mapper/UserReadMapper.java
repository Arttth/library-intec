package org.arta.libraryintec.mapper;

import org.arta.libraryintec.database.entity.User;
import org.arta.libraryintec.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
          object.getId(),
          object.getName()
        );
    }
}
