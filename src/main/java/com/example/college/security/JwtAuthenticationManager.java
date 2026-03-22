package com.example.college.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        String token = authentication.getCredentials().toString();

        try {
            String username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);
            System.out.println("USERNAME FROM TOKEN: " + username);
            System.out.println("ROLE FROM TOKEN: " + role);

            if (username == null || role == null) {
                return Mono.error(new RuntimeException("Invalid token"));
            }

            return Mono.just(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    )
            );

        } catch (Exception e) {
            return Mono.error(new RuntimeException("Invalid token"));
        }
    }
}