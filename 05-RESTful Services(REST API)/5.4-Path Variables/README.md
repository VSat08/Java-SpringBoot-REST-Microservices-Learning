# 5.4-Path Variables

## Introduction

Welcome to **5.4 - Path Variables**

If you’re new to coding, this is your guide to making REST APIs dynamic with path variables! We’ll explore **path variables** (data in URLs), enhance the `jacksondemo` app from [5.3](#53-java-json-binding-jackson-project), and use Jackson for JSON binding. Think of this as adding a student ID to your URL to fetch one student instead of all—building on REST, JSON, and Jackson from earlier sections. Let’s get started! 🚀

---

## Table of Contents

1. [What Are Path Variables?](#1-what-are-path-variables)
   - [1.1 Path Variables: Dynamic URLs](#11-path-variables-dynamic-urls)
   - [1.2 Revisiting `jacksondemo`](#12-revisiting-jacksondemo)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Why Path Variables](#21-why-path-variables)
   - [2.2 How They Work](#22-how-they-work)
   - [2.3 Coding with Path Variables](#23-coding-with-path-variables)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Understanding Path Variables](#31-understanding-path-variables)
   - [3.2 Enhancing `jacksondemo`](#32-enhancing-jacksondemo)
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

## 1. What Are Path Variables?

### 1.1 Path Variables: Dynamic URLs

- **Definition**: Path variables are values in a URL (URI) that change what a REST API returns.
- **Purpose**: Fetch specific data (e.g., one student by ID) instead of everything.

#### Real-World Analogy

Think of path variables as house numbers—`street/students` gets all houses, `street/students/1` gets one!

### 1.2 Revisiting `jacksondemo`

- **Recap**: From [5.3](#53-java-json-binding-jackson-project), `jacksondemo` returns a list of students as a JSON array via `/api/students`.
- **Goal**: Add a way to get one student (e.g., `/api/students/0` for the first student).

#### Real-World Analogy

We’re upgrading from a class roster to a student ID lookup—more precise!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term               | Meaning                          | Example                         |
| ------------------ | -------------------------------- | ------------------------------- |
| **Path Variable**  | Value in URL for specific data   | `/students/1` (ID = 1)          |
| **URI/URL**        | Web address for a resource       | `localhost:8080/api/students/0` |
| **@PathVariable**  | Annotation to grab URL data      | `@PathVariable("id")`           |
| **@PostConstruct** | Runs once after bean setup       | Loads students                  |
| **Jackson**        | Converts Java to JSON (and back) | `Student` → `{"name": "Liam"}`  |
| **Index**          | List position (starts at 0)      | `get(0)` = first item           |

---

## 2. Learning Roadmap

Your path to mastering path variables!

### 2.1 Why Path Variables

- **What You’ll Learn**: Why we need dynamic URLs.
- **Goal**: See their use in REST.

### 2.2 How They Work

- **What You’ll Learn**: How path variables bind to code.
- **Goal**: Understand `@PathVariable` and Jackson’s role.

### 2.3 Coding with Path Variables

- **What You’ll Do**: Add a path variable endpoint.
- **Goal**: Fetch one student dynamically.

---

## 3. Practical Demonstration

Let’s enhance `jacksondemo` with path variables!

### 3.1 Understanding Path Variables

- **Why?**:
  - In [5.3](#53-java-json-binding-jackson-project), `/api/students` gave all students—great, but what if you want just one?
  - Example: `localhost:8080/api/students/1` → Second student (Mario Rossi).
- **What’s a Path Variable?**:
  - Part of the URL (e.g., `1` in `/students/1`)—sent from client (browser/Postman).
  - Maps to a variable in your code to fetch specific data.
- **Flow**:
  - Client: `GET /api/students/0`.
  - Service: Reads `0` as a path variable → Returns student at index 0 → Jackson converts to JSON.
- **Syntax**:
  - In `@GetMapping`: Use `{variable}` (e.g., `/students/{studentId}`).
  - In method: Use `@PathVariable` to bind it (e.g., `@PathVariable("studentId") int studentId`).
- **Jackson’s Role**: Converts the `Student` object to a JSON object (not an array, since it’s one student).

> [!NOTE]
> Path variables make URLs smart—pick exactly what you want!

### 3.2 Enhancing `jacksondemo`

- **Goal**: Add `/api/students/{studentId}` to get one student.
- **Steps**:

  1. **Open `jacksondemo`**:
     - From [5.3](#53-java-json-binding-jackson-project), ensure it’s set up (Spring Web, DevTools).
  2. **Update `StudentRestController`**:

     - Edit `src/main/java/com.example.jacksondemo.controller/StudentRestController.java`.
     - Add a list, load method, and new endpoint:

       ```java
       package com.example.jacksondemo.controller;

       import com.example.jacksondemo.entity.Student;
       import org.springframework.web.bind.annotation.RestController;
       import org.springframework.web.bind.annotation.RequestMapping;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.PathVariable;
       import jakarta.annotation.PostConstruct;
       import java.util.ArrayList;
       import java.util.List;

       @RestController
       @RequestMapping("/api")
       public class StudentRestController {

           private List<Student> students;

           // Load students once after bean creation
           @PostConstruct
           public void loadStudents() {
               students = new ArrayList<>();
               students.add(new Student("Liam", "Neeson"));  // Index 0
               students.add(new Student("Mario", "Rossi"));  // Index 1
               students.add(new Student("Ram", "Charan"));   // Index 2
               students.add(new Student("Amir", "Khan"));    // Index 3
               students.add(new Student("Bruce", "Willis")); // Index 4
           }

           // Return all students
           @GetMapping("/students")
           public List<Student> getStudents() {
               return students;
           }

           // Return one student by ID
           @GetMapping("/students/{studentId}")
           public Student getStudent(@PathVariable("studentId") int studentId) {
               return students.get(studentId);
           }
       }
       ```

  3. **Run App**:
     - Right-click `JacksondemoApplication.java` > `Run As > Spring Boot App`.
     - Console: "Tomcat started on port(s): 8080."
     - DevTools reloads changes automatically—no restart needed!

- **What’s New?**:
  - **List**: `students` moved to a class field—shared by both methods.
  - **`@PostConstruct`**: Runs `loadStudents()` once after the controller bean is created—loads 5 students (0-4).
  - **Path Variable**: `/students/{studentId}` binds to `studentId` (e.g., `0` → `students.get(0)`).
  - **Jackson**: Converts `Student` to JSON object (e.g., `{"firstName": "Liam", "lastName": "Neeson"}`).


> [!TIP] 
>`@PostConstruct` is your setup buddy—loads data once, no repeats!

### 3.3 Testing with Browser and Postman

- **Browser Test**:
  - Visit `localhost:8080/api/students` → JSON array of 5 students.
  - Try `localhost:8080/api/students/0` → `{"firstName": "Liam", "lastName": "Neeson"}`.
  - Test others:
    - `/api/students/1` → Mario Rossi.
    - `/api/students/2` → Ram Charan.
    - `/api/students/3` → Amir Khan.
    - `/api/students/4` → Bruce Willis.
  - Error: `/api/students/99` → .
- **Postman Test**:
  - Open Postman, set method to `GET`.
  - URL: `localhost:8080/api/students` → 5 students, `200 OK`.
  - Try:
    - `/api/students/0` → First student.
    - `/api/students/3` → Fourth student.
    - `/api/students/99` → Error (to be handled later).
- **What’s Happening**:
  - `/students`: Returns `List<Student>` → Jackson → JSON array.
  - `/students/{id}`: Returns `Student` → Jackson → JSON object.
  - DevTools: Auto-reloads after code changes.

> [!NOTE]
> Path variables + Jackson = dynamic REST magic—pick any student!

---

## 4. Practical Application

Lock in your skills!

### 4.1 Best Practices

- **Clear Paths**: Use `{studentId}`, not `{x}`—name it meaningfully.
- **Load Once**: Use `@PostConstruct`—avoid redundant data loading.
- **Test All**: Check every ID in Postman—ensure it works.
- **Match Names**: `@PathVariable("studentId")` must match `{studentId}`.

### 4.2 Common Mistakes to Avoid

- **Typo in Path**: `/student/{id}` vs. `/students/{id}`? Fix it!
- **No List Init**: Forgot `@PostConstruct`? Null error—add it!
- **Wrong Index**: `/students/5` when list has 0-4? Check bounds!
- **Missing Import**: `@PathVariable` not imported? Add it!

### 4.3 Hands-On Exercises

Try these:

1. **Add Student**:
   - Add a 6th student (e.g., `"Priya", "Sharma"`) in `loadStudents()`—test `/api/students/5`.
2. **Break It**:
   - Change `{studentId}` to `{id}` but not `@PathVariable`—fix the error.
3. **Test Range**:
   - Test `/api/students/0` to `/api/students/4` in Postman—screenshot results.
4. **New Endpoint**:
   - Add `/api/students/count` returning `students.size()`—test in browser.
5. **Error Check**:
   - Try `/api/students/99`—note the error.

> [!TIP]
> Play with paths—master dynamic REST!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Grow your skills:

- **Spring REST**: [spring.io/guides/gs/rest-service](https://spring.io/guides/gs/rest-service/) - REST guide.
- **Jackson**: [github.com/FasterXML/jackson](https://github.com/FasterXML/jackson) - Official repo.
- **Postman**: [learning.postman.com](https://learning.postman.com/) - Tutorials.
- **Annotations**: [docs.spring.io/spring-framework/docs/current/javadoc-api/](https://docs.spring.io/spring-framework/docs/current/javadoc-api/) - Spring docs.

### 5.2 Summary of Key Takeaways

- **Path Variables**: Add dynamic IDs to URLs (e.g., `/students/1`) with `@PathVariable`.
- **Jackson**: Converts `Student` to JSON object, `List<Student>` to JSON array.
- **`@PostConstruct`**: Loads data once—cuts redundancy.
- **Demo**: Enhanced `jacksondemo`—`/api/students` (all), `/api/students/{id}` (one).

> [!TIP]
> You’re a REST path pro now—variables make it personal!

### 5.3 What’s Next

- **5.5-REST Exception Handling**: Handle errors (e.g., `/students/99`).

---
