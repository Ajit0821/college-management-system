package com.example.college.service;

import com.example.college.dto.LoginRequest;
import com.example.college.dto.RegisterRequest;
import com.example.college.entity.User;
import com.example.college.repository.UserRepository;
import com.example.college.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;

 // ✅ Register (Reactive)
 public Mono<String> register(RegisterRequest request){

  User user = new User();
  user.setUsername(request.getUsername());
  user.setPassword(passwordEncoder.encode(request.getPassword()));
  user.setRole(String.valueOf(request.getRole())); // should be String

  return userRepository.save(user)
          .map(u -> "User registered");
 }

 // ✅ Login (Reactive)
 public Mono<String> login(LoginRequest request){

  return userRepository.findByUsername(request.getUsername())
          .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
          .flatMap(user -> {

           if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return Mono.just(
                    jwtUtil.generateToken(user.getUsername(), user.getRole())
            );
           }

           return Mono.error(new RuntimeException("Invalid credentials"));
          });
 }
}