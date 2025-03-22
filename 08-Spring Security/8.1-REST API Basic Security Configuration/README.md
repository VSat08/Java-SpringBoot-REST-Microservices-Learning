# 8.1 - REST API Basic Security Configuration

## Introduction

Welcome to **8.1 - REST API Basic Security Configuration**

In this section, we’ll dive into configuring basic security for a Spring Boot REST API using Spring Security. Building on [08](#08-spring-security), where we secured endpoints with a default login, we’ll now define specific users, assign roles (e.g., `EMPLOYEE`, `MANAGER`, `ADMIN`), and restrict access to endpoints based on roles and HTTP methods. You’ll use in-memory authentication to set up users like John, Mary, and Susan, then test these restrictions with Postman and a browser. Before starting, we’ll ensure the database connection is correctly configured. Perfect for beginners ready to control who does what in their app! 🔐

---

## Table of Contents

1. [What Is Basic Security Configuration?](#1-what-is-basic-security-configuration)
   - [1.1 Overview](#11-overview)
   - [1.2 Users, Passwords, and Roles](#12-users-passwords-and-roles)
   - [1.3 Restricting Access with Filter Chains](#13-restricting-access-with-filter-chains)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Defining Users and Roles In-Memory](#21-defining-users-and-roles-in-memory)
   - [2.2 Setting Access Rules by Role and HTTP Method](#22-setting-access-rules-by-role-and-http-method)
   - [2.3 Configuring Basic Authentication and CSRF](#23-configuring-basic-authentication-and-csrf)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.0 Checking Database Configuration](#30-checking-database-configuration)
   - [3.1 Setting Up the Security Config Class](#31-setting-up-the-security-config-class)
   - [3.2 Enhancing the REST Controller](#32-enhancing-the-rest-controller)
   - [3.3 Testing with Browser and Postman](#33-testing-with-browser-and-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Basic Security Configuration?

### 1.1 Overview

- **Goal**: Secure a REST API (e.g., employee management system) by defining users, their roles, and access privileges using Spring Security.
- **How**: Create a configuration class (`DemoSecurityConfig`) with in-memory users and a security filter chain to enforce role-based access.
- **Why**: Ensures only authorized users (e.g., employees, managers, admins) can perform specific actions—no unrestricted access!

#### Real-World Analogy

Think of this as setting up a company directory: employees can view it, managers can edit it, and only admins can delete entries!

### 1.2 Users, Passwords, and Roles

- **Users**: Three predefined users for our employee management REST API.
- **Details**: Stored in-memory (temporary, lives as long as the app runs—not in a database yet).

| Username | Password      | Roles                | Description                     |
|----------|---------------|----------------------|---------------------------------|
| `john`   | `john@123`    | `EMPLOYEE`           | Can only view employee data     |
| `mary`   | `mary@123`    | `EMPLOYEE`, `MANAGER`| Can view, create, and update    |
| `susan`  | `susan@123`   | `EMPLOYEE`, `MANAGER`, `ADMIN` | Full access: view, create, update, delete |

- **Password Format**: Spring Security uses `{id}password` (e.g., `{noop}test123` for plain text, `{bcrypt}encoded` for encrypted). Here, we use `{noop}` (no operation = plain text).
- **Roles**: Define privileges—`EMPLOYEE` (view), `MANAGER` (edit), `ADMIN` (delete).

#### Why In-Memory?

- Temporary storage in the app—great for testing before moving to a database (see [8.2](#82-spring-security-user-accounts-stored-db)).

### 1.3 Restricting Access with Filter Chains

- **What**: A security filter chain defines which roles can access which endpoints and HTTP methods.
- **Endpoints**: Our REST API uses `/api/employees` (list or create) and `/api/employees/{id}` (specific employee actions).
- **Rules**:

| Role       | HTTP Method | Endpoint              | Privilege                     |
|------------|-------------|-----------------------|-------------------------------|
| `EMPLOYEE` | GET         | `/api/employees`      | View all employees            |
| `EMPLOYEE` | GET         | `/api/employees/{id}` | View specific employee by ID  |
| `MANAGER`  | POST        | `/api/employees`      | Create new employee           |
| `MANAGER`  | PUT         | `/api/employees`      | Update existing employee      |
| `ADMIN`    | DELETE      | `/api/employees/{id}` | Delete employee by ID         |

- **Key Point**: Same endpoint (e.g., `/api/employees`) behaves differently based on the HTTP method and role—GET for `EMPLOYEE`, POST/PUT for `MANAGER`.

>[!NOTE]
>Filter chains = traffic cops—directing requests based on role and method!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                | Meaning                                      | Example                       |
|---------------------|----------------------------------------------|-------------------------------|
| **In-Memory**       | Data stored in app memory, not persistent    | `john` with `EMPLOYEE` role   |
| **`@Configuration`** | Marks a class as a Spring config source      | `DemoSecurityConfig`          |
| **UserDetailsManager** | Manages user details (e.g., in-memory)     | Stores `john`, `mary`, `susan`|
| **Filter Chain**    | Rules for securing endpoints/methods         | GET `/api/employees` → `EMPLOYEE` |
| **Basic Authentication** | Username/password login mechanism         | `john`/`john@123` in Postman  |
| **CSRF**            | Cross-Site Request Forgery protection        | Disabled for REST APIs        |
| **`requestMatchers`** | Matches HTTP methods and paths             | `HttpMethod.GET`, `/api/**`   |
| **`hasRole`**       | Restricts access to a specific role          | `hasRole("MANAGER")`          |

---

## 2. Learning Roadmap

Your path to mastering basic security configuration!

### 2.1 Defining Users and Roles In-Memory

- **What**: Set up users (`john`, `mary`, `susan`) with passwords and roles in a config class.
- **Goal**: Use `InMemoryUserDetailsManager` to authenticate users without a database.

### 2.2 Setting Access Rules by Role and HTTP Method

- **What**: Configure a filter chain to restrict `/api/employees` and `/api/employees/{id}` based on roles and HTTP methods.
- **Goal**: Ensure `EMPLOYEE` can only GET, `MANAGER` can POST/PUT, and `ADMIN` can DELETE.

### 2.3 Configuring Basic Authentication and CSRF

- **What**: Enable basic authentication (username/password) and disable CSRF for REST APIs.
- **Goal**: Simplify login for REST clients (e.g., Postman) and remove unnecessary web protections.

---

## 3. Practical Demonstration

Let’s secure an employee management REST API (`rest-api-spring-demo`) from the transcript, starting with database configuration!

### 3.0 Checking Database Configuration

- **Purpose**: Ensure the database connection in `application.properties` is correct before running the Spring app—critical for JPA-based data access.
- **File**: `src/main/resources/application.properties`.
- **Code**:
  ```properties
  # Database connectivity
  spring.datasource.url=jdbc:mysql://localhost:3306/empdir
  spring.datasource.username=root
  spring.datasource.password=root

  # Spring Security default user (commented out - using in-memory instead)
  #spring.security.user.name=scott
  #spring.security.user.password=test123
  ```
- **Steps**:
  1. **Open MySQL**: Log in (e.g., via MySQL Command Line Client or Workbench).
  2. **Verify Credentials**: Check your MySQL username (e.g., `root`) and password (e.g., `root` or your custom one).
     - Command: `SELECT USER();` → Shows current user (e.g., `root@localhost`).
     - If password differs, update it in MySQL: `ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';`.
  3. **Update `application.properties`**: Replace `spring.datasource.password` with your actual MySQL password (e.g., `root` → `newpassword`).
  4. **Check Database**: Ensure `empdir` exists (`SHOW DATABASES;`)—create if missing: `CREATE DATABASE empdir;`.
- **Why**: Incorrect credentials cause "Failed to configure a DataSource" errors—always verify before starting!

>[!TIP]
>Double-check `spring.datasource.password`—it’s your MySQL key to the app!

### 3.1 Setting Up the Security Config Class

- **Purpose**: Define users and access rules in a Spring Security configuration class.
- **File**: `com.example.restdemo.security.DemoSecurityConfig.java`.
- **Code**:
  ```java
  package com.example.restdemo.security;

  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Bean;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.core.userdetails.User;
  import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.provisioning.InMemoryUserDetailsManager;
  import org.springframework.security.web.SecurityFilterChain;

  @Configuration
  public class DemoSecurityConfig {

      // Define users and roles in-memory
      @Bean
      public InMemoryUserDetailsManager userDetailsManager() {
          UserDetails john = User.builder()
                  .username("john")
                  .password("{noop}john@123")
                  .roles("EMPLOYEE")
                  .build();

          UserDetails mary = User.builder()
                  .username("mary")
                  .password("{noop}mary@123")
                  .roles("EMPLOYEE", "MANAGER")
                  .build();

          UserDetails susan = User.builder()
                  .username("susan") // Fixed typo from transcript ("mary" was incorrect)
                  .password("{noop}susan@123")
                  .roles("EMPLOYEE", "MANAGER", "ADMIN")
                  .build();

          return new InMemoryUserDetailsManager(john, mary, susan);
      }

      // Configure security filter chain
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http.authorizedHttpRequests(configure ->
              configure
                  // EMPLOYEE: GET all or specific employees
                  .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                  .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                  // MANAGER: POST and PUT employees
                  .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                  .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                  // ADMIN: DELETE employees
                  .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
          )
          .httpBasic(customizer -> customizer.withDefaults()) // Basic authentication
          .csrf(csrf -> csrf.disable()); // Disable CSRF for REST API

          return http.build();
      }
  }
  ```
- **Details**:
  - **`userDetailsManager`**:
    - Creates `john` (EMPLOYEE), `mary` (EMPLOYEE, MANAGER), `susan` (EMPLOYEE, MANAGER, ADMIN).
    - `{noop}`: Plain text passwords (e.g., `john@123`)—no encryption yet (see [8.3](#83-spring-security-password-encryption)).
    - Returns `InMemoryUserDetailsManager` with all users.
  - **`filterChain`**:
    - `requestMatchers`: Matches HTTP methods and paths (e.g., `GET /api/employees` → `EMPLOYEE`).
    - `/**`: Wildcard for subpaths (e.g., `/api/employees/1`).
    - `httpBasic`: Enables basic authentication (username/password).
    - `csrf.disable()`: Disables CSRF—REST APIs are stateless, no need for form tokens (unlike web apps with GET).

>[!TIP]
>`{noop}` = plain text for now—later, we’ll encrypt with `{bcrypt}`!

### 3.2 Enhancing the REST Controller

- **Purpose**: Define REST endpoints for employee CRUD operations.
- **File**: `com.example.restdemo.controller.EmployeeController.java`.
- **Code**:
  ```java
  package com.example.restdemo.controller;

  import com.example.entity.Employee;
  import com.example.service.EmployeeService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.*;

  @RestController
  @RequestMapping("/api")
  public class EmployeeController {

      private EmployeeService employeeService;

      @Autowired
      public EmployeeController(EmployeeService employeeService) {
          this.employeeService = employeeService;
      }

      @GetMapping("/employees")
      public List<Employee> findAll() {
          return employeeService.findAll();
      }

      @GetMapping("/employees/{employeeId}")
      public Employee findById(@PathVariable int employeeId) {
          return employeeService.findById(employeeId);
      }

      @PostMapping("/employees")
      public Employee addEmployee(@RequestBody Employee employee) {
          employee.setId(0); // Ensure new ID is generated
          return employeeService.save(employee);
      }

      @PutMapping("/employees")
      public Employee updateEmployee(@RequestBody Employee employee) {
          return employeeService.save(employee); // ID must be included
      }

      @DeleteMapping("/employees/{employeeId}")
      public String deleteEmployee(@PathVariable int employeeId) {
          employeeService.deleteById(employeeId);
          return "Employee with ID " + employeeId + " deleted";
      }
  }
  ```
- **Details**:
  - `@RequestMapping("/api")`: Base path for all endpoints (e.g., `/api/employees`).
  - Uses `EmployeeService` (with `JpaRepository`) for CRUD—assumes `Employee` entity and repository exist (from prior sessions).
  - Endpoints match filter chain rules (e.g., `POST /api/employees` → `MANAGER`).

### 3.3 Testing with Browser and Postman

- **Setup**: 
  - Add `spring-boot-starter-security` to `pom.xml` (from [08](#08-spring-security)):
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    ```
  - Verify `application.properties` (from 3.0) before running.
  - Run `rest-api-spring-demo` (`Tomcat started on port(s): 8080`).
  - Assume `employee` table has 4 records (e.g., IDs 1-4) + new ones added during tests.

#### Browser Tests (GET Only)

- **URL**: `http://localhost:8080/api/employees`.
  - **Result**: Login prompt (Spring Security default form).
  - **Login**: `john`/`john@123` → JSON list of 5 employees (after tests below).
  - **Why**: `EMPLOYEE` role allows GET—John can view all.
- **URL**: `http://localhost:8080/api/employees/1`.
  - **Login**: `john`/`john@123` → JSON of employee ID 1.
  - **Why**: `EMPLOYEE` role allows GET with `/**`.

#### Postman Tests (All Methods)

1. **GET All Employees**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **No Auth**: 401 Unauthorized.
   - **Basic Auth**: `john`/`john@123` → 200 OK, JSON list (e.g., 5 employees after tests).
   - **Why**: `EMPLOYEE` role allows GET.

2. **GET Specific Employee**:
   - **Request**: GET `http://localhost:8080/api/employees/1`.
   - **Basic Auth**: `john`/`john@123` → 200 OK, JSON of employee ID 1.
   - **Why**: Same as above—`/**` covers subpaths.

3. **POST New Employee (John)**:
   - **Request**: POST `http://localhost:8080/api/employees`.
   - **Body** (JSON): `{"firstName": "Brad", "lastName": "Pitt", "email": "brad@pitt.com"}`.
   - **Basic Auth**: `john`/`john@123` → 403 Forbidden.
   - **Why**: `EMPLOYEE` can’t POST—only `MANAGER` can.

4. **POST New Employee (Mary)**:
   - **Request**: Same as above.
   - **Basic Auth**: `mary`/`mary@123` → 201 Created, JSON with ID 12 (e.g., `{"id": 12, "firstName": "Brad", ...}`).
   - **Why**: `MANAGER` role allows POST—Mary creates "Brad Pitt".

5. **PUT Update Employee (Mary)**:
   - **Request**: PUT `http://localhost:8080/api/employees`.
   - **Body**: `{"id": 12, "firstName": "Brad", "lastName": "Pitt", "email": "brad@pitt.com"}`.
   - **Basic Auth**: `mary`/`mary@123` → 200 OK, updated JSON (e.g., capitalized names).
   - **Why**: `MANAGER` role allows PUT—Mary updates ID 12.

6. **DELETE Employee (Mary)**:
   - **Request**: DELETE `http://localhost:8080/api/employees/12`.
   - **Basic Auth**: `mary`/`mary@123` → 403 Forbidden.
   - **Why**: `MANAGER` can’t DELETE—only `ADMIN` can.

7. **DELETE Employee (Susan)**:
   - **Request**: Same as above.
   - **Basic Auth**: `susan`/`susan@123` → 200 OK, `"Employee with ID 12 deleted"`.
   - **Why**: `ADMIN` role allows DELETE—Susan removes ID 12.

8. **POST and GET (Susan)**:
   - **POST**: Same body → 201 Created, ID 13 ("Brad Pitt" again).
   - **GET**: `http://localhost:8080/api/employees/13` → 200 OK, JSON of ID 13.
   - **Why**: `MANAGER` (and `EMPLOYEE`) roles allow POST and GET—Susan has all privileges.

>[!NOTE]
>403 Forbidden = "You can’t do that!"—role-based access works as designed!

---

## 4. What’s Next

- **Next Session**: **8.2 - Spring Security User Accounts Stored DB**—move users, passwords, and roles to a database for persistent storage.

>[!TIP]
>You’ve secured your API with roles—next, make it permanent with a database!

---

