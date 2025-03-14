package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	List<Student> students = new ArrayList<Student>();

	@PostConstruct
	public void loadStudents() {

		students.add(new Student("Liam", "Neeson"));  
        students.add(new Student("Mario", "Rossi")); 
        students.add(new Student("Ram", "Charan"));   
        students.add(new Student("Amir", "Khan"));    
        students.add(new Student("Bruce", "Willis")); 
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	@GetMapping("/students/{studentID}")
	public Student getStudent(@PathVariable int studentID) {
		return students.get(studentID);
	}
}
