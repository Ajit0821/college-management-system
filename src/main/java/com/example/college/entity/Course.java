
package com.example.college.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String courseName;
}
