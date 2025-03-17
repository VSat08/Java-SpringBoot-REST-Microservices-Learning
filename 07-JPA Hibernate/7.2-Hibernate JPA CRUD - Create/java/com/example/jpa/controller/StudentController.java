package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dao.StudentDao;
import com.example.jpa.entity.Student;

@RestController
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@PostMapping("/students")
	public String addStudent(@RequestBody Student stud) {

		studentDao.save(stud);
		return "Student saved successfully";
	}

}
