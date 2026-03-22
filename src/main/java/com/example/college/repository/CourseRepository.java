
package com.example.college.repository;

import com.example.college.entity.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
}
