# Mini Project - Library Management System

## Introduction

Welcome to **Mini Project - Library Management System**!

This mini project ties together everything we’ve learned about Java Database Connectivity (JDBC) in a practical application. We’ll build a simple Library Management System using MySQL as the RDBMS, Java with JDBC API, and a `library` database featuring two tables: `authors` and `books`. With CRUD operations (Create, Read, Update, Delete) implemented across four Java classes in NetBeans, this guide offers a beginner-friendly walkthrough with a menu-driven interface. Let’s manage a library with code! 🚀

---

## Table of Contents

1. [What Is the Library Management System?](#1-what-is-the-library-management-system)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why This Mini Project Matters](#12-why-this-mini-project-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Project Structure with JDBC](#2-project-structure-with-jdbc)
   - [2.1 Overview of the Project](#21-overview-of-the-project)
   - [2.2 Database Design](#22-database-design)
   - [2.3 Java Classes and CRUD Operations](#23-java-classes-and-crud-operations)
   - [2.4 Menu-Driven Interface](#24-menu-driven-interface)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Running the Application in NetBeans](#32-running-the-application-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for the Project](#41-best-practices-for-the-project)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Is the Library Management System?

### 1.1 Definition and Purpose

The Library Management System is a mini project demonstrating JDBC by managing books and authors.

- **Definition**: A console-based Java application with a MySQL backend, featuring CRUD operations on `authors` and `books` tables linked by a primary-foreign key relationship.
- **Purpose**: Showcases database connectivity, SQL queries, and Java programming in a real-world context.

#### Real-World Analogy

Think of it as a librarian’s digital notebook—tracking authors and their books, ensuring every book has an author, and managing updates or deletions seamlessly.

### 1.2 Why This Mini Project Matters

- **Integration**: Combines JDBC, SQL, and Java concepts (e.g., prepared statements, relationships).
- **Practicality**: Simulates a real application with user interaction via a menu.
- **Learning**: Reinforces CRUD operations and database design.

#### Example Benefit

Adding a new author (e.g., “Herbert Schildt”) and linking a book (e.g., “Java Complete Reference”) dynamically.

### 1.3 Key Terms for Beginners

| Term                  | Meaning                                 | Example                         |
| --------------------- | --------------------------------------- | ------------------------------- |
| **CRUD**              | Create, Read, Update, Delete operations | Add author, list books          |
| **Primary Key**       | Uniquely identifies rows in a table     | `id` in `authors`               |
| **Foreign Key**       | Links to a primary key in another table | `author_id` in `books`          |
| **Auto-Increment**    | Automatically generates unique IDs      | `id` starts at 31               |
| **PreparedStatement** | Precompiled SQL for safety and reuse    | `INSERT INTO authors`           |
| **JDBC API**          | Java Database Connectivity interface    | `DriverManager.getConnection()` |

---

## 2. Project Structure with JDBC

### 2.1 Overview of the Project

This project uses:

- **RDBMS**: MySQL with a `library` database.
- **Tables**: `authors` (parent) and `books` (child).
- **Java Package**: `librarymanagementsystem` with four classes.
- **Features**: Menu-driven CRUD operations.

#### Prerequisites

- MySQL installed, `library` database created.
- `mysql-connector-j-8.1.0.jar` added to NetBeans.

### 2.2 Database Design

#### Definition

Two tables with a one-to-many relationship.

#### Why Use It?

Ensures data integrity (e.g., books require existing authors).

#### Instructions

- `authors`: `id` (PK, auto-increment), `name`.
- `books`: `id` (PK, auto-increment), `title`, `author_id` (FK to `authors.id`).

#### Example

```sql
CREATE TABLE authors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

### 2.3 Java Classes and CRUD Operations

#### Definition

Four classes handle connectivity and operations.

#### Why Use It?

Organizes code logically for scalability.

#### Instructions

- **DBConnector**: Manages connection.
- **Author**: CRUD for `authors`.
- **Book**: CRUD for `books`.
- **Main**: Entry point with menu.

#### Example

```java
// DBConnector
Connection cn = DriverManager.getConnection(url, uname, pwd);
// Author - Add
PreparedStatement ps = cn.prepareStatement("INSERT INTO authors (name) VALUES (?)");
ps.setString(1, "Herbert Schildt");
ps.executeUpdate();
```

### 2.4 Menu-Driven Interface

#### Definition

A console menu for user interaction.

#### Why Use It?

Simplifies execution of CRUD operations.

#### Instructions

- Options 1-4: Author CRUD.
- Options 5-8: Book CRUD.
- Option 9: Exit.

#### Example

```
Library Management System
1-Add Author  2-Update Author  3-Delete Author  4-List All Authors
5-Add Book    6-Update Book    7-Delete Book    8-List All Books
9-Exit
Enter Choice: 4
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: `library`
  ```sql
  CREATE DATABASE library;
  USE library;
  CREATE TABLE authors (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50)
  );
  CREATE TABLE books (
      id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(100),
      author_id INT,
      FOREIGN KEY (author_id) REFERENCES authors(id)
  );
  INSERT INTO authors (id, name) VALUES (31, 'Dennis Ritchie'), (32, 'Bjarne Stroustrup'), (33, 'Zed Shaw');
  INSERT INTO books (id, title, author_id) VALUES
      (31, 'C Programming', 31),
      (32, 'Java Demystified', 33),
      (34, 'OOP Using C++', 32);
  ```
- **Verification**:
  ```sql
  SHOW TABLES; -- authors, books
  SELECT * FROM authors; -- 3 rows
  SELECT * FROM books; -- 3 rows
  ```

### 3.2 Running the Application in NetBeans

- **Project**: `JDBCDemo`, package `librarymanagementsystem`.
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
- **Output** (Example Interaction):

```
#Connection Successful
Library Management System
1-Add Author  2-Update Author  3-Delete Author  4-List All Authors
5-Add Book    6-Update Book    7-Delete Book    8-List All Books
9-Exit
Enter Choice: 4
Author ID: 31    Name: Dennis Ritchie
Author ID: 32    Name: Bjarne Stroustrup
Author ID: 33    Name: Zed Shaw
Enter Choice: 8
Book ID: 31    Title: C Programming    Author ID: 31
Book ID: 32    Title: Java Demystified    Author ID: 33
Book ID: 34    Title: OOP Using C++    Author ID: 32
Enter Choice: 1
Enter Author Name: Herbert Schildt
Author Added Successfully
Enter Choice: 5
Enter Book Title: Java Complete Reference
Enter Author ID: 34
Book Added Successfully
Enter Choice: 9
Thanks for Visiting!
```

> [!NOTE] >`id` values may differ based on prior insertions/deletions due to auto-increment.

#### Verification

```sql
SELECT * FROM authors; -- Includes Herbert Schildt (34)
SELECT * FROM books; -- Includes Java Complete Reference (new id, 34)
```

---

## 4. Practical Application

### 4.1 Best Practices for the Project

- Use `PreparedStatement` to prevent SQL injection.
- Validate `author_id` exists before adding books.
- Close connections (`cn.close()`) to free resources.
- Modularize code with separate classes for clarity.

### 4.2 Common Mistakes to Avoid

- **Foreign Key Violation**: Deleting an author with linked books (e.g., ID 31) fails without cascading.
- **Hardcoding**: Avoid fixed IDs or queries—use user input.
- **No Validation**: Adding books with non-existent `author_id` breaks integrity.
- **Unclosed Resources**: Risks memory leaks without `close()`.

### 4.3 Hands-On Exercises

1. **Add Author**: Insert a new author and list all authors.
2. **Add Book**: Link a book to an existing author, verify with list.
3. **Update**: Change a book title and author name, check results.
4. **Delete**: Remove a book, then an unused author, ensure no errors.
5. **Extend**: Add a `borrowers` table and link it to `books` for a checkout feature.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **JDBC Basics**: [https://docs.oracle.com/javase/tutorial/jdbc/](https://docs.oracle.com/javase/tutorial/jdbc/)
- **MySQL Relationships**: [https://dev.mysql.com/doc/refman/8.0/en/constraint-foreign-keys.html](https://dev.mysql.com/doc/refman/8.0/en/constraint-foreign-keys.html)

### 5.2 Summary of Key Takeaways

This mini project demonstrates:

- **Database**: `library` with `authors` (PK) and `books` (FK).
- **JDBC**: Four classes (`DBConnector`, `Author`, `Book`, `Main`) for CRUD.
- **Execution**: Menu-driven interface for managing library data.
- **Key Insight**: Relationships enforce integrity, CRUD operations enable functionality.

> [!TIP]
> Enhance the project by adding input validation or a GUI for a polished experience.

### 5.3 Complete Code Reference

Below is a synthesized version based on the transcript, as the full code wasn’t provided. Replace placeholders with your actual credentials.

> [!WARNING]
> Make sure to create the `library` Database and all the required `Tables` in MySQL server before running this app.
>
> Also, the code attached is `Eclipse IDE Based code`, so avoid direct copying and running.
>
> First setup your Project in the relevant IDE and then copy the codes. Make sure to connect `MYSQL Connector` for JDBC integration.

```java
// DBConnector.java
package librarymanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private Connection cn;
    public DBConnector() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library";
        String uname = "<USERNAME>";
        String pwd = "<PASSWORD>";
        cn = DriverManager.getConnection(url, uname, pwd);
    }
    public Connection getConnection() { return cn; }
    public void closeConnection() throws Exception { cn.close(); }
}

// Author.java
package librarymanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Author {
    private Connection cn;
    public Author(Connection cn) { this.cn = cn; }

    public void addAuthor(String name) throws Exception {
        String sql = "INSERT INTO authors (name) VALUES (?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Author Added Successfully");
    }

    public void updateAuthor(int id, String name) throws Exception {
        String sql = "UPDATE authors SET name = ? WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Author Updated Successfully");
    }

    public void deleteAuthor(int id) throws Exception {
        String sql = "DELETE FROM authors WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Author Deleted Successfully");
    }

    public List<String> getAllAuthors() throws Exception {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            authors.add("Author ID: " + rs.getInt("id") + "\tName: " + rs.getString("name"));
        }
        return authors;
    }
}

// Book.java
package librarymanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private Connection cn;
    public Book(Connection cn) { this.cn = cn; }

    public void addBook(String title, int authorId) throws Exception {
        String sql = "INSERT INTO books (title, author_id) VALUES (?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setInt(2, authorId);
        ps.executeUpdate();
        System.out.println("Book Added Successfully");
    }

    public void updateBook(int id, String title, int authorId) throws Exception {
        String sql = "UPDATE books SET title = ?, author_id = ? WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setInt(2, authorId);
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("Book Updated Successfully");
    }

    public void deleteBook(int id) throws Exception {
        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Book Deleted Successfully");
    }

    public List<String> getAllBooks() throws Exception {
        List<String> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            books.add("Book ID: " + rs.getInt("id") + "\tTitle: " + rs.getString("title") + "\tAuthor ID: " + rs.getInt("author_id"));
        }
        return books;
    }
}

// Main.java
package librarymanagementsystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DBConnector connector = new DBConnector();
        Connection cn = connector.getConnection();
        System.out.println("#Connection Successful");

        Author author = new Author(cn);
        Book book = new Book(cn);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1-Add Author  2-Update Author  3-Delete Author  4-List All Authors");
            System.out.println("5-Add Book    6-Update Book    7-Delete Book    8-List All Books");
            System.out.println("9-Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Author Name: ");
                    author.addAuthor(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter Author ID: ");
                    int aId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Author Name: ");
                    author.updateAuthor(aId, sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter Author ID to Delete: ");
                    author.deleteAuthor(sc.nextInt());
                    break;
                case 4:
                    for (String a : author.getAllAuthors()) {
                        System.out.println(a);
                    }
                    break;
                case 5:
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author ID: ");
                    book.addBook(title, sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    int bId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Book Title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Enter New Author ID: ");
                    book.updateBook(bId, newTitle, sc.nextInt());
                    break;
                case 7:
                    System.out.print("Enter Book ID to Delete: ");
                    book.deleteBook(sc.nextInt());
                    break;
                case 8:
                    for (String b : book.getAllBooks()) {
                        System.out.println(b);
                    }
                    break;
                case 9:
                    System.out.println("Thanks for Visiting!");
                    connector.closeConnection();
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
        scanner.close();
		connector.closeConnection();
    }
}
```

---

_This project focuses on the JDBC library usage and implementation exposing all the possible operations performed for development. This project is just a basic implementation of Library management._

---
