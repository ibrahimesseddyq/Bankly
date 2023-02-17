package com.bankly.gatewayservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;
@EnableWebFluxSecurity
@Configuration
public class WebFluxConfig {

//    @Bean
//    public WebFilter csrfFilter() {
//        System.out.println("aaaaa");
//        return (exchange, chain) -> chain.filter(exchange);
//    }
    @Bean
    SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http) {
        http
                .csrf().disable();

        return http.build();
    }
}
