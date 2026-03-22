package com.example.college.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("course")
public class Course {

 @Id
 private Long id;

 private String courseName;
}