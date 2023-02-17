package com.example.usersservice.Controller;

import com.example.usersservice.dto.CredentialsDto;
import com.example.usersservice.dto.UserDto;
import com.example.usersservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@RequestBody CredentialsDto credentialsDto) throws JsonProcessingException {
        System.out.println("login with"+credentialsDto.getLogin());
        log.info("Trying to login {}", credentialsDto.getLogin());
        return ResponseEntity.ok(userService.signIn(credentialsDto));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserDto> signIn(@RequestParam String token) throws JsonProcessingException {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
