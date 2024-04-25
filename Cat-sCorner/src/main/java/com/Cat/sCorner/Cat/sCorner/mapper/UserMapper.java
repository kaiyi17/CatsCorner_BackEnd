package com.Cat.sCorner.Cat.sCorner.mapper;

import org.springframework.stereotype.Component;
import com.Cat.sCorner.Cat.sCorner.dto.UserDTO;
import com.Cat.sCorner.Cat.sCorner.entity.User;
import com.Cat.sCorner.Cat.sCorner.entity.Role;


import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getUsername(),
                user.getId(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );    }
}
