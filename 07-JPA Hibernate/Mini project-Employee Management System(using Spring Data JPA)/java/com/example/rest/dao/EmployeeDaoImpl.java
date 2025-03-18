package com.example.rest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.rest.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Employee save(Employee employee) {
		Employee dbEmployee = entityManager.merge(employee);
		return dbEmployee;
	}

	@Override
	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public List<Employee> findAll() {

		TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;

	}

	@Override
	public void deleteById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		entityManager.remove(employee);

	}

}
