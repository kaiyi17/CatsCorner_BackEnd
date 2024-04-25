package com.Cat.sCorner.Cat.sCorner.service.impl;

import com.Cat.sCorner.Cat.sCorner.dto.CourseDTO;
import com.Cat.sCorner.Cat.sCorner.entity.Course;
import com.Cat.sCorner.Cat.sCorner.entity.User;
import com.Cat.sCorner.Cat.sCorner.repository.CourseRepository;
import com.Cat.sCorner.Cat.sCorner.repository.UserRepository;
import com.Cat.sCorner.Cat.sCorner.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        List<CourseDTO> courseDTOs = courses.stream().map(course -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(course.getId());
            dto.setName(course.getName());
            dto.setDescription(course.getDescription());
            dto.setSchedule(course.getSchedule());
            return dto;
        }).collect(Collectors.toList());

        return courseDTOs;
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void registerCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getStudents().add(user);
        courseRepository.save(course);
    }

    @Override
    public void unregisterCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getStudents().remove(user);
        courseRepository.save(course);
    }

    public List<CourseDTO> getRegisteredCourses(Long userId) {
        Set<Course> courses = courseRepository.findByStudents_Id(userId);
        return courses.stream()
                .map(course -> new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getSchedule()))
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getRegisteredCourses(String username) {
        Set<Course> courses = courseRepository.findCoursesByStudentUsername(username);
        return courses.stream()
                .map(course -> new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getSchedule()))
                .collect(Collectors.toList());
    }

}
