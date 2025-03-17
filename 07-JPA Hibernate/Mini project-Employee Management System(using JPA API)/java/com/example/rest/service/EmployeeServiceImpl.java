package com.example.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.dao.EmployeeDao;
import com.example.rest.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee findById(int id) {
		return employeeDao.findById(id);

	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();

	}

	@Transactional
	@Override
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		employeeDao.deleteById(id);

	}

}
