package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.entity.Employee;
import com.example.rest.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

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
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);

	}

}
