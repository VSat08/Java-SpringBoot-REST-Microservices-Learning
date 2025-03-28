# 9.3 - Reading Form Data

## Introduction

Welcome to **9.3 - Reading Form Data**

In this section, we’ll build on [9.2](#92-thymeleaf-template-engine-for-view) by adding form handling to Spring MVC. We’ll create a Thymeleaf form to collect a name, process it on the server using a controller, and display it back in uppercase—all with the full MVC flow (Model, View, Controller). Perfect for beginners learning to handle user input in web apps! 📝

---

## Table of Contents

1. [What Is Form Data Reading in Spring MVC?](#1-what-is-form-data-reading-in-spring-mvc)
   - [1.1 Overview](#11-overview)
   - [1.2 Application Flow](#12-application-flow)
   - [1.3 Key Concepts](#13-key-concepts)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Setting Up the Controller](#21-setting-up-the-controller)
   - [2.2 Creating the Form View](#22-creating-the-form-view)
   - [2.3 Processing Form Data](#23-processing-form-data)
   - [2.4 Displaying the Result](#24-displaying-the-result)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Creating the Project](#31-creating-the-project)
   - [3.2 Writing the Controller](#32-writing-the-controller)
   - [3.3 Building the Form Page](#33-building-the-form-page)
   - [3.4 Building the Result Page](#34-building-the-result-page)
   - [3.5 Running and Testing](#35-running-and-testing)
4. [Understanding HTTP Methods](#4-understanding-http-methods)
   - [4.1 GET vs. POST](#41-get-vs-post)
   - [4.2 Mapping Annotations](#42-mapping-annotations)
5. [What’s Next](#5-whats-next)

---

## 1. What Is Form Data Reading in Spring MVC?

### 1.1 Overview

- **Goal**: Capture user input from a form, process it, and display a response using Spring MVC and Thymeleaf.
- **What**: A web app that:
  1. Shows a form asking "What is your name?"
  2. Reads the submitted name.
  3. Displays it back in uppercase (e.g., "spring boot" → "SPRING BOOT").
- **Components**: Controller with two methods, two Thymeleaf views, and a model to carry data.

#### Real-World Analogy

Imagine a waiter (controller) taking your order (form data), cooking it (processing), and serving it back styled (view) on a plate (model)!

### 1.2 Application Flow

- **Steps**:
  1. **Request Form**: Browser hits `GET /showForm` → Controller returns `hello-form.html`.
  2. **Submit Form**: User enters a name and submits → `POST /processForm` → Controller processes and returns `result.html`.
  3. **Display Result**: Name shown in uppercase on `result.html`.
- **Diagram**:
  - Browser → `GET /showForm` → Controller → `hello-form.html` → User submits → `POST /processForm` → Controller → `result.html` → Browser.

### 1.3 Key Concepts

- **Model**: Carries data (e.g., the name) between controller and view.
- **`@RequestParam`**: Reads form data (e.g., `studentName`) and binds it to a variable.
- **Thymeleaf Form**: Uses `<form>` with `th:action` to submit data.
- **GET vs. POST**:
  - `GET`: Fetches the form (visible in URL if data is sent).
  - `POST`: Submits data securely (in request body, not URL).

>[!NOTE]
>`POST` hides sensitive data—ideal for forms!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                       |
|-----------------------|----------------------------------------------|-------------------------------|
| **Form Data**         | User input from HTML forms                   | Name in a text box            |
| **`@RequestParam`**   | Binds form parameters to controller variables| `@RequestParam("studentName")`|
| **`th:action`**       | Thymeleaf attribute for form submission     | `th:action="@{/processForm}"` |
| **Model**             | Object carrying data to the view             | `model.addAttribute("name", ...)` |
| **`GET` Mapping**     | Handles requests to fetch resources          | `@GetMapping("/showForm")`    |
| **`POST` Mapping**    | Handles form submissions                     | `@PostMapping("/processForm")`|
| **Thymeleaf View**    | Dynamic HTML page                            | `result.html`                 |

---

## 2. Learning Roadmap

Your path to mastering form data reading!

### 2.1 Setting Up the Controller

- **What**: Create a controller with two methods—one to show the form, one to process it.
- **Goal**: Handle both form display and submission.

### 2.2 Creating the Form View

- **What**: Build a Thymeleaf page with a text box and submit button.
- **Goal**: Allow user input collection.

### 2.3 Processing Form Data

- **What**: Use `@RequestParam` to read form data, process it (uppercase), and add to a model.
- **Goal**: Transform and store user input.

### 2.4 Displaying the Result

- **What**: Create a Thymeleaf page to show the processed data.
- **Goal**: Present the result to the user.

---

## 3. Practical Demonstration

Let’s build `thymeleaf-reading-form-data` to read and display a name!

### 3.1 Creating the Project

- **Purpose**: Set up a Spring Boot project with Thymeleaf.
- **Tool**: Eclipse (or Spring Initializr).
- **Steps**:
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `thymeleaf-reading-form-data`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `formdata`.
     - Package: `com.example.formdata`.
  3. **Dependencies**:
     - `Spring Web`: For MVC and Tomcat.
     - `Spring Boot DevTools`: For auto-restart.
     - `Spring Boot Starter Thymeleaf`: For Thymeleaf templates.
  4. **Finish**: Generate and open.
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
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
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

>[!TIP]
>`spring-boot-starter-thymeleaf` = your form’s dynamic engine!

### 3.2 Writing the Controller

- **Purpose**: Handle form display and processing.
- **File**: `com.example.formdata.controller.HelloController.java`.
- **Code**:
  ```java
  package com.example.formdata.controller;

  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;

  @Controller
  public class HelloController {

      @GetMapping("/showForm")
      public String showForm() {
          return "hello-form";
      }

      @PostMapping("/processForm")
      public String processForm(@RequestParam("studentName") String name, Model model) {
          model.addAttribute("name", name.toUpperCase());
          return "result";
      }
  }
  ```
- **Details**:
  - **`@GetMapping("/showForm")`**: Shows the form via `hello-form.html`.
  - **`@PostMapping("/processForm")`**: Processes the submitted form.
  - **`@RequestParam("studentName")`**: Binds the form’s `studentName` field to `name`.
  - **`model.addAttribute`**: Adds the uppercase name to the model as `"name"`.
  - **`return "result"`**: Resolves to `result.html`.

>[!NOTE]
>`studentName` must match the form field name!

### 3.3 Building the Form Page

- **Purpose**: Create a Thymeleaf form to collect the name.
- **File**: `src/main/resources/templates/hello-form.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Form Demo</title>
      <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css}" />
  </head>
  <body>
      <form th:action="@{/processForm}" method="post">
          <input type="text" name="studentName" placeholder="What is your name?" />
          <input type="submit" value="Submit" />
      </form>
  </body>
  </html>
  ```
- **Details**:
  - **`th:action="@{/processForm}"`**: Submits to `/processForm` via POST.
  - **`name="studentName"`**: Matches `@RequestParam("studentName")`.
  - **Bootstrap CDN**: Adds basic styling to the form.

>[!TIP]
>`th:action` uses `@` for context paths—keep it clean!

### 3.4 Building the Result Page

- **Purpose**: Display the processed name.
- **File**: `src/main/resources/templates/result.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Result</title>
      <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css}" />
  </head>
  <body>
      <h1>Hello, welcome to Spring MVC!</h1>
      <p>Your name: <span th:text="${name}"></span></p>
  </body>
  </html>
  ```
- **Details**:
  - **`th:text="${name}"`**: Displays the model attribute `"name"` (uppercase).
  - **Bootstrap**: Enhances presentation.

>[!NOTE]
>`${name}` pulls data from the model—match the attribute name!

### 3.5 Running and Testing

- **Purpose**: Verify form reading and processing.
- **Steps**:
  1. **Run**: Right-click `ThymeleafReadingFormDataApplication.java` → Run As → Spring Boot App.
     - Output: `Tomcat started on port(s): 8080`.
  2. **Test**:
     - **Step 1**: `http://localhost:8080/showForm` → Shows form ("What is your name?").
     - **Step 2**: Enter "spring boot" → Click Submit → Displays "Hello, welcome to Spring MVC! Your name: SPRING BOOT".
- **Flow**:
  - `GET /showForm` → `showForm()` → `hello-form.html`.
  - Submit → `POST /processForm` → `processForm()` → Reads `studentName`, uppercases, adds to model → `result.html`.

>[!TIP]
>`/showForm` not `/`—no root mapping here!

---

## 4. Understanding HTTP Methods

### 4.1 GET vs. POST

- **GET**:
  - **Purpose**: Fetch resources (e.g., show form).
  - **Data**: Appended to URL (e.g., `?studentName=spring`).
  - **Pros**: Debug-friendly, bookmarkable.
  - **Cons**: Limited data, visible (less secure).
- **POST**:
  - **Purpose**: Submit data (e.g., process form).
  - **Data**: Sent in request body (not URL).
  - **Pros**: Secure, no data limit, supports binary data.
  - **Cons**: Not bookmarkable.
- **Form Rule**: Use `POST` for submissions (e.g., login, this app)—keeps data hidden.

| Feature           | GET                  | POST                 |
|-------------------|----------------------|----------------------|
| **Purpose**       | Fetch data           | Submit data          |
| **Data Location** | URL                  | Request body         |
| **Visibility**    | Visible              | Hidden               |
| **Security**      | Less secure          | More secure          |
| **Data Limit**    | Limited              | Unlimited            |

>[!NOTE]
>`POST` for forms—protects your data!

### 4.2 Mapping Annotations

- **`@RequestMapping`**:
  - Handles all HTTP methods (GET, POST, etc.).
  - Restrict with `method`: `@RequestMapping(path = "/processForm", method = RequestMethod.POST)`.
- **`@GetMapping`**:
  - Shortcut for GET-only requests.
  - Rejects non-GET methods.
- **`@PostMapping`**:
  - Shortcut for POST-only requests.
  - Rejects non-POST methods.
- **Usage**:
  - `@GetMapping("/showForm")`: Fetch form.
  - `@PostMapping("/processForm")`: Submit form.

>[!TIP]
>Use `@GetMapping`/`@PostMapping` for clarity—less code, more specific!

---

## 5. What’s Next

- **Next Session**: **9.4 - Spring MVC - Form Data Binding - Text Box**—simplify form handling with data binding.

>[!TIP]
>You’ve read form data—next, bind it directly to objects!

---
