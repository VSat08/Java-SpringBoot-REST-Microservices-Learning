# 7.5 - JPA Hibernate - Creating Tables from the Code

## Introduction

Welcome to **7.5 - JPA Hibernate - Creating Tables from the Code** 

In this section, we’ll explore how to auto-generate database tables directly from Java code using Hibernate and JPA in Spring Boot. Building on [7.4](#74-hibernate-jpa-crud---update--delete), where we completed CRUD operations, we’ll now let Hibernate create the `student` table for us—no manual SQL required! This is ideal for beginners eager to streamline development and testing. 🚀

---

## Table of Contents

1. [What Is Table Creation from Code?](#1-what-is-table-creation-from-code)
   - [1.1 Overview](#11-overview)
   - [1.2 How Hibernate Does It](#12-how-hibernate-does-it)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Setting Up DDL Auto](#21-setting-up-ddl-auto)
   - [2.2 Using Entity Annotations](#22-using-entity-annotations)
   - [2.3 Testing Table Creation](#23-testing-table-creation)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Copying the Project](#31-copying-the-project)
   - [3.2 Configuring application.properties](#32-configuring-applicationproperties)
   - [3.3 Verifying Table Creation](#33-verifying-table-creation)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Table Creation from Code?

### 1.1 Overview

- **Goal**: Create the `student` table in a database (e.g., `jpa_db`) from Java code using Hibernate/JPA.
- **How**: Use a Spring Boot property (`spring.jpa.hibernate.ddl-auto`) and entity annotations—no SQL scripts needed.
- **Why**: Simplifies development and testing—Hibernate generates tables based on your Java entities!

#### Real-World Analogy

Think of this as sketching a blueprint (Java entity) and letting Hibernate build the house (database table) for you!

### 1.2 How Hibernate Does It

- **Property**: `spring.jpa.hibernate.ddl-auto`—tells Hibernate how to handle table creation (e.g., `create`, `update`).
- **Annotations**: `@Entity`, `@Id`, `@Column` (from [7.1](#71-setting-up-hibernate-jpa-project))—define the table structure.
- **Process**: Hibernate reads your entity class (e.g., `Student`), converts it to SQL (e.g., `CREATE TABLE`), and executes it.

#### DDL Options

| Option       | Action                                      | Use Case                     |
|--------------|---------------------------------------------|------------------------------|
| `create`     | Drops existing tables, creates new ones     | Testing, fresh start         |
| `update`     | Creates tables if missing, updates schema   | Development, data retention  |
| `create-drop`| Drops, creates, drops on shutdown          | Unit testing                |
| `validate`   | Checks schema matches entity, no changes    | Production validation        |
| `none`       | No action—tables unchanged                  | Production, manual setup     |

>[!WARNING]
>`update` keeps data; `create` wipes it—choose wisely!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term                     | Meaning                                      | Example                       |
|--------------------------|----------------------------------------------|-------------------------------|
| **DDL**                  | Data Definition Language (e.g., `CREATE`)    | `CREATE TABLE student`        |
| **`ddl-auto`**           | Hibernate property for table management      | `spring.jpa.hibernate.ddl-auto=update` |
| **`@Entity`**            | Marks a class as a database table            | `@Entity(name = "student")`   |
| **`@Id`**                | Defines the primary key                      | `@Id` on `id` field           |
| **`@GeneratedValue`**    | Auto-increments the primary key              | `strategy = IDENTITY`         |
| **Schema**               | Database table structure                     | Columns: `id`, `first_name`   |

---

## 2. Learning Roadmap

Your path to auto-creating tables!

### 2.1 Setting Up DDL Auto

- **What**: Add `spring.jpa.hibernate.ddl-auto` to `application.properties`.
- **Goal**: Configure Hibernate to create or update tables.

### 2.2 Using Entity Annotations

- **What**: Define the `Student` entity with JPA annotations.
- **Goal**: Map Java fields to database columns.

### 2.3 Testing Table Creation

- **What**: Run the app and verify the table in a new database.
- **Goal**: Confirm Hibernate builds the table and supports CRUD.

---

## 3. Practical Demonstration

Let’s create a new project (`jpa-create-table-demo`) based on `hibernate-demo` from [7.4](#74-hibernate-jpa-crud---update--delete) to auto-generate the `student` table!

### 3.1 Copying the Project

- **Purpose**: Start fresh with a new database (`jpa_db`).
- **Steps**:
  1. Copy `hibernate-demo` to a new project: `jpa-create-table-demo`.
  2. Keep all code (`Student`, `StudentDAO`, `StudentController`)—focus shifts to table creation.
- **Entity Recap** (from `com.example.jpa.entity.Student.java`):
  ```java
  package com.example.jpa.entity;

  import jakarta.persistence.Column;
  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.Table;

  @Entity
  @Table(name = "student")
  public class Student {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id")
      private int id;

      @Column(name = "first_name")
      private String firstName;

      @Column(name = "last_name")
      private String lastName;

      @Column(name = "email")
      private String email;

      // Constructors, getters, setters (e.g., via Lombok or manual)
      public Student() {}
      public Student(String firstName, String lastName, String email) {
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
      }
      // ... getters and setters ...
  }
  ```
- **Details**:
  - `@Entity`: Marks `Student` as a table.
  - `@Table(name = "student")`: Names the table `student`.
  - `@Id` + `@GeneratedValue`: `id` is auto-incremented primary key.
  - `@Column`: Maps fields to columns (e.g., `firstName` → `first_name`).

### 3.2 Configuring application.properties

- **Purpose**: Set up a new database (`jpa_db`) and enable table creation.
- **File**: `src/main/resources/application.properties`.
- **Code**:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/jpa_db
  spring.datasource.username=root
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  ```
- **Details**:
  - `spring.datasource.url`: Points to `jpa_db`—create it manually first (`CREATE DATABASE jpa_db;` in MySQL).
  - `spring.jpa.hibernate.ddl-auto=update`:
    - Creates `student` table if it doesn’t exist.
    - Updates schema if `Student` changes (e.g., adding a field).
    - Preserves data—safer than `create`.

#### Why `update`?

- `create`: Drops all tables, then recreates—loses data each run.
- `update`: Creates if missing, alters if changed—keeps data intact.

### 3.3 Verifying Table Creation

- **Steps**:
  1. **Pre-Check**: In MySQL:
     - `SHOW DATABASES;` → Confirm `jpa_db` exists.
     - `USE jpa_db; SHOW TABLES;` → No tables yet.
  2. **Run App**: Start `jpa-create-table-demo` (`Tomcat started on port(s): 8080`).
  3. **Post-Check**: In MySQL:
     - `SHOW TABLES;` → `student` appears.
     - `SHOW CREATE TABLE student;` → Confirms structure:
       ```sql
       CREATE TABLE `student` (
         `id` int NOT NULL AUTO_INCREMENT,
         `first_name` varchar(255),
         `last_name` varchar(255),
         `email` varchar(255),
         PRIMARY KEY (`id`)
       )
       ```
     - `SELECT * FROM student;` → Empty (no data yet).
  4. **Test CRUD**:
     - POST `http://localhost:8080/students` (via Postman):
       ```json
       {
           "firstName": "John",
           "lastName": "Doe",
           "email": "john@doe.com"
       }
       ```
       → `"Student saved successfully"`.
     - GET `http://localhost:8080/students` → `[{"id": 1, "firstName": "John", ...}]`.
     - MySQL: `SELECT * FROM student;` → 1 row (`id=1`, `first_name=John`).

- **Details**:
  - Hibernate executed `CREATE TABLE student` based on `Student` entity.
  - CRUD works—table is functional, not just created!

>[!TIP]
>No SQL, just Java—Hibernate builds your database for you!

---

## 4. What’s Next

- **Next Session**: Build an **Employee Management System** with CRUD operations.
- **Upcoming**: Transition to Spring Data JPA—simplify CRUD even more!


>[!TIP]
>Tables from code mastered—next, manage employees with ease!

---
