# 7.4 - Hibernate JPA CRUD - Update & Delete

## Introduction

Welcome to **7.4 - Hibernate JPA CRUD - Update & Delete**

 In this section, we’ll tackle the "Update" and "Delete" parts of CRUD (Create, Read, Update, Delete) using Hibernate and JPA in Spring Boot. Building on [7.3](#73-hibernate-jpa-crud---read), where we read students from `student_db`, we’ll now modify and remove them. You’ll use `EntityManager`’s `merge` and `remove` methods, enhance the DAO, and create REST endpoints—perfect for beginners wrapping up their CRUD journey! 🚀

---

## Table of Contents

1. [What Is CRUD - Update & Delete?](#1-what-is-crud---update--delete)
   - [1.1 Overview](#11-overview)
   - [1.2 Updating with EntityManager](#12-updating-with-entitymanager)
   - [1.3 Deleting with EntityManager](#13-deleting-with-entitymanager)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Updating a Student](#21-updating-a-student)
   - [2.2 Deleting a Student](#22-deleting-a-student)
   - [2.3 Exposing via REST](#23-exposing-via-rest)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Updating DAO Interface](#31-updating-dao-interface)
   - [3.2 Implementing DAO Methods](#32-implementing-dao-methods)
   - [3.3 Enhancing REST Controller](#33-enhancing-rest-controller)
   - [3.4 Testing with Postman](#34-testing-with-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is CRUD - Update & Delete?

### 1.1 Overview

- **Goal**: Modify (update) or remove (delete) `Student` records in the `student` table of `student_db` (from [07](#07-jpa-and-hibernate)).
- **How**: Use `EntityManager` to update entire `Student` objects or delete by ID, then expose via REST.
- **Why**: Completes CRUD—manage data fully with Hibernate’s ORM power!

#### Real-World Analogy

Think of this as editing a student’s info (update) or dropping them from the roster (delete)—Hibernate handles the database heavy lifting!

### 1.2 Updating with EntityManager

- **Method**: `entityManager.merge(student)`—updates an existing `Student` by merging changes into the database record.
- **Approach**: Pass a `Student` object with updated fields (including `id`)—Hibernate syncs it with the matching record.
- **Alternative**: JPQL (`UPDATE Student SET firstName = 'value' WHERE ...`) with `executeUpdate()`—for specific fields or conditions (not implemented here).

#### Merge vs. Persist

- `persist`: Creates a new record (from [7.2](#72-hibernate-jpa-crud---create)).
- `merge`: Updates an existing record—requires `id` to match.

### 1.3 Deleting with EntityManager

- **Method**: `entityManager.remove(student)`—deletes a `Student` by object reference.
- **Approach**: Find the `Student` by `id` with `find()`, then `remove()` it.
- **Alternative**: JPQL (`DELETE FROM Student WHERE ...`) with `executeUpdate()`—for conditional or bulk deletes (not implemented here).

#### Write Operations

- Both update and delete modify the database—require `@Transactional` (from [7.2](#72-hibernate-jpa-crud---create)).

>[!NOTE]
>`merge` updates, `remove` deletes—`EntityManager` makes it object-oriented!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term             | Meaning                                      | Example                       |
|------------------|----------------------------------------------|-------------------------------|
| **Update**       | Modify an existing record                    | Change `firstName`            |
| **Delete**       | Remove a record                              | Delete `Student` by `id`      |
| **`merge()`**    | Updates an entity in the database            | `merge(student)`              |
| **`remove()`**   | Deletes an entity from the database          | `remove(student)`             |
| **`@PutMapping`**| REST endpoint for updating data              | `/students` PUT               |
| **`@DeleteMapping`**| REST endpoint for deleting data           | `/students/{id}` DELETE       |

---

## 2. Learning Roadmap

Your path to mastering "Update" and "Delete"!

### 2.1 Updating a Student

- **What**: Add an `update` method to modify a `Student` object.
- **Goal**: Use `merge` to sync changes to the database.

### 2.2 Deleting a Student

- **What**: Add a `deleteById` method to remove a `Student` by `id`.
- **Goal**: Use `find` and `remove` for precise deletion.

### 2.3 Exposing via REST

- **What**: Create PUT and DELETE endpoints to update or delete `Student` records.
- **Goal**: Test operations via Postman.

---

## 3. Practical Demonstration

Let’s extend `hibernate-demo` from [7.3](#73-hibernate-jpa-crud---read) to update and delete `Student` data!

### 3.1 Updating DAO Interface

- **Purpose**: Add methods for updating and deleting `Student` records.
- **File**: `com.example.jpa.dao.StudentDAO.java`.
- **Code**:
  ```java
  package com.example.jpa.dao;

  import com.example.jpa.entity.Student;
  import java.util.List;

  public interface StudentDAO {
      void save(Student student);       // From 7.2
      Student findById(Integer id);    // From 7.3
      List<Student> findAll();         // From 7.3
      void update(Student student);
      void deleteById(Integer id);
  }
  ```
- **Details**:
  - `update(Student student)`: Updates a `Student`—pass the full object with changes.
  - `deleteById(Integer id)`: Deletes a `Student` by `id`.

### 3.2 Implementing DAO Methods

- **Purpose**: Implement `update` and `deleteById` using `EntityManager`.
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
      public void save(Student student) { // From 7.2
          entityManager.persist(student);
      }

      @Override
      public Student findById(Integer id) { // From 7.3
          return entityManager.find(Student.class, id);
      }

      @Override
      public List<Student> findAll() { // From 7.3
          TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
          return query.getResultList();
      }

      @Override
      @Transactional
      public void update(Student student) {
          entityManager.merge(student);
      }

      @Override
      @Transactional
      public void deleteById(Integer id) {
          Student student = entityManager.find(Student.class, id);
          entityManager.remove(student);
      }
  }
  ```
- **Details**:
  - **`update`**:
    - `entityManager.merge(student)`: Merges the passed `Student` with the existing record—updates all fields (e.g., `id`, `firstName`).
    - `@Transactional`: Write operation—ensures atomicity.
  - **`deleteById`**:
    - `entityManager.find(Student.class, id)`: Finds the `Student` by `id`.
    - `entityManager.remove(student)`: Deletes it from the database.
    - `@Transactional`: Write operation—database is modified.

>[!TIP]
>`merge` syncs changes; `remove` erases—both need `@Transactional` for safety!

### 3.3 Enhancing REST Controller

- **Purpose**: Add PUT and DELETE endpoints for updating and deleting `Student` records.
- **File**: `com.example.jpa.controller.StudentController.java`.
- **Code**:
  ```java
  package com.example.jpa.controller;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.DeleteMapping;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.PutMapping;
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
      public Student getStudent(@PathVariable Integer studentId) { // From 7.3
          return studentDAO.findById(studentId);
      }

      @GetMapping("/students")
      public List<Student> getAllStudents() { // From 7.3
          return studentDAO.findAll();
      }

      @PutMapping("/students")
      public String updateStudent(@RequestBody Student student) {
          studentDAO.update(student);
          return "Student updated successfully";
      }

      @DeleteMapping("/students/{studentId}")
      public String deleteStudent(@PathVariable Integer studentId) {
          studentDAO.deleteById(studentId);
          return "Student with ID: " + studentId + " deleted successfully";
      }
  }
  ```
- **Details**:
  - **`updateStudent`**:
    - `@PutMapping("/students")`: PUT endpoint—updates a `Student`.
    - `@RequestBody`: Converts JSON to `Student`—must include `id` and updated fields.
    - Returns success message.
  - **`deleteStudent`**:
    - `@DeleteMapping("/students/{studentId}")`: DELETE endpoint (e.g., `/students/2`).
    - `@PathVariable`: Extracts `studentId` from URL.
    - Returns message with deleted `id`.

#### HTTP Mapping Recap

- **POST**: Create (7.2).
- **GET**: Read (7.3).
- **PUT**: Update—modify resource.
- **DELETE**: Delete—remove resource.

### 3.4 Testing with Postman

- **Setup**: Run `hibernate-demo` (`Tomcat started on port(s): 8080`).
- **Update**:
  - **Request**: PUT `http://localhost:8080/students`.
  - **Body**: `Raw` > `JSON`:
    ```json
    {
        "id": 1,
        "firstName": "Liam",
        "lastName": "Neeson",
        "email": "liam@neeson.com"
    }
    ```
    - Change: Capitalize `firstName` to "Liam" and `lastName` to "Neeson".
  - **Response**: `"Student updated successfully"`.
  - **Verify**: GET `http://localhost:8080/students/1` → `{"id": 1, "firstName": "Liam", "lastName": "Neeson", ...}`.
- **Delete**:
  - **Request**: DELETE `http://localhost:8080/students/2`.
  - **Response**: `"Student with ID: 2 deleted successfully"`.
  - **Verify**: GET `http://localhost:8080/students` → List without `id=2` (e.g., Bruce Willis gone, 3 students remain).
  - **Database**: `SELECT * FROM student;` → No `id=2`.

>[!NOTE]
>PUT updates via JSON; DELETE uses URL—Postman proves it works!

---

## 4. What’s Next

- **Next Session**: **JPA Hibernate - Creating Tables from the Code**—auto-generate tables from entities.


>[!TIP]
>CRUD complete—next, let Hibernate build your tables!

---
