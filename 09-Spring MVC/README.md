
# 03-Spring Boot\03 - Spring MVC

## Introduction

Welcome to **03 - Spring MVC** 🌟! This section dives into the Model-View-Controller (MVC) pattern using Spring Boot, perfect for building interactive web apps. Designed for beginners, this roadmap covers controllers, views with Thymeleaf, and form handling. Let’s create dynamic web pages together! 🚀

---

## Table of Contents

1. [What Is Spring MVC?](#1-what-is-spring-mvc)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why Spring MVC Matters](#12-why-spring-mvc-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 MVC Pattern](#21-mvc-pattern)
   - [2.2 Writing Controllers](#22-writing-controllers)
   - [2.3 Thymeleaf Template Engine](#23-thymeleaf-template-engine)
   - [2.4 Request Mapping and Parameters](#24-request-mapping-and-parameters)
   - [2.5 Form Handling and Lombok](#25-form-handling-and-lombok)
3. [What You’ll Build](#3-what-youll-build)
4. [Next Steps](#4-next-steps)

---

## 1. What Is Spring MVC?

### 1.1 Definition and Purpose

Spring MVC is a framework for building web applications using the MVC pattern.

- **Definition**: Separates logic (Model), UI (View), and requests (Controller).
- **Purpose**: Creates structured, maintainable web apps.

#### Real-World Analogy

Imagine a restaurant: the chef (Model) prepares food, the waiter (Controller) takes orders, and the plate (View) presents it.

### 1.2 Why Spring MVC Matters

- **Organization**: Keeps code modular.
- **Flexibility**: Supports various view technologies.
- **User Interaction**: Handles forms and requests easily.

#### Example Benefit

Display a book list on a webpage with clickable updates.

### 1.3 Key Terms for Beginners

| Term              | Meaning                                      | Example                     |
|-------------------|----------------------------------------------|-----------------------------|
| **MVC**           | Model-View-Controller pattern                | Data, UI, logic separation  |
| **Controller**    | Handles requests and responses               | `@Controller` class         |
| **Thymeleaf**     | Template engine for views                    | `<h1 th:text="${title}">`   |
| **Request Mapping** | Maps URLs to methods                     | `@GetMapping("/books")`     |
| **Lombok**        | Simplifies Java beans                        | `@Data` annotation          |

---

## 2. Learning Roadmap

### 2.1 MVC Pattern

- **What You’ll Learn**: How Model, View, and Controller work together.
- **Goal**: Understand MVC’s structure.

### 2.2 Writing Controllers

- **What You’ll Learn**: Creating controllers to handle requests.
- **Goal**: Manage web interactions.

### 2.3 Thymeleaf Template Engine

- **What You’ll Learn**: Using Thymeleaf for dynamic views.
- **Goal**: Render HTML with data.

### 2.4 Request Mapping and Parameters

- **What You’ll Learn**: Mapping URLs and handling inputs.
- **Goal**: Process user requests accurately.

### 2.5 Form Handling and Lombok

- **What You’ll Learn**: Managing forms and simplifying beans with Lombok.
- **Goal**: Build interactive forms efficiently.

---

## 3. What You’ll Build

You’ll create a Spring MVC app with:
- A controller for `/books`.
- A Thymeleaf page listing books.
- A form to add books using Lombok beans.

>[!NOTE]
>Your first webpage will feel like magic!

---

## 4. Next Steps

Onward to **Spring Boot Data Access with JPA**! You’ll connect your app to a database for persistent data.

---