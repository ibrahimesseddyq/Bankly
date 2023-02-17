package com.example.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    public Long id;
    public String login;
    public String token;
}
