# 9.6 - Form Data Binding - Radio Buttons

## Introduction

Welcome to **9.6 - Form Data Binding - Radio Buttons**

In this section, we’re building on [9.5](#95-form-data-binding---dropdown-list) by adding radio buttons to our Spring MVC form. We’ll extend the student form to include a `favoriteLanguage` field, letting users pick one programming language from options like "Java," "Go," and "Python," and bind it to our `Student` object. We’ll also explore how to populate radio buttons dynamically from `application.properties` (as an optional twist). This is a fantastic step for beginners to master single-choice inputs! 💻

---

## Table of Contents

1. [What Is Form Data Binding with Radio Buttons?](#1-what-is-form-data-binding-with-radio-buttons)
   - [1.1 Overview](#11-overview)
   - [1.2 Application Flow](#12-application-flow)
   - [1.3 Key Concepts](#13-key-concepts)
   - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Updating the Student Bean](#21-updating-the-student-bean)
   - [2.2 Adding Radio Buttons to the Form View](#22-adding-radio-buttons-to-the-form-view)
   - [2.3 Optional: Populating Radio Buttons Dynamically](#23-optional-populating-radio-buttons-dynamically)
   - [2.4 Processing and Displaying the Selection](#24-processing-and-displaying-the-selection)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Project](#31-setting-up-the-project)
   - [3.2 Updating the Student Class](#32-updating-the-student-class)
   - [3.3 Modifying the Controller](#33-modifying-the-controller)
   - [3.4 Enhancing the Form Page](#34-enhancing-the-form-page)
   - [3.5 Updating the Confirmation Page](#35-updating-the-confirmation-page)
   - [3.6 Running and Testing](#36-running-and-testing)
   - [3.7 Optional: Dynamic Radio Buttons](#37-optional-dynamic-radio-buttons)
4. [What’s Next](#4-whats-next)

---

## 1. What Is Form Data Binding with Radio Buttons?

### 1.1 Overview

- **Goal**: Add radio buttons to a form, bind the selected option to a Java object, and display it.
- **What You’ll Build**: A web app where:
  1. A form asks for first name, last name, country, and favorite programming language (via radio buttons).
  2. You pick "Java" from options like "Go," "Java," and "Python."
  3. The server binds it to a `Student` object and shows "Student is confirmed: James Gosling, United States of America, Java."
- **Why It’s Great**: Radio buttons ensure only one choice—perfect for things like gender or preferences!
- **Tools**:
  - **Spring MVC**: Manages the app’s flow.
  - **Thymeleaf**: Displays radio buttons with binding.
  - **Java Bean**: Stores the selected language.

#### Real-World Analogy

Think of radio buttons as a jukebox (form) with song options (languages). You press one button (Java), the DJ (controller) records it on your ticket (Student object), and plays it back (confirmation)—only one song at a time!

### 1.2 Application Flow

- **Steps Explained**:
  1. **Show Form**: Visit `http://localhost:8080/showStudentForm`. See `student-form.html` with text fields, a dropdown, and radio buttons.
  2. **Submit Form**: Enter "James" and "Gosling," pick "United States of America," select "Java," and submit to `POST /processStudentForm`.
  3. **Process Data**: Spring binds all fields to `Student`, logs them, and prepares `student-confirmation.html`.
  4. **Show Result**: Browser shows "Student is confirmed: James Gosling, United States of America, Java"; console logs the same.
- **Diagram**:
  - Browser → `GET /showStudentForm` → Controller → `student-form.html` → Submit → `POST /processStudentForm` → Controller → `student-confirmation.html` → Browser.

### 1.3 Key Concepts

- **Radio Buttons**: HTML `<input type="radio">`—only one can be selected from a group with the same `name`.
- **Thymeleaf Binding**: `th:field` links all radio buttons to one `Student` field (e.g., `favoriteLanguage`).
- **Single Selection**: All radio buttons share the same field name; the selected `value` gets bound.
- **Dynamic Option (Optional)**: Load values from `application.properties` using `@Value` and a `List`.
- **Getters/Setters**:
  - **Load**: `getFavoriteLanguage()` sets the default (if any).
  - **Submit**: `setFavoriteLanguage()` saves the chosen value.

>[!NOTE]
>Radio buttons enforce one choice—unlike dropdowns, they’re always visible!

### 1.4 Key Terms for Beginners

Your newbie cheat sheet—know these!

| Term                  | Meaning                                      | Example                       | Why It’s Cool          |
|-----------------------|----------------------------------------------|-------------------------------|-----------------------|
| **Radio Button**      | A single-choice input from a group           | `<input type="radio">`        | One pick only          |
| **`th:field`**        | Binds a control to an object field           | `th:field="*{favoriteLanguage}"` | Auto-links data     |
| **`th:value`**        | Sets the value sent on selection             | `th:value="'Java'"`           | Defines what’s sent    |
| **`@Value`**          | Pulls data from `application.properties`     | `@Value("${languages}")`      | Flexible config        |
| **Model Attribute**   | Data shared with the view                    | `model.addAttribute()`        | Bridges code and page  |
| **`th:each`**         | Loops over a list in Thymeleaf               | `th:each="lang : ${languages}"` | Dynamic lists       |
| **Lombok**            | Auto-generates boilerplate code              | `@Data` on `Student`          | Saves time             |

---

## 2. Learning Roadmap

Your step-by-step guide to radio button binding!

### 2.1 Updating the Student Bean

- **What**: Add a `favoriteLanguage` field to `Student`.
- **Goal**: Hold the selected radio button value.
- **How**: Add a field with Lombok handling getters/setters.

### 2.2 Adding Radio Buttons to the Form View

- **What**: Add radio buttons to the form.
- **Goal**: Let users pick one language, bound to `Student`.
- **How**: Use `th:field` with hardcoded options.

### 2.3 Optional: Populating Radio Buttons Dynamically

- **What**: Load options from `application.properties`.
- **Goal**: Make radio buttons flexible and reusable.
- **How**: Use `@Value`, a `List`, and `th:each`.

### 2.4 Processing and Displaying the Selection

- **What**: Bind and show the selected language.
- **Goal**: Confirm all fields, including `favoriteLanguage`.
- **How**: Use `@ModelAttribute` and update the confirmation page.

---

## 3. Practical Demonstration

Let’s enhance `form-data-binding` with radio buttons!

### 3.1 Setting Up the Project

- **Purpose**: Build on 9.5’s project or start anew.
- **Tool**: Eclipse (or Spring Initializr).
- **Steps** (if new):
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `form-data-binding`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `binding`.
     - Package: `com.example.binding`.
  3. **Dependencies**: Spring Web, DevTools, Thymeleaf, Lombok.
  4. **Finish**: Generate and open.
- **Result**:
  - `pom.xml` (same as 9.5):
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

>[!TIP]
>Using 9.5’s project? Just tweak the existing files—faster setup!

### 3.2 Updating the Student Class

- **Purpose**: Add a field for the radio button selection.
- **File**: `com.example.binding.model.Student.java`.
- **Code**:
  ```java
  package com.example.binding.model;

  import lombok.Data;

  @Data
  public class Student {
      private String firstName;
      private String lastName;
      private String country;
      private String favoriteLanguage;
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Data`**: Lombok auto-adds getters, setters, etc.
  - **`firstName`, `lastName`, `country`**: From 9.5.
  - **`favoriteLanguage`**: New field for the selected language (e.g., "Java").
- **Lombok Magic**: Adding `favoriteLanguage` auto-generates `getFavoriteLanguage()` and `setFavoriteLanguage()`.

>[!NOTE]
>`favoriteLanguage` must match the form’s binding—exact spelling matters!

### 3.3 Modifying the Controller

- **Purpose**: Keep the form flow intact, log the new field.
- **File**: `com.example.binding.controller.StudentController.java`.
- **Code**:
  ```java
  package com.example.binding.controller;

  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import com.example.binding.model.Student;
  import java.util.List;

  @Controller
  public class StudentController {

      private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

      @Value("${countries}")
      private List<String> countries;

      @GetMapping("/showStudentForm")
      public String showForm(Model model) {
          model.addAttribute("student", new Student());
          model.addAttribute("countries", countries);
          return "student-form";
      }

      @PostMapping("/processStudentForm")
      public String processForm(@ModelAttribute("student") Student student) {
          logger.info("Student: {} {} - Country: {} - Favorite Language: {}", 
                      student.getFirstName(), student.getLastName(), 
                      student.getCountry(), student.getFavoriteLanguage());
          return "student-confirmation";
      }
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Value("${countries}")`**: From 9.5—loads country list.
  - **`showForm`**: Adds empty `Student` (with `favoriteLanguage`) and `countries` to the model.
  - **`processForm`**: Binds all fields, including `favoriteLanguage`.
  - **`logger.info`**: Updated to log "Student: James Gosling - Country: United States of America - Favorite Language: Java".
- **No Big Changes**: Same structure as 9.5, just logs the new field.

>[!TIP]
>Keep `@Value` for countries—add `@Value("${languages}")` later if going dynamic!

### 3.4 Enhancing the Form Page

- **Purpose**: Add radio buttons for `favoriteLanguage`.
- **File**: `src/main/resources/templates/student-form.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Student Form</title>
      <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css}" />
  </head>
  <body>
      <h2>Student Form</h2>
      <form th:action="@{/processStudentForm}" th:object="${student}" method="post">
          <label>First Name: </label>
          <input type="text" th:field="*{firstName}" /><br/>
          <label>Last Name: </label>
          <input type="text" th:field="*{lastName}" /><br/>
          <label>Select Country: </label>
          <select th:field="*{country}">
              <option th:each="tempCountry : ${countries}" 
                      th:value="${tempCountry}" 
                      th:text="${tempCountry}"></option>
          </select><br/>
          <label>Favorite Programming Language: </label><br/>
          <input type="radio" th:field="*{favoriteLanguage}" th:value="'Go'" /> Go <br/>
          <input type="radio" th:field="*{favoriteLanguage}" th:value="'Java'" /> Java <br/>
          <input type="radio" th:field="*{favoriteLanguage}" th:value="'Python'" /> Python <br/>
          <input type="submit" value="Submit" />
      </form>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **`th:object="${student}"`**: Links form to `Student`.
  - **`th:field="*{firstName}"`, etc.**: From 9.5—text and dropdown.
  - **`<label>Favorite Programming Language:`**: Static text.
  - **`<input type="radio" th:field="*{favoriteLanguage}" th:value="'Go'" /> Go`**: Radio button for "Go".
  - **Same for "Java" and "Python"**: All share `th:field="*{favoriteLanguage}"`—only one gets selected.
  - **`<br/>`**: Puts each radio on a new line (optional styling).
- **How It Works**: All radio buttons bind to `favoriteLanguage`; `th:value` sets what’s sent (e.g., "Java").

>[!TIP]
>All `th:field` values must be identical for radio buttons—ensures one choice!

### 3.5 Updating the Confirmation Page

- **Purpose**: Display the selected language.
- **File**: `src/main/resources/templates/student-confirmation.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Student Confirmation</title>
      <link rel="stylesheet" th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css}" />
  </head>
  <body>
      <h2>Student is confirmed</h2>
      <p>First Name: <span th:text="${student.firstName}"></span></p>
      <p>Last Name: <span th:text="${student.lastName}"></span></p>
      <p>Country: <span th:text="${student.country}"></span></p>
      <p>Favorite Language: <span th:text="${student.favoriteLanguage}"></span></p>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **`th:text="${student.favoriteLanguage}"`**: Adds the selected language (e.g., "Java").
  - **Rest**: Same as 9.5, with one new line.

>[!NOTE]
>`favoriteLanguage` pulls the bound value—e.g., "Java" if selected!

### 3.6 Running and Testing

- **Purpose**: Confirm radio buttons bind and display.
- **Steps**:
  1. **Run**: Right-click `FormDataBindingApplication.java` → Run As → Spring Boot App.
     - Console: `Tomcat started on port(s): 8080`.
  2. **Test**:
     - **Step 1**: `http://localhost:8080/showStudentForm`.
       - See: Form with text fields, country dropdown, and "Favorite Programming Language" radio buttons (Go, Java, Python).
     - **Step 2**: Enter "James" and "Gosling," pick "United States of America," select "Java" → Submit.
       - Page: "Student is confirmed: First Name: James, Last Name: Gosling, Country: United States of America, Favorite Language: Java".
       - Console: `Student: James Gosling - Country: United States of America - Favorite Language: Java`.
- **Flow**:
  - `GET /showStudentForm` → Adds `Student` and `countries` → `student-form.html`.
  - Submit → `POST /processStudentForm` → Binds data, logs it → `student-confirmation.html`.
- **Troubleshooting**:
  - **No Selection?**: Ensure `th:field` matches `favoriteLanguage`.
  - **All Selected?**: Check that `th:field` is the same for all radio buttons.

>[!TIP]
>Pick "Python" instead—see it update instantly with DevTools!

### 3.7 Optional: Dynamic Radio Buttons

- **Purpose**: Populate radio buttons from `application.properties`.
- **Steps**:
  1. **Update `application.properties`**:
     ```properties
     countries=India,Brazil,United Kingdom,United States of America,Australia,South Africa
     languages=Go,Java,Python,Rust,TypeScript,JavaScript
     ```
  2. **Update Controller**:
     ```java
     @Value("${countries}")
     private List<String> countries;

     @Value("${languages}")
     private List<String> languages;

     @GetMapping("/showStudentForm")
     public String showForm(Model model) {
         model.addAttribute("student", new Student());
         model.addAttribute("countries", countries);
         model.addAttribute("languages", languages);
         return "student-form";
     }
     ```
  3. **Update Form**:
     ```html
     <label>Favorite Programming Language: </label><br/>
     <div th:each="tempLang : ${languages}">
         <input type="radio" th:field="*{favoriteLanguage}" 
                th:value="${tempLang}" /> <span th:text="${tempLang}"></span><br/>
     </div>
     ```
- **How It Works**:
  - **`@Value("${languages}")`**: Loads the list (e.g., "Go", "Java").
  - **`model.addAttribute("languages", languages)`**: Shares it with the view.
  - **`th:each`**: Loops over `languages`, creating a radio button per option.
- **Result**: Radio buttons for all languages in `application.properties`.

>[!TIP]
>Add "C++" to `languages`—reload and see it appear without code changes!

---

## 4. What’s Next

- **Next Session**: **9.7 - Spring MVC - Form Data Binding - Check Box**—move to multi-select options with checkboxes—more binding power awaits!

>[!TIP]
>You’ve nailed radio buttons—checkboxes are your next challenge!

---

