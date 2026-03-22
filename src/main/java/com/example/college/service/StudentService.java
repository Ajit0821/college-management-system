
package com.example.college.service;

import com.example.college.entity.Student;
import com.example.college.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

 private final StudentRepository repository;

 public Mono<Student> addStudent(Student student){
  return repository.save(student);
 }

 public Flux<Student> getStudents(){
  return repository.findAll();
 }
}
