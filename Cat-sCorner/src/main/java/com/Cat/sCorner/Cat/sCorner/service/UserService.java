package com.Cat.sCorner.Cat.sCorner.service;

import com.Cat.sCorner.Cat.sCorner.dto.CourseDTO;
import com.Cat.sCorner.Cat.sCorner.dto.LoginRequest;
import com.Cat.sCorner.Cat.sCorner.dto.RegisterRequest;
import com.Cat.sCorner.Cat.sCorner.dto.UserDTO;
import com.Cat.sCorner.Cat.sCorner.entity.User;

import java.util.List;

public interface UserService {
    String registerUser(RegisterRequest registerRequest);

    String loginUser(LoginRequest loginRequest);

    List<User> getAllUser();

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

}
