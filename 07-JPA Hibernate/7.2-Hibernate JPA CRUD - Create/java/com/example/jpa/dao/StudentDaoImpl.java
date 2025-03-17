package com.example.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Student student) {
		entityManager.persist(student);
	}

}
