package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

	@GetMapping("/emps")
	public String getEmp() {
		Employee emp = new Employee("John", 12345);
		return emp.getName() + " " + emp.getId();
	}
}
