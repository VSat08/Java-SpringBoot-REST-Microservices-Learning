# 5.7-REST API Design-Best Practises

## Introduction

Welcome to **5.7 - REST API Design-Best Practises**

If you’re new to coding, this is your guide to designing REST APIs the right way! We’ll explore **how to design REST APIs**—who uses them, how, and best practices to make them clean and effective. We’ll use an employee directory example and learn from real-world APIs like PayPal and GitHub, building on REST basics, JSON, path variables, and exception handling from earlier sections. Think of this as planning a user-friendly app roadmap—let’s get started! 🚀

---

## Table of Contents

1. [What Is REST API Design?](#1-what-is-rest-api-design)
   - [1.1 REST API Design: Planning for Users](#11-rest-api-design-planning-for-users)
   - [1.2 Why Design Matters](#12-why-design-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Step 1: Review Requirements](#21-step-1-review-requirements)
   - [2.2 Step 2: Identify Resources](#22-step-2-identify-resources)
   - [2.3 Step 3: Assign HTTP Methods](#23-step-3-assign-http-methods)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Employee Directory Example](#31-employee-directory-example)
   - [3.2 Anti-Patterns to Avoid](#32-anti-patterns-to-avoid)
   - [3.3 Real-World Examples](#33-real-world-examples)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices](#41-best-practices)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 What’s Next](#53-whats-next)

---

## 1. What Is REST API Design?

### 1.1 REST API Design: Planning for Users

- **Definition**: REST API design is planning how clients (users or apps) interact with your API—who uses it and how.
- **Purpose**: Creates clear, reusable endpoints for real-time projects.

#### Real-World Analogy

Think of it as designing a library—where books (resources) are, how to borrow (actions), and who visits!

### 1.2 Why Design Matters

- **Why**: Good design ensures clients can easily get, add, update, or delete data—bad design confuses everyone!
- **From Earlier**: Builds on [5.1](#51-introduction-to-restful-web-services) (REST basics), [5.4](#54-path-variables) (dynamic URLs), and [5.5-5.6](#55-rest-exception-handling) (error handling).

#### Real-World Analogy

A messy library vs. an organized one—design makes finding books (data) simple!

### 1.3 Key Terms for Beginners

Your newbie glossary:

| Term             | Meaning                                      | Example                   |
|------------------|----------------------------------------------|---------------------------|
| **Resource**     | Main "thing" in your API                    | `employee`                |
| **Entity**       | Same as resource—data object                | `employee` with ID, name  |
| **HTTP Method**  | Action type (GET, POST, etc.)               | `GET` = read              |
| **Endpoint**     | URL path to access resource                 | `/api/employees`          |
| **CRUD**         | Create, Read, Update, Delete                | Full API actions          |
| **Path Variable**| Dynamic part of URL                         | `/employees/1` (ID = 1)   |

---

## 2. Learning Roadmap

Your path to REST design mastery!

### 2.1 Step 1: Review Requirements

- **What You’ll Learn**: Understand what clients need.
- **Goal**: List API actions.

### 2.2 Step 2: Identify Resources

- **What You’ll Learn**: Pick the main "noun" (resource).
- **Goal**: Name your endpoint.

### 2.3 Step 3: Assign HTTP Methods

- **What You’ll Learn**: Map actions to HTTP methods.
- **Goal**: Build CRUD endpoints.

---

## 3. Practical Demonstration

Let’s design a REST API step-by-step!

### 3.1 Employee Directory Example

- **Step 1: Review API Requirements**:
  - **Requirement**: "Create a REST API for an employee directory" (e.g., employee ID, name).
  - **Who Uses It**: Clients (apps, users) needing employee info.
  - **How They Use It**: 
    - Get all employees.
    - Get one employee by ID.
    - Add a new employee.
    - Update an existing employee.
    - Delete an employee.
  - **Result**: Full CRUD—Create, Read, Update, Delete.

- **Step 2: Identify Main Resource**:
  - **Prominent Noun**: "Employee"—the key entity.
  - **Convention**: Use plural form → `employees`.
  - **Base Endpoint**: `/api/employees` (or just `/employees`—`/api` is optional).

- **Step 3: Assign HTTP Methods**:
  - **Actions & Endpoints**:

    | HTTP Method | Endpoint                | Action                  |
    |-------------|-------------------------|-------------------------|
    | `POST`      | `/api/employees`        | Create a new employee   |
    | `GET`       | `/api/employees`        | Read all employees      |
    | `GET`       | `/api/employees/{id}`   | Read one employee       |
    | `PUT`       | `/api/employees/{id}`   | Update an employee      |
    | `DELETE`    | `/api/employees/{id}`   | Delete an employee      |

  - **Notes**:
    - Same endpoint (`/api/employees`)—HTTP method defines action.
    - `{id}` (path variable from [5.4](#54-path-variables)) targets specific employees.
    - Matches CRUD: `POST` (Create), `GET` (Read), `PUT` (Update), `DELETE` (Delete).

>[!NOTE]
>HTTP methods + plural nouns = REST magic—simple yet powerful!

### 3.2 Anti-Patterns to Avoid

- **Bad Practices** (Anti-Patterns):
  - Don’t add actions to endpoints:
    - ❌ `/api/employeeList` (for `GET` all).
    - ❌ `/api/addEmployee` (for `POST`).
    - ❌ `/api/deleteEmployee` (for `DELETE`).
    - ❌ `/api/updateEmployee` (for `PUT`).
  - Why Bad?: Clutters URLs—HTTP methods already say the action!
- **Good Practice**:
  - ✅ `/api/employees`—use `GET`, `POST`, etc., to define action.
  - Keep endpoints noun-focused, plural, and clean.

>[!TIP]
>Let HTTP methods do the talking—keep endpoints action-free!

### 3.3 Real-World Examples

- **PayPal Invoicing API**:
  - **Create**: `POST /v1/invoicing/invoices` → Draft invoice.
  - **Read All**: `GET /v1/invoicing/invoices` → List invoices.
  - **Read One**: `GET /v1/invoicing/invoices/{invoiceId}` → Single invoice.
  - **Update**: `PUT /v1/invoicing/invoices/{invoiceId}` → Update invoice.
  - **Delete**: `DELETE /v1/invoicing/invoices/{invoiceId}` → Delete invoice.
  - **Pattern**: Same endpoint (`/invoices`), different methods—standard REST!

- **GitHub Repositories API**:
  - **Create**: `POST /user/repos` → New repository.
  - **Read All**: `GET /user/repos` → List repositories.
  - **Delete**: `DELETE /repos/{owner}/{repo}` → Delete repository.
  - **Update**: Similar pattern with `PUT` (details vary).
  - **Pattern**: `repos` (plural noun), methods define action—clean design!

>[!NOTE]
>Big players like PayPal and GitHub follow these rules—proof it works!

---

## 4. Practical Application

Make it yours!

### 4.1 Best Practices

- **User Focus**: Ask "Who uses it? How?"—design for them.
- **Plural Nouns**: Use `employees`, not `employee`—convention matters.
- **HTTP Actions**: Map `POST`, `GET`, `PUT`, `DELETE` to CRUD—keep it standard.
- **Simple Endpoints**: Avoid action words—`/api/employees` is enough.

### 4.2 Common Mistakes to Avoid

- **Action in URL**: `/api/getEmployees`? Use `GET /api/employees` instead!
- **Singular Noun**: `/api/employee`? Switch to plural `/api/employees`!
- **Wrong Method**: `POST` to update? Use `PUT`—match intent!
- **Overcomplicating**: `/api/employees/list/all`? Simplify to `/api/employees`!

### 4.3 Hands-On Exercises

Try these:

1. **Design Your API**:
   - Requirement: "Library book catalog"—list actions (get all, add, etc.)—write endpoints and methods.
2. **Fix Anti-Pattern**:
   - Rewrite `/api/bookAdd` and `/api/bookDelete`—use proper REST style.
3. **Compare APIs**:
   - Visit [developer.github.com](https://developer.github.com) and [developer.paypal.com](https://developer.paypal.com)—spot plural nouns and methods.
4. **Plan Errors**:
   - Add a "book not found" case to your library API—plan a 404 response (from [5.5](#55-rest-exception-handling)).
5. **Sketch It**:
   - Draw a table like 3.1 for a "student roster" API—methods and endpoints.

>[!TIP]
>Design like a pro—practice makes perfect!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Level up:

- **REST Basics**: [restfulapi.net](https://restfulapi.net/) - REST principles.
- **Spring REST**: [spring.io/guides/gs/rest-service](https://spring.io/guides/gs/rest-service/) - REST guide.
- **PayPal API**: [developer.paypal.com/docs/api/](https://developer.paypal.com/docs/api/) - Real example.
- **GitHub API**: [docs.github.com/en/rest](https://docs.github.com/en/rest) - Another standard.

### 5.2 Summary of Key Takeaways

- **Steps**: Review requirements → Identify resource (`employees`) → Map HTTP methods to CRUD.
- **Design**: `/api/employees`—plural, method-driven (e.g., `GET` = read, `POST` = create).
- **Avoid**: Action-based URLs—use HTTP methods instead.
- **Proof**: PayPal, GitHub use this—industry standard!

>[!TIP]
>You’re a REST designer now—build APIs clients love!

### 5.3 What’s Next

- **5.8 - Employee Directory Project**: Implement this design in code.
- **Later**: Full CRUD, databases—REST comes alive!

---
