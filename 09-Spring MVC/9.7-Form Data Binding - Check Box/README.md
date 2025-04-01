# 9.7 - Form Data Binding - Check Box

## Introduction

Welcome to **9.7 - Form Data Binding - Check Box**

In this section, we’re enhancing our Spring MVC form by adding checkboxes to collect multiple skills a student possesses. We’ll update the form to include a `skill` field, allowing users to select multiple options (e.g., "Java," "Python," "SQL") from a dynamic list sourced from `application.properties`. These selections will bind to a `Student` object as a comma-separated string and display in a neatly formatted confirmation page. This is a fantastic step for beginners to master multi-select inputs! 🖥️

---

## Table of Contents

- [1. What Is Form Data Binding with Checkboxes?](#1-what-is-form-data-binding-with-checkboxes)
  - [1.1 Overview](#11-overview)
  - [1.2 Application Flow](#12-application-flow)
  - [1.3 Key Concepts](#13-key-concepts)
  - [1.4 Key Terms for Beginners](#14-key-terms-for-beginners)
- [2. Learning Roadmap](#2-learning-roadmap)
  - [2.1 Updating the Student Bean](#21-updating-the-student-bean)
  - [2.2 Adding Checkboxes to the Form View](#22-adding-checkboxes-to-the-form-view)
  - [2.3 Processing and Displaying Multiple Selections](#23-processing-and-displaying-multiple-selections)
- [3. Practical Demonstration](#3-practical-demonstration)
  - [3.1 Setting Up the Project](#31-setting-up-the-project)
  - [3.2 Defining the Student Class](#32-defining-the-student-class)
  - [3.3 Configuring the Controller](#33-configuring-the-controller)
  - [3.4 Setting Up Application Properties](#34-setting-up-application-properties)
  - [3.5 Building the Form Page](#35-building-the-form-page)
  - [3.6 Creating the Confirmation Page](#36-creating-the-confirmation-page)
  - [3.7 Running and Testing](#37-running-and-testing)
- [4. What’s Next](#4-whats-next)

---

## 1. What Is Form Data Binding with Checkboxes?

### 1.1 Overview

- **Goal**: Add checkboxes to a form, bind multiple selected values to a Java object as a string, and display them.
- **What You’ll Build**: A web app where:
  1. A form collects first name, last name, country, gender, and skills (via checkboxes).
  2. You select "Java," "Python," and "SQL."
  3. The server binds them to a `Student` object as "Java,Python,SQL" and shows a table listing "John Doe, United States of America, Male, Skills: Java, Python, SQL."
- **Why It’s Awesome**: Checkboxes allow multiple choices—ideal for skills or preferences!
- **Tools**:
  - **Spring MVC**: Handles the app’s flow.
  - **Thymeleaf**: Renders dynamic checkboxes and a table.
  - **Java Bean**: Stores selections in a string.

#### Real-World Analogy

Imagine a job application (form) with checkboxes for skills. You check "coding," "design," and "testing" (Java, Python, SQL); the recruiter (controller) records them as "coding,design,testing" on your resume (Student object) and lists them back (confirmation)—multiple skills, one shot!

### 1.2 Application Flow

- **Steps Explained**:
  1. **Show Form**: Visit `http://localhost:8080/showStudentForm`. See `student-form.html` with text fields, a dropdown, radio buttons, and checkboxes.
  2. **Submit Form**: Enter "John" and "Doe," pick "United States of America," "Male," check "Java," "Python," "SQL," and submit to `POST /processStudentForm`.
  3. **Process Data**: Spring binds all fields, including `skill` as "Java,Python,SQL," logs them, and prepares `student-confirmation.html`.
  4. **Show Result**: Browser displays a table: "First Name: John, Last Name: Doe, Country: United States of America, Gender: Male, Skills: Java, Python, SQL"; console logs the details.
- **Diagram**:
  - Browser → `GET /showStudentForm` → Controller → `student-form.html` → Submit → `POST /processStudentForm` → Controller → `student-confirmation.html` → Browser.

### 1.3 Key Concepts

- **Checkboxes**: HTML `<input type="checkbox">`—supports multiple selections.
- **Thymeleaf Binding**: `th:field` binds checkboxes to one `Student` field (`skill`), storing values as a comma-separated string.
- **Dynamic Options**: Skills are loaded from `application.properties` and rendered with `th:each`.
- **Multi-Select Display**: Confirmation page splits the string to list skills individually.
- **Getters/Setters**:
  - **Load**: `getSkill()` for pre-filled checkboxes (if any).
  - **Submit**: `setSkill()` saves selections as a string.

> [!NOTE]
> `skill` as a string is simple but limited—`List<String>` is better for advanced use (noted later)!

### 1.4 Key Terms for Beginners

Your newbie cheat sheet—know these!

| Term                         | Meaning                                  | Example                   | Why It’s Cool         |
|------------------------------|------------------------------------------|---------------------------|-----------------------|
| **Checkbox**                 | A multi-choice input                     | `<input type="checkbox">` | Pick many options     |
| **`th:field`**               | Binds a control to an object field       | `th:field="*{skill}"`     | Auto-links data       |
| **`th:each`**                | Loops over a list or string in Thymeleaf | `th:each="s : ${skills}"` | Dynamic rendering     |
| **`@Value`**                 | Pulls data from `application.properties` | `@Value("${skills}")`     | Config-driven         |
| **Model Attribute**          | Data shared with the view                | `model.addAttribute()`    | Connects code to page |
| **Lombok**                   | Auto-generates boilerplate code          | `@Data` on `Student`      | Less typing           |
| **`application.properties`** | Config file for app settings             | `skills=Java,Python,...`  | Flexible data source  |

---

## 2. Learning Roadmap

Your step-by-step guide to checkbox binding!

### 2.1 Updating the Student Bean

- **What**: Define a `skill` field in `Student`.
- **Goal**: Store multiple checkbox selections as a string.
- **How**: Use a string field with Lombok annotations.

### 2.2 Adding Checkboxes to the Form View

- **What**: Add dynamic checkboxes to the form.
- **Goal**: Let users pick multiple skills, bound to `Student`.
- **How**: Use `th:field` and `th:each` with a skills list.

### 2.3 Processing and Displaying Multiple Selections

- **What**: Bind and display the selected skills.
- **Goal**: Log and list all chosen skills in a table.
- **How**: Use `@ModelAttribute` and format output with Thymeleaf.

---

## 3. Practical Demonstration

Let’s build `form-data-binding` with checkboxes for skills!

### 3.1 Setting Up the Project

- **Purpose**: Use your existing setup or start fresh.
- **Tool**: Eclipse (or Spring Initializr).
- **Steps** (if new):
  1. **New Project**: File → New → Spring Starter Project.
  2. **Details**:
     - Name: `FormDataBinding`.
     - Type: Maven.
     - Java Version: 17.
     - Packaging: JAR.
     - Group: `com.example`.
     - Artifact: `mvc`.
     - Package: `com.example.mvc`.
  3. **Dependencies**: Spring Web, DevTools, Thymeleaf, Lombok.
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

> [!TIP]
> Your code uses `com.example.mvc`—ensure paths match your setup!

### 3.2 Defining the Student Class

- **Purpose**: Create a bean with a `skill` field for checkboxes.
- **File**: `src/main/java/com/example/mvc/model/Student.java`.
- **Code**:
  ```java
  package com.example.mvc.model;

  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class Student {
      private String firstName;
      private String lastName;
      private String country;
      private String gender;
      private String skill;
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Data`**: Lombok adds getters, setters, etc.
  - **`@AllArgsConstructor`**: Constructor with all fields.
  - **`@NoArgsConstructor`**: Empty constructor (for Spring binding).
  - **`firstName`, `lastName`, `country`, `gender`**: Single-value fields.
  - **`skill`**: String for multiple skills (e.g., "Java,Python,SQL").
- **Why String?**: Spring joins checkbox values into one string—your choice for simplicity.

> [!NOTE]
> `skill` as a string works but may need parsing—consider `List<String>` for flexibility!

### 3.3 Configuring the Controller

- **Purpose**: Manage form display and process submissions.
- **File**: `src/main/java/com/example/mvc/controller/StudentController.java`.
- **Code**:
  ```java
  package com.example.mvc.controller;

  import java.util.List;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import com.example.mvc.model.Student;

  @Controller
  public class StudentController {
      private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

      @Value("${countries}")
      private List<String> countries;

      @Value("${skills}")
      private List<String> skills;

      @GetMapping("/showStudentForm")
      public String showForm(Model model) {
          model.addAttribute("student", new Student());
          model.addAttribute("countries", countries);
          model.addAttribute("skills", skills);
          return "student-form";
      }

      @PostMapping("/processStudentForm")
      public String processForm(@ModelAttribute("student") Student student) {
          logger.info("""
                  Student Registration Details:
                  ----------------------------
                  First Name: {}
                  Last Name:  {}
                  Country:    {}
                  Gender:     {}
                  Skills:     {}
                  """,
                  student.getFirstName(),
                  student.getLastName(),
                  student.getCountry(),
                  student.getGender(),
                  student.getSkill());
          return "student-confirmation";
      }

      @GetMapping("/")
      public String redirectToStudentApi() {
          return "redirect:/showStudentForm"; // Redirects to /showStudentForm
      }
  }
  ```
- **Line-by-Line Breakdown**:
  - **`@Value("${countries}")`**: Loads country list.
  - **`@Value("${skills}")`**: Loads skills list for checkboxes.
  - **`showForm`**: Adds empty `Student`, `countries`, and `skills` to the model.
  - **`processForm`**: Binds form data, logs it with a multi-line template, and returns the confirmation page.
  - **`logger.info`**: Logs formatted output like:
    ```
    Student Registration Details:
    ----------------------------
    First Name: John
    Last Name:  Doe
    Country:    United States of America
    Gender:     Male
    Skills:     Java,Python,SQL
    ```
  - **`redirectToStudentApi`**: Redirects root to the form.

> [!TIP]
> The multi-line `logger.info` makes debugging readable—great for forms!

### 3.4 Setting Up Application Properties

- **Purpose**: Define dynamic data for countries and skills.
- **File**: `src/main/resources/application.properties`.
- **Code**:
  ```properties
  spring.application.name=FormDataBinding
  countries=India,Sri Lanka,Brazil,United Kingdom,United States of America,Australia,South Africa
  skills=SQL,Java,Python,JavaScript,C++,C#,Ruby,Go,Swift,Kotlin,PHP,TypeScript,R,Perl,Scala,Dart,Shell,SAS,HTML,CSS,React,Angular,Vue.js,Node.js,Spring,Flask,Django,TensorFlow,PyTorch,Machine Learning,Data Science,Big Data,Cloud Computing,AWS,Azure,GCP,DevOps,Kubernetes,Docker,CI/CD,Blockchain,Cybersecurity
  ```
- **Details**:
  - **`countries=`**: Options for the dropdown.
  - **`skills=`**: Options for checkboxes—extensive list!
- **Why Here?**: External config allows easy updates without touching code.

> [!NOTE]
> No spaces after commas (e.g., `Java,Python`)—ensures clean binding!

### 3.5 Building the Form Page

- **Purpose**: Create a form with dynamic skill checkboxes.
- **File**: `src/main/resources/templates/student-form.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
      <title>Form Binding</title>
  </head>
  <body>
      <h1>Student Form</h1>
      <form th:action="@{/processStudentForm}" th:object="${student}" method="post">
          First Name: 
          <input type="text" placeholder="Your First name" th:field="*{firstName}"/>
          <br/><br/>
          Last Name: 
          <input type="text" placeholder="Your Last name" th:field="*{lastName}"/>
          <br/><br/>
          Country:
          <select th:field="*{country}">
              <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}"/>
          </select>
          <br/><br/>
          Gender: 
          <br/>
          <input id="Male" type="radio" th:value="Male" th:field="*{gender}">
          <label for="Male">Male</label>
          <input id="Female" type="radio" th:value="Female" th:field="*{gender}">
          <label for="Female">Female</label>
          <br/><br/>
          Skills
          <br/>
          <input type="checkbox" th:field="*{skill}" th:each="skill: ${skills}" 
                 th:value="${skill}" th:text="${skill}" class="m-2" />
          <br/><br/>
          <input type="submit" value="Submit"/>
      </form>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **`th:object="${student}"`**: Binds form to `Student`.
  - **`th:field="*{firstName}"`, etc.**: Text inputs.
  - **`th:field="*{country}"`**: Dynamic dropdown from `countries`.
  - **`th:field="*{gender}"`**: Radio buttons for gender.
  - **`<input type="checkbox" th:field="*{skill}" th:each="skill: ${skills}" ...>`**: Loops over `skills` for checkboxes.
  - **`th:value="${skill}"`**: Value sent (e.g., "Java").
  - **`th:text="${skill}"`**: Label displayed (e.g., "Java").
  - **`class="m-2"`**: Bootstrap margin for spacing.
- **How It Works**: `th:each` creates a checkbox per skill; checked values join into `skill`.

> [!TIP]
> Add "Rust" to `skills` in `application.properties`—it’ll show up instantly!

### 3.6 Creating the Confirmation Page

- **Purpose**: Display student details in a table, including skills.
- **File**: `src/main/resources/templates/student-confirmation.html`.
- **Code**:
  ```html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
      <title>Form Binding to an Object</title>
  </head>
  <body>
      <h1>Student Confirmed</h1>
      <table class="table">
          <tbody>
              <tr>
                  <th scope="row">First Name:</th>
                  <td><span th:text="${student.firstName}"></span></td>
              </tr>
              <tr>
                  <th scope="row">Last Name:</th>
                  <td><span th:text="${student.lastName}"></span></td>
              </tr>
              <tr>
                  <th scope="row">Country:</th>
                  <td><span th:text="${student.country}"></span></td>
              </tr>
              <tr>
                  <th scope="row">Gender:</th>
                  <td><span th:text="${student.gender}"></span></td>
              </tr>
              <tr>
                  <th scope="row">Skills:</th>
                  <td>
                      <ul class="list-unstyled">
                          <li th:each="skill : ${student.skill}" th:text="${skill}"></li>
                      </ul>
                  </td>
              </tr>
          </tbody>
      </table>
  </body>
  </html>
  ```
- **Line-by-Line Breakdown**:
  - **`<table class="table">`**: Bootstrap table for styling.
  - **`th:text="${student.firstName}"`, etc.**: Displays single fields.
  - **`th:each="skill : ${student.skill}"`**: Splits `skill` string (e.g., "Java,Python,SQL") into a list and loops over it.
  - **`<li th:text="${skill}">`**: Lists each skill (e.g., "Java").


### 3.7 Running and Testing

- **Purpose**: Verify checkbox binding and display.
- **Steps**:
  1. **Run**: Right-click `FormDataBindingApplication.java` → Run As → Spring Boot App.
     - Console: `Tomcat started on port(s): 8080`.
  2. **Test**:
     - **Step 1**: `http://localhost:8080/` (redirects to `/showStudentForm`).
       - See: Form with text fields, dropdown, radio buttons, and "Skills" checkboxes (SQL, Java, Python, etc.).
     - **Step 2**: Enter "John" and "Doe," pick "United States of America," "Male," check "Java," "Python," "SQL" → Submit.
       - Page: Table showing:
         ```
         First Name: John
         Last Name:  Doe
         Country:    United States of America
         Gender:     Male
         Skills:     Java
                     Python
                     SQL
         ```
       - Console:
         ```
         Student Registration Details:
         ----------------------------
         First Name: John
         Last Name:  Doe
         Country:    United States of America
         Gender:     Male
         Skills:     Java,Python,SQL
         ```
- **Flow**:
  - `GET /showStudentForm` → Adds `Student`, `countries`, `skills` → `student-form.html`.
  - Submit → `POST /processStudentForm` → Binds data, logs it → `student-confirmation.html`.
- **Troubleshooting**:
  - **No Data Logged?**: Verify `th:field="*{skill}"` and `skills` in properties.

> [!TIP]
> Check "C++" too—watch it join the list seamlessly with DevTools!

---

## 4. What’s Next

- **Next Session**: **9.8- Spring MVC - Form validation(Bean Validation API)**

> [!TIP]
> You’ve nailed checkboxes—Form Validation will tie it all together!

---
