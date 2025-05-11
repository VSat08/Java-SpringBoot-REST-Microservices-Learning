package com.example.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc.entity.Employee;
import com.example.mvc.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	@Override
	public Employee save(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);

	}

}
