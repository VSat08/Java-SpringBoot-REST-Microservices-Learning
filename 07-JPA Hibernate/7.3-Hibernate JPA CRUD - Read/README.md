# 7.3 - Hibernate JPA CRUD - Read

## Introduction

Welcome to **7.3 - Hibernate JPA CRUD - Read** 

In this section, we’ll explore the "Read" part of CRUD (Create, Read, Update, Delete) using Hibernate and JPA in Spring Boot. Building on [7.2](#72-hibernate-jpa-crud---create), where we saved students to `student_db`, we’ll now retrieve them—either a single student by ID or all students at once. You’ll use `EntityManager`, JPQL, and REST endpoints to fetch data, making this perfect for beginners eager to see their database in action! 🚀

---

## Table of Contents

1. [What Is CRUD - Read?](#1-what-is-crud---read)
   - [1.1 Overview](#11-overview)
   - [1.2 Retrieving with EntityManager](#12-retrieving-with-entitymanager)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Reading a Single Student](#21-reading-a-single-student)
   - [2.2 Reading All Students with JPQL](#22-reading-all-students-with-jpql)
   - [2.3 Exposing via REST](#23-exposing-via-rest)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Updating DAO Interface](#31-updating-dao-interface)
   - [3.2 Implementing DAO Methods](#32-implementing-dao-methods)
   - [3.3 Enhancing REST Controller](#33-enhancing-rest-controller)
   - [3.4 Testing with Browser/Postman](#34-testing-with-browserpostman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is CRUD - Read?

### 1.1 Overview

- **Goal**: Retrieve (read/fetch) `Student` records from the `student` table in `student_db` (from [07](#07-jpa-and-hibernate)).
- **How**: Use `EntityManager` to find a single student by ID or query all students with JPQL, then expose via REST.
- **Why**: Simplifies data retrieval—no raw SQL, just Java objects!

#### Real-World Analogy

Think of this as checking a student roster—either look up one student by their ID or get the full class list!

### 1.2 Retrieving with EntityManager

- **Single Record**: Use `EntityManager.find()` with a primary key (e.g., `id`)—returns one `Student` or `null` if not found.
- **All Records**: Use `EntityManager.createQuery()` with JPQL (Java Persistence Query Language)—returns a list of `Student` objects.
- **Flow**: DAO → `EntityManager` → `DataSource` → Database (from [7.2](#72-hibernate-jpa-crud---create)).

#### JPQL vs. SQL

- **SQL**: Works on database tables/columns (e.g., `SELECT * FROM student`).
- **JPQL**: Works on Java entities/fields (e.g., `FROM Student`)—database-agnostic, handled by Hibernate.

>[!NOTE]
>`EntityManager` = your data fetcher—simple for one, powerful for all!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term             | Meaning                                      | Example                       |
|------------------|----------------------------------------------|-------------------------------|
| **Read**         | Fetch data from the database                 | Retrieve a `Student`          |
| **`find()`**     | EntityManager method for single record       | `find(Student.class, 1)`      |
| **JPQL**         | Java Persistence Query Language              | `FROM Student`                |
| **TypedQuery**   | JPA interface for typed query results        | `TypedQuery<Student>`         |
| **`@GetMapping`**| REST endpoint for reading data               | `/students` GET               |
| **`@PathVariable`**| Extracts URL parameter                     | `/students/{id}` → `id`       |

---

## 2. Learning Roadmap

Your path to mastering the "Read" operation!

### 2.1 Reading a Single Student

- **What**: Add a `findById` method to fetch one `Student` by `id`.
- **Goal**: Use `EntityManager.find()` for quick, ID-based retrieval.

### 2.2 Reading All Students with JPQL

- **What**: Add a `findAll` method to fetch all `Student` records using JPQL.
- **Goal**: Query the database with entity-focused syntax.

### 2.3 Exposing via REST

- **What**: Create GET endpoints to return a single `Student` or all `Student` records as JSON.
- **Goal**: Test retrieval via browser or Postman.

---

## 3. Practical Demonstration

Let’s enhance `hibernate-demo` from [7.2](#72-hibernate-jpa-crud---create) to read `Student` data!

### 3.1 Updating DAO Interface

- **Purpose**: Add methods for reading one or all `Student` records.
- **File**: `com.example.jpa.dao.StudentDAO.java`.
- **Code**:
  ```java
  package com.example.jpa.dao;

  import com.example.jpa.entity.Student;
  import java.util.List;

  public interface StudentDAO {
      void save(Student student); // From 7.2
      Student findById(Integer id);
      List<Student> findAll();
  }
  ```
- **Details**:
  - `findById(Integer id)`: Returns a `Student` by primary key (`id`).
  - `findAll()`: Returns a `List<Student>` of all students—no parameters needed.

### 3.2 Implementing DAO Methods

- **Purpose**: Implement `findById` and `findAll` using `EntityManager`.
- **File**: `com.example.jpa.dao.StudentDAOImpl.java`.
- **Code**:
  ```java
  package com.example.jpa.dao;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Repository;
  import org.springframework.transaction.annotation.Transactional;
  import com.example.jpa.entity.Student;
  import jakarta.persistence.EntityManager;
  import jakarta.persistence.TypedQuery;
  import java.util.List;

  @Repository
  public class StudentDAOImpl implements StudentDAO {

      private EntityManager entityManager;

      @Autowired
      public StudentDAOImpl(EntityManager entityManager) {
          this.entityManager = entityManager;
      }

      @Override
      @Transactional
      public void save(Student student) {
          entityManager.persist(student); // From 7.2
      }

      @Override
      public Student findById(Integer id) {
          return entityManager.find(Student.class, id);
      }

      @Override
      public List<Student> findAll() {
          TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
          return query.getResultList();
      }
  }
  ```
- **Details**:
  - **`findById`**:
    - `entityManager.find(Student.class, id)`: Fetches a `Student` by `id`.
    - Returns `null` if `id` doesn’t exist (e.g., `id=10` when only 1-4 exist)—exception handling optional (see [5.4](#54-exception-handling)).
    - No `@Transactional`—read-only, no database changes.
  - **`findAll`**:
    - `TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class)`: JPQL query for all `Student` entities.
    - `query.getResultList()`: Returns a `List<Student>`—all records.
    - No `@Transactional`—read-only.

#### JPQL Note

- `"FROM Student"`: Uses entity name (`Student`), not table name (`student`)—maps to `firstName`, `lastName`, etc., not `first_name`, `last_name`.
- Hibernate converts this to SQL (`SELECT * FROM student`) behind the scenes.

>[!TIP]
>JPQL = SQL for Java entities—keeps your code database-independent!

### 3.3 Enhancing REST Controller

- **Purpose**: Add GET endpoints for single and all `Student` records.
- **File**: `com.example.jpa.controller.StudentController.java`.
- **Code**:
  ```java
  package com.example.jpa.controller;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RestController;
  import com.example.jpa.dao.StudentDAO;
  import com.example.jpa.entity.Student;
  import java.util.List;

  @RestController
  public class StudentController {

      private StudentDAO studentDAO;

      @Autowired
      public StudentController(StudentDAO studentDAO) {
          this.studentDAO = studentDAO;
      }

      @PostMapping("/students")
      public String addStudent(@RequestBody Student student) { // From 7.2
          studentDAO.save(student);
          return "Student saved successfully";
      }

      @GetMapping("/students/{studentId}")
      public Student getStudent(@PathVariable Integer studentId) {
          return studentDAO.findById(studentId);
      }

      @GetMapping("/students")
      public List<Student> getAllStudents() {
          return studentDAO.findAll();
      }
  }
  ```
- **Details**:
  - **`getStudent`**:
    - `@GetMapping("/students/{studentId}")`: GET endpoint with a path variable (e.g., `/students/1`).
    - `@PathVariable Integer studentId`: Extracts `studentId` from the URL.
    - Returns a `Student`—Jackson converts it to JSON.
  - **`getAllStudents`**:
    - `@GetMapping("/students")`: GET endpoint for all students.
    - Returns a `List<Student>`—Jackson converts it to a JSON array.

#### Jackson Role

- `Student` → JSON (single object): `{"id": 1, "firstName": "Liam", ...}`.
- `List<Student>` → JSON array: `[{"id": 1, ...}, {"id": 2, ...}, ...]`.

### 3.4 Testing with Browser/Postman

- **Setup**: Run `hibernate-demo` (`Tomcat started on port(s): 8080`).
- **Single Student**:
  - **Browser**: `http://localhost:8080/students/1` → `{"id": 1, "firstName": "Liam", "lastName": "Neeson", "email": "liam@neeson.com"}`.
  - **Postman**: GET `http://localhost:8080/students/2` → `{"id": 2, "firstName": "Bruce", "lastName": "Willis", "email": "bruce@willis.com"}`.
  - Verify: Matches `student` table (e.g., `id=3` → Tom Hanks, `id=4` → Kate Winslet from [7.2](#72-hibernate-jpa-crud---create)).
- **All Students**:
  - **Browser**: `http://localhost:8080/students` → JSON array of all 4 students.
  - **Postman**: GET `http://localhost:8080/students` → Same array: `[{"id": 1, ...}, {"id": 2, ...}, {"id": 3, ...}, {"id": 4, ...}]`.
- **Notes**:
  - No GET for `/students` existed in [7.2](#72-hibernate-jpa-crud---create)—now it works!
  - Invalid ID (e.g., `/students/10`) returns `null`—enhance with exceptions later.

>[!NOTE]
>From DB to JSON: Hibernate fetches, Jackson formats—seamless retrieval!

---

## 4. What’s Next

- **Next Session**: **Hibernate JPA CRUD - Update & Delete**—modify and remove `Student` records.

>[!TIP]
>You’ve read your data—next, let’s change it!

---

