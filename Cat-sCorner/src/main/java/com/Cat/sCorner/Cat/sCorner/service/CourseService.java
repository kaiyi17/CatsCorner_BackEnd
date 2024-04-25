package com.Cat.sCorner.Cat.sCorner.service;

import com.Cat.sCorner.Cat.sCorner.dto.CourseDTO;
import com.Cat.sCorner.Cat.sCorner.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(Course course);

    Optional<Course> getCourseById(Long courseId);

    List<CourseDTO> getAllCourses();

    Course updateCourse(Course course);

    void deleteCourse(Long courseId);

    void registerCourse(Long userId, Long courseId);
    void unregisterCourse(Long userId, Long courseId);

    List<CourseDTO> getRegisteredCourses(Long userId);

    List<CourseDTO> getRegisteredCourses(String username);
}
