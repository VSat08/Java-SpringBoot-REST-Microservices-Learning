# 9.8 - Form Validation (Bean Validation API)

## Introduction

Welcome to **9.8 - Form Validation (Bean Validation API)**

In this section, we’re adding form validation to our Spring MVC application using the **Bean Validation API**. Building on [9.7 - Form Data Binding - Check Box](#97-form-data-binding---check-box), we’ll validate a `Customer` form with fields like `lastName` (required, minimum size), `freePasses` (numeric range), and `postalCode` (regular expression pattern). Errors will display in red on the form if validation fails—perfect for ensuring clean data before submission! 🖥️

---

## Table of Contents

- [1. What Is Form Validation with Bean Validation API?](#1-what-is-form-validation-with-bean-validation-api)
  - [1.1 Overview](#11-overview)
  - [1.2 Application Flow](#12-application-flow)
  - [1.3 Key Concepts](#13-key-concepts)
  - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
- [2. Learning Roadmap](#2-learning-roadmap)
  - [2.1 Setting Up Validation Dependencies](#21-setting-up-validation-dependencies)
  - [2.2 Defining Validation Rules in the Bean](#22-defining-validation-rules-in-the-bean)
  - [2.3 Enhancing the Controller with Validation](#23-enhancing-the-controller-with-validation)
  - [2.4 Building the Form with Error Display](#24-building-the-form-with-error-display)
  - [2.5 Creating the Confirmation Page](#25-creating-the-confirmation-page)
- [3. Practical Demonstration](#3-practical-demonstration)
  - [3.1 Setting Up the Project](#31-setting-up-the-project)
  - [3.2 Creating the Customer Class](#32-creating-the-customer-class)
  - [3.3 Configuring the Customer Controller](#33-configuring-the-customer-controller)
  - [3.4 Styling Errors with CSS](#34-styling-errors-with-css)
  - [3.5 Building the Customer Form](#35-building-the-customer-form)
  - [3.6 Creating the Confirmation Page](#36-creating-the-confirmation-page)
  - [3.7 Running and Testing](#37-running-and-testing)
- [4. What’s Next](#4-whats-next)

---

## 1. What Is Form Validation with Bean Validation API?

### 1.1 Overview

- **Goal**: Validate form inputs before submission, ensuring data meets rules (e.g., required fields, size limits, patterns).
- **What You’ll Build**: A web app where:
  1. A form asks for `firstName`, `lastName` (required, min 2 chars), `freePasses` (0-10), and `postalCode` (5 chars/digits).
  2. Submit with errors (e.g., no `lastName`) → Red error messages appear on the form.
  3. Submit with valid data (e.g., "James Gosling, 5, ABCDE") → See confirmation page.
- **Why It’s Awesome**: Prevents bad data (e.g., empty fields, invalid numbers) from hitting the server—saves processing time!
- **Tools**:
  - **Bean Validation API**: Java standard for validation (via annotations).
  - **Spring MVC**: Integrates validation with forms.
  - **Thymeleaf**: Displays errors dynamically.

#### Real-World Analogy

Think of a library card form (form). You must fill in your last name (`lastName`), pick 0-10 books to borrow (`freePasses`), and enter a 5-digit ZIP code (`postalCode`). The librarian (controller) checks your entries—miss something, and you fix it with red notes before borrowing books (confirmation)!

### 1.2 Application Flow

- **Steps Explained**:
  1. **Show Form**: Visit `http://localhost:8080/showForm`. See `customer-form.html` with fields and validation hints (e.g., `*` for required).
  2. **Submit Form**: Enter "James," leave `lastName` blank, "5," "ABCDE" → Submit to `POST /processForm`.
  3. **Validate**: Spring checks rules:
     - `lastName` blank → Errors: "Last name is required" and "Last name size >= 2 is required."
     - Form reloads with red messages.
  4. **Success**: Fix to "James," "Gosling," "5," "ABCDE" → Submit → See `customer-confirmation.html` with all details.
- **Diagram**:
  - Browser → `GET /showForm` → Controller → `customer-form.html` → Submit → `POST /processForm` → Validate → Errors? → Form / Success → `customer-confirmation.html`.

### 1.3 Key Concepts

- **Bean Validation API**: Java standard (JSR-380) for validating objects with annotations (e.g., `@NotNull`).
- **Annotations**: Rules on bean fields (e.g., `@Size`, `@Min`, `@Pattern`).
- **Spring Integration**: `@Valid` triggers validation; `BindingResult` holds errors.
- **Thymeleaf Errors**: `th:if="${#fields.hasErrors('field')}"` shows error messages.
- **CSS Styling**: Custom styles (e.g., red text) highlight errors.

> [!NOTE]
> Validation stops bad data early—before it hits your server logic!

### 1.4 Key Terms for Beginners

Your newbie glossary—master these!

| Term                  | Meaning                                      | Example                       | Why It’s Cool          |
|-----------------------|----------------------------------------------|-------------------------------|-----------------------|
| **Bean Validation**   | Java API for validating objects              | `@NotNull` on `lastName`      | Standardizes rules     |
| **`@NotNull`**        | Field can’t be null                         | `lastName` must exist         | Ensures data presence  |
| **`@Size`**           | Limits string length                        | `min=2` for `lastName`        | Controls input size    |
| **`@Min`/`@Max`**     | Sets numeric range                          | `0-10` for `freePasses`       | Bounds numbers         |
| **`@Pattern`**        | Matches a regex pattern                     | `^[a-zA-Z0-9]{5}$` for code   | Flexible validation    |
| **`@Valid`**          | Triggers validation in controller           | `@Valid Customer`             | Easy integration       |
| **`BindingResult`**   | Holds validation errors                     | `result.hasErrors()`          | Error handling         |
| **`th:errors`**       | Displays error messages                     | `<span th:errors="*{field}">` | Dynamic feedback       |

---

## 2. Learning Roadmap

Your path to validation mastery!

### 2.1 Setting Up Validation Dependencies

- **What**: Add the Bean Validation API to your project.
- **Goal**: Enable validation annotations.
- **How**: Include `spring-boot-starter-validation` in `pom.xml`.

### 2.2 Defining Validation Rules in the Bean

- **What**: Add rules to the `Customer` class.
- **Goal**: Enforce `lastName` (required, min 2), `freePasses` (0-10), `postalCode` (5 chars).
- **How**: Use `@NotNull`, `@Size`, `@Min`, `@Max`, `@Pattern`.

### 2.3 Enhancing the Controller with Validation

- **What**: Check form data in the controller.
- **Goal**: Reject invalid submissions, show errors, or confirm valid ones.
- **How**: Use `@Valid` and `BindingResult`.

### 2.4 Building the Form with Error Display

- **What**: Create a Thymeleaf form with error feedback.
- **Goal**: Show red error messages if validation fails.
- **How**: Use `th:errors` and custom CSS.

### 2.5 Creating the Confirmation Page

- **What**: Display validated data.
- **Goal**: Show all customer details on success.
- **How**: Use Thymeleaf with Bootstrap styling.

---

## 3. Practical Demonstration

Let’s build a `form-validation-demo` app with Bean Validation!

### 3.1 Setting Up the Project

- **Purpose**: Start fresh or extend 9.7’s project.
- **Tool**: Eclipse (or Spring Initializr).
- **Steps**:
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `form-validation-demo`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `validation`.
     - Package: `com.example.validation`.
  3. **Dependencies**: Spring Web, DevTools, Thymeleaf, Lombok, **Validation**.
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
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ```
- **Key Addition**: `<spring-boot-starter-validation>` brings in the Bean Validation API.

> [!TIP]
> Check `pom.xml`—missing `validation` dependency? No annotations will work!

### 3.2 Creating the Customer Class

- **Purpose**: Define the bean with validation rules.
- **File**: `src/main/java/com/example/validation/model/Customer.java`.
- **Code**:
  ```java
  package com.example.validation.model;

  import jakarta.validation.constraints.NotNull;
  import jakarta.validation.constraints.Size;
  import jakarta.validation.constraints.Min;
  import jakarta.validation.constraints.Max;
  import jakarta.validation.constraints.Pattern;
  import lombok.Data;

  @Data
  public class Customer {
      private String firstName;

      @NotNull(message = "Last name is required")
      @Size(min = 2, message = "Last name size >= 2 is required")
      private String lastName;

      @Min(value = 0, message = "Must be greater than or equal to 0")
      @Max(value = 10, message = "Must be less than or equal to 10")
      private int freePasses;

      @Pattern(regexp = "^[a-zA-Z0-9]{5}$", message = "Only 5 characters or digits")
      private String postalCode;
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Data`**: Lombok adds getters, setters, and constructors.
  - **`firstName`**: No validation—optional field.
  - **`@NotNull`**: `lastName` must exist.
  - **`@Size(min = 2)`**: `lastName` needs 2+ characters.
  - **`@Min(0)`**: `freePasses` can’t be negative.
  - **`@Max(10)`**: `freePasses` can’t exceed 10.
  - **`@Pattern`**: `postalCode` must be exactly 5 alphanumeric chars (e.g., "ABC12").
- **Why These Rules?**: Matches transcript—required field, size, range, and pattern.

> [!NOTE]
> `message` in annotations customizes error text—keep it clear!

### 3.3 Configuring the Customer Controller

- **Purpose**: Handle form display and validation.
- **File**: `src/main/java/com/example/validation/controller/CustomerController.java`.
- **Code**:
  ```java
  package com.example.validation.controller;

  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.validation.BindingResult;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import com.example.validation.model.Customer;
  import jakarta.validation.Valid;

  @Controller
  public class CustomerController {
      private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

      @GetMapping("/showForm")
      public String showForm(Model model) {
          model.addAttribute("customer", new Customer());
          return "customer-form";
      }

      @PostMapping("/processForm")
      public String processForm(@Valid @ModelAttribute("customer") Customer customer, 
                                BindingResult bindingResult) {
          if (bindingResult.hasErrors()) {
              return "customer-form"; // Reload form with errors
          }
          logger.info("Customer: {} {}, Free Passes: {}, Postal Code: {}", 
                      customer.getFirstName(), customer.getLastName(), 
                      customer.getFreePasses(), customer.getPostalCode());
          return "customer-confirmation";
      }

      @GetMapping("/")
      public String redirectToForm() {
          return "redirect:/showForm";
      }
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@GetMapping("/showForm")`**: Adds empty `Customer` to model, shows form.
  - **`@PostMapping("/processForm")`**: Validates and processes submission.
  - **`@Valid`**: Triggers Bean Validation on `customer`.
  - **`BindingResult`**: Stores validation errors.
  - **`if (bindingResult.hasErrors())`**: Returns form if errors exist.
  - **`logger.info`**: Logs valid data (e.g., "Customer: James Gosling, Free Passes: 5, Postal Code: ABCDE").
  - **`redirectToForm`**: Root URL redirects to form.
- **Key Change**: Validation logic with `@Valid` and `BindingResult`.

> [!TIP]
> `BindingResult` must follow `@Valid` parameter—order matters!

### 3.4 Styling Errors with CSS

- **Purpose**: Make error messages red and styled.
- **File**: `src/main/resources/static/css/demo.css`.
- **Code**:
  ```css
  .error {
      color: red;
      border: 1px solid red;
      margin: 5px;
      padding: 5px;
      background-color: #ffe6e6;
  }
  ```
- **Details**:
  - **`color: red`**: Red text for visibility.
  - **`border`**: Red outline.
  - **`margin`, `padding`**: Spacing for readability.
  - **`background-color`**: Light red background.
- **Why Custom CSS?**: Transcript uses this over Bootstrap for errors.

> [!NOTE]
> Place `demo.css` in `static/css/`—Spring serves it automatically!

### 3.5 Building the Customer Form

- **Purpose**: Create a form with validation feedback.
- **File**: `src/main/resources/templates/customer-form.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <title>Customer Form</title>
      <link rel="stylesheet" th:href="@{/css/demo.css}">
  </head>
  <body>
      <h1>Customer Form</h1>
      <form th:action="@{/processForm}" th:object="${customer}" method="post">
          <label>First Name: </label>
          <input type="text" th:field="*{firstName}"><br/>

          <label>Last Name: <span style="color:red">*</span></label>
          <input type="text" th:field="*{lastName}">
          <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}" class="error"></span><br/>

          <label>Free Passes: </label>
          <input type="number" th:field="*{freePasses}">
          <span th:if="${#fields.hasErrors('freePasses')}" th:errors="*{freePasses}" class="error"></span><br/>

          <label>Postal Code: </label>
          <input type="text" th:field="*{postalCode}">
          <span th:if="${#fields.hasErrors('postalCode')}" th:errors="*{postalCode}" class="error"></span><br/>

          <input type="submit" value="Submit">
      </form>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **`th:href="@{/css/demo.css}"`**: Links custom CSS.
  - **`th:object="${customer}"`**: Binds form to `Customer`.
  - **`th:field="*{firstName}"`**: Maps to `firstName` (no validation).
  - **`*`**: Red asterisk marks `lastName` as required.
  - **`th:if="${#fields.hasErrors('lastName')}"`**: Shows errors if `lastName` fails.
  - **`th:errors="*{lastName}"`**: Displays error message (e.g., "Last name is required").
  - **`class="error"`**: Applies red styling.
  - **Same for `freePasses`, `postalCode`**: Number input and pattern validation.
- **How It Works**: Errors appear below fields if validation fails.

> [!TIP]
> Test with blank `lastName`—see both `@NotNull` and `@Size` errors stack!

### 3.6 Creating the Confirmation Page

- **Purpose**: Show validated customer data.
- **File**: `src/main/resources/templates/customer-confirmation.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <title>Customer Confirmation</title>
      <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css}">
  </head>
  <body>
      <h1>Customer is Confirmed</h1>
      <div class="container">
          <p>First Name: <span th:text="${customer.firstName}"></span></p>
          <p>Last Name: <span th:text="${customer.lastName}"></span></p>
          <p>Free Passes: <span th:text="${customer.freePasses}"></span></p>
          <p>Postal Code: <span th:text="${customer.postalCode}"></span></p>
      </div>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **Bootstrap CSS**: Styles the page (transcript choice).
  - **`th:text="${customer.firstName}"`**: Displays validated data.
  - **No Errors Here**: Only shows up if validation passes.
- **Why Simple?**: Focus is on validation, not styling.

> [!NOTE]
> Bootstrap here, custom CSS on form—mixes styles per transcript!

### 3.7 Running and Testing

- **Purpose**: Verify validation works as expected.
- **Steps**:
  1. **Run**: Right-click `FormValidationDemoApplication.java` → Run As → Spring Boot App.
     - Console: `Tomcat started on port(s): 8080`.
  2. **Test Cases**:
     - **Valid Input**:
       - `http://localhost:8080/` → "James," "Gosling," "5," "ABCDE" → Submit.
       - Page: "Customer is Confirmed: James, Gosling, 5, ABCDE".
       - Console: `Customer: James Gosling, Free Passes: 5, Postal Code: ABCDE`.
     - **Missing Last Name**:
       - "James," blank, "5," "12345" → Submit.
       - Form reloads: "Last name is required" and "Last name size >= 2 is required" in red.
     - **Invalid Free Passes**:
       - "James," "Gosling," "-1," "ABCDE" → "Must be greater than or equal to 0".
       - "20" → "Must be less than or equal to 10".
     - **Invalid Postal Code**:
       - "James," "Gosling," "5," "ABCD" → "Only 5 characters or digits".
       - "ABCDEF" → Same error.
- **Troubleshooting**:
  - **No Errors Shown?**: Check `spring-boot-starter-validation` in `pom.xml` and `@Valid` in controller.
  - **Errors Not Red?**: Verify `demo.css` path (`/css/demo.css`).

> [!TIP]
> Enter "A" for `lastName`—see size error alone; add blank—see both!

---

## 4. What’s Next

- **Next Session**: Start a real-time MVC project with CRUD operations (Employee Management System)—validation will shine there!

> [!TIP]
> You’ve conquered validation—CRUD will make it practical!

---
