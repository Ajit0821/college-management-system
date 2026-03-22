
package com.example.college.repository;

import com.example.college.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
}
