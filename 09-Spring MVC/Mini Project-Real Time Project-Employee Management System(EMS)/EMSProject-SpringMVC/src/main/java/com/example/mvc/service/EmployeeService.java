package com.example.mvc.service;

import java.util.List;
import java.util.Optional;

import com.example.mvc.entity.Employee;

public interface EmployeeService {

	public Employee save(Employee employee);

	public Optional<Employee> findById(int id);

	public List<Employee> findAll();

	public void deleteById(int id);
}
