package com.example.studentrecordmanager.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "students")
public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
       @Column(name = "Name")
        private String name;
    @Column(name = "RollNumber")
        private String rollNumber;
    @Column(name = "Course")
        private String course;
    @Column(name = "GPA")
        private Double gpa;
        public Student() {}
        public Student(String name, String rollNumber, String course, Double gpa) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.course = course;
            this.gpa = gpa;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getRollNumber() { return rollNumber; }
        public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

        public String getCourse() { return course; }
        public void setCourse(String course) { this.course = course; }

        public Double getGpa() { return gpa; }
        public void setGpa(Double gpa) { this.gpa = gpa; }
    }

