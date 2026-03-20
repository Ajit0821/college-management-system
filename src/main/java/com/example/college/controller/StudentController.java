
package com.example.college.controller;

import com.example.college.entity.Student;
import com.example.college.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

 private final StudentService service;

 @PostMapping
 public Student addStudent(@RequestBody Student student){
  return service.addStudent(student);
 }

 @GetMapping
 public List<Student> getStudents(){
  return service.getStudents();
 }
}
