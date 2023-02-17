package com.example.usersservice.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSubjectJWT {
    String login;
    String role;
}
