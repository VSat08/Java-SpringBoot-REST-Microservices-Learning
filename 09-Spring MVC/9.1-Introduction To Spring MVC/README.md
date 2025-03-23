# 9.1 - Introduction to Spring MVC

## Introduction

Welcome to **9.1 - Introduction to Spring MVC**

In this section, we kick off the Spring MVC module of our Spring Boot course! We’ll explore the Model-View-Controller (MVC) design pattern and how Spring MVC builds on it to create web applications. Starting with a simple example, we’ll set up a Spring Boot project, write a controller, and display a static HTML page—no database or dynamic content yet. Perfect for beginners stepping into web development with Spring! 🌐

---

## Table of Contents

1. [What Is Spring MVC?](#1-what-is-spring-mvc)
   - [1.1 Overview](#11-overview)
   - [1.2 The MVC Pattern](#12-the-mvc-pattern)
   - [1.3 How Spring MVC Works](#13-how-spring-mvc-works)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Understanding MVC Components](#21-understanding-mvc-components)
   - [2.2 Setting Up a Spring MVC Project](#22-setting-up-a-spring-mvc-project)
   - [2.3 Creating a Basic Controller and View](#23-creating-a-basic-controller-and-view)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Creating a Spring MVC Project](#31-creating-a-spring-mvc-project)
   - [3.2 Writing a Controller](#32-writing-a-controller)
   - [3.3 Adding a Static View](#33-adding-a-static-view)
   - [3.4 Running and Testing the Application](#34-running-and-testing-the-application)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Spring MVC?

### 1.1 Overview

- **Goal**: Build web applications using the Spring Framework’s MVC architecture.
- **What**: Spring MVC is a request-driven framework that processes HTTP requests and generates responses, typically HTML pages.
- **Core Component**: The `DispatcherServlet`, a front controller provided by Spring, manages the request flow—developers don’t write it.

#### Real-World Analogy

Think of Spring MVC as a restaurant: the `DispatcherServlet` is the host directing customers (requests) to tables (controllers), where chefs (business logic) prepare meals (responses) served on plates (views)!

### 1.2 The MVC Pattern

- **Definition**: Model-View-Controller (MVC) is a design pattern splitting app logic into three parts:
  - **Model**: Holds data and business logic (e.g., employee records from a database).
  - **View**: Displays data to the user (e.g., HTML pages).
  - **Controller**: Handles requests, processes logic, and connects model to view.
- **Pre-Spring**: Used servlets (controllers) and JSPs (views) with separate model logic.
- **Spring MVC**: Enhances this with Spring’s framework, using annotations and a central servlet.

### 1.3 How Spring MVC Works

- **Flow**:
  1. **Request**: Browser sends an HTTP request (e.g., `GET /`).
  2. **DispatcherServlet**: Spring’s front controller receives it and delegates to a controller.
  3. **Controller**: Processes the request, optionally creates/updates a model, and specifies a view.
  4. **Model**: Carries data (e.g., a list of employees)—optional for simple responses.
  5. **View**: Renders the response (e.g., HTML), resolved by the `DispatcherServlet`.
  6. **Response**: Sent back to the browser.
- **Diagram Summary**:
  - Browser → `DispatcherServlet` → Controller → (Model) → View → Browser.
- **Key Point**: `DispatcherServlet` is automatic in Spring Boot—no coding required.

>[!NOTE]
>`DispatcherServlet` is Spring’s magic glue—handles routing behind the scenes!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term              | Meaning                                      | Example                       |
|-------------------|----------------------------------------------|-------------------------------|
| **MVC**           | Model-View-Controller pattern                | Separates data, UI, and logic |
| **`DispatcherServlet`** | Spring’s front controller              | Routes requests to controllers|
| **Controller**    | Handles requests and responses               | `@Controller` class           |
| **Model**         | Object holding data                          | List of employees             |
| **View**          | UI template (e.g., HTML)                     | `welcome.html`                |
| **Spring Boot**   | Simplifies Spring setup with defaults        | Auto-configures `DispatcherServlet` |
| **Static Resource** | Non-dynamic files (e.g., HTML, CSS)        | `/static/welcome.html`        |

---

## 2. Learning Roadmap

Your path to mastering Spring MVC basics!

### 2.1 Understanding MVC Components

- **What**: Learn the roles of Model, View, and Controller in Spring MVC.
- **Goal**: Grasp how requests flow through the framework.

### 2.2 Setting Up a Spring MVC Project

- **What**: Create a Spring Boot project with web dependencies.
- **Goal**: Establish a foundation for MVC development.

### 2.3 Creating a Basic Controller and View

- **What**: Write a controller and a static HTML view—no model yet.
- **Goal**: Display a simple welcome page via Spring MVC.

---

## 3. Practical Demonstration

Let’s build a basic Spring MVC app: `mvc-demo`!

### 3.1 Creating a Spring MVC Project

- **Purpose**: Set up a Spring Boot project for MVC.
- **Tool**: Eclipse (or any IDE with Spring Initializr support).
- **Steps**:
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `mvc-demo`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `mvc`.
     - Package: `com.example.mvc`.
  3. **Dependencies**:
     - `Spring Web`: For MVC and embedded Tomcat.
     - `Spring Boot DevTools`: For auto-restart during development.
  4. **Finish**: Generate and open the project.
- **Result**:
  - `pom.xml`:
    ```xml
    <dependencies>
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ```
  - Main class: `com.example.mvc.MvcDemoApplication.java` with `@SpringBootApplication`.

>[!TIP]
>`spring-boot-starter-web` = MVC essentials + Tomcat—no extra setup!

### 3.2 Writing a Controller

- **Purpose**: Create a controller to handle requests and return a view.
- **File**: `com.example.mvc.controller.DemoController.java`.
- **Code**:
  ```java
  package com.example.mvc.controller;

  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;

  @Controller
  public class DemoController {

      @GetMapping("/")
      public String welcome() {
          return "welcome.html";
      }
  }
  ```
- **Details**:
  - `@Controller`: Marks this as an MVC controller (not `@RestController`—we’re returning views, not JSON).
  - `@GetMapping("/")`: Maps `GET /` (root URL) to the `welcome` method.
  - `return "welcome.html"`: Specifies the view file name (resolved by Spring).

>[!NOTE]
>`welcome.html` isn’t dynamic yet—static for simplicity!

### 3.3 Adding a Static View

- **Purpose**: Create a static HTML page as the view.
- **Location**: `src/main/resources/static/welcome.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html>
  <head>
      <title>Welcome</title>
  </head>
  <body>
      <h1>Welcome to Spring MVC</h1>
  </body>
  </html>
  ```
- **Details**:
  - **Path**: `/static/` is Spring Boot’s default folder for static resources (e.g., HTML, CSS, images).
  - **Content**: Simple `<h1>` tag—no dynamic data yet.
  - **Resolution**: `welcome.html` returned by the controller maps to `/static/welcome.html`.

>[!TIP]
>Static pages go in `/static/`—dynamic ones (e.g., Thymeleaf) go in `/templates/` (next session)!

### 3.4 Running and Testing the Application

- **Purpose**: Launch and verify the MVC app.
- **Steps**:
  1. **Run**: Right-click `MvcDemoApplication.java` → Run As → Spring Boot App.
     - Output: `Tomcat started on port(s): 8080 (http)`.
  2. **Test**: Open a browser to `http://localhost:8080/`.
     - Result: Displays "Welcome to Spring MVC".
- **How It Works**:
  - Browser sends `GET /`.
  - `DispatcherServlet` routes to `DemoController.welcome()`.
  - Returns `welcome.html`, resolved to `/static/welcome.html`.
  - Browser renders the static HTML.
- **Limitations**: No model or dynamic content yet—just a controller and view.

>[!NOTE]
>No model here—data comes in the next session with Thymeleaf!

---

## 4. What’s Next

- **Next Session**: **9.2 - Spring MVC - Thymeleaf Template Engine for View**—add dynamic views with Thymeleaf.

>[!TIP]
>You’ve built a basic MVC app—next, make it dynamic with Thymeleaf!

---

