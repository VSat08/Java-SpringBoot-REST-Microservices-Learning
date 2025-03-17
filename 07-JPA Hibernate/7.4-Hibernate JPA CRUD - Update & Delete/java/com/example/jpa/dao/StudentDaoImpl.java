package com.example.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

	@Override
	public Student findById(Integer id) {

		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		entityManager.merge(student);

	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);

	}

}
