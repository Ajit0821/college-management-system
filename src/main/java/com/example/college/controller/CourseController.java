
package com.example.college.controller;

import com.example.college.entity.Course;
import com.example.college.service.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

 private final CourseService service;

 @PostMapping
 public Course createCourse(@RequestBody Course course){
  return service.createCourse(course);
 }

 @GetMapping
 public List<Course> getCourses(){
  return service.getCourses();
 }
}
