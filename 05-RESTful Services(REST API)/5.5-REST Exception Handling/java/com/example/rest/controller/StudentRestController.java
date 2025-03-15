package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Student;
import com.example.rest.entity.StudentErrorReponse;

import exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	List<Student> students = new ArrayList<Student>();

	@PostConstruct
	public void loadStudents() {

		students.add(new Student("Leong", "Rizel"));
		students.add(new Student("Mario", "Rossie"));
		students.add(new Student("Razel", "Moore"));
		students.add(new Student("Samson", "Clich"));
		students.add(new Student("Amir", "Singh"));
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	@GetMapping("/students/{studentID}")
	public Student getStudent(@PathVariable int studentID) {

		if (studentID >= students.size() || studentID < 0) {
			throw new StudentNotFoundException("student id not found - " + studentID);

		}
		return students.get(studentID);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorReponse> handleException(StudentNotFoundException e) {

		StudentErrorReponse error = new StudentErrorReponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMesssage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<StudentErrorReponse>(error, HttpStatus.NOT_FOUND);

	}
}
