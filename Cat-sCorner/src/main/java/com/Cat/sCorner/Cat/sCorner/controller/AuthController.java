package com.Cat.sCorner.Cat.sCorner.controller;

import cn.hutool.core.map.MapUtil;
import com.Cat.sCorner.Cat.sCorner.dto.LoginRequest;
import com.Cat.sCorner.Cat.sCorner.dto.RegisterRequest;
import com.Cat.sCorner.Cat.sCorner.dto.UserDTO;
import com.Cat.sCorner.Cat.sCorner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        String jwt = userService.registerUser(registerRequest);
        MapUtil.of("token", jwt);
        return ResponseEntity.ok(MapUtil.of("token", jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String jwt = userService.loginUser(loginRequest);
        UserDTO user = userService.getUserByEmail(loginRequest.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("user", user);
        return ResponseEntity.ok(response);    }

}
