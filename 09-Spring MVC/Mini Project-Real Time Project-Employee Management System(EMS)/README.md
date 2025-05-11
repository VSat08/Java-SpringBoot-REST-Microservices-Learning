# 9.9 - Spring MVC CRUD: Employee Management System (EMS)

## Introduction

Welcome to **9.9 - Spring MVC CRUD: Employee Management System (EMS)**! 🚀

This project is a real-time **Employee Management System (EMS)** built using **Spring Boot**, **Spring MVC**, **Thymeleaf**, and **Spring Data JPA**. It extends a REST API-based CRUD application by adding a dynamic web interface, allowing users to perform **Create**, **Read**, **Update**, **Delete**, and **View** (CRUD + View) operations on employee records stored in a MySQL database named `springmvc`. The application features a polished UI styled with **Bootstrap 5**, making it intuitive and visually appealing. Whether you're a beginner or an aspiring Java developer, this project is a hands-on way to master Spring Boot MVC while building a functional web app! 🖥️

---

## Table of Contents

- [1. What Is the Employee Management System?](#1-what-is-the-employee-management-system)
  - [1.1 Overview](#11-overview)
  - [1.2 Application Flow](#12-application-flow)
  - [1.3 Key Concepts](#13-key-concepts)
  - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
- [2. Learning Roadmap](#2-learning-roadmap)
  - [2.1 Setting Up Project Dependencies](#21-setting-up-project-dependencies)
  - [2.2 Configuring the Database](#22-configuring-the-database)
  - [2.3 Defining the Employee Entity](#23-defining-the-employee-entity)
  - [2.4 Creating the Repository and Service](#24-creating-the-repository-and-service)
  - [2.5 Building the Employee Controller](#25-building-the-employee-controller)
  - [2.6 Designing Thymeleaf Pages](#26-designing-thymeleaf-pages)
- [3. Practical Demonstration](#3-practical-demonstration)
  - [3.1 Prerequisites](#31-prerequisites)
  - [3.2 Setting Up the Project](#32-setting-up-the-project)
  - [3.3 Configuring application.properties](#33-configuring-applicationproperties)
  - [3.4 Creating the Employee Entity](#34-creating-the-employee-entity)
  - [3.5 Implementing the Repository and Service](#35-implementing-the-repository-and-service)
  - [3.6 Configuring the Employee Controller](#36-configuring-the-employee-controller)
  - [3.7 Creating Thymeleaf Pages](#37-creating-thymeleaf-pages)
  - [3.8 Running and Testing](#38-running-and-testing)
  - [3.9 Troubleshooting Common Issues](#39-troubleshooting-common-issues)
- [4. Code Walkthrough](#4-code-walkthrough)
  - [4.1 Employee Entity](#41-employee-entity)
  - [4.2 Employee Repository](#42-employee-repository)
  - [4.3 Employee Service](#43-employee-service)
  - [4.4 Employee Controller](#44-employee-controller)
  - [4.5 Thymeleaf Templates](#45-thymeleaf-templates)
- [5. What’s Next](#5-whats-next)
  - [5.1 Enhancements](#51-enhancements)
  - [5.2 Learning Path](#52-learning-path)

---

## 1. What Is the Employee Management System?

### 1.1 Overview

- **Goal**: Create a web-based EMS to manage employee records with CRUD operations and a view feature.
- **What You’ll Build**: A Spring Boot web app running on port `1803` where users can:
  1. **List Employees**: View all employees (`id`, `firstName`, `lastName`, `email`) in a table with **View**, **Update**, and **Delete** buttons.
  2. **Add Employee**: Click **Add Employee** to open a form, enter details (e.g., "Tom," "Cruise," "tom@cruise.com"), and save.
  3. **View Employee**: Click **View** to see an employee’s details in a dedicated page.
  4. **Update Employee**: Click **Update** to edit an employee’s details in a pre-populated form (e.g., change "emma@stone.com" to "emma@stone.net").
  5. **Delete Employee**: Click **Delete**, confirm via a dialog, and remove the employee.
- **Why It’s Awesome**: Combines Spring MVC’s power, Thymeleaf’s dynamic templating, and JPA’s database magic into a real-world app. Perfect for learning modern Java web development!
- **Tools**:
  - **Spring Boot 3.4.4**: Simplifies setup and configuration.
  - **Spring MVC**: Handles HTTP requests and responses.
  - **Thymeleaf**: Renders server-side HTML with data binding.
  - **Spring Data JPA**: Provides easy database operations.
  - **MySQL**: Stores employee data in the `springmvc` database.
  - **Bootstrap 5**: Styles the UI with responsive tables, buttons, and forms.

#### Real-World Analogy

Think of an HR dashboard for a company. The dashboard (Thymeleaf) shows a table of employees (database). HR can add new hires (Create), view individual profiles (Read/View), update contact info (Update), or remove employees with confirmation (Delete). The backend (Spring MVC, JPA) ensures data is securely stored and updated, while the UI (Bootstrap) makes it user-friendly.

### 1.2 Application Flow

- **Steps Explained**:
  1. **List Employees**: Navigate to `http://localhost:1803/api/employees`. Displays `employees-list.html` with a table of employees and an **Add Employee** button.
  2. **Add Employee**: Click **Add Employee** → Opens `employee-form.html`. Enter "Tom," "Cruise," "tom@cruise.com" → Submit to `POST /api/save`. Redirects to employee list.
  3. **View Employee**: Click **View** on an employee (e.g., "Brad Pitt") → Opens `view-employee.html` with their details.
  4. **Update Employee**: Click **Update** → Opens `employee-form.html` pre-populated (e.g., "Emma Stone"). Update email to "emma@stone.net" → Submit to `POST /api/save`. Redirects to list.
  5. **Delete Employee**: Click **Delete** → Confirms via JavaScript dialog → Deletes employee, redirects to list.
- **Diagram**:
  ```
  Browser → GET /api/employees → Controller → employees-list.html
      ↓ (Add) GET /api/form → employee-form.html → POST /api/save → Redirect to GET /api/employees
      ↓ (View) GET /api/view?employeeID=X → view-employee.html
      ↓ (Update) GET /api/update?employeeID=X → employee-form.html → POST /api/save → Redirect
      ↓ (Delete) GET /api/delete?employeeID=X → Confirm → Redirect to GET /api/employees
  ```

### 1.3 Key Concepts

- **Spring MVC**: Maps URLs (e.g., `/api/employees`, `/api/save`) to controller methods using `@GetMapping`, `@PostMapping`.
- **Thymeleaf**: Binds model data to HTML using `th:each`, `th:object`, `th:field` for dynamic rendering.
- **Spring Data JPA**: Provides free CRUD methods (e.g., `findAll()`, `save()`) via `JpaRepository`.
- **Model Attribute**: Transfers data (e.g., employee list or single employee) from controller to view using `@ModelAttribute`.
- **Bootstrap 5**: Enhances UI with classes like `table-striped`, `btn-primary`, `form-control`.
- **JavaScript Confirmation**: Uses `confirm()` to prevent accidental deletions.
- **Hidden Fields**: Stores `id` in forms for updates without displaying it.

> [!NOTE]
> This project reuses the REST API project’s entity, repository, and service, adding MVC and Thymeleaf for a web interface!

### 1.4 Key Terms for Beginners

A newbie-friendly glossary to understand the project:

| Term                  | Meaning                                      | Example                              | Why It’s Cool          |
|-----------------------|----------------------------------------------|--------------------------------------|-----------------------|
| **Spring MVC**        | Framework for web apps (Model-View-Controller)| `@GetMapping("/api/employees")`      | Organizes request handling |
| **Thymeleaf**         | Template engine for dynamic HTML             | `th:each="emp : ${employees}"`       | Binds data to UI       |
| **JPA Repository**    | Interface for database operations            | `findAll()`                          | Free CRUD methods      |
| **`@ModelAttribute`** | Binds form data to an object                | `@ModelAttribute("employee")`         | Simplifies form handling |
| **`th:action`**       | Specifies form submission URL                | `th:action="@{/api/save}"`           | Dynamic routing        |
| **Bootstrap 5**       | CSS framework for styling                    | `btn btn-primary`                    | Polished, responsive UI |
| **Confirm Dialog**    | JavaScript prompt for user confirmation      | `onclick="confirm('Are you...')"`    | Prevents accidental deletes |
| **Hidden Field**      | Form input not displayed but submitted       | `<input type="hidden" th:field="*{id}">` | Tracks IDs for updates |

---

## 2. Learning Roadmap

Your step-by-step path to mastering EMS and Spring Boot MVC!

### 2.1 Setting Up Project Dependencies

- **What**: Configure Maven dependencies for Spring Boot, MVC, Thymeleaf, JPA, and MySQL.
- **Goal**: Enable web development, templating, and database connectivity.
- **How**: Update `pom.xml` with required dependencies.

### 2.2 Configuring the Database

- **What**: Set up MySQL connection to the `springmvc` database.
- **Goal**: Allow Spring Boot to connect to the `employee` table.
- **How**: Edit `application.properties` with database URL, username, password, and JPA settings.

### 2.3 Defining the Employee Entity

- **What**: Create a Java class representing the `employee` table.
- **Goal**: Map `id`, `first_name`, `last_name`, `email` to database columns.
- **How**: Use JPA annotations (`@Entity`, `@Id`, `@Column`) and Lombok.

### 2.4 Creating the Repository and Service

- **What**: Build layers for data access and business logic.
- **Goal**: Provide CRUD operations with abstraction.
- **How**: Extend `JpaRepository` for the repository; define a service interface and implementation.

### 2.5 Building the Employee Controller

- **What**: Handle HTTP requests for CRUD and View operations.
- **Goal**: Map URLs to list, add, view, update, and delete actions.
- **How**: Use Spring MVC annotations (`@Controller`, `@GetMapping`, `@PostMapping`).

### 2.6 Designing Thymeleaf Pages

- **What**: Create HTML templates for the UI.
- **Goal**: Display employee lists, forms, and details with Bootstrap styling.
- **How**: Use Thymeleaf tags (`th:each`, `th:object`) and Bootstrap classes.

---

## 3. Practical Demonstration

Let’s build the EMS step by step! This section is your manual to set up, code, and test the app.

### 3.1 Prerequisites

Before starting, ensure you have:

- **Java 17**: Required for Spring Boot 3.4.4.
- **Maven**: For dependency management.
- **Eclipse IDE** (or IntelliJ, VS Code with Java extensions).
- **MySQL**: Installed and running (e.g., MySQL Workbench).
- **Git**: To clone or manage the repository.
- **Basic Knowledge**: Java, HTML, and SQL basics.

### 3.2 Setting Up the Project

- **Purpose**: Create a Spring Boot project with necessary dependencies.
- **Tool**: Eclipse (or Spring Initializr at `start.spring.io`).
- **Steps**:
  1. **New Project in Eclipse**:
     - File → New → Spring Starter Project.
     - **Details**:
       - Name: `EMSProject-SpringMVC`.
       - Type: Maven.
       - Java Version: 17.
       - Packaging: JAR.
       - Group: `com.example`.
       - Artifact: `EMSProject-SpringMVC`.
       - Package: `com.example.mvc`.
  2. **Dependencies**:
     - Spring Web
     - Spring Boot DevTools
     - Spring Data JPA
     - MySQL Driver
     - Thymeleaf
     - Lombok
  3. **Finish**: Generate the project and open it in Eclipse.
- **Result**: A Maven project with `pom.xml`:
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>3.4.4</version>
          <relativePath/>
      </parent>
      <groupId>com.example</groupId>
      <artifactId>EMSProject-SpringMVC</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <name>EMSProject-SpringMVC</name>
      <description>Demo project for Form Data Binding in Spring MVC using thymeleaf </description>
      <properties>
          <java.version>17</java.version>
      </properties>
      <dependencies>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-data-jpa</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-thymeleaf</artifactId>
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
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
          </dependency>
      </dependencies>
      <build>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <configuration>
                      <annotationProcessorPaths>
                          <path>
                              <groupId>org.projectlombok</groupId>
                              <artifactId>lombok</artifactId>
                          </path>
                      </annotationProcessorPaths>
                  </configuration>
              </plugin>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <configuration>
                      <excludes>
                          <exclude>
                              <groupId>org.projectlombok</groupId>
                              <artifactId>lombok</artifactId>
                          </exclude>
                      </excludes>
                  </configuration>
              </plugin>
          </plugins>
      </build>
  </project>
  ```
- **Key Dependencies**:
  - `spring-boot-starter-web`: For Spring MVC.
  - `spring-boot-starter-thymeleaf`: For Thymeleaf templates.
  - `spring-boot-starter-data-jpa`: For database operations.
  - `mysql-connector-j`: For MySQL connectivity.
  - `lombok`: For reducing boilerplate code.
  - `spring-boot-devtools`: For hot reloading during development.

> [!TIP]
> Run `mvn clean install` to download dependencies and verify the setup!

### 3.3 Configuring application.properties

- **Purpose**: Connect Spring Boot to the `springmvc` MySQL database and configure the server port.
- **File**: `src/main/resources/application.properties`.
- **Code**:
  ```properties
  spring.application.name=EMSProject-SpringMVC

  # Server Configuration
  spring.datasource.url=jdbc:mysql://localhost:3306/springmvc
  spring.datasource.username=root
  spring.datasource.password=Sanchii@1803
  server.port=1803

  # JPA/Hibernate Configuration
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

  # Thymeleaf Configuration
  spring.thymeleaf.cache=false
  ```
- **Line-by-Line Breakdown**:
  - `spring.application.name`: Sets the app name to `EMSProject-SpringMVC`.
  - `spring.datasource.url`: Connects to the `springmvc` database on `localhost:3306`.
  - `spring.datasource.username/password`: MySQL credentials (`root`, `Sanchii@1803`).
  - `server.port=1803`: Runs the app on port `1803`.
  - `spring.jpa.hibernate.ddl-auto=update`: Auto-creates/updates the `employee` table.
  - `spring.jpa.show-sql=true`: Logs SQL queries for debugging.
  - `spring.jpa.properties.hibernate.dialect`: Uses MySQL 8 dialect.
  - `spring.thymeleaf.cache=false`: Disables caching for live Thymeleaf changes.
- **Database Setup**:
  1. Open MySQL Workbench (or CLI).
  2. Create database:
     ```sql
     CREATE DATABASE springmvc;
     ```
  3. Verify: `SHOW DATABASES;`.

> [!WARNING]
> Ensure MySQL is running and the password `Sanchii@1803` is correct for the `root` user. Update if different!

### 3.4 Creating the Employee Entity

- **Purpose**: Define the data model for the `employee` table.
- **File**: `src/main/java/com/example/mvc/entity/Employee.java`.
- **Code**:
  ```java
  package com.example.mvc.entity;

  import jakarta.persistence.Column;
  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.Table;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Entity
  @Table(name = "employee")
  public class Employee {
      @Id
      @Column(name = "id")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @Column(name = "first_name")
      private String firstName;

      @Column(name = "last_name")
      private String lastName;

      @Column(name = "email")
      private String email;
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Entity`, `@Table`**: Maps class to `employee` table.
  - **`@Id`, `@GeneratedValue`**: Auto-increments `id` as primary key.
  - **`@Column`**: Maps fields to columns (`first_name`, `last_name`, `email`).
  - **`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`**: Lombok adds getters, setters, and constructors.
- **Why These Fields?**: Matches the database schema and UI requirements (ID, first name, last name, email).

> [!NOTE]
> JPA will create the `employee` table with columns `id`, `first_name`, `last_name`, `email` if `spring.jpa.hibernate.ddl-auto=update`!

### 3.5 Implementing the Repository and Service

- **Purpose**: Enable database operations and business logic.
- **Files**:
  - **Repository**: `src/main/java/com/example/mvc/repository/EmployeeRepository.java`.
    ```java
    package com.example.mvc.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import com.example.mvc.entity.Employee;

    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    }
    ```
  - **Service Interface**: `src/main/java/com/example/mvc/service/EmployeeService.java`.
    ```java
    package com.example.mvc.service;

    import java.util.List;
    import java.util.Optional;

    import com.example.mvc.entity.Employee;

    public interface EmployeeService {
        public Employee save(Employee employee);
        public Optional<Employee> findById(int id);
        public List<Employee> findAll();
        public void deleteById(int id);
    }
    ```
  - **Service Implementation**: `src/main/java/com/example/mvc/service/EmployeeServiceImplementation.java`.
    ```java
    package com.example.mvc.service;

    import java.util.List;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.example.mvc.entity.Employee;
    import com.example.mvc.repository.EmployeeRepository;

    import jakarta.transaction.Transactional;

    @Service
    public class EmployeeServiceImplementation implements EmployeeService {
        @Autowired
        private EmployeeRepository employeeRepository;

        @Transactional
        @Override
        public Employee save(Employee employee) {
            return employeeRepository.save(employee);
        }

        @Override
        public Optional<Employee> findById(int id) {
            return employeeRepository.findById(id);
        }

        @Override
        public List<Employee> findAll() {
            return employeeRepository.findAll();
        }

        @Transactional
        @Override
        public void deleteById(int id) {
            employeeRepository.deleteById(id);
        }
    }
    ```
- **Line-by-Line Breakdown**:
  - **Repository**:
    - Extends `JpaRepository<Employee, Integer>`: Provides free CRUD methods (`findAll()`, `save()`, `findById()`, `deleteById()`).
    - `@Repository`: Marks as a Spring bean.
  - **Service Interface**:
    - Declares methods: `save()`, `findById()`, `findAll()`, `deleteById()`.
  - **Service Implementation**:
    - `@Service`: Marks as a Spring service bean.
    - `@Autowired`: Injects `EmployeeRepository`.
    - `@Transactional`: Ensures database operations are atomic for `save` and `deleteById`.
    - Delegates calls to repository methods.
- **Why Separate Layers?**:
  - **Repository**: Handles raw database access.
  - **Service**: Adds business logic and abstraction, making the controller cleaner.

> [!TIP]
> Test the repository by creating a test class to call `findAll()` and verify database connectivity!

### 3.6 Configuring the Employee Controller

- **Purpose**: Handle HTTP requests for CRUD and View operations.
- **File**: `src/main/java/com/example/mvc/controller/EmployeeController.java`.
- **Code**:
  ```java
  package com.example.mvc.controller;

  import java.util.List;
  import java.util.Optional;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;

  @Controller
  @RequestMapping("/api")
  public class EmployeeController {

      @Autowired
      private EmployeeService employeeService;

      // find All Employees
      @GetMapping("/employees")
      public String getEmployees(Model model) {
          List<Employee> employees = employeeService.findAll();
          model.addAttribute("employees", employees);
          return "employees-list";
      }

      @GetMapping("/form")
      public String showForm(Model model) {
          Employee employee = new Employee();
          model.addAttribute("employee", employee);
          return "employee-form";
      }

      // Save
      @PostMapping("/save")
      public String saveEmployee(@ModelAttribute("employee") Employee employee) {
          employeeService.save(employee);
          return "redirect:/api/employees";
      }

      @GetMapping("/view")
      public String getEmployeeById(@RequestParam("employeeID") int id, Model model) {
          Optional<Employee> employeeOpt = employeeService.findById(id);
          model.addAttribute("employee", employeeOpt.get());
          return "view-employee";
      }

      // Update
      @GetMapping("/update")
      public String updateEmployee(@RequestParam("employeeID") int id, Model model) {
          Optional<Employee> employee = employeeService.findById(id);
          model.addAttribute("employee", employee);
          return "employee-form";
      }

      // Delete Employee
      @GetMapping("/delete")
      public String deleteEmployee(@RequestParam("employeeID") int id) {
          employeeService.deleteById(id);
          return "redirect:/api/employees";
      }
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Controller`, `@RequestMapping("/api")`**: Marks as a web controller, prefixes all mappings with `/api`.
  - **`@Autowired`**: Injects `EmployeeService` for business logic.
  - **`@GetMapping("/employees")`**: Fetches all employees, adds to model as `employees`, returns `employees-list` template.
  - **`@GetMapping("/form")`**: Creates a new `Employee`, adds to model, returns `employee-form` for adding.
  - **`@GetMapping("/view")`**: Fetches employee by `employeeID`, adds to model, returns `view-employee`.
  - **`@GetMapping("/update")`**: Fetches employee by `employeeID`, adds to model, returns `employee-form` for editing.
  - **`@PostMapping("/save")`**: Saves employee (add or update) via `@ModelAttribute`, redirects to `/api/employees`.
  - **`@GetMapping("/delete")`**: Deletes employee by `employeeID`, redirects to `/api/employees`.
- **Key Feature**: Uses redirects (`redirect:/api/employees`) to refresh the list after save or delete.

> [!WARNING]
> Ensure `@RequestParam("employeeID")` matches the `employeeID` parameter in Thymeleaf URLs (e.g., `employeeID=${emp.id}`)!

### 3.7 Creating Thymeleaf Pages

- **Purpose**: Build the UI for listing, adding, updating, viewing, and deleting employees.
- **Files** (in `src/main/resources/templates/`):
  - **List Page**: `employees-list.html`.
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <!-- Bootstrap 5 CSS CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <meta charset="UTF-8">
        <title>Employees List</title>
    </head>
    <body>
        <div class="container">
            <h1 class="display-4">Employees List</h1>
            <a th:href="@{/api/form}" class="btn btn-outline-primary my-3 btn-sm"> Add Employee</a>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover table-sm">
                    <thead class="table-dark">
                        <tr>
                            <th>Employee ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="emp : ${employees}">
                            <td th:text="${emp.id}"/>
                            <td th:text="${emp.firstName}"/>
                            <td th:text="${emp.lastName}"/>
                            <td th:text="${emp.email}"/>
                            <td>
                                <a th:href="@{/api/view(employeeID=${emp.id})}" class="btn btn-success btn-sm">View</a>
                                 
                                <a th:href="@{/api/update(employeeID=${emp.id})}" class="btn btn-warning btn-sm">Update</a>
                                 
                                <a th:href="@{/api/delete(employeeID=${emp.id})}" onclick="if(!(confirm('Are you want to delete this employee permanently?'))) return false" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    </html>
    ```
  - **Form Page**: `employee-form.html`.
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <!-- Bootstrap 5 CSS CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <title>Add/Update Employee</title>
    </head>
    <body>
        <div class="container">
            <a th:href="@{/api/employees}" class="btn btn-success btn-sm my-3">See All Employee</a>
            <h1 class="display-4">Add/Update Employee</h1>
            <form action="#" th:action="@{/api/save}" method="post" th:object="${employee}">
                <div class="mb-3">
                    <input type="hidden" th:field="*{id}"/>
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" th:field="*{firstName}" placeholder="Enter First Name">
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" th:field="*{lastName}" placeholder="Enter Last Name">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" th:field="*{email}" placeholder="Enter Email">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </body>
    </html>
    ```
  - **View Page**: `view-employee.html`.
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>View Employee</title>
        <!-- Bootstrap 5 CSS CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="display-6 mb-4">Employee Details</h2>
            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <td th:text="${employee.id}"></td>
                </tr>
                <tr>
                    <th>First Name</th>
                    <td th:text="${employee.firstName}"></td>
                </tr>
                <tr>
                    <th>Last Name</th>
                    <td th:text="${employee.lastName}"></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td th:text="${employee.email}"></td>
                </tr>
            </table>
            <a th:href="@{/api/employees}" class="btn btn-secondary mt-3">Back to List</a>
        </div>
    </body>
    </html>
    ```
- **Line-by-Line Breakdown**:
  - **List Page (`employees-list.html`)**:
    - **`th:each="emp : ${employees}"`**: Loops through the `employees` model attribute.
    - **`th:text`**: Displays `id`, `firstName`, `lastName`, `email`.
    - **`th:href`**: Creates links for **View**, **Update**, and **Delete** with `employeeID`.
    - **`onclick`**: Adds JavaScript `confirm()` for delete confirmation.
    - **Bootstrap Classes**: `table-striped`, `table-bordered`, `btn-success`, `btn-warning`, `btn-danger`.
  - **Form Page (`employee-form.html`)**:
    - **`th:object="${employee}"`**: Binds form to the `employee` model attribute.
    - **`th:field`**: Maps inputs to `id`, `firstName`, `lastName`, `email`.
    - **`<input type="hidden">`**: Hides `id` for updates.
    - **Bootstrap Classes**: `form-control`, `btn-primary`, `mb-3` for styling.
    - **Back Button**: Links to `/api/employees`.
  - **View Page (`view-employee.html`)**:
    - **`th:text`**: Displays `id`, `firstName`, `lastName`, `email` from the `employee` model.
    - **Table**: Presents details in a clean format.
    - **Back Button**: Links to `/api/employees`.
    - **Bootstrap Classes**: `table-bordered`, `btn-secondary`.
- **Why Three Pages?**:
  - `employees-list.html`: Displays the employee table.
  - `employee-form.html`: Handles both add and update operations.
  - `view-employee.html`: Shows individual employee details.

> [!TIP]
> Test `th:each` by adding multiple employees and verify they all appear in the list!

### 3.8 Running and Testing

- **Purpose**: Verify all CRUD and View operations work as expected.
- **Steps**:
  1. **Run the Application**:
     - Right-click `EMSProjectSpringMVCApplication.java` → Run As → Spring Boot App.
     - Console output: `Tomcat started on port(s): 1803`.
  2. **Test Cases**:
     - **List Employees**:
       - Visit `http://localhost:1803/api/employees`.
       - Expected: Table shows employees (e.g., "Tom Cruise", "Emma Stone") with **View**, **Update**, **Delete** buttons.
       - Verify in MySQL: `SELECT * FROM employee;`.
     - **Add Employee**:
       - Click **Add Employee** → Enter "Prabhas", "Raju", "prabhas@raju.com" → Submit.
       - Expected: Redirects to `/api/employees`, new employee appears.
       - MySQL: Confirm new row with `SELECT * FROM employee;`.
     - **View Employee**:
       - Click **View** on "Prabhas" → See details (ID, First Name, Last Name, Email).
       - Expected: `view-employee.html` shows correct data.
       - Click **Back to List** to return.
     - **Update Employee**:
       - Click **Update** on "Emma Stone" → Change email to "emma@stone.net" → Submit.
       - Expected: Redirects to `/api/employees`, email updated in table.
       - MySQL: Verify with `SELECT * FROM employee WHERE first_name='Emma';`.
     - **Delete Employee**:
       - Click **Delete** on "Prabhas" → Confirm dialog appears → Click OK.
       - Expected: Redirects to `/api/employees`, "Prabhas" is gone.
       - MySQL: Confirm row deleted with `SELECT * FROM employee;`.
  3. **Verify Database**:
     - Open MySQL Workbench or CLI.
     - Run: `USE springmvc; SELECT * FROM employee;`.
     - Expected: Reflects all CRUD operations (e.g., new rows, updated emails, deleted rows).
- **Sample Test Scenario**:
  - Add "John Doe" (`john@doe.com`).
  - View his details.
  - Update email to `john.doe@new.com`.
  - Delete him.
  - Verify each step in the UI and database.

> [!TIP]
> Perform a full CRUD cycle (add, view, update, delete) on one employee to ensure everything works!

### 3.9 Troubleshooting Common Issues

- **White Label Error Page**:
  - **Cause**: Wrong URL or missing Thymeleaf template.
  - **Fix**: Visit `http://localhost:1803/api/employees` (not `/`). Ensure `employees-list.html` exists in `src/main/resources/templates/`.
- **No Data in Table**:
  - **Cause**: Database empty or misconfigured.
  - **Fix**: Check `application.properties` for correct `spring.datasource` settings. Run `INSERT INTO employee (first_name, last_name, email) VALUES ('Test', 'User', 'test@user.com');` in MySQL.
- **Form Not Submitting**:
  - **Cause**: Incorrect `th:field` or missing `th:object`.
  - **Fix**: Verify `employee-form.html` has `th:object="${employee}"` and `th:field` matches `Employee` fields (`id`, `firstName`, `lastName`, `email`).
- **HTTP 500 Error**:
  - **Cause**: Code error (e.g., `Optional.get()` on empty result).
  - **Fix**: Check logs in Eclipse console. Add null checks (e.g., `employeeOpt.orElseThrow()` in `EmployeeController`).
- **Bootstrap Styling Not Applied**:
  - **Cause**: Incorrect CDN URL.
  - **Fix**: Ensure `<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" ...>` is in `<head>`.
- **Database Connection Error**:
  - **Cause**: MySQL not running or wrong credentials.
  - **Fix**: Start MySQL service (`sudo service mysql start` on Linux or equivalent). Verify `spring.datasource.username=root` and `password=Sanchii@1803`.

> [!WARNING]
> Always check the Spring Boot console logs for detailed error messages when debugging!

---

## 4. Code Walkthrough

A detailed look at each component of the EMS project, using your provided code.

### 4.1 Employee Entity

- **File**: `src/main/java/com/example/mvc/entity/Employee.java`.
- **Purpose**: Maps the `employee` table to a Java class.
- **Key Features**:
  - `@Entity`, `@Table(name = "employee")`: Links to the `employee` table.
  - `@Id`, `@GeneratedValue`: Auto-increments `id`.
  - `@Column`: Maps `firstName`, `lastName`, `email` to `first_name`, `last_name`, `email`.
  - Lombok (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`): Generates getters, setters, and constructors.
- **Code**:
  ```java
  package com.example.mvc.entity;

  import jakarta.persistence.Column;
  import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.Table;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Entity
  @Table(name = "employee")
  public class Employee {
      @Id
      @Column(name = "id")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @Column(name = "first_name")
      private String firstName;

      @Column(name = "last_name")
      private String lastName;

      @Column(name = "email")
      private String email;
  }
  ```

### 4.2 Employee Repository

- **File**: `src/main/java/com/example/mvc/repository/EmployeeRepository.java`.
- **Purpose**: Provides CRUD operations for the `Employee` entity.
- **Key Features**:
  - Extends `JpaRepository<Employee, Integer>`: Inherits methods like `findAll()`, `save()`, `findById()`, `deleteById()`.
  - `@Repository`: Marks as a Spring bean.
- **Code**:
  ```java
  package com.example.mvc.repository;

  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.stereotype.Repository;

  import com.example.mvc.entity.Employee;

  @Repository
  public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  }
  ```

### 4.3 Employee Service

- **Files**:
  - Interface: `src/main/java/com/example/mvc/service/EmployeeService.java`.
  - Implementation: `src/main/java/com/example/mvc/service/EmployeeServiceImplementation.java`.
- **Purpose**: Encapsulates business logic and delegates to the repository.
- **Key Features**:
  - **Interface**: Defines `save()`, `findById()`, `findAll()`, `deleteById()`.
  - **Implementation**:
    - `@Service`: Marks as a Spring service.
    - `@Autowired`: Injects `EmployeeRepository`.
    - `@Transactional`: Ensures atomicity for `save` and `deleteById`.
    - Calls corresponding repository methods.
- **Code**:
  - **Interface**:
    ```java
    package com.example.mvc.service;

    import java.util.List;
    import java.util.Optional;

    import com.example.mvc.entity.Employee;

    public interface EmployeeService {
        public Employee save(Employee employee);
        public Optional<Employee> findById(int id);
        public List<Employee> findAll();
        public void deleteById(int id);
    }
    ```
  - **Implementation**:
    ```java
    package com.example.mvc.service;

    import java.util.List;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.example.mvc.entity.Employee;
    import com.example.mvc.repository.EmployeeRepository;

    import jakarta.transaction.Transactional;

    @Service
    public class EmployeeServiceImplementation implements EmployeeService {
        @Autowired
        private EmployeeRepository employeeRepository;

        @Transactional
        @Override
        public Employee save(Employee employee) {
            return employeeRepository.save(employee);
        }

        @Override
        public Optional<Employee> findById(int id) {
            return employeeRepository.findById(id);
        }

        @Override
        public List<Employee> findAll() {
            return employeeRepository.findAll();
        }

        @Transactional
        @Override
        public void deleteById(int id) {
            employeeRepository.deleteById(id);
        }
    }
    ```

### 4.4 Employee Controller

- **File**: `src/main/java/com/example/mvc/controller/EmployeeController.java`.
- **Purpose**: Handles HTTP requests and maps them to Thymeleaf templates.
- **Key Features**:
  - `@Controller`, `@RequestMapping("/api")`: Defines web controller with `/api` prefix.
  - `@Autowired`: Injects `EmployeeService`.
  - Methods:
    - `getEmployees()`: Lists all employees.
    - `showForm()`: Displays add form.
    - `saveEmployee()`: Saves add or update.
    - `getEmployeeById()`: Shows employee details.
    - `updateEmployee()`: Displays update form.
    - `deleteEmployee()`: Deletes employee.
  - Uses `@ModelAttribute`, `@RequestParam`, and redirects.
- **Code**:
  ```java
  package com.example.mvc.controller;

  import java.util.List;
  import java.util.Optional;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;

  @Controller
  @RequestMapping("/api")
  public class EmployeeController {

      @Autowired
      private EmployeeService employeeService;

      // find All Employees
      @GetMapping("/employees")
      public String getEmployees(Model model) {
          List<Employee> employees = employeeService.findAll();
          model.addAttribute("employees", employees);
          return "employees-list";
      }

      @GetMapping("/form")
      public String showForm(Model model) {
          Employee employee = new Employee();
          model.addAttribute("employee", employee);
          return "employee-form";
      }

      // Save
      @PostMapping("/save")
      public String saveEmployee(@ModelAttribute("employee") Employee employee) {
          employeeService.save(employee);
          return "redirect:/api/employees";
      }

      @GetMapping("/view")
      public String getEmployeeById(@RequestParam("employeeID") int id, Model model) {
          Optional<Employee> employeeOpt = employeeService.findById(id);
          model.addAttribute("employee", employeeOpt.get());
          return "view-employee";
      }

      // Update
      @GetMapping("/update")
      public String updateEmployee(@RequestParam("employeeID") int id, Model model) {
          Optional<Employee> employee = employeeService.findById(id);
          model.addAttribute("employee", employee);
          return "employee-form";
      }

      // Delete Employee
      @GetMapping("/delete")
      public String deleteEmployee(@RequestParam("employeeID") int id) {
          employeeService.deleteById(id);
          return "redirect:/api/employees";
      }
  }
  ```

### 4.5 Thymeleaf Templates

- **Files**:
  - `src/main/resources/templates/employees-list.html`
  - `src/main/resources/templates/employee-form.html`
  - `src/main/resources/templates/view-employee.html`
- **Purpose**: Render dynamic HTML for the UI.
- **Key Features**:
  - **List Page**:
    - Displays employee table with ID, First Name, Last Name, Email, and Action buttons.
    - Uses `th:each` for iteration, `th:href` for links, and `onclick` for delete confirmation.
    - Bootstrap styling for responsiveness.
  - **Form Page**:
    - Handles add and update with a single form.
    - Uses `th:object`, `th:field`, and hidden `id` field.
    - Includes a back button to the list.
  - **View Page**:
    - Shows employee details in a table.
    - Includes a back button.
- **Code**: (See [3.7 Creating Thymeleaf Pages](#37-creating-thymeleaf-pages) for full code.)

---

## 5. What’s Next

### 5.1 Enhancements

Take EMS to the next level with these additions:

- **Spring Security**: Add login and registration pages with role-based access (e.g., admin vs. user).
- **Form Validation**: Use Bean Validation API (`@NotNull`, `@Email`) to validate inputs in `Employee`.
- **Search Functionality**: Add a search bar to filter employees by name or email.
- **Pagination**: Implement pagination for large employee lists using Spring Data’s `Pageable`.
- **REST API Integration**: Expose CRUD endpoints (e.g., `/api/employees`) alongside MVC.
- **Error Handling**: Add custom error pages for 404, 500 errors.
- **Logging**: Use SLF4J/Logback to log user actions (e.g., "Employee deleted: ID=5").
- **Unit Tests**: Write JUnit tests for `EmployeeService` and `EmployeeController`.

### 5.2 Learning Path

- **Deepen Spring Boot Knowledge**:
  - Learn Spring Security for authentication/authorization.
  - Explore Spring Boot Actuator for monitoring.
  - Study Spring Data REST for automatic REST APIs.
- **Advance to Microservices**:
  - Break EMS into microservices (e.g., Employee Service, UI Service).
  - Use Spring Cloud for service discovery and configuration.
- **Frontend Enhancements**:
  - Replace Thymeleaf with a frontend framework (e.g., React, Angular) consuming REST APIs.
  - Add client-side validation with JavaScript.
- **Resources**:
  - [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/3.4.4/reference/htmlsingle/)
  - [Baeldung Spring Tutorials](https://www.baeldung.com/spring-boot)
  - [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
  - [Bootstrap 5 Docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)

> [!TIP]
> Start by adding Spring Security to make EMS production-ready, then explore microservices for scalability!

---

Congratulations! 🎉 You’ve built a fully functional Employee Management System with Spring Boot MVC. Clone this repository, run it on `http://localhost:1803/api/employees`, and enhance it with your own features. Happy coding! 💻