# 5.6-Global Exception Handling

## Introduction

Welcome to **5.6 - Global Exception Handling**

If you’re new to coding, this is your guide to handling errors across your entire REST API—not just one controller!We’ll explore **global exception handling** using `@ControllerAdvice`. We’ll upgrade the exception handling from [5.5](#55-rest-exception-handling) to work app-wide, promoting reuse and centralization. Think of this as turning a single classroom rule into a school-wide policy—building on REST, JSON, path variables, and local exception handling. Let’s get started! 🚀

---

## Table of Contents

1. [What Is Global Exception Handling?](#1-what-is-global-exception-handling)
   - [1.1 Global Exception Handling: App-Wide Errors](#11-global-exception-handling-app-wide-errors)
   - [1.2 The Limit of Local Handling](#12-the-limit-of-local-handling)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Why Go Global](#21-why-go-global)
   - [2.2 How `@ControllerAdvice` Works](#22-how-controlleradvice-works)
   - [2.3 Building Global Handling](#23-building-global-handling)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Understanding the Shift](#31-understanding-the-shift)
   - [3.2 Creating `rest-api-global-exception-demo`](#32-creating-rest-api-global-exception-demo)
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

## 1. What Is Global Exception Handling?

### 1.1 Global Exception Handling: App-Wide Errors

- **Definition**: Global exception handling catches errors across all controllers in a REST API using a centralized mechanism.
- **Purpose**: Reuses error-handling code—no need to repeat it per controller.

#### Real-World Analogy

Think of it as a school principal handling all classroom issues—not each teacher doing it alone!

### 1.2 The Limit of Local Handling

- **Recap**: In [5.5](#55-rest-exception-handling), `rest-api-exception-demo` handled bad student IDs in `StudentRestController`—worked fine!
- **Problem**: That `@ExceptionHandler` only works for one controller—not reusable in big apps with many controllers (e.g., student, login, profile).

#### Real-World Analogy

It’s like a single teacher managing chaos—works for one class, but not the whole school!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                    | Example                            |
| --------------------- | ------------------------------------------ | ---------------------------------- |
| **Global Handling**   | Error management for all controllers       | App-wide 404 errors                |
| **@ControllerAdvice** | Centralized exception handler              | Catches all exceptions             |
| **@ExceptionHandler** | Marks a method to catch exceptions         | Handles `StudentNotFoundException` |
| **Reuse**             | Use one solution everywhere                | One handler, many controllers      |
| **AOP**               | Aspect-Oriented Programming—Spring feature | `@ControllerAdvice` uses it        |
| **ResponseEntity**    | Wraps HTTP responses                       | Returns error JSON                 |

---

## 2. Learning Roadmap

Your path to global error-handling mastery!

### 2.1 Why Go Global

- **What You’ll Learn**: Why local handling isn’t enough.
- **Goal**: See the need for app-wide solutions.

### 2.2 How `@ControllerAdvice` Works

- **What You’ll Learn**: How `@ControllerAdvice` centralizes handling.
- **Goal**: Understand its role vs. local handlers.

### 2.3 Building Global Handling

- **What You’ll Do**: Move exception handling to a global level.
- **Goal**: Reuse error logic across controllers.

---

## 3. Practical Demonstration

Let’s upgrade to global exception handling with a new app!

### 3.1 Understanding the Shift

- **Local Recap** (from [5.5](#55-rest-exception-handling)):
  - Client sends bad ID (e.g., `/api/students/99`) → `StudentRestController` throws `StudentNotFoundException` → Local `@ExceptionHandler` catches → Returns `StudentErrorResponse` as JSON.
  - Worked perfectly—but only for that controller.
- **Why Global?**:
  - Big apps have many controllers (e.g., `StudentController`, `LoginController`, `ProfileController`).
  - Repeating `@ExceptionHandler` in each is redundant—global handling reuses it.
- **How?**:
  - Use `@ControllerAdvice`—a Spring feature (part of Aspect-Oriented Programming, AOP).
  - Acts like a filter: Pre-processes requests, post-processes responses (here, we use it for exceptions).
- **New Flow**:
  - Client → Request → Service throws exception → `@ControllerAdvice` catches → Returns error JSON.

> [!NOTE]
> Global handling = less work, more power—centralize and conquer!

### 3.2 Creating `rest-api-global-exception-demo`

- **Goal**: Move exception handling from `StudentRestController` to a global handler.
- **Steps**:

  1. **Setup Project**:
     - Copy `rest-api-exception-demo` from [5.5](#55-rest-exception-handling) → Rename to `rest-api-global-exception-demo`.
     - Keep `pom.xml` (Spring Web, DevTools), `Student`, `StudentErrorResponse`, `StudentNotFoundException` unchanged.
  2. **Create `StudentRestExceptionHandler`**:

     - In `src/main/java/com.example.restapiglobalexceptiondemo.exception`, add:

       ```java
       package com.example.restapiglobalexceptiondemo.exception;

       import com.example.restapiglobalexceptiondemo.entity.StudentErrorResponse;
       import org.springframework.http.HttpStatus;
       import org.springframework.http.ResponseEntity;
       import org.springframework.web.bind.annotation.ControllerAdvice;
       import org.springframework.web.bind.annotation.ExceptionHandler;

       @ControllerAdvice
       public class StudentRestExceptionHandler {

           @ExceptionHandler
           public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
               StudentErrorResponse error = new StudentErrorResponse();
               error.setStatus(HttpStatus.NOT_FOUND.value()); // 404
               error.setMessage(exc.getMessage()); // "Student id not found - 99"
               error.setTimestamp(System.currentTimeMillis()); // Milliseconds
               return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
           }
       }
       ```

     - `@ControllerAdvice` makes this global—no constructor needed.

  3. **Update `StudentRestController`**:

     - Edit `src/main/java/com.example.restapiglobalexceptiondemo.controller/StudentRestController.java`:

       ```java
       package com.example.restapiglobalexceptiondemo.controller;

       import com.example.restapiglobalexceptiondemo.entity.Student;
       import com.example.restapiglobalexceptiondemo.exception.StudentNotFoundException;
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

     - Remove the `@ExceptionHandler` method—it’s now in `StudentRestExceptionHandler`.

  4. **Run App**:
     - Right-click `RestApiGlobalExceptionDemoApplication.java` > `Run As > Spring Boot App`.
     - Console: "Tomcat started on port(s): 8080."

- **What’s Happening**:
  - Bad ID → Throws `StudentNotFoundException` → `@ControllerAdvice` catches → Returns JSON error.
  - Same logic as [5.5](#55-rest-exception-handling), but now reusable across all controllers!

> [!TIP] >`@ControllerAdvice` is your app’s safety net—catches errors everywhere!

### 3.3 Testing with Browser and Postman

- **Browser Test**:
  - `localhost:8080/api/students` → JSON array of 5 students.
  - `/api/students/1` → `{"firstName": "Mario", "lastName": "Rossi"}`.
  - `/api/students/5` → `{"status": 404, "message": "Student id not found - 5", "timestamp": 1234567890}`.
  - `/api/students/100` → Same 404—clean response!
- **Postman Test**:
  - `GET localhost:8080/api/students` → 5 students, `200 OK`.
  - `/api/students/0` → First student, `200 OK`.
  - `/api/students/100` → `{"status": 404, "message": "Student id not found - 100", "timestamp": ...}`, `404 Not Found`.
- **What’s Happening**:
  - No internal server errors—global handler works like the local one, but for any controller.
  - DevTools auto-reloads changes.

> [!NOTE]
> Global handling = same result, bigger reach—reuse rules!

---

## 4. Practical Application

Solidify your skills!

### 4.1 Best Practices

- **Centralize**: Use `@ControllerAdvice`—keep error logic in one place.
- **Clear Names**: `StudentRestExceptionHandler`—specific and readable.
- **Reuse**: Plan for multiple controllers—global saves time.
- **Test Broadly**: Check good and bad IDs—ensure coverage.

### 4.2 Common Mistakes to Avoid

- **Left Local**: Forgot to remove `@ExceptionHandler` from controller? Delete it!
- **No Advice**: Missed `@ControllerAdvice`? Errors leak—add it!
- **Wrong Package**: Handler in wrong package? Import fails—fix it!
- **Untested**: Skipped `/api/students/100`? Test it—catch issues!

### 4.3 Hands-On Exercises

Try these:

1. **Add Controller**:
   - Create `ProfileRestController` with `/api/profiles/{id}`—throw `StudentNotFoundException`—test global handling.
2. **Break It**:
   - Remove `@ControllerAdvice`—test `/api/students/99`—fix it back!
3. **Custom Error**:
   - Add a second `@ExceptionHandler` for `NullPointerException` in `StudentRestExceptionHandler`—test with null data.
4. **Message Tweak**:
   - Change message to "ID " + studentId + " not found!"—test in Postman.
5. **Full Test**:
   - Test `/api/students/0` to `/api/students/5`—screenshot results.

> [!TIP]
> Play with globals—make errors your friend!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Level up:

- **Spring AOP**: [spring.io/guides/gs/aop](https://spring.io/guides/gs/aop) - AOP basics.
- **ControllerAdvice**: [docs.spring.io/spring-framework/docs/current/javadoc-api/](https://docs.spring.io/spring-framework/docs/current/javadoc-api/) - Spring docs.
- **REST**: [spring.io/guides/gs/rest-service](https://spring.io/guides/gs/rest-service/) - REST guide.
- **Postman**: [learning.postman.com](https://learning.postman.com/) - Tutorials.
- **Udemy**: [Course Link](#) - Full lecture (placeholder).

### 5.2 Summary of Key Takeaways

- **Local Limit**: [5.5](#55-rest-exception-handling) worked for one controller—too narrow.
- **Global Fix**: `@ControllerAdvice` centralizes handling—reusable for all.
- **Demo**: Built `rest-api-global-exception-demo`—moved `@ExceptionHandler` to `StudentRestExceptionHandler`.
- **AOP**: Spring’s aspect magic powers this—pre/post-processing.

> [!TIP]
> You’re a global error pro—REST stays tidy everywhere!

### 5.3 What’s Next

- **5.7 - REST API Design**: Plan and structure your APIs.
- **Later**: CRUD, databases—REST grows bigger!

---
