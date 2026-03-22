
package com.example.college.controller;

import com.example.college.entity.Student;
import com.example.college.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

 private final StudentService service;

 @PostMapping
 public Mono<Student> addStudent(@RequestBody Student student){
  return service.addStudent(student);
 }

 @GetMapping
 public Flux<Student> getStudents(){
  return service.getStudents();
 }
}
