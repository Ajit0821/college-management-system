package com.example.college.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {

        var response = exchange.getResponse();

        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add("Content-Type", "application/json");

        String body = "{\"status\":403,\"error\":\"FORBIDDEN\"}";

        var buffer = response.bufferFactory().wrap(body.getBytes());

        return response.writeWith(Mono.just(buffer));
    }
}