package com.example.studentrecordmanager.controller;

import com.example.studentrecordmanager.entity.Student;
import com.example.studentrecordmanager.sevice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/all-students")
    public String listAll(Model model) {
        List<Student> students = service.findAll();
        model.addAttribute("students", students);
        return "list-students";
    }

    // Fix here: removed "/students" to "" so path is /students
    @GetMapping("")
    public String getAllStudents(Model model) {
        // Add students to model if needed
        List<Student> students = service.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/add-student")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students/home";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = service.findById(id);
        if (student == null) {
            return "redirect:/students/all-students";
        }
        model.addAttribute("student", student);
        return "add-student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/students/all-students";
    }

    @GetMapping("/filter-gpa")
    public String filterGpaForm() {
        return "filter-gpa";
    }

    @GetMapping("/filter")
    public String filterByGpa(@RequestParam Double value, @RequestParam String type, Model model) {
        List<Student> filtered;
        if ("above".equals(type)) {
            filtered = service.findByGpaAbove(value);
        } else {
            filtered = service.findByGpaBelow(value);
        }
        model.addAttribute("students", filtered);
        return "list-students";
    }

    @GetMapping("/by-course")
    public String listByCourse(@RequestParam String course, Model model) {
        List<Student> students = service.findByCourse(course);

        model.addAttribute("students", students);
        model.addAttribute("course", course);

        model.addAttribute("summary", new GpaSummary(
                service.getCountByCourse(course),
                service.getAvgGpaByCourse(course),
                service.getMaxGpaByCourse(course),
                service.getMinGpaByCourse(course)
        ));
        return "students-by-course";
    }

    public static class GpaSummary {
        private Long count;
        private Double average;
        private Double max;
        private Double min;

        public GpaSummary(Long count, Double average, Double max, Double min) {
            this.count = count;
            this.average = average == null ? 0.0 : average;
            this.max = max == null ? 0.0 : max;
            this.min = min == null ? 0.0 : min;
        }

        public Long getCount() { return count; }
        public Double getAverage() { return average; }
        public Double getMax() { return max; }
        public Double getMin() { return min; }
    }
}

