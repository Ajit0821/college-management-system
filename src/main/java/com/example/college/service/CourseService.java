
package com.example.college.service;

import com.example.college.entity.Course;
import com.example.college.repository.CourseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

 private final CourseRepository repository;

 public Course createCourse(Course course){
  return repository.save(course);
 }

 public List<Course> getCourses(){
  return repository.findAll();
 }
}
