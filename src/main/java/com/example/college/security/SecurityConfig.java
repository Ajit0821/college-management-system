package com.example.college.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

 private final JwtFilter jwtFilter;

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

  http
          .csrf(csrf -> csrf.disable())
          .sessionManagement(session ->
                  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          )
          .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/auth/**",
                          "/v3/api-docs/**",
                          "/swagger-ui/**").permitAll()


                  .requestMatchers(HttpMethod.GET, "/students/**")
                  .hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                  .requestMatchers(HttpMethod.GET, "/courses/**")
                  .hasAnyRole("ADMIN", "TEACHER", "STUDENT")


                  .requestMatchers(HttpMethod.POST, "/students/**")
                  .hasRole("ADMIN")

                  .requestMatchers(HttpMethod.POST, "/courses/**")
                  .hasRole("ADMIN")

                  .anyRequest().authenticated()
          )
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

  return http.build();
 }

 @Bean
 public PasswordEncoder passwordEncoder(){
  return new BCryptPasswordEncoder();
 }
}