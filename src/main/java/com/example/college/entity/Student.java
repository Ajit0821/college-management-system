package com.example.college.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Table("student")
public class Student {

 @Id
 private Long id;

 private String name;

 private String email;
}