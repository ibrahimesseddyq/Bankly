package com.example.usersservice.Filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AFilter extends Filter {

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        System.out.println("request received");
    }

    @Override
    public String description() {
        return null;
    }
}
