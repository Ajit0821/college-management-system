package com.example.college.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        AuthenticationWebFilter authFilter =
                new AuthenticationWebFilter(jwtAuthenticationManager);

        authFilter.setServerAuthenticationConverter(this::convert);

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

                .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)

                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint((exchange, ex) -> {

                            var response = exchange.getResponse();

                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            response.getHeaders().add("Content-Type", "application/json");

                            String body = "{\"status\":401,\"error\":\"UNAUTHORIZED\"}";
                            var buffer = response.bufferFactory().wrap(body.getBytes());

                            return response.writeWith(Mono.just(buffer));
                        })
                        .accessDeniedHandler((exchange, denied) -> {

                            var response = exchange.getResponse();

                            response.setStatusCode(HttpStatus.FORBIDDEN);
                            response.getHeaders().add("Content-Type", "application/json");

                            String body = "{\"status\":403,\"error\":\"FORBIDDEN\"}";
                            var buffer = response.bufferFactory().wrap(body.getBytes());

                            return response.writeWith(Mono.just(buffer));
                        })
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .pathMatchers(HttpMethod.POST, "/students/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/courses/**").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/students/**")
                        .hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                        .pathMatchers(HttpMethod.GET, "/courses/**")
                        .hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                        .anyExchange().authenticated()
                )
                .build();
    }

    private Mono<Authentication> convert(ServerWebExchange exchange) {

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Mono.empty();
        }

        String token = authHeader.substring(7).trim();

        return Mono.just(
                new UsernamePasswordAuthenticationToken(token, token)
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}