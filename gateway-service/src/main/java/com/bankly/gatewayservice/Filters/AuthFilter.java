package com.bankly.gatewayservice.Filters;

import com.bankly.gatewayservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{
    public AuthFilter() {
        super(Config.class);
    }
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)->{
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing auth infos");
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            if(parts.length !=2 || !"Bearer".equals(parts[0])){
                throw new RuntimeException("Incorrect Auth structure");
            }
            return webClientBuilder.build().post()
                    .uri("http://localhost:8091/validateToken?token="+parts[1])
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .map(userDto ->{
                        exchange.getRequest().mutate().header("X-auth-user-id",String.valueOf(userDto.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
         };
    }

    public static class Config{

    }

}
