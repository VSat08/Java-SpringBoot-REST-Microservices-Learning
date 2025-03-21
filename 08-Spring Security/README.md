# 08 - Spring Security

## Introduction

Welcome to **08 - Spring Security**!

In this section, we’ll dive into securing your Spring Boot applications using **Spring Security**. Whether you're building REST APIs or web applications, security is a critical aspect of modern software development. We’ll explore how to protect your endpoints, manage user roles, and store credentials securely. By the end of this section, you’ll be able to implement authentication and authorization in your Spring Boot applications like a pro. Let’s get started! 🔒

---

## Table of Contents

1. [What Is Spring Security?](#1-what-is-spring-security)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why Spring Security Matters](#12-why-spring-security-matters)
   - [1.3 Key Concepts: Authentication and Authorization](#13-key-concepts-authentication-and-authorization)
   - [1.4 Spring Security Architecture](#14-spring-security-architecture)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Securing REST APIs](#21-securing-rest-apis)
   - [2.2 Defining Users and Roles](#22-defining-users-and-roles)
   - [2.3 Role-Based Access Control (RBAC)](#23-role-based-access-control-rbac)
   - [2.4 Storing User Credentials](#24-storing-user-credentials)
   - [2.5 Password Encryption](#25-password-encryption)
3. [What You’ll Build](#3-what-youll-build)
   - [3.1 Basic Security Configuration](#31-basic-security-configuration)
   - [3.2 In-Memory Authentication](#32-in-memory-authentication)
   - [3.3 Database-Backed Authentication](#33-database-backed-authentication)
   - [3.4 Custom Security Configurations](#34-custom-security-configurations)
4. [Upcoming Sessions](#4-upcoming-sessions)
5. [Next Steps](#5-next-steps)

---

## 1. What Is Spring Security?

### 1.1 Definition and Purpose

Spring Security is a powerful framework for securing Spring-based applications. It provides comprehensive security features, including authentication, authorization, and protection against common vulnerabilities like CSRF and session fixation.

- **Definition**: Spring Security is a framework that provides authentication, authorization, and other security features for Java applications.
- **Purpose**: It ensures that only authorized users can access specific resources in your application.
- **How**: It uses servlet filters to intercept requests and enforce security rules.

#### Real-World Analogy

Think of Spring Security as a bouncer at a club. It checks your ID (authentication) and ensures you have the right access (authorization) before letting you in.

### 1.2 Why Spring Security Matters

- **Protection**: Secures your application from unauthorized access and common attacks.
- **Flexibility**: Supports multiple authentication mechanisms (in-memory, database, LDAP, OAuth, etc.).
- **Integration**: Seamlessly integrates with Spring Boot and other Spring projects.
- **Customizability**: Allows you to define custom security rules and configurations.

#### Example Benefit

With Spring Security, you can easily restrict access to specific endpoints based on user roles, such as allowing only admins to delete records.

### 1.3 Key Concepts: Authentication and Authorization

- **Authentication**: Verifies the identity of a user (e.g., username and password).
- **Authorization**: Determines what a user is allowed to do (e.g., access specific resources).

#### Example

- **Authentication**: A user logs in with their credentials.
- **Authorization**: The user is allowed to view their profile but not edit others’ profiles.

### 1.4 Spring Security Architecture

Spring Security uses **servlet filters** to intercept and process web requests. These filters handle authentication and authorization before the request reaches your controllers.

- **Servlet Filters**: Pre-process and post-process web requests.
- **Security Filters**: Apply security logic (e.g., check credentials, enforce roles).
- **Configuration**: Defined in `application.properties` or custom security configuration classes.

#### Flow of Action

1. A request is made to a protected resource.
2. Spring Security filters intercept the request.
3. The filters check if the user is authenticated and authorized.
4. If successful, the request is routed to the resource; otherwise, access is denied.

---

## 2. Learning Roadmap

Your path to mastering Spring Security over the next 4-5 sessions!

### 2.1 Securing REST APIs

- **What You’ll Learn**: How to secure REST endpoints using Spring Security.
- **Goal**: Protect your APIs from unauthorized access.

### 2.2 Defining Users and Roles

- **What You’ll Learn**: How to define users and roles in your application.
- **Goal**: Implement role-based access control (RBAC).

### 2.3 Role-Based Access Control (RBAC)

- **What You’ll Learn**: How to restrict access to resources based on user roles.
- **Goal**: Ensure users can only access resources they are authorized to use.

### 2.4 Storing User Credentials

- **What You’ll Learn**: How to store user credentials in a database.
- **Goal**: Move from in-memory authentication to database-backed authentication.

### 2.5 Password Encryption

- **What You’ll Learn**: How to store passwords securely using encryption.
- **Goal**: Protect user passwords from being exposed in plain text.

---

## 3. What You’ll Build

You’ll build a secure Spring Boot application with role-based access control and database-backed authentication.

### 3.1 Basic Security Configuration

- **What You’ll Do**: Add Spring Security to your project and secure all endpoints by default.
- **Tools**: Spring Boot, Spring Security.

#### Steps

1. Add the `spring-boot-starter-security` dependency to your `pom.xml`.
2. Run the application and observe the default login form.
3. Use the auto-generated password (found in the console logs) to log in.

### 3.2 In-Memory Authentication

- **What You’ll Do**: Define users and roles in memory using a configuration class.
- **Tools**: Spring Security, Java Configuration.

#### Example

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("{noop}admin123").roles("ADMIN")
            .and()
            .withUser("user").password("{noop}user123").roles("USER");
    }
}
```

### 3.3 Database-Backed Authentication

- **What You’ll Do**: Store user credentials and roles in a database.
- **Tools**: Spring Data JPA, MySQL, Spring Security.

#### Steps

1. Create a `User` entity and `Role` entity.
2. Configure Spring Security to use the database for authentication.
3. Implement custom queries to fetch user details.

### 3.4 Custom Security Configurations

- **What You’ll Do**: Define custom security rules for specific endpoints.
- **Tools**: Spring Security, Java Configuration.

#### Example

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin();
}
```

---

## 4. Upcoming Sessions

Here’s your learning journey over the next few sessions—stay tuned to build your skills step-by-step!

| Session Title                        | Chapters | What You’ll Learn                                      |
|--------------------------------------|----------|-------------------------------------------------------|
| **REST API Basic Security Configuration** | 8.1  | Configure basic security for REST APIs.               |
| **Spring Security User Accounts Stored in DB** | 8.2  | Store user credentials and roles in a database.       |
| **Spring Security Password Encryption** | 8.3  | Encrypt passwords for secure storage.                 |
| **Spring Security Custom Tables**    | 8.4  | Create custom tables for users and roles.             |

---

## 5. Next Steps

- Start with **REST API Basic Security Configuration** to secure your endpoints.
- Explore **Database-Backed Authentication** to store user credentials securely.

---

Your app is about to get a whole lot safer—keep going! 🔒🚀