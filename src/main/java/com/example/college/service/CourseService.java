
package com.example.college.service;

import com.example.college.entity.Course;
import com.example.college.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseService {

 private final CourseRepository repository;

 public Mono<Course> createCourse(Course course){
  return repository.save(course);
 }

 public Flux<Course> getCourses(){
  return repository.findAll();
 }
}
