package com.Cat.sCorner.Cat.sCorner.service.impl;

import com.Cat.sCorner.Cat.sCorner.config.JwtTokenProvider;
import com.Cat.sCorner.Cat.sCorner.dto.LoginRequest;
import com.Cat.sCorner.Cat.sCorner.dto.RegisterRequest;
import com.Cat.sCorner.Cat.sCorner.dto.UserDTO;
import com.Cat.sCorner.Cat.sCorner.entity.User;
import com.Cat.sCorner.Cat.sCorner.entity.UserPrincipal;
import com.Cat.sCorner.Cat.sCorner.exception.BizExceptionKit;
import com.Cat.sCorner.Cat.sCorner.exception.Status;
import com.Cat.sCorner.Cat.sCorner.mapper.UserMapper;
import com.Cat.sCorner.Cat.sCorner.repository.UserRepository;
import com.Cat.sCorner.Cat.sCorner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;

    }

    @Override
    public String registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw BizExceptionKit.of(Status.BadRequest, "Current user has been registered");
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

        UserPrincipal userPrincipal = new UserPrincipal(user, null);

        return jwtTokenProvider.generateToken(userPrincipal);
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isPresent()) {
            String password = loginRequest.getPassword();
            BizExceptionKit.of("password is not correct").throwIfNot(passwordEncoder.matches(password, optionalUser.get().getPassword()));
            UserPrincipal userPrincipal = new UserPrincipal(optionalUser.get(), null);
            return jwtTokenProvider.generateToken(userPrincipal);
        }
        BizExceptionKit.of("Current user not exist").throwIt();
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->BizExceptionKit.of("Can not find the user by the user ID"));
        return userMapper.apply(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.apply(user);
        } else {
            throw BizExceptionKit.of("User with email " + email + " not found");
        }
    }
}
