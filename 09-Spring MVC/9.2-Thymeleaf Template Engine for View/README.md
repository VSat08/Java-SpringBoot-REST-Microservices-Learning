# 9.2 - Thymeleaf Template Engine for View

## Introduction

Welcome to **9.2 - Thymeleaf Template Engine for View**

In this section, we’ll enhance our Spring MVC skills from [9.1](#91-introduction-to-spring-mvc) by introducing Thymeleaf, Spring Boot’s default template engine. We’ll move beyond static HTML to dynamic views, displaying server-side data (like the current date) in a browser. We’ll set up a project, create a controller with a model, and style a Thymeleaf page with CSS—perfect for beginners diving into dynamic web development! 🌿

---

## Table of Contents

1. [What Is Thymeleaf?](#1-what-is-thymeleaf)
   - [1.1 Overview](#11-overview)
   - [1.2 Thymeleaf vs. JSP](#12-thymeleaf-vs-jsp)
   - [1.3 How Thymeleaf Works in Spring MVC](#13-how-thymeleaf-works-in-spring-mvc)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Adding Thymeleaf Dependency](#21-adding-thymeleaf-dependency)
   - [2.2 Creating a Controller with a Model](#22-creating-a-controller-with-a-model)
   - [2.3 Building a Thymeleaf View](#23-building-a-thymeleaf-view)
   - [2.4 Styling with CSS](#24-styling-with-css)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Project](#31-setting-up-the-project)
   - [3.2 Writing the Controller](#32-writing-the-controller)
   - [3.3 Creating the Thymeleaf Template](#33-creating-the-thymeleaf-template)
   - [3.4 Adding CSS Styling](#34-adding-css-styling)
   - [3.5 Running and Testing](#35-running-and-testing)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Thymeleaf?

### 1.1 Overview

- **Goal**: Use Thymeleaf to create dynamic HTML views in Spring MVC.
- **What**: Thymeleaf is a modern, server-side Java template engine—Spring Boot’s default for rendering views.
- **Features**:
  - Processes templates on the server.
  - Generates HTML responses with dynamic data (e.g., dates, lists).
  - Works standalone or with web apps, not tied to Spring.

#### Real-World Analogy

Think of Thymeleaf as a chef adding fresh ingredients (Java data) to a plain dish (HTML) to serve a customized meal (dynamic webpage)!

### 1.2 Thymeleaf vs. JSP

- **JSP (JavaServer Pages)**:
  - Java code embedded in HTML with `.jsp` extension.
  - Traditional, less natural for designers.
- **Thymeleaf**:
  - Pure HTML (`.html`) with Thymeleaf expressions (e.g., `th:text`).
  - Cleaner, designer-friendly, and dynamic via Java integration.
- **Spring Boot Default**: Thymeleaf—simpler and more modern than JSP.

### 1.3 How Thymeleaf Works in Spring MVC

- **Flow**:
  1. **Request**: Browser sends a request (e.g., `GET /date`).
  2. **Controller**: Processes the request, creates a `Model` with data (e.g., current date), and specifies a view (e.g., `date.html`).
  3. **Thymeleaf**: Server processes the `.html` template in `/templates/`, injecting model data using expressions (e.g., `th:text="${date}"`).
  4. **Response**: Rendered HTML sent to the browser.
- **Key Point**: Templates live in `src/main/resources/templates/`—Spring Boot auto-resolves them.

>[!NOTE]
>Thymeleaf runs server-side—browser sees only the final HTML!

### 1.4 Key Terms for Beginners

Your newbie glossary:

| Term                  | Meaning                                      | Example                       |
|-----------------------|----------------------------------------------|-------------------------------|
| **Thymeleaf**         | Java template engine for dynamic views       | Processes `date.html`         |
| **`th:` Namespace**   | Prefix for Thymeleaf expressions             | `th:text`                     |
| **Model**             | Spring object carrying data to the view      | `model.addAttribute("date", ...)` |
| **Template**          | HTML file with Thymeleaf expressions         | `date.html` in `/templates/`  |
| **`th:text`**         | Displays model data in HTML                  | `th:text="${date}"`           |
| **Static Resource**   | CSS, JS, images in `/static/`                | `demo.css`                    |
| **`th:href`**         | Links to static resources                    | `th:href="@{/css/demo.css}"`  |

---

## 2. Learning Roadmap

Your path to mastering Thymeleaf in Spring MVC!

### 2.1 Adding Thymeleaf Dependency

- **What**: Include `spring-boot-starter-thymeleaf` in the project.
- **Goal**: Enable Thymeleaf auto-configuration in Spring Boot.

### 2.2 Creating a Controller with a Model

- **What**: Write a controller that adds data (e.g., date) to a `Model` and returns a view name.
- **Goal**: Pass server-side data to the Thymeleaf template.

### 2.3 Building a Thymeleaf View

- **What**: Create an HTML file with Thymeleaf expressions to display model data.
- **Goal**: Render dynamic content in the browser.

### 2.4 Styling with CSS

- **What**: Add CSS (local or Bootstrap CDN) to enhance the view.
- **Goal**: Improve presentation of dynamic content.

---

## 3. Practical Demonstration

Let’s build `mvc-thymeleaf-app` to display the current date with Thymeleaf!

### 3.1 Setting Up the Project

- **Purpose**: Create a Spring Boot project with Thymeleaf support.
- **Tool**: Eclipse (or Spring Initializr).
- **Steps**:
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `mvc-thymeleaf-app`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `thymeleaf`.
     - Package: `com.example.thymeleaf`.
  3. **Dependencies**:
     - `Spring Web`: For MVC and Tomcat.
     - `Spring Boot DevTools`: For auto-restart.
     - `Spring Boot Starter Thymeleaf`: For Thymeleaf templates.
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
  - Main class: `com.example.thymeleaf.MvcThymeleafAppApplication.java`.

>[!TIP]
>`spring-boot-starter-thymeleaf` = dynamic views made easy!

### 3.2 Writing the Controller

- **Purpose**: Create a controller to handle `/date` requests and add the current date to a model.
- **File**: `com.example.thymeleaf.controller.DemoController.java`.
- **Code**:
  ```java
  package com.example.thymeleaf.controller;

  import java.util.Date;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;

  @Controller
  public class DemoController {

      @GetMapping("/date")
      public String getDate(Model model) {
          model.addAttribute("date1", new Date());
          return "date";
      }
  }
  ```
- **Details**:
  - `@Controller`: Marks this as an MVC controller.
  - `@GetMapping("/date")`: Maps `GET /date` to `getDate`.
  - `Model model`: Spring auto-injects a `Model` object (from `org.springframework.ui.Model`).
  - `model.addAttribute("date1", new Date())`: Adds the current date as `"date1"`.
  - `return "date"`: Resolves to `date.html` in `/templates/`.

>[!NOTE]
>`"date1"` is the attribute name—use it in the Thymeleaf template!

### 3.3 Creating the Thymeleaf Template

- **Purpose**: Build a Thymeleaf page to display the date.
- **File**: `src/main/resources/templates/date.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Date and Time</title>
  </head>
  <body>
      <h1>Today's Date and Time</h1>
      <p th:text="'Time and date on server is ' + ${date1}"></p>
  </body>
  </html>
  ```
- **Details**:
  - **`xmlns:th`**: Declares the Thymeleaf namespace (`http://www.thymeleaf.org`)—mandatory for Thymeleaf expressions.
  - **`th:text`**: Displays text and model data:
    - `'Time and date on server is '`: Static text.
    - `${date1}`: Dynamic value from the model (current date).
    - `+`: Concatenates in Thymeleaf.
  - **Location**: `/templates/`—Spring Boot’s default for Thymeleaf views.

>[!TIP]
>`${}` = your key to model data—match the attribute name (`date1`)!

### 3.4 Adding CSS Styling

- **Purpose**: Style the Thymeleaf page with local CSS and explore Bootstrap CDN.
- **Option 1: Local CSS**:
  - **File**: `src/main/resources/static/css/demo.css`.
  - **Code**:
    ```css
    .funny {
        font-style: italic;
        color: red;
        font-size: medium;
    }
    ```
  - **Update `date.html`**:
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Date and Time</title>
        <link rel="stylesheet" th:href="@{/css/demo.css}" />
    </head>
    <body>
        <h1>Today's Date and Time</h1>
        <p class="funny" th:text="'Time and date on server is ' + ${date1}"></p>
    </body>
    </html>
    ```
  - **Details**:
    - **`th:href="@{/css/demo.css}"`**: Links to `/static/css/demo.css` using Thymeleaf’s `@` syntax.
    - **`class="funny"`**: Applies the `.funny` style (italic, red, medium font).
- **Option 2: Bootstrap CDN**:
  - **Update `date.html`**:
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Date and Time</title>
        <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css}" />
    </head>
    <body>
        <h1>Today's Date and Time</h1>
        <p th:text="'Time and date on server is ' + ${date1}"></p>
    </body>
    </html>
    ```
  - **Details**:
    - **`th:href` with CDN**: Links to Bootstrap’s CSS—applies default styles to `<h1>` and `<p>`.
    - No local CSS—Bootstrap overrides with its own look.

>[!TIP]
>`/static/` for CSS, `@` for paths—keep it organized!

### 3.5 Running and Testing

- **Purpose**: Launch and verify the Thymeleaf app.
- **Steps**:
  1. **Run**: Right-click `MvcThymeleafAppApplication.java` → Run As → Spring Boot App.
     - Output: `Tomcat started on port(s): 8080 (http)`.
  2. **Test**: Open a browser to `http://localhost:8080/date`.
     - **Without CSS**: "Time and date on server is [current date]" (plain text).
     - **With Local CSS**: Same text, styled red, italic, medium size.
     - **With Bootstrap**: Same text, styled with Bootstrap’s `<h1>` and `<p>` defaults.
- **How It Works**:
  - `GET /date` → `DemoController.getDate()` → Adds `date1` to model → Returns `date` → Resolves to `/templates/date.html` → Thymeleaf processes → Browser renders.
- **Fixing Errors**:
  - If "Classpath resource not found": Ensure `date.html` is in `/templates/`, not `/static/`.

>[!NOTE]
>`DevTools` auto-reloads changes—save and refresh to see updates!

---

## 4. What’s Next

- **Next Session**: **9.3 - Spring MVC - Reading Form Data**—handle user input with Thymeleaf forms.

>[!TIP]
>You’ve gone dynamic with Thymeleaf—next, capture user data!

---

