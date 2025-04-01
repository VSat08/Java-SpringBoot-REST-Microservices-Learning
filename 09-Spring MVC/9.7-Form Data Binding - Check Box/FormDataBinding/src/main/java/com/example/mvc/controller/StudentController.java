package com.example.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mvc.model.Student;

@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Value("${countries}")
	private List<String> countries;

	@Value("${skills}")
	private List<String> skills;

	@GetMapping("/showStudentForm")
	public String showForm(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("countries", countries);
		model.addAttribute("skills", skills);
		return "student-form";
	}

	@PostMapping("/processStudentForm")
	public String processForm(@ModelAttribute("student") Student student) {
//		 log the input data
		logger.info("""
			    Student Registration Details:
			    ----------------------------
			    First Name: {}
			    Last Name:  {}
			    Country:    {}
			    Gender:     {}
			    Skills:     {}
			    """, 
			    student.getFirstName(),
			    student.getLastName(),
			    student.getCountry(),
			    student.getGender(),
			    String.join(", ", student.getSkill()));
		return "student-confirmation";
	}

	@GetMapping("/")
	public String redirectToStudentApi() {
		return "redirect:/showStudentForm"; // Redirects to /showStudentForm
	}
}
