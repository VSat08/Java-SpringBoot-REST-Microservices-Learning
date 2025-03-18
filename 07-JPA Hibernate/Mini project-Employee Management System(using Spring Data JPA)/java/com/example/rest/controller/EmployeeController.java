package com.example.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Employee;
import com.example.rest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable int id) {
		return employeeService.findById(id);
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Optional<Employee> employee = employeeService.findById(id);

		if (employee == null) {
			throw new RuntimeException("Employee not found with id: " + id);
		} else {
			employeeService.deleteById(id);
		}

		return "Employee deleted with id: " + id;
	}
}
