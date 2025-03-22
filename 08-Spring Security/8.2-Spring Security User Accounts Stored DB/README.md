# 8.2 - Spring Security User Accounts Stored DB

## Introduction

Welcome to **8.2 - Spring Security User Accounts Stored DB**

In this section, we’ll transition from in-memory user storage (from [8.1](#81-rest-api-basic-security-configuration)) to storing user accounts in a database using Spring Security. We’ll use MySQL with JPA and Hibernate to persist users (`john`, `mary`, `susan`) and their roles (`EMPLOYEE`, `MANAGER`, `ADMIN`) in predefined tables (`users` and `authorities`). You’ll configure JDBC authentication, set up database schemas, and test endpoint security—perfect for beginners ready to make their app’s security persistent! 🗄️

---

## Table of Contents

1. [What Is User Accounts Stored in DB?](#1-what-is-user-accounts-stored-in-db)
   - [1.1 Overview](#11-overview)
   - [1.2 Users, Passwords, and Roles in Database](#12-users-passwords-and-roles-in-database)
   - [1.3 Spring Security’s Default Schema](#13-spring-securitys-default-schema)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Setting Up Database Tables](#21-setting-up-database-tables)
   - [2.2 Configuring JDBC Authentication](#22-configuring-jdbc-authentication)
   - [2.3 Testing Persistent Security](#23-testing-persistent-security)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.0 Checking Database Configuration](#30-checking-database-configuration)
   - [3.1 Creating Users and Authorities Tables](#31-creating-users-and-authorities-tables)
   - [3.2 Updating Security Configuration](#32-updating-security-configuration)
   - [3.3 Testing with Browser and Postman](#33-testing-with-browser-and-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is User Accounts Stored in DB?

### 1.1 Overview

- **Goal**: Move user accounts (usernames, passwords, roles) from in-memory storage to a persistent database for the employee management REST API.
- **How**: Use Spring Security’s JDBC authentication with predefined `users` and `authorities` tables, connected via a `DataSource`.
- **Why**: Persistence ensures user data survives app restarts—unlike in-memory, which resets each time.

#### Real-World Analogy

Think of this as upgrading from a temporary guest list (in-memory) to a permanent employee roster in a database!

### 1.2 Users, Passwords, and Roles in Database

- **Users**: Same three users from [8.1](#81-rest-api-basic-security-configuration), now stored in MySQL.
- **Details**: Persistent in `empdir` database, replacing hardcoded `InMemoryUserDetailsManager`.

| Username | Password      | Roles                | Description                     |
|----------|---------------|----------------------|---------------------------------|
| `john`   | `john@123`    | `EMPLOYEE`           | Can only view employee data     |
| `mary`   | `mary@123`    | `EMPLOYEE`, `MANAGER`| Can view, create, and update    |
| `susan`  | `susan@123`   | `EMPLOYEE`, `MANAGER`, `ADMIN` | Full access: view, create, update, delete |

- **Password Format**: `{noop}password` (plain text for now—encryption comes in [8.3](#83-spring-security-password-encryption)).
- **Roles**: Stored as `ROLE_<name>` (e.g., `ROLE_EMPLOYEE`) in the database—Spring Security’s convention.

### 1.3 Spring Security’s Default Schema

- **Tables**: Two predefined tables—`users` and `authorities`—required by Spring Security.
- **Users Table**:
  - Stores username, password, and enabled status (1 = active, 0 = inactive).
  - Schema:

| Column     | Type         | Constraints      | Description                  |
|------------|--------------|------------------|------------------------------|
| `username` | VARCHAR(50)  | PRIMARY KEY, NOT NULL | Unique username (e.g., `john`) |
| `password` | VARCHAR(50)  | NOT NULL         | Password (e.g., `{noop}john@123`) |
| `enabled`  | TINYINT      | NOT NULL         | 1 = active, 0 = inactive    |

- **Authorities Table**:
  - Stores roles linked to usernames.
  - Schema:

| Column      | Type         | Constraints                     | Description                  |
|-------------|--------------|---------------------------------|------------------------------|
| `username`  | VARCHAR(50)  | NOT NULL, FOREIGN KEY to `users(username)` | Links to `users` table |
| `authority` | VARCHAR(50)  | NOT NULL                        | Role (e.g., `ROLE_EMPLOYEE`) |
| UNIQUE KEY  | -            | (`username`, `authority`)       | Ensures no duplicate roles   |

- **Relationship**: `username` in `authorities` is a foreign key referencing `users(username)`—roles only for existing users.

>[!NOTE]
>`ROLE_` prefix is mandatory—Spring Security expects it in the database!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                       |
|-----------------------|----------------------------------------------|-------------------------------|
| **JDBC**             | Java Database Connectivity—connects app to DB | Links Spring to MySQL         |
| **`DataSource`**     | Bean with DB connection details              | URL, username, password       |
| **JdbcUserDetailsManager** | Manages users from DB tables           | Replaces in-memory manager    |
| **`users` Table**    | Stores username, password, enabled           | `john`, `{noop}john@123`, 1   |
| **`authorities` Table** | Stores roles linked to users             | `john`, `ROLE_EMPLOYEE`       |
| **Foreign Key**      | Links tables—ensures data consistency        | `username` in `authorities`   |
| **Persistent**       | Data survives app restarts                   | Stored in MySQL, not memory   |

---

## 2. Learning Roadmap

Your path to mastering database-stored user accounts!

### 2.1 Setting Up Database Tables

- **What**: Create `users` and `authorities` tables in MySQL with Spring Security’s schema.
- **Goal**: Store users and roles persistently in `empdir` database.

### 2.2 Configuring JDBC Authentication

- **What**: Update `DemoSecurityConfig` to use `JdbcUserDetailsManager` with a `DataSource`.
- **Goal**: Fetch user details from the database instead of hardcoding.

### 2.3 Testing Persistent Security

- **What**: Test endpoints with browser (GET) and Postman (all methods) using DB-stored users.
- **Goal**: Verify role-based access works with persistent data.

---

## 3. Practical Demonstration

Let’s enhance `rest-api-spring-seq-demo-with-db` to use database-stored user accounts!

### 3.0 Checking Database Configuration

- **Purpose**: Ensure the database connection in `application.properties` is correct before running—critical for JPA and JDBC.
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
     - Update password if needed: `ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';`.
  3. **Update `application.properties`**: Replace `spring.datasource.password` with your actual MySQL password (e.g., `root` → `newpassword`).
  4. **Check Database**: Ensure `empdir` exists (`SHOW DATABASES;`)—create if missing: `CREATE DATABASE empdir;`.
- **Why**: Incorrect settings cause "Failed to configure a DataSource" errors—verify first!

>[!WARNING]
>Always check `spring.datasource.password`—it’s your app’s lifeline to MySQL!

### 3.1 Creating Users and Authorities Tables

- **Purpose**: Set up `users` and `authorities` tables in `empdir` with data for `john`, `mary`, `susan`.
- **SQL Script**:
  ```sql
  -- Create users table
  CREATE TABLE users (
      username VARCHAR(50) NOT NULL PRIMARY KEY,
      password VARCHAR(50) NOT NULL,
      enabled TINYINT NOT NULL
  );

  -- Create authorities table
  CREATE TABLE authorities (
      username VARCHAR(50) NOT NULL,
      authority VARCHAR(50) NOT NULL,
      CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username),
      UNIQUE KEY (username, authority)
  );

  -- Insert users
  INSERT INTO users (username, password, enabled) VALUES
      ('john', '{noop}john@123', 1),
      ('mary', '{noop}mary@123', 1),
      ('susan', '{noop}susan@123', 1);

  -- Insert authorities (roles)
  INSERT INTO authorities (username, authority) VALUES
      ('john', 'ROLE_EMPLOYEE'),
      ('mary', 'ROLE_EMPLOYEE'),
      ('mary', 'ROLE_MANAGER'),
      ('susan', 'ROLE_EMPLOYEE'),
      ('susan', 'ROLE_MANAGER'),
      ('susan', 'ROLE_ADMIN');
  ```
- **Details**:
  - **`users`**: Stores usernames, plain text passwords (`{noop}`), and enabled status (1 = active).
  - **`authorities`**: Links usernames to roles with `ROLE_` prefix (e.g., `ROLE_EMPLOYEE`).
  - **Foreign Key**: Ensures `username` in `authorities` matches `users`.
  - **Unique Key**: Prevents duplicate roles per user (e.g., `john` can’t have two `ROLE_EMPLOYEE`).
- **Verify**:
  - `SHOW TABLES;` → `employee`, `users`, `authorities`.
  - `SELECT * FROM users;` → 3 rows.
  - `SELECT * FROM authorities;` → 6 rows (John: 1, Mary: 2, Susan: 3).

>[!NOTE]
>Run this script in MySQL before starting the app—tables must exist!

### 3.2 Updating Security Configuration

- **Purpose**: Switch from in-memory to JDBC authentication using `JdbcUserDetailsManager`.
- **Dependencies**: Ensure `pom.xml` includes MySQL and JPA (from prior sessions):
  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
      </dependency>
  </dependencies>
  ```
- **File**: `com.example.security.DemoSecurityConfig.java`.
- **Code**:
  ```java
  package com.example.security;

  import org.springframework.context.annotation.Configuration;
  import org.springframework.http.HttpMethod;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.provisioning.JdbcUserDetailsManager;
  import org.springframework.security.web.SecurityFilterChain;
  import javax.sql.DataSource;

  @Configuration
  public class DemoSecurityConfig {

      // Configure JDBC user details manager with DataSource
      @Bean
      public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
          return new JdbcUserDetailsManager(dataSource);
      }

      // Security filter chain (same as 8.1)
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
  - **`userDetailsManager`**: Injects `DataSource` (from `application.properties`) and returns `JdbcUserDetailsManager`.
    - Replaces `InMemoryUserDetailsManager`—no hardcoded users.
    - Auto-queries `users` and `authorities` tables.
  - **`filterChain`**: Unchanged from [8.1](#81-rest-api-basic-security-configuration)—same role-based restrictions.
  - **Controller**: `EmployeeController` (from 8.1) remains identical—`/api/employees` endpoints.

>[!TIP]
>`DataSource` = your bridge to MySQL—Spring Boot wires it automatically!

### 3.3 Testing with Browser and Postman

- **Setup**: 
  - Verify `application.properties` (3.0) and run SQL script (3.1).
  - Run `rest-api-spring-seq-demo-with-db` (`Tomcat started on port(s): 8080`).
  - Assume `employee` table has 6 records (IDs 1-15) after tests.

#### Browser Tests (GET Only)

- **URL**: `http://localhost:8080/api/employees`.
  - **Login**: `mary`/`mary@123` → JSON list of 6 employees.
  - **Why**: `EMPLOYEE` role (via DB) allows GET.
- **URL**: `http://localhost:8080/api/employees/15`.
  - **Login**: `mary`/`mary@123` → JSON of employee ID 15.
  - **Why**: Same role, `/**` covers subpaths.

#### Postman Tests (All Methods)

1. **GET All Employees (John)**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **Basic Auth**: `john`/`john@123` → 200 OK, JSON list (6 employees).
   - **Why**: `ROLE_EMPLOYEE` from DB allows GET.

2. **GET Specific Employee (John)**:
   - **Request**: GET `http://localhost:8080/api/employees/2`.
   - **Basic Auth**: `john`/`john@123` → 200 OK, JSON of ID 2.
   - **Why**: Same as above.

3. **POST New Employee (John)**:
   - **Request**: POST `http://localhost:8080/api/employees`.
   - **Body**: `{"firstName": "Nicolas", "lastName": "Cage", "email": "nicolas@cage.com"}`.
   - **Basic Auth**: `john`/`john@123` → 403 Forbidden.
   - **Why**: `ROLE_EMPLOYEE` can’t POST—needs `ROLE_MANAGER`.

4. **POST New Employee (Mary)**:
   - **Request**: Same as above.
   - **Basic Auth**: `mary`/`mary@123` → 201 Created, JSON with ID 16.
   - **Why**: `ROLE_MANAGER` from DB allows POST.

5. **PUT Update Employee (Mary)**:
   - **Request**: PUT `http://localhost:8080/api/employees`.
   - **Body**: `{"id": 16, "firstName": "Nicolas", "lastName": "Cage", "email": "nicolas@cage.net"}`.
   - **Basic Auth**: `mary`/`mary@123` → 200 OK, updated JSON (`.net` email).
   - **Why**: `ROLE_MANAGER` allows PUT.

6. **DELETE Employee (Mary)**:
   - **Request**: DELETE `http://localhost:8080/api/employees/16`.
   - **Basic Auth**: `mary`/`mary@123` → 403 Forbidden.
   - **Why**: `ROLE_MANAGER` can’t DELETE—needs `ROLE_ADMIN`.

7. **DELETE Employee (Susan)**:
   - **Request**: Same as above.
   - **Basic Auth**: `susan`/`susan@123` → 200 OK, `"Employee 16 got deleted"`.
   - **Why**: `ROLE_ADMIN` from DB allows DELETE.

8. **POST New Employee (Susan)**:
   - **Request**: POST `http://localhost:8080/api/employees`.
   - **Body**: Same as 3.
   - **Basic Auth**: `susan`/`susan@123` → 201 Created, ID 17.
   - **Why**: `ROLE_MANAGER` (and others) allows POST.

>[!NOTE]
>Data now comes from MySQL—same rules, persistent storage!

---

## 4. What’s Next

- **Next Session**: **8.3 - Spring Security Password Encryption**—encrypt passwords in the database for added security.

>[!TIP]
>You’ve gone persistent—next, lock it down with encryption!

---
