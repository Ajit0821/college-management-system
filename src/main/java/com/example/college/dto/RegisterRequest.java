
package com.example.college.dto;

import com.example.college.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

 private String username;
 private String password;
 private Role role;

}
