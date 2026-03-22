
package com.example.college.controller;

import com.example.college.dto.LoginRequest;
import com.example.college.dto.RegisterRequest;
import com.example.college.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

 private final AuthService authService;

 @PostMapping("/register")
 public Mono<String> register(@RequestBody RegisterRequest request){
  return authService.register(request);
 }

 @PostMapping("/login")
 public Mono<String>  login(@RequestBody LoginRequest request){
  return authService.login(request);
 }
}
