package com.example.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc.entity.Employee;
import com.example.mvc.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	find All Employees
	@GetMapping("/employees")
	public String getEmployees(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		return "employees-list";
	}

	@GetMapping("/form")
	public String showForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee-form";
	}

	// Save
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/api/employees";
	}

	@GetMapping("/view")
	public String getEmployeeById(@RequestParam("employeeID") int id, Model model) {
		Optional<Employee> employeeOpt = employeeService.findById(id);

		model.addAttribute("employee", employeeOpt.get());

		return "view-employee";
	}

//	Update
	@GetMapping("/update")
	public String updateEmployee(@RequestParam("employeeID") int id, Model model) {
		Optional<Employee> employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		return "employee-form";
	}

//	Delete Employee
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeID") int id) {

		employeeService.deleteById(id);

		return "redirect:/api/employees";
	}

}
