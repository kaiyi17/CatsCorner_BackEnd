package com.Cat.sCorner.Cat.sCorner.repository;

import com.Cat.sCorner.Cat.sCorner.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :userId")
    Set<Course> findByStudents_Id(@Param("userId") Long userId);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.username = :username")
    Set<Course> findCoursesByStudentUsername(@Param("username") String username);
}
