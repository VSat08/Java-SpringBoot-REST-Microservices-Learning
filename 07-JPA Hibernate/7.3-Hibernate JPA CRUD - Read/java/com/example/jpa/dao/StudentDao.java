package com.example.jpa.dao;

import java.util.List;

import com.example.jpa.entity.Student;

public interface StudentDao {

	public void save(Student student);

	public Student findById(Integer id);

	public List<Student> findAll();
}
