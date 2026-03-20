
package com.example.college.service;

import com.example.college.entity.Student;
import com.example.college.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

 private final StudentRepository repository;

 public Student addStudent(Student student){
  return repository.save(student);
 }

 public List<Student> getStudents(){
  return repository.findAll();
 }
}
