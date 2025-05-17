package com.example.studentrecordmanager.repository;

import com.example.studentrecordmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

        List<Student> findByCourse(String course);

        List<Student> findByGpaGreaterThan(Double gpa);

        List<Student> findByGpaLessThan(Double gpa);

        @Query("SELECT AVG(s.gpa) FROM Student s WHERE s.course = :course")
        Double findAverageGpaByCourse(String course);

        @Query("SELECT MAX(s.gpa) FROM Student s WHERE s.course = :course")
        Double findMaxGpaByCourse(String course);

        @Query("SELECT MIN(s.gpa) FROM Student s WHERE s.course = :course")
        Double findMinGpaByCourse(String course);

        @Query("SELECT COUNT(s) FROM Student s WHERE s.course = :course")
        Long countByCourse(String course);
}


