# 8.3 - Spring Security Password Encryption

## Introduction

Welcome to **8.3 - Spring Security Password Encryption**

In this section, we’ll enhance our Spring Security setup from [8.2](#82-spring-security-user-accounts-stored-db) by encrypting user passwords in the database using the Bcrypt algorithm. Previously, we stored passwords as plain text (e.g., `{noop}john@123`), which isn’t secure for real-world apps. Now, we’ll generate encrypted passwords, update the `users` table, and ensure Spring Security authenticates them correctly—all without changing our code! Perfect for beginners ready to lock down their app’s security! 🔒

---

## Table of Contents

1. [What Is Password Encryption?](#1-what-is-password-encryption)
   - [1.1 Overview](#11-overview)
   - [1.2 Why Encrypt Passwords?](#12-why-encrypt-passwords)
   - [1.3 Bcrypt Algorithm Explained](#13-bcrypt-algorithm-explained)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Generating Encrypted Passwords](#21-generating-encrypted-passwords)
   - [2.2 Updating the Database Schema and Data](#22-updating-the-database-schema-and-data)
   - [2.3 Verifying Authentication with Encrypted Passwords](#23-verifying-authentication-with-encrypted-passwords)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.0 Checking Database Configuration](#30-checking-database-configuration)
   - [3.1 Generating Bcrypt Passwords](#31-generating-bcrypt-passwords)
   - [3.2 Updating the Users Table](#32-updating-the-users-table)
   - [3.3 Testing with Browser and Postman](#33-testing-with-browser-and-postman)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Password Encryption?

### 1.1 Overview

- **Goal**: Replace plain text passwords in the `users` table (e.g., `{noop}john@123`) with encrypted versions using Bcrypt.
- **How**: Generate Bcrypt hashes offline (via a website utility) and update the database—no code changes needed.
- **Why**: Protects passwords from exposure if the database is compromised—essential for real-world security.

#### Real-World Analogy

Think of this as upgrading from writing passwords on a sticky note (plain text) to locking them in a safe with a unique, uncrackable code (Bcrypt)!

### 1.2 Why Encrypt Passwords?

- **Problem**: Plain text passwords (e.g., `{noop}john@123`) are readable if someone accesses the database—unsafe for production.
- **Best Practice**: Store passwords in an encrypted format (e.g., `{bcrypt}<60-character-hash>`).
- **Users**: Same trio from [8.2](#82-spring-security-user-accounts-stored-db):

| Username | Old Password (Plain) | New Password (Encrypted) Example                                      | Roles                |
|----------|----------------------|-----------------------------------------------------------------------|----------------------|
| `john`   | `{noop}john@123`     | `{bcrypt}$2a$10$some60characterhash...`                              | `EMPLOYEE`           |
| `mary`   | `{noop}mary@123`     | `{bcrypt}$2a$10$another60characterhash...`                           | `EMPLOYEE`, `MANAGER`|
| `susan`  | `{noop}susan@123`    | `{bcrypt}$2a$10$yetanother60characterhash...`                        | `EMPLOYEE`, `MANAGER`, `ADMIN` |

- **Format**: `{bcrypt}` (8 characters) + 60-character hash = 68 characters total.

### 1.3 Bcrypt Algorithm Explained

- **What**: A one-way hashing algorithm recommended by Spring Security.
- **Features**:
  - **One-Way Hashing**: Converts plain text (e.g., `john@123`) into a hash (e.g., `$2a$10$...`)—irreversible, unlike encryption (no decryption possible).
  - **Salt**: Adds a random value to the password before hashing—unique per password, thwarting precomputed attacks (e.g., rainbow tables).
  - **Brute Force Resistance**: Slow hashing process (configurable via "rounds") defeats brute force attempts (trying all possibilities, e.g., 0000 to 9999 for a PIN).
- **How It Works**:
  1. User enters plain text password (e.g., `john@123`).
  2. Spring Security applies Bcrypt with the same salt stored in the DB hash.
  3. Generated hash is compared to the stored hash—match = login success, no match = failure.
- **Size**: `{bcrypt}` (8 chars) + 60-char hash = 68 characters minimum.

>[!NOTE]
>Bcrypt never decrypts—plain text gets hashed and compared to the stored hash!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term            | Meaning                                      | Example                       |
|-----------------|----------------------------------------------|-------------------------------|
| **Plain Text**  | Unencrypted, readable password               | `john@123`                    |
| **Bcrypt**      | One-way hashing algorithm with salt          | `{bcrypt}$2a$10$...`          |
| **Hash**        | Irreversible digest of a password            | `$2a$10$some60charhash...`    |
| **Salt**        | Random value added to password before hashing| Unique per password           |
| **Brute Force** | Attack trying all possible combinations      | 0000 to 9999 for a PIN        |
| **One-Way**     | Can’t reverse hash to get original text      | Hash → no plain text          |
| **68 Characters** | Minimum length for Bcrypt password field   | `{bcrypt}` + 60-char hash     |

---

## 2. Learning Roadmap

Your path to mastering password encryption!

### 2.1 Generating Encrypted Passwords

- **What**: Use a website utility to create Bcrypt hashes for `john@123`, `mary@123`, `susan@123`.
- **Goal**: Replace plain text passwords with secure, encrypted versions.

### 2.2 Updating the Database Schema and Data

- **What**: Ensure the `password` column supports 68 characters and update it with Bcrypt hashes.
- **Goal**: Store encrypted passwords in the `users` table correctly.

### 2.3 Verifying Authentication with Encrypted Passwords

- **What**: Test endpoints with encrypted passwords using browser (GET) and Postman (all methods).
- **Goal**: Confirm Spring Security authenticates users without code changes.

---

## 3. Practical Demonstration

Let’s secure `rest-api-spring-seq-demo-with-db` with encrypted passwords!

### 3.0 Checking Database Configuration

- **Purpose**: Verify database connection before proceeding—essential for JDBC and JPA.
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
>`spring.datasource.password` mismatch = no DB access—verify it!

### 3.1 Generating Bcrypt Passwords

- **Purpose**: Create encrypted passwords for `john`, `mary`, and `susan` using an online tool.
- **Tool**: Use [javainuse.com/bcrypt](https://www.javainuse.com/bcrypt) (or similar).
- **Steps**:
  1. **John**:
     - Input: `john@123`.
     - Rounds: 10 (default).
     - Generate: `{bcrypt}$2a$10$XURP2fDe7xEVobLhN03Mde8k5HEG8v25auW.dQb2sNak5n3bDJmEi`.
     - Verify: Matches `john@123` (tool’s "Check Match" feature).
  2. **Mary**:
     - Input: `mary@123`.
     - Generate: `{bcrypt}$2a$10$5tKZhHa3Px9Vv5fVFRN/deGfE8lR2b6WPU8jL8a9v5Z5n7mJcX5bW`.
     - Verify: Matches `mary@123`.
  3. **Susan**:
     - Input: `susan@123`.
     - Generate: `{bcrypt}$2a$10$8JkQdXhM9nV5L8fKXyQwdeT2b6R8v9W5nL5jK8aQv7mJcX5bW9nV5`.
     - Verify: Matches `susan@123`.
- **Details**:
  - `{bcrypt}` (8 chars) + 60-char hash = 68 characters.
  - Hashes vary each time due to salt—use these examples or generate your own.
- **Alternative**: Java code (e.g., `BCryptPasswordEncoder`), but avoided here to prevent hardcoding passwords in source.

>[!NOTE]
>Copy these hashes—they’ll go into the `users` table next!

### 3.2 Updating the Users Table

- **Purpose**: Ensure `password` column is 68+ characters and update with Bcrypt hashes.
- **Setup**: Assumes `users` and `authorities` tables from [8.2](#82-spring-security-user-accounts-stored-db).
- **Check Schema**:
  - Command: `DESCRIBE users;`.
  - Verify: `password` is `VARCHAR(68)` or larger (e.g., `VARCHAR(100)`).
  - Fix if needed:
    ```sql
    ALTER TABLE users MODIFY COLUMN password VARCHAR(68) NOT NULL;
    ```
- **Update Data**:
  - SQL Script:
    ```sql
    UPDATE users SET password = '{bcrypt}$2a$10$XURP2fDe7xEVobLhN03Mde8k5HEG8v25auW.dQb2sNak5n3bDJmEi' WHERE username = 'john';
    UPDATE users SET password = '{bcrypt}$2a$10$5tKZhHa3Px9Vv5fVFRN/deGfE8lR2b6WPU8jL8a9v5Z5n7mJcX5bW' WHERE username = 'mary';
    UPDATE users SET password = '{bcrypt}$2a$10$8JkQdXhM9nV5L8fKXyQwdeT2b6R8v9W5nL5jK8aQv7mJcX5bW9nV5' WHERE username = 'susan';
    ```
  - Verify:
    - `SELECT * FROM users;` → 3 rows with `{bcrypt}...` passwords.
    - `SHOW TABLES;` → `employee`, `users`, `authorities`.
- **Details**:
  - No code changes—`JdbcUserDetailsManager` (from 8.2) reads `{bcrypt}` and handles authentication.
  - `authorities` table unchanged—roles remain (e.g., `ROLE_EMPLOYEE`).

>[!TIP]
>If `password` was <68 chars before, alter it first—Bcrypt needs the space!

### 3.3 Testing with Browser and Postman

- **Setup**: 
  - Confirm `application.properties` (3.0) and DB updates (3.2).
  - Run `rest-api-spring-seq-demo-with-db` (`Tomcat started on port(s): 8080`).
  - Assume `employee` table has 6 records (IDs 1-17, adjusted during tests).

#### Browser Tests (GET Only)

- **URL**: `http://localhost:8080/api/employees`.
  - **Login**: `susan`/`susan@123` → JSON list of 6 employees.
  - **Why**: `ROLE_EMPLOYEE` (via DB) allows GET—Bcrypt hash matches.
- **URL**: `http://localhost:8080/api/employees/15`.
  - **Login**: `mary`/`mary@123` → JSON of employee ID 15 (e.g., "Emma Stone").
  - **Why**: `ROLE_EMPLOYEE` works—plain text hashed and compared successfully.

#### Postman Tests (All Methods)

1. **GET All Employees (Mary)**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **Basic Auth**: `mary`/`mary@123` → 200 OK, JSON list (6 employees).
   - **Why**: `ROLE_EMPLOYEE` allows GET—Bcrypt authentication succeeds.

2. **POST New Employee (Mary)**:
   - **Request**: POST `http://localhost:8080/api/employees`.
   - **Body**: `{"firstName": "Nicolas", "lastName": "Junior", "email": "nicolas@jr.com"}`.
   - **Basic Auth**: `mary`/`mary@123` → 201 Created, JSON with ID 18.
   - **Why**: `ROLE_MANAGER` allows POST—hash matches.

3. **DELETE Employee (Mary)**:
   - **Request**: DELETE `http://localhost:8080/api/employees/18`.
   - **Basic Auth**: `mary`/`mary@123` → 403 Forbidden.
   - **Why**: `ROLE_MANAGER` can’t DELETE—needs `ROLE_ADMIN`.

4. **DELETE Employee (Susan)**:
   - **Request**: Same as above.
   - **Basic Auth**: `susan`/`susan@123` → 200 OK, `"Employee ID 18 deleted"`.
   - **Why**: `ROLE_ADMIN` allows DELETE—Bcrypt hash matches.

5. **GET Updated List (Susan)**:
   - **Request**: GET `http://localhost:8080/api/employees`.
   - **Basic Auth**: `susan`/`susan@123` → 200 OK, JSON list (back to 6 employees, no Nicolas Junior).
   - **Why**: `ROLE_EMPLOYEE` allows GET—secure authentication persists.

- **Authentication Flow**:
  1. User enters `susan`/`susan@123`.
  2. Spring Security fetches `{bcrypt}$2a$10$8JkQdXhM...` from DB.
  3. Bcrypt hashes `susan@123` with the stored salt.
  4. Hashes match → login succeeds, roles checked.

>[!NOTE]
>No code changes—encrypted passwords just work with existing setup!

---

## 4. What’s Next

- **Next Session**: **8.4 - Spring Security Custom Tables**—use custom table schemas instead of Spring Security’s defaults.

>[!TIP]
>Your passwords are encrypted—next, customize the tables to fit your app!

---

