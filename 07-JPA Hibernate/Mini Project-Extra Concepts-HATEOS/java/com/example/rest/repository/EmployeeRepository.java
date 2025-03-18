package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.rest.entity.Employee;

@Repository
@RepositoryRestResource(path = "emp")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// provide CRUD methods for free
}
