# 7.1 - Setting Up Hibernate (JPA) Project

## Introduction

Welcome to **7.1 - Setting Up Hibernate (JPA) Project** 🌟! In this section, we’ll set up a Spring Boot project with Hibernate and JPA to connect to a database (like MySQL’s `student_db` from [07](#07-jpa-and-hibernate)). Tailored for beginners, we’ll cover adding dependencies, configuring database connections, and creating an entity class for Object-Relational Mapping (ORM). By the end, your app will be ready to talk to a database—let’s get started! 🚀

---

## Table of Contents

1. [What You’ll Set Up](#1-what-youll-set-up)
   - [1.1 Project Overview](#11-project-overview)
   - [1.2 Dependencies Needed](#12-dependencies-needed)
   - [1.3 Configuring Application Properties](#13-configuring-application-properties)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Setup Roadmap](#2-setup-roadmap)
   - [2.1 Creating the Project](#21-creating-the-project)
   - [2.2 Adding Dependencies](#22-adding-dependencies)
   - [2.3 Setting Up Database Connection](#23-setting-up-database-connection)
   - [2.4 Defining an Entity Class](#24-defining-an-entity-class)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Project Creation](#31-project-creation)
   - [3.2 Dependency Check](#32-dependency-check)
   - [3.3 Application Properties](#33-application-properties)
   - [3.4 Student Entity Class](#34-student-entity-class)
   - [3.5 Running the App](#35-running-the-app)
4. [What’s Next](#4-whats-next)

---

## 1. What You’ll Set Up

### 1.1 Project Overview

- **Goal**: Build a Spring Boot project (`hibernate-demo`) with Hibernate/JPA to connect to MySQL and map Java objects to database tables (e.g., `student` from [07](#07-jpa-and-hibernate)).
- **Why**: Sets the foundation for CRUD operations—Hibernate simplifies database work compared to raw JDBC.

#### Real-World Analogy

Think of this as setting up a phone line (Spring Boot) to a database (MySQL)—Hibernate’s the operator making the connection smooth!

### 1.2 Dependencies Needed

To connect to MySQL and use JPA/Hibernate, you’ll need:

- **MySQL Driver**: Connects Java to MySQL (`mysql-connector-j`).
- **Spring Data JPA**: Provides JPA API with Hibernate as the default implementation (`spring-boot-starter-data-jpa`).
- **Spring Web**: Enables REST API capabilities (`spring-boot-starter-web`).
- **Spring Boot DevTools**: Live reload for faster development (`spring-boot-devtools`).
- **Lombok**: Cuts boilerplate code for entities (e.g., getters/setters from [06](#06-lombok-a-bean-management-framework)).

>[!NOTE]
>Maven fetches these automatically—no manual downloads like with JDBC!

### 1.3 Configuring Application Properties

- **What**: `application.properties` file in `src/main/resources`—tells Spring Boot how to connect to your database.
- **Key Properties**:
  - `spring.datasource.url`: Database location (e.g., `jdbc:mysql://localhost:3306/student_db`).
  - `spring.datasource.username`: MySQL user (e.g., `root`).
  - `spring.datasource.password`: MySQL password (e.g., `root` or `Changeme`).
- **Result**: Spring Boot auto-configures a `DataSource` bean—handles connection details (from [07](#07-jpa-and-hibernate)).

#### Why No Driver Class?

Spring Boot detects `mysql-connector-j` from `pom.xml` and the URL—no need to specify `com.mysql.jdbc.Driver`.

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                       |
|-----------------------|----------------------------------------------|-------------------------------|
| **Dependency**        | Library Maven fetches for your project       | `spring-boot-starter-data-jpa`|
| **DataSource**        | Bean for database connection                 | Auto-configured by Spring     |
| **Entity Class**      | Java class mapped to a table                 | `Student` → `student` table   |
| **Annotation**        | Tag for special behavior                     | `@Entity`, `@Id`              |
| **application.properties** | File for app settings                   | Database URL, username        |

---

## 2. Setup Roadmap

Your step-by-step path to a Hibernate-ready project!

### 2.1 Creating the Project

- **What**: Use Spring Initializr (via Eclipse plugin or `start.spring.io`) to generate `hibernate-demo`.
- **Goal**: Get a basic Spring Boot structure.

### 2.2 Adding Dependencies

- **What**: Add MySQL Driver, Spring Data JPA, Spring Web, DevTools, and Lombok to `pom.xml`.
- **Goal**: Equip the project for database and REST functionality.

### 2.3 Setting Up Database Connection

- **What**: Configure `application.properties` with MySQL details.
- **Goal**: Link Spring Boot to `student_db`.

### 2.4 Defining an Entity Class

- **What**: Create a `Student` class with JPA annotations to map to the `student` table.
- **Goal**: Enable ORM—connect Java to the database table.

---

## 3. Practical Demonstration

Let’s build `hibernate-demo` step-by-step!

### 3.1 Project Creation

- **Tool**: Eclipse with Spring Tools plugin.
- **Steps**:
  1. Go to `File > New > Spring Starter Project`.
  2. Name: `hibernate-demo`.
  3. Group: `com.example.jpa`.
  4. Description: “Demo project for Spring Boot with Hibernate”.
  5. Dependencies:
     - `Spring Web`
     - `Spring Boot DevTools`
     - `Lombok`
     - `MySQL Driver`
     - `Spring Data JPA`
  6. Click `Next` > `Finish`—Maven imports everything (e.g., 66%... 100% done).

- **Result**: Project structure with `HibernateDemoApplication.java` as the main class.

### 3.2 Dependency Check

- **File**: `pom.xml` in project root.
- **Content**:
  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-devtools</artifactId>
          <scope>runtime</scope>
          <optional>true</optional>
      </dependency>
      <dependency>
          <groupId>com.mysql</groupId>
          <artifactId>mysql-connector-j</artifactId>
          <scope>runtime</scope>
      </dependency>
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <optional>true</optional>
      </dependency>
  </dependencies>
  ```
- **How**: Maven downloads these (e.g., `mysql-connector-j`) and adds them to the classpath—no manual setup!

>[!TIP]
>Maven = your dependency butler—fetches and serves everything!

### 3.3 Application Properties

- **File**: `src/main/resources/application.properties`.
- **Content**:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/student_db
  spring.datasource.username=root
  spring.datasource.password=root
  ```
- **Details**:
  - URL: `jdbc:mysql://localhost:3306/student_db`—connects to `student_db` from [07](#07-jpa-and-hibernate) on MySQL’s default port (3306).
  - Username/Password: Use your MySQL credentials (e.g., `root`/`root` or `root`/`Changeme`).
- **Purpose**: Spring Boot uses this to configure the `DataSource`—no errors if MySQL is running.

#### H2 Note

Spring Boot includes H2 (in-memory database) by default, but we’re using MySQL instead—`application.properties` overrides H2.

### 3.4 Student Entity Class

- **Goal**: Map a `Student` class to the `student` table.
- **Steps**:
  1. Create package: `com.example.jpa.entity`.
  2. Add class: `Student.java`.
  3. Code:
     ```java
     package com.example.jpa.entity;

     import jakarta.persistence.Column;
     import jakarta.persistence.Entity;
     import jakarta.persistence.GeneratedValue;
     import jakarta.persistence.GenerationType;
     import jakarta.persistence.Id;
     import jakarta.persistence.Table;
     import lombok.Data;
     import lombok.NoArgsConstructor;
     import lombok.AllArgsConstructor;

     @Entity
     @Table(name = "student")
     @Data
     @NoArgsConstructor
     @AllArgsConstructor
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
     }
     ```
- **Annotations Explained**:
  - `@Entity`: Marks this as an entity class—maps to a table.
  - `@Table(name = "student")`: Specifies the table name (optional—if omitted, defaults to class name `Student`).
  - `@Id`: Marks `id` as the primary key.
  - `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Auto-increments `id` (like MySQL’s `AUTO_INCREMENT`).
  - `@Column`: Maps Java fields to table columns:
    - `id` → `id`
    - `firstName` → `first_name`
    - `lastName` → `last_name`
    - `email` → `email` (optional here—field matches column).
  - Lombok: `@Data` (getters/setters, `toString`, etc.), `@NoArgsConstructor` (required no-args constructor), `@AllArgsConstructor` (all-args constructor).

#### Notes

- **Constructor Rule**: Entities need a public/protected no-args constructor—`@NoArgsConstructor` ensures this (parameterized constructors like `@AllArgsConstructor` are optional).
- **Column Optional**: If field names match column names (e.g., `email`), `@Column` isn’t needed—defaults to field name.
- **Future Bonus**: Define this class without a table—Hibernate can create it (shown later!).

>[!NOTE]
>`Student` class = your ORM bridge—Java meets database!

### 3.5 Running the App

- **Steps**:
  1. Right-click `HibernateDemoApplication.java` > `Run As > Spring Boot App`.
  2. Console: “Tomcat started on port(s): 8080”—no errors if MySQL and `student_db` are ready.
- **Test**: Visit `localhost:8080`—see a “Whitelabel Error Page” (normal—no controllers yet).
- **What’s Happening**:
  - Spring Boot connects to `student_db` via `DataSource`.
  - `Student` entity maps to the `student` table—ready for operations.

#### Troubleshooting

- **Error?**: Check MySQL is running and `application.properties` matches your credentials.

---

## 4. What’s Next

- **Next Session**: **Hibernate JPA CRUD - Create**—start adding data to `student_db` via `Student`.
>[!TIP]
>Your app’s now database-ready—CRUD adventures await!

---

