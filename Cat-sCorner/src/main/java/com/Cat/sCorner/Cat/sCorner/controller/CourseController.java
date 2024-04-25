package com.Cat.sCorner.Cat.sCorner.controller;

import com.Cat.sCorner.Cat.sCorner.dto.CourseDTO;
import com.Cat.sCorner.Cat.sCorner.dto.CourseRegisterDTO;
import com.Cat.sCorner.Cat.sCorner.dto.UserDTO;
import com.Cat.sCorner.Cat.sCorner.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourse()
    {
        List<CourseDTO> courseList = courseService.getAllCourses();
        return ResponseEntity.ok(courseList);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCourse(@RequestBody CourseRegisterDTO registration) {
        try {
            courseService.registerCourse(registration.getUserId(), registration.getCourseId());
            return ResponseEntity.ok("Registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/unregisterCourse")
    public ResponseEntity<?> unregisterCourse(@RequestBody CourseRegisterDTO registration) {
        try {
            courseService.unregisterCourse(registration.getUserId(), registration.getCourseId());
            return ResponseEntity.ok("Unregistered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unregistration failed: " + e.getMessage());
        }
    }

    @GetMapping("/registered")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<?> getRegisteredCourses(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username  = userDetails.getUsername();
        List<CourseDTO> registeredCourses = courseService.getRegisteredCourses(username);
        return ResponseEntity.ok(registeredCourses);
    }
}

