package com.example.studentrecordmanager.sevice;
import com.example.studentrecordmanager.entity.Student;
import com.example.studentrecordmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public List<Student> findByCourse(String course) {
        return repo.findByCourse(course);
    }

    public List<Student> findByGpaAbove(Double gpa) {
        return repo.findByGpaGreaterThan(gpa);
    }

    public List<Student> findByGpaBelow(Double gpa) {
        return repo.findByGpaLessThan(gpa);
    }

    public Double getAvgGpaByCourse(String course) {
        return repo.findAverageGpaByCourse(course);
    }

    public Double getMaxGpaByCourse(String course) {
        return repo.findMaxGpaByCourse(course);
    }

    public Double getMinGpaByCourse(String course) {
        return repo.findMinGpaByCourse(course);
    }

    public Long getCountByCourse(String course) {
        return repo.countByCourse(course);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Student findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}


