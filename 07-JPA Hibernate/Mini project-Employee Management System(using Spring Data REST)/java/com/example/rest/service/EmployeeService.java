package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import com.example.rest.entity.Employee;

public interface EmployeeService {

//	save and update
	public Employee save(Employee employee);

	public Optional<Employee> findById(int id);

	public List<Employee> findAll();

	public void deleteById(int id);

}
