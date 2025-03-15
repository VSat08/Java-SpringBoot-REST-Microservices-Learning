# 5.5-REST Exception Handling\README.md

## Introduction

Welcome to **5.5 - REST Exception Handling** 

 If you’re new to coding, this is your guide to handling errors gracefully in REST APIs! We’ll explore **exception handling**—fixing ugly error logs when things go wrong (like a bad student ID from [5.4](#54-path-variables)). We’ll enhance our app with custom errors, using Jackson for JSON, and make it user-friendly. Think of this as turning a server crash into a polite "Not Found" note—building on REST, JSON, and path variables. Let’s dive in! 🚀

---

## Table of Contents

1. [What Is REST Exception Handling?](#1-what-is-rest-exception-handling)
   - [1.1 Exception Handling: Fixing Errors](#11-exception-handling-fixing-errors)
   - [1.2 The Problem from `jacksondemo`](#12-the-problem-from-jacksondemo)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Why Handle Exceptions](#21-why-handle-exceptions)
   - [2.2 Four Steps to Handle Errors](#22-four-steps-to-handle-errors)
   - [2.3 Building Custom Error Handling](#23-building-custom-error-handling)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Understanding the Problem](#31-understanding-the-problem)
   - [3.2 Creating `rest-api-exception-demo`](#32-creating-rest-api-exception-demo)
   - [3.3 Testing with Browser and Postman](#33-testing-with-browser-and-postman)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices](#41-best-practices)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 What’s Next](#53-whats-next)

---

## 1. What Is REST Exception Handling?

### 1.1 Exception Handling: Fixing Errors

- **Definition**: Exception handling catches and manages errors in REST APIs, sending nice responses instead of crashes.
- **Purpose**: Avoids ugly internal server errors—makes apps user-friendly.

#### Real-World Analogy

Think of it as a polite apology— вместо "Crash!" you say, "Sorry, that student isn’t here!"

### 1.2 The Problem from `jacksondemo`

- **Recap**: From [5.4](#54-path-variables), `jacksondemo` lists students at `/api/students` and fetches one via `/api/students/{id}` (0-4).
- **Issue**: Asking for `/api/students/99` (beyond the list) gives an ugly "Internal Server Error" log—bad for users!

#### Real-World Analogy

It’s like asking for a nonexistent student ID and getting a filing cabinet explosion—let’s clean it up!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                          |
|-----------------------|----------------------------------------------|----------------------------------|
| **Exception**         | Error that crashes the app                  | `ArrayIndexOutOfBounds`          |
| **Custom Exception**  | Your own error type                         | `StudentNotFoundException`       |
| **Error Response**    | Friendly error message                      | `{"status": 404, "message": "..."}` |
| **@ExceptionHandler** | Catches exceptions in controllers           | Handles `StudentNotFoundException` |
| **ResponseEntity**    | Wraps HTTP responses                        | Returns error with status 404    |
| **Jackson**           | Converts Java to JSON                       | `StudentErrorResponse` → JSON    |

---

## 2. Learning Roadmap

Your path to error-handling mastery!

### 2.1 Why Handle Exceptions

- **What You’ll Learn**: Why errors need fixing.
- **Goal**: See the user benefit.

### 2.2 Four Steps to Handle Errors

- **What You’ll Learn**: Steps to create custom error handling.
- **Goal**: Understand the process.

### 2.3 Building Custom Error Handling

- **What You’ll Do**: Fix `jacksondemo` errors.
- **Goal**: Send pretty error messages.

---

## 3. Practical Demonstration

Let’s fix errors in a new `rest-api-exception-demo` app!

### 3.1 Understanding the Problem

- **The Issue**:
  - From [5.4](#54-path-variables), `/api/students/99` (beyond 0-4) crashes with an "Internal Server Error" (e.g., `ArrayIndexOutOfBoundsException`).
  - Ugly log: Timestamp, status, error, path—not user-friendly.
- **Goal**:
  - Replace with: `{"status": 404, "message": "Student id not found - 99", "timestamp": 1234567890}`.
- **Flow**:
  - Client (browser/Postman) sends bad ID → Service checks → Throws exception → Handler catches → Sends nice JSON error.
- **Why?**: Users deserve clear messages, not server logs!

>[!NOTE]
>Errors happen—handling them makes your app polite!

### 3.2 Creating `rest-api-exception-demo`

- **Goal**: Handle bad student IDs with a custom error response.
- **Steps**:
  1. **Setup Project**:
     - Copy `jacksondemo` from [5.4](#54-path-variables) → Rename to `rest-api-exception-demo`.
     - Keep `pom.xml` (Spring Web, DevTools), `Student` POJO unchanged.
  2. **Step 1: Create `StudentErrorResponse` POJO**:
     - In `src/main/java/com.example.restapiexceptiondemo.entity`, add:
       ```java
       package com.example.restapiexceptiondemo.entity;

       public class StudentErrorResponse {
           private int status;
           private String message;
           private long timestamp;

           // Default constructor
           public StudentErrorResponse() {}

           // Parameterized constructor
           public StudentErrorResponse(int status, String message, long timestamp) {
               this.status = status;
               this.message = message;
               this.timestamp = timestamp;
           }

           // Getters and Setters
           public int getStatus() { return status; }
           public void setStatus(int status) { this.status = status; }
           public String getMessage() { return message; }
           public void setMessage(String message) { this.message = message; }
           public long getTimestamp() { return timestamp; }
           public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
       }
       ```
     - Note: Boilerplate—Lombok (later topic) could simplify this.
  3. **Step 2: Create `StudentNotFoundException`**:
     - In `src/main/java/com.example.restapiexceptiondemo.exception`, create package `exception`, then:
       ```java
       package com.example.restapiexceptiondemo.exception;

       public class StudentNotFoundException extends RuntimeException {
           public StudentNotFoundException(String message) {
               super(message);
           }
       }
       ```
     - Extends `RuntimeException` (or `Exception`), passes message (e.g., "Student id not found - 99") to parent.
  4. **Step 3: Update `StudentRestController` to Throw Exception**:
     - Edit `src/main/java/com.example.restapiexceptiondemo.controller/StudentRestController.java`:
       ```java
       package com.example.restapiexceptiondemo.controller;

       import com.example.restapiexceptiondemo.entity.Student;
       import com.example.restapiexceptiondemo.exception.StudentNotFoundException;
       import jakarta.annotation.PostConstruct;
       import org.springframework.web.bind.annotation.*;

       import java.util.ArrayList;
       import java.util.List;

       @RestController
       @RequestMapping("/api")
       public class StudentRestController {

           private List<Student> students;

           @PostConstruct
           public void loadStudents() {
               students = new ArrayList<>();
               students.add(new Student("Liam", "Neeson"));  // 0
               students.add(new Student("Mario", "Rossi"));  // 1
               students.add(new Student("Ram", "Charan"));   // 2
               students.add(new Student("Amir", "Khan"));    // 3
               students.add(new Student("Bruce", "Willis")); // 4
           }

           @GetMapping("/students")
           public List<Student> getStudents() {
               return students;
           }

           @GetMapping("/students/{studentId}")
           public Student getStudent(@PathVariable("studentId") int studentId) {
               if (studentId >= students.size() || studentId < 0) {
                   throw new StudentNotFoundException("Student id not found - " + studentId);
               }
               return students.get(studentId);
           }
       }
       ```
     - Checks if `studentId` is invalid (negative or ≥ list size)—throws exception if so.
  5. **Step 4: Add Exception Handler**:
     - In same `StudentRestController`, add:
       ```java
       import com.example.restapiexceptiondemo.entity.StudentErrorResponse;
       import org.springframework.http.HttpStatus;
       import org.springframework.http.ResponseEntity;

       // ... (inside StudentRestController class)

       @ExceptionHandler
       public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
           StudentErrorResponse error = new StudentErrorResponse();
           error.setStatus(HttpStatus.NOT_FOUND.value()); // 404
           error.setMessage(exc.getMessage()); // "Student id not found - 99"
           error.setTimestamp(System.currentTimeMillis()); // Milliseconds
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
       }
       ```
     - `@ExceptionHandler` catches `StudentNotFoundException`, builds `StudentErrorResponse`, wraps in `ResponseEntity`.
  6. **Run App**:
     - Right-click `RestApiExceptionDemoApplication.java` > `Run As > Spring Boot App`.
     - Console: "Tomcat started on port(s): 8080."

- **What’s Happening**:
  - Bad ID → Throws `StudentNotFoundException` → Handler catches → Returns JSON error (Jackson converts `StudentErrorResponse`).

>[!TIP]
>`ResponseEntity` is your HTTP wrapper—status, body, all in one!

### 3.3 Testing with Browser and Postman

- **Browser Test**:
  - `localhost:8080/api/students` → JSON array of 5 students.
  - `/api/students/0` → `{"firstName": "Liam", "lastName": "Neeson"}`.
  - `/api/students/99` → `{"status": 404, "message": "Student id not found - 99", "timestamp": 1234567890}`.
  - `/api/students/5` → Same 404 error—clean and clear!
- **Postman Test**:
  - `GET localhost:8080/api/students` → 5 students, `200 OK`.
  - `/api/students/2` → Third student, `200 OK`.
  - `/api/students/99` → `{"status": 404, "message": "Student id not found - 99", "timestamp": ...}`, `404 Not Found`.
- **What’s Happening**:
  - No more server errors—custom JSON response instead!
  - DevTools auto-reloads changes.

>[!NOTE]
>Exceptions handled—users get clarity, not chaos!

---

## 4. Practical Application

Make it stick!

### 4.1 Best Practices

- **Clear Messages**: "Student id not found - 99"—specific and helpful.
- **Use 404**: Matches "Not Found" status—standard REST practice.
- **Encapsulate**: Keep fields private in POJOs—OOP rules!
- **Test Errors**: Try bad IDs—ensure handling works.

### 4.2 Common Mistakes to Avoid

- **No Check**: Forgot `if` condition? Crash returns—add it!
- **Wrong Status**: Used `500` instead of `404`? Fix to match intent!
- **Missing Imports**: `ResponseEntity` not imported? Eclipse auto-import helps!
- **No Handler**: Forgot `@ExceptionHandler`? Errors leak—add it!

### 4.3 Hands-On Exercises

Try these:

1. **New Error**:
   - Add a check in `getStudents()`—throw if `students.isEmpty()`—test empty list error.
2. **Break It**:
   - Remove `@ExceptionHandler`—test `/api/students/99`—fix it back!
3. **Custom Message**:
   - Change message to "Oops! Student ID " + studentId + " not found!"—test in Postman.
4. **Timestamp Play**:
   - Use `new Date().toString()` instead of `System.currentTimeMillis()`—compare outputs.
5. **Bad ID Test**:
   - Test `/api/students/-1`—screenshot the 404 response.

>[!TIP]
>Tweak and test—own exception handling!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Level up:

- **Spring REST**: [spring.io/guides/gs/rest-service](https://spring.io/guides/gs/rest-service/) - REST guide.
- **Jackson**: [github.com/FasterXML/jackson](https://github.com/FasterXML/jackson) - Official repo.
- **Exceptions**: [docs.spring.io/spring-framework/docs/current/javadoc-api/](https://docs.spring.io/spring-framework/docs/current/javadoc-api/) - Spring docs.
- **Postman**: [learning.postman.com](https://learning.postman.com/) - Tutorials.


### 5.2 Summary of Key Takeaways

- **Problem**: Bad IDs crashed `jacksondemo`—ugly logs.
- **Solution**: Four steps—custom error POJO, exception class, throw logic, handler.
- **Demo**: Built `rest-api-exception-demo`—404 JSON responses for bad IDs.
- **Tools**: Jackson (JSON), `ResponseEntity` (HTTP), `@ExceptionHandler` (catch).

>[!TIP]
>You’re an error-handling hero—REST stays smooth now!

### 5.3 What’s Next

- **5.6 - REST API - Global Exception Handling**: Handle errors app-wide.
- **Later**: CRUD, databases—REST grows stronger!

---
