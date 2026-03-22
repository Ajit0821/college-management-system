package com.example.college.controller;

import com.example.college.entity.Course;
import com.example.college.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

 private final CourseService service;

 @PostMapping
 public Mono<Course> createCourse(@RequestBody Course course){
  return service.createCourse(course);
 }

 @GetMapping
 public Flux<Course> getCourses(){
  return service.getCourses();
 }
}