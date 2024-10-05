package org.arta.libraryintec.mapper;

import org.arta.libraryintec.database.entity.User;
import org.arta.libraryintec.dto.UserCreateDto;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper implements Mapper<UserCreateDto, User>{
    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .name(object.name())
                .build();
    }
}
