# 7.2 - Hibernate JPA CRUD - Create

## Introduction

Welcome to **7.2 - Hibernate JPA CRUD - Create** 

 In this section, we’ll dive into the "Create" part of CRUD (Create, Read, Update, Delete) using Hibernate and JPA in Spring Boot. Building on [7.1](#71-setting-up-hibernate-jpa-project), where we set up the `hibernate-demo` project and mapped the `Student` entity to the `student` table, we’ll now save new student records to the database. You’ll learn to use a Data Access Object (DAO), EntityManager, and a REST controller—perfect for beginners ready to make their app data-driven! 🚀

---

## Table of Contents

1. [What Is CRUD - Create?](#1-what-is-crud---create)
   - [1.1 Overview](#11-overview)
   - [1.2 DAO and EntityManager](#12-dao-and-entitymanager)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Understanding DAO](#21-understanding-dao)
   - [2.2 Implementing Save with EntityManager](#22-implementing-save-with-entitymanager)
   - [2.3 Adding a REST Endpoint](#23-adding-a-rest-endpoint)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 DAO Interface](#31-dao-interface)
   - [3.2 DAO Implementation](#32-dao-implementation)
   - [3.3 REST Controller](#33-rest-controller)
   - [3.4 Annotations Explained](#34-annotations-explained)
   - [3.5 Testing with Postman](#35-testing-with-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is CRUD - Create?

### 1.1 Overview

- **Goal**: Add (create/save) a new `Student` to the `student` table in `student_db` (from [07](#07-jpa-and-hibernate)).
- **How**: Use Hibernate/JPA to map a Java `Student` object to the database via a Data Access Object (DAO) and a REST API endpoint.
- **Why**: Simplifies database inserts—no manual SQL, just object-oriented code!

#### Real-World Analogy

Think of this as adding a new student to a school roster—Hibernate handles the paperwork (database insert) for you!

### 1.2 DAO and EntityManager

- **DAO (Data Access Object)**: A design pattern—interfaces with the database to perform operations (e.g., save, find, update, delete).
- **EntityManager**: JPA’s core component (from [07](#07-jpa-and-hibernate))—executes queries like saving (`persist`) using a `DataSource`.
- **Flow**: DAO → EntityManager → DataSource → Database.

#### Architecture

- Spring Boot auto-creates `EntityManager` and `DataSource` (configured via `application.properties` in [7.1](#71-setting-up-hibernate-jpa-project)).
- DAO uses `EntityManager` methods (e.g., `persist`) to save objects.

>[!NOTE]
>DAO = your database middleman; EntityManager = the magic wand!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term              | Meaning                                      | Example                       |
|-------------------|----------------------------------------------|-------------------------------|
| **CRUD**          | Create, Read, Update, Delete operations      | Save a `Student`              |
| **DAO**           | Data Access Object—handles DB ops            | `StudentDAO`                  |
| **EntityManager** | JPA component for queries                    | `entityManager.persist()`     |
| **@Transactional**| Auto-manages database transactions          | Begin/end for writes          |
| **@Repository**   | Marks a class as a DB operation handler      | `@Repository` on `StudentDAOImpl` |
| **@PostMapping**  | REST endpoint for creating data              | `/students` POST              |
| **@RequestBody**  | Converts JSON to Java object                 | JSON → `Student`              |

---

## 2. Learning Roadmap

Your path to mastering the "Create" operation!

### 2.1 Understanding DAO

- **What**: Define an interface (`StudentDAO`) and implementation (`StudentDAOImpl`) for database operations.
- **Goal**: Organize code to save `Student` objects cleanly.

### 2.2 Implementing Save with EntityManager

- **What**: Use `EntityManager`’s `persist` method to insert data, with transaction management.
- **Goal**: Save a `Student` to the database effortlessly.

### 2.3 Adding a REST Endpoint

- **What**: Create a REST controller with a POST endpoint to accept JSON and save it.
- **Goal**: Test the save operation via a REST API.

---

## 3. Practical Demonstration

Let’s extend `hibernate-demo` from [7.1](#71-setting-up-hibernate-jpa-project) to save a new `Student`!

### 3.1 DAO Interface

- **Purpose**: Declare the `save` method for creating a `Student`.
- **Steps**:
  1. Create package: `com.example.jpa.dao`.
  2. Add interface: `StudentDAO.java`.
  3. Code:
     ```java
     package com.example.jpa.dao;

     import com.example.jpa.entity.Student;

     public interface StudentDAO {
         void save(Student student);
     }
     ```
- **Details**:
  - `void save(Student student)`: Abstract method—saves a `Student` object (no return value).
  - Interface methods are implicitly `public` and `abstract`.

### 3.2 DAO Implementation

- **Purpose**: Implement `save` using `EntityManager`.
- **Steps**:
  1. In `com.example.jpa.dao`, add class: `StudentDAOImpl.java`.
  2. Code:
     ```java
     package com.example.jpa.dao;

     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.stereotype.Repository;
     import org.springframework.transaction.annotation.Transactional;
     import com.example.jpa.entity.Student;
     import jakarta.persistence.EntityManager;

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
             entityManager.persist(student);
         }
     }
     ```
- **Details**:
  - `@Repository`: Marks this as a DAO—enables component scanning and JDBC exception handling.
  - `@Autowired`: Injects `EntityManager` via constructor (Spring provides it—alternative: field injection with `@Autowired private EntityManager entityManager;`).
  - `@Transactional`: Auto-manages transactions (begin/end) for write operations like `persist`.
  - `persist()`: Saves the `Student` to the `student` table—Hibernate maps it automatically.

>[!TIP]
>`@Transactional` = your write operation safety net—no manual transaction code needed!

### 3.3 REST Controller

- **Purpose**: Add a POST endpoint to save a `Student` via JSON.
- **Steps**:
  1. Create package: `com.example.jpa.controller`.
  2. Add class: `StudentController.java`.
  3. Code:
     ```java
     package com.example.jpa.controller;

     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.web.bind.annotation.PostMapping;
     import org.springframework.web.bind.annotation.RequestBody;
     import org.springframework.web.bind.annotation.RestController;
     import com.example.jpa.dao.StudentDAO;
     import com.example.jpa.entity.Student;

     @RestController
     public class StudentController {

         private StudentDAO studentDAO;

         @Autowired
         public StudentController(StudentDAO studentDAO) {
             this.studentDAO = studentDAO;
         }

         @PostMapping("/students")
         public String addStudent(@RequestBody Student student) {
             studentDAO.save(student);
             return "Student saved successfully";
         }
     }
     ```
- **Details**:
  - `@RestController`: Marks this as a REST API handler.
  - `@Autowired`: Injects `StudentDAO` (Spring wires `StudentDAOImpl`).
  - `@PostMapping("/students")`: POST endpoint—creates a `Student`.
  - `@RequestBody`: Converts incoming JSON to a `Student` object (uses Jackson from [5.3](#53-java-json-binding-jackson-project)).
  - `save()`: Calls the DAO’s method—saves to database.
  - Return: Simple success message (customizable later).

#### HTTP Mapping Recap

- **POST**: Create (save)—`@PostMapping`.
- **GET**: Read—coming next!
- **PUT**: Update—later.
- **DELETE**: Delete—later.

### 3.4 Annotations Explained

Here’s a table detailing all annotations used in the code—why they’re used and their importance:

| Annotation         | Used In                | Purpose                                              | Importance                                                                 |
|--------------------|------------------------|------------------------------------------------------|---------------------------------------------------------------------------|
| **`@Repository`**  | `StudentDAOImpl`       | Marks the class as a data repository for DB ops      | Enables Spring to scan it as a component, handle JDBC exceptions, and recognize it as a DAO for CRUD operations. |
| **`@Transactional`**| `save` method          | Auto-manages database transactions (begin/end)       | Ensures write operations (e.g., save) are atomic—success or rollback, no manual transaction code needed. |
| **`@Autowired`**   | Constructor injections | Injects dependencies (e.g., `EntityManager`, `StudentDAO`) | Simplifies wiring—Spring auto-provides beans, reducing manual instantiation and enhancing modularity. |
| **`@RestController`**| `StudentController`   | Marks the class as a REST API handler                | Tells Spring to handle HTTP requests and return responses (e.g., JSON/text), key for RESTful apps. |
| **`@PostMapping`** | `addStudent` method    | Defines a POST endpoint (e.g., `/students`)          | Maps HTTP POST requests to save data—aligns with REST best practices for creating resources. |
| **`@RequestBody`** | `addStudent` parameter | Converts JSON request body to a Java object          | Enables REST API to accept JSON input (e.g., `Student` data), crucial for client-to-server communication. |

#### Why Annotations Matter

- **Simplicity**: Replace boilerplate code (e.g., transaction management, dependency setup).
- **Clarity**: Clearly define roles (DAO, controller) and behaviors (POST, transactional).
- **Spring Magic**: Leverage Spring’s auto-configuration and dependency injection—less work, more focus on logic!

>[!NOTE]
>Annotations = your Spring shortcuts—master them, master Spring Boot!

### 3.5 Testing with Postman

- **Setup**: Ensure `hibernate-demo` is running (`Tomcat started on port(s): 8080`).
- **Steps**:
  1. Open Postman (or similar REST client).
  2. Set request:
     - Method: `POST`.
     - URL: `http://localhost:8080/students`.
     - Body: `Raw` > `JSON`.
     - JSON:
       ```json
       {
           "firstName": "Kate",
           "lastName": "Winslet",
           "email": "kate@winslet.com"
       }
       ```
     - Note: `id` omitted—auto-increments via `@GeneratedValue` (from [7.1](#71-setting-up-hibernate-jpa-project)).
  3. Click `Send`.
- **Result**: Response: `"Student saved successfully"`.
- **Verify**:
  - In MySQL: `SELECT * FROM student;`
  - Before: 3 rows (e.g., Liam Neeson, Bruce Willis, Tom Hanks from [07](#07-jpa-and-hibernate)).
  - After: 4 rows—new row: `id=4, first_name=Kate, last_name=Winslet, email=kate@winslet.com`.

#### Flow Recap

1. JSON → `Student` object (Jackson).
2. `Student` → `student` table (Hibernate ORM).
3. Success message returned.

>[!NOTE]
>Two mappings in action: JSON-to-Java + Java-to-Database—Hibernate ties it all together!

---

## 4. What’s Next

- **Next Session**: **Hibernate JPA CRUD - Read**—retrieve `Student` records (e.g., all students or by ID) using GET mappings.


>[!TIP]
>You’ve just created data—next, let’s read it back!

---
