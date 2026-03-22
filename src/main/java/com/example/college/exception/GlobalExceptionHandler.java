package com.example.college.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public Mono<ResponseEntity<String>> handleAccessDenied() {
        return Mono.just(ResponseEntity.status(403).body("Access Denied"));
    }
}