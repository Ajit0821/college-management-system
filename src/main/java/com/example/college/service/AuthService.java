
package com.example.college.service;

import com.example.college.dto.LoginRequest;
import com.example.college.dto.RegisterRequest;
import com.example.college.entity.User;
import com.example.college.repository.UserRepository;
import com.example.college.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;

 public String register(RegisterRequest request){

  User user = new User();
  user.setUsername(request.getUsername());
  user.setPassword(passwordEncoder.encode(request.getPassword()));
  user.setRole(request.getRole());

  userRepository.save(user);

  return "User registered";
 }

 public String login(LoginRequest request){

  User user = userRepository.findByUsername(request.getUsername())
          .orElseThrow();

  if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
   return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
  }

  throw new RuntimeException("Invalid credentials");
 }
}
