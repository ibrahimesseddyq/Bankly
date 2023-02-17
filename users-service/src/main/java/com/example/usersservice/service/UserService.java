package com.example.usersservice.service;

import com.example.usersservice.Entity.User;
import com.example.usersservice.Exceptions.AppException;
import com.example.usersservice.VO.UserSubjectJWT;
import com.example.usersservice.dto.CredentialsDto;
import com.example.usersservice.dto.UserDto;
import com.example.usersservice.mapper.UserMapper;
import com.example.usersservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private UserMapper userMapper;
    @Autowired

    private UserRepository userRepository;
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public UserDto signIn(CredentialsDto credentialsDto) throws JsonProcessingException {

        Optional<User> optionalUser = userRepository.findByLogin(credentialsDto.getLogin());

                if(optionalUser.isEmpty()){
                    new AppException("User not found", HttpStatus.NOT_FOUND);
                };
        User user=optionalUser.get();
        if (passwordEncoder.matches(credentialsDto.getPassword(), user.getPassword())) {
            return userMapper.toUserDto(user, createToken(user));
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) throws JsonProcessingException {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isEmpty()) {
            throw new AppException("User not found", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        return userMapper.toUserDto(user, createToken(user));
    }

    private String createToken(User user) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        UserSubjectJWT userSubjectJWT= new UserSubjectJWT();
        userSubjectJWT.setLogin(user.getLogin());
        userSubjectJWT.setRole(user.getRole().toString());
        String subject = objectWriter.writeValueAsString(userSubjectJWT);
        Claims claims = Jwts.claims().setSubject(subject);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
