package com.example.college.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Table("users")
public class User {

 @Id
 private Long id;

 private String username;

 private String password;

 private String role;
}