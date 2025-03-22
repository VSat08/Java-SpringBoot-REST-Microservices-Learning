
## Introduction

Welcome to **8.4 - Spring Security Custom Tables**

In this section, we’ll move beyond Spring Security’s default `users` and `authorities` tables (from [8.2](#82-spring-security-user-accounts-stored-db) and [8.3](#83-spring-security-password-encryption)) to use custom tables (`members` and `roles`) for storing user accounts in our employee management REST API. We’ll create these tables, populate them with encrypted passwords, and configure Spring Security with custom SQL queries—all while keeping our endpoint security intact. Perfect for beginners ready to tailor security to their own database design! 🛠️

---

## Table of Contents

1. [What Are Custom Tables in Spring Security?](#1-what-are-custom-tables-in-spring-security)
   - [1.1 Overview](#11-overview)
   - [1.2 Default vs. Custom Tables](#12-default-vs-custom-tables)
   - [1.3 Configuring Custom Queries](#13-configuring-custom-queries)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Creating Custom Tables](#21-creating-custom-tables)
   - [2.2 Populating Custom Tables](#22-populating-custom-tables)
   - [2.3 Updating Security Configuration](#23-updating-security-configuration)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.0 Checking Database Configuration](#30-checking-database-configuration)
   - [3.1 Creating and Populating Custom Tables](#31-creating-and-populating-custom-tables)
   - [3.2 Configuring Spring Security with Custom Queries](#32-configuring-spring-security-with-custom-queries)
   - [3.3 Testing with Browser and Postman](#33-testing-with-browser-and-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Are Custom Tables in Spring Security?

### 1.1 Overview

- **Goal**: Replace Spring Security’s default `users` and `authorities` tables with custom tables (`members` and `roles`) for user account storage.
- **How**: Create custom tables in MySQL, populate them with data, and configure `JdbcUserDetailsManager` with custom SQL queries.
- **Why**: Allows flexibility to match your app’s database schema—default tables aren’t always a fit.

#### Real-World Analogy

Think of this as swapping a standard filing cabinet (`users`/`authorities`) for a custom-designed one (`members`/`roles`) tailored to your office needs!

### 1.2 Default vs. Custom Tables

- **Default Tables (8.2 & 8.3)**:
  - `users`: `username` (PK), `password`, `enabled`.
  - `authorities`: `username` (FK to `users`), `authority`, unique key (`username`, `authority`).
- **Custom Tables (This Session)**:
  - `members`: `user_id` (PK), `pwd`, `active`.
  - `roles`: `user_id` (FK to `members`), `role`, unique key (`user_id`, `role`).
- **Users and Roles**: Same trio from prior sessions:

| User ID  | Password (Encrypted) Example                                      | Roles                | Description                     |
|----------|-------------------------------------------------------------------|----------------------|---------------------------------|
| `john`   | `{bcrypt}$2a$10$XURP2fDe7xEVobLhN03Mde8k5HEG8v25auW.dQb2sNak5n3bDJmEi` | `EMPLOYEE`           | Can only view employee data     |
| `mary`   | `{bcrypt}$2a$10$5tKZhHa3Px9Vv5fVFRN/deGfE8lR2b6WPU8jL8a9v5Z5n7mJcX5bW` | `EMPLOYEE`, `MANAGER`| Can view, create, and update    |
| `susan`  | `{bcrypt}$2a$10$8JkQdXhM9nV5L8fKXyQwdeT2b6R8v9W5nL5jK8aQv7mJcX5bW9nV5` | `EMPLOYEE`, `MANAGER`, `ADMIN` | Full access: view, create, update, delete |

- **Key Difference**: Table and column names are custom—no match to defaults—but structure and purpose align (user info + roles).

### 1.3 Configuring Custom Queries

- **What**: Tell Spring Security how to fetch users and roles from custom tables.
- **How**: Use `JdbcUserDetailsManager` methods:
  - `setUsersByUsernameQuery`: Query for user details (e.g., `SELECT user_id, pwd, active FROM members WHERE user_id = ?`).
  - `setAuthoritiesByUsernameQuery`: Query for roles (e.g., `SELECT user_id, role FROM roles WHERE user_id = ?`).
- **Placeholder (`?`)**: Replaced by the username from the login form (e.g., `john`) during authentication.
- **Process**:
  1. User enters `john`/`john@123`.
  2. Spring runs custom queries with `john` in `?`.
  3. Fetches encrypted password and roles, verifies, and grants access based on roles.

>[!NOTE]
>`?` = dynamic slot—Spring fills it with the login username!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                       |
|-----------------------|----------------------------------------------|-------------------------------|
| **Custom Tables**     | User-defined tables instead of defaults      | `members`, `roles`            |
| **`user_id`**         | Custom primary key for users                 | `john`                        |
| **`pwd`**             | Custom column for encrypted password         | `{bcrypt}$2a$10$...`          |
| **`active`**          | Custom enabled flag (1 = active)             | `1`                           |
| **`role`**            | Custom column for roles                      | `ROLE_EMPLOYEE`               |
| **Custom Query**      | SQL to map custom tables to Spring Security  | `SELECT user_id, pwd, active FROM members WHERE user_id = ?` |
| **Foreign Key**       | Links tables for data consistency            | `user_id` in `roles` → `members` |

---

## 2. Learning Roadmap

Your path to mastering custom tables!

### 2.1 Creating Custom Tables

- **What**: Define `members` and `roles` tables in MySQL with custom names and columns.
- **Goal**: Replace `users` and `authorities` with a tailored schema.

### 2.2 Populating Custom Tables

- **What**: Copy data from `users` and `authorities` into `members` and `roles`.
- **Goal**: Migrate existing users and roles to the new tables.

### 2.3 Updating Security Configuration

- **What**: Modify `DemoSecurityConfig` to use custom queries with `JdbcUserDetailsManager`.
- **Goal**: Enable Spring Security to authenticate using `members` and `roles`.

---

## 3. Practical Demonstration

Let’s customize `rest-api-spring-security-demo-db-custom-tables`!

### 3.0 Checking Database Configuration

- **Purpose**: Verify database connection before proceeding—critical for JDBC and JPA.
- **File**: `src/main/resources/application.properties`.
- **Code**:
  ```properties
  # Database connectivity
  spring.datasource.url=jdbc:mysql://localhost:3306/empdir
  spring.datasource.username=root
  spring.datasource.password=root
  ```
- **Steps**:
  1. **Open MySQL**: Log in (e.g., via MySQL Command Line Client or Workbench).
  2. **Verify Credentials**: Check your MySQL username (e.g., `root`) and password (e.g., `root` or custom).
     - Command: `SELECT USER();` → Shows current user (e.g., `root@localhost`).
     - Update if needed: `ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';`.
  3. **Update `application.properties`**: Replace `spring.datasource.password` with your MySQL password (e.g., `root` → `newpassword`).
  4. **Check Database**: Ensure `empdir` exists (`SHOW DATABASES;`)—create if missing: `CREATE DATABASE empdir;`.
- **Why**: Prevents "Failed to configure a DataSource" errors—always check first!

>[!TIP]
>`spring.datasource.password` is your key—mismatch means no access!

### 3.1 Creating and Populating Custom Tables

- **Purpose**: Set up `members` and `roles` tables and migrate data from `users` and `authorities`.
- **SQL Script**:
  ```sql
  -- Create members table
  CREATE TABLE members (
      user_id VARCHAR(50) NOT NULL PRIMARY KEY,
      pwd VARCHAR(68) NOT NULL,
      active TINYINT NOT NULL
  );

  -- Create roles table
  CREATE TABLE roles (
      user_id VARCHAR(50) NOT NULL,
      role VARCHAR(50) NOT NULL,
      CONSTRAINT fk_roles_members FOREIGN KEY (user_id) REFERENCES members(user_id),
      UNIQUE KEY (user_id, role)
  );

  -- Populate members from users
  INSERT INTO members SELECT * FROM users;

  -- Populate roles from authorities
  INSERT INTO roles SELECT * FROM authorities;
  ```
- **Details**:
  - **`members`**:
    - `user_id`: PK, matches `username` from `users`.
    - `pwd`: 68 chars for Bcrypt passwords (from [8.3](#83-spring-security-password-encryption)).
    - `active`: 1 = active, 0 = inactive.
  - **`roles`**:
    - `user_id`: FK to `members(user_id)`.
    - `role`: e.g., `ROLE_EMPLOYEE`.
    - Unique key: No duplicate roles per user.
  - **Data Migration**:
    - Copies `john`, `mary`, `susan` (with Bcrypt passwords) from `users`.
    - Copies 6 roles (e.g., `john` → `ROLE_EMPLOYEE`) from `authorities`.
- **Verify**:
  - `SHOW TABLES;` → `employee`, `members`, `roles` (plus `users`, `authorities` if not dropped).
  - `SELECT * FROM members;` → 3 rows.
  - `SELECT * FROM roles;` → 6 rows.

>[!NOTE]
>Run this script before updating the config—tables must exist!

### 3.2 Configuring Spring Security with Custom Queries

- **Purpose**: Update `DemoSecurityConfig` to use `members` and `roles` instead of defaults.
- **Dependencies**: Same as [8.3](#83-spring-security-password-encryption) (`spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `mysql-connector-java`).
- **File**: `com.example.security.DemoSecurityConfig.java`.
- **Code**:
  ```java
  package com.example.security;

  import org.springframework.context.annotation.Configuration;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.provisioning.JdbcUserDetailsManager;
  import org.springframework.security.web.SecurityFilterChain;
  import org.springframework.context.annotation.Bean;
  import javax.sql.DataSource;

  @Configuration
  public class DemoSecurityConfig {

      // Configure JDBC user details manager with custom queries
      @Bean
      public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
          JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
          manager.setUsersByUsernameQuery("SELECT user_id, pwd, active FROM members WHERE user_id = ?");
          manager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id = ?");
          return manager;
      }

      // Security filter chain (same as previous sessions)
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http.authorizedHttpRequests(configure ->
              configure
                  .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                  .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                  .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                  .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                  .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
          )
          .httpBasic(customizer -> customizer.withDefaults())
          .csrf(csrf -> csrf.disable());

          return http.build();
      }
  }
  ```
- **Details**:
  - **`userDetailsManager`**:
    - Injects `DataSource` (from `application.properties`).
    - `setUsersByUsernameQuery`: Maps `user_id`, `pwd`, `active` to Spring’s expected `username`, `password`, `enabled`.
    - `setAuthoritiesByUsernameQuery`: Maps `user_id`, `role` to `username`, `authority`.
    - `?`: Placeholder for login username (e.g., `mary`).
  - **`filterChain`**: Unchanged—same role-based restrictions.
  - **Controller**: `EmployeeController` (from prior sessions) remains identical.

>[!TIP]
>Custom queries = your bridge from custom tables to Spring Security!

### 3.3 Testing with Browser and Postman

- **Setup**: 
  - Verify `application.properties` (3.0), run SQL script (3.1), and update Maven project.
  - Run `rest-api-spring-security-demo-db-custom-tables` (`Tomcat started on port(s): 8080`).
  - Assume `employee` table has 6 records (IDs 1-20, adjusted during tests).

#### Browser Tests (GET Only)

- **URL**: `http://localhost:8080/api/employees`.
  - **Login**: `mary`/`mary@123` → JSON list of 6 employees.
  - **Why**: `ROLE_EMPLOYEE` from `roles` allows GET—custom query fetches data.
- **URL**: `http://localhost:8080/api/employees/15`.
  - **Login**: `mary`/`mary@123` → JSON of employee ID 15 (e.g., "Emma Stone").
  - **Why**: Same role, `/**` works.

#### Postman Tests (All Methods)

1. **GET All Employees (John)**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **Basic Auth**: `john`/`john@123` → 200 OK, JSON list (6 employees).
   - **Why**: `ROLE_EMPLOYEE` from `roles` allows GET.

2. **POST New Employee (John)**:
   - **Request**: POST `http://localhost:8080/api/employees`.
   - **Body**: `{"firstName": "Nicolas", "lastName": "Junior", "email": "nicolas@jr.com"}`.
   - **Basic Auth**: `john`/`john@123` → 403 Forbidden.
   - **Why**: `ROLE_EMPLOYEE` can’t POST—needs `ROLE_MANAGER`.

3. **POST New Employee (Susan)**:
   - **Request**: Same as above.
   - **Basic Auth**: `susan`/`susan@123` → 201 Created, JSON with ID 19.
   - **Why**: `ROLE_MANAGER` from `roles` allows POST.

4. **DELETE Employee (Susan)**:
   - **Request**: DELETE `http://localhost:8080/api/employees/19`.
   - **Basic Auth**: `susan`/`susan@123` → 200 OK, `"Employee with ID 19 deleted"`.
   - **Why**: `ROLE_ADMIN` allows DELETE.

5. **POST New Employee (Susan)**:
   - **Request**: Same body as 3.
   - **Basic Auth**: `susan`/`susan@123` → 201 Created, ID 20.
   - **Why**: `ROLE_MANAGER` works.

6. **PUT Update Employee (Mary)**:
   - **Request**: PUT `http://localhost:8080/api/employees`.
   - **Body**: `{"id": 20, "firstName": "Nicolas", "lastName": "Junior", "email": "nicolas.junior@cage.net"}`.
   - **Basic Auth**: `mary`/`mary@123` → 200 OK, updated JSON (new email).
   - **Why**: `ROLE_MANAGER` allows PUT.

7. **GET Updated List (Mary)**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **Basic Auth**: `mary`/`mary@123` → 200 OK, JSON list (6 employees, updated ID 20).
   - **Why**: `ROLE_EMPLOYEE` allows GET.

- **Authentication Flow**:
  1. `mary`/`mary@123` entered.
  2. Queries run: `SELECT user_id, pwd, active FROM members WHERE user_id = 'mary'` and `SELECT user_id, role FROM roles WHERE user_id = 'mary'`.
  3. Bcrypt hashes `mary@123`, matches DB password, fetches roles (`ROLE_EMPLOYEE`, `ROLE_MANAGER`).
  4. Access granted per filter chain.

>[!NOTE]
>Custom tables, same security—Spring adapts to your schema!

---

## 4. What’s Next

- **Next Session**: **Spring MVC with Security**—apply Spring Security to MVC apps using Thymeleaf templates.

>[!TIP]
>You’ve customized your tables—next, secure a web frontend!

---
