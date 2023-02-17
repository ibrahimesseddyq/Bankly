package com.example.usersservice.mapper;

import com.example.usersservice.Entity.User;
import com.example.usersservice.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring")
    public interface UserMapper {

        @Mapping(source = "user.id", target = "id")
        @Mapping(source = "user.login", target = "login")
        @Mapping(source = "token", target = "token")
        UserDto toUserDto(User user, String token);
    }


