package com.example.college.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(JwtAuthenticationManager authenticationManager) {
        super(authenticationManager);
        setServerAuthenticationConverter(this::convert);
    }

    private Mono<Authentication> convert(ServerWebExchange exchange) {

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Mono.empty();
        }

        String token = authHeader.substring(7);

        return Mono.just(
                new UsernamePasswordAuthenticationToken(token, token)
        );
    }
}