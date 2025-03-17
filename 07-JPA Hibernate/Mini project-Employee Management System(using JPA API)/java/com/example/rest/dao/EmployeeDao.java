package com.example.rest.dao;

import java.util.List;

import com.example.rest.entity.Employee;

public interface EmployeeDao {

	// insert and update
	public Employee save(Employee employee);

	public Employee findById(int id);

	public List<Employee> findAll();

	public void deleteById(int id);

}
