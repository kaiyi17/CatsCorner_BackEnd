package com.Cat.sCorner.Cat.sCorner.dto;


import jakarta.validation.constraints.Email;
import com.Cat.sCorner.Cat.sCorner.entity.Role;


import java.io.Serializable;
import java.util.Set;

public record UserDTO (@Email String email, String username, Integer id, Set<String> roles) implements Serializable {}
