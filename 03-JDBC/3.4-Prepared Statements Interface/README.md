# 3.4 - Prepared Statements Interface

## Introduction

Welcome to **3.4 - Prepared Statements Interface** 🌟! This chapter explores the `PreparedStatement` interface in Java Database Connectivity (JDBC), a powerful tool for executing parameterized SQL queries. Building on our JDBC skills, we’ll work with a `STUDENT` table in a `sampledb` database, inserting, updating, and retrieving data securely and efficiently using `PreparedStatement` in NetBeans. Designed for beginners, this guide offers clear steps, practical code, and real-world examples to master this essential interface. Let’s get started with safer, faster database operations! 🚀

---

## Table of Contents

1. [What Is the PreparedStatement Interface?](#1-what-is-the-preparedstatement-interface)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why PreparedStatement Matters](#12-why-preparedstatement-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [PreparedStatement with JDBC](#2-preparedstatement-with-jdbc)
   - [2.1 Overview of PreparedStatement in JDBC](#21-overview-of-preparedstatement-in-jdbc)
   - [2.2 Creating a PreparedStatement](#22-creating-a-preparedstatement)
   - [2.3 Setting Parameters](#23-setting-parameters)
   - [2.4 Executing Queries](#24-executing-queries)
   - [2.5 Comparing Statement vs. PreparedStatement](#25-comparing-statement-vs-preparedstatement)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Using PreparedStatement in NetBeans](#32-using-preparedstatement-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for PreparedStatement](#41-best-practices-for-preparedstatement)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Is the PreparedStatement Interface?

### 1.1 Definition and Purpose

The `PreparedStatement` interface in JDBC allows you to execute precompiled SQL queries with placeholders (`?`) for parameters.

- **Definition**: An interface extending `Statement`, it uses `?` placeholders in queries, filled dynamically with setter methods (e.g., `setInt()`, `setString()`).
- **Purpose**: Enhances security (prevents SQL injection), improves performance (query reuse), and supports parameterized queries.

#### Real-World Analogy

Think of `PreparedStatement` as a form with blank fields (`?`). You fill in the blanks (parameters) before submitting it, keeping the structure safe and reusable.

### 1.2 Why PreparedStatement Matters

- **Security**: Blocks SQL injection attacks (e.g., `WHERE sID = 100 OR 1=1`) by separating query logic from data.
- **Performance**: Precompiles queries for repeated execution, reducing overhead.
- **Flexibility**: Accepts runtime inputs (e.g., user-entered data).

#### Example Benefit

Safely inserting a student (`200, "Simon", 8.8`) or updating a name without risking injection.

### 1.3 Key Terms for Beginners

| Term                  | Meaning                                      | Example                     |
|-----------------------|----------------------------------------------|-----------------------------|
| **PreparedStatement** | Precompiled SQL query with placeholders      | `INSERT INTO STUDENT VALUES (?,?,?)` |
| **Placeholder (`?`)** | Marks a parameter position                   | `WHERE sID = ?`             |
| **setInt()**          | Sets an integer parameter                    | `pst.setInt(1, 200)`        |
| **setString()**       | Sets a string parameter                      | `pst.setString(2, "Simon")` |
| **SQL Injection**     | Malicious query manipulation                 | `sID = 100 OR 1=1`          |
| **executeUpdate()**   | Executes INSERT/UPDATE, returns row count    | `pst.executeUpdate()`       |

---

## 2. PreparedStatement with JDBC

### 2.1 Overview of PreparedStatement in JDBC

`PreparedStatement` enhances JDBC by:
- Using `Connection.prepareStatement()` to create a precompiled query.
- Setting parameters with methods like `setInt()`, `setString()`, `setDouble()`.
- Executing via `executeUpdate()` (INSERT/UPDATE) or `executeQuery()` (SELECT).

#### Prerequisites

- A table exists (e.g., `STUDENT` with `sID`, `sName`, `CGPA`).
- JDBC connection established (`java.sql.*` imported).

### 2.2 Creating a PreparedStatement

#### Definition

Prepares a query with `?` placeholders instead of hardcoded values.

#### Why Use It?

Allows dynamic data input (e.g., user-provided values).

#### Instructions

- Define a query string with `?` for each parameter.
- Pass it to `Connection.prepareStatement()`.

#### Example

```java
String query = "INSERT INTO STUDENT VALUES (?,?,?)";
PreparedStatement pst = cn.prepareStatement(query);
```

### 2.3 Setting Parameters

#### Definition

Fills `?` placeholders with values using setter methods.

#### Why Use It?

Customizes the query safely at runtime.

#### Instructions

- Use `setType(position, value)` (e.g., `setInt(1, 200)` for first `?`).
- Match data types: `int` → `setInt()`, `String` → `setString()`, `double` → `setDouble()`.

#### Example

```java
pst.setInt(1, 200);
pst.setString(2, "Simon");
pst.setDouble(3, 8.8);
```

### 2.4 Executing Queries

#### Definition

Runs the prepared query with set parameters.

#### Why Use It?

Performs database operations (e.g., INSERT, UPDATE, SELECT).

#### Instructions

- Use `executeUpdate()` for INSERT/UPDATE (returns rows affected).
- Use `executeQuery()` for SELECT (returns `ResultSet`).

#### Example

```java
int x = pst.executeUpdate(); // INSERT
ResultSet rs = pst.executeQuery("SELECT * FROM STUDENT"); // SELECT
```

### 2.5 Comparing Statement vs. PreparedStatement

| Feature               | Statement                  | PreparedStatement             |
|-----------------------|----------------------------|-------------------------------|
| **Query Type**        | Fully compiled             | Parameterized (`?`)           |
| **SQL Injection**     | Vulnerable                 | Protected                     |
| **Performance**       | Slower (recompiles)        | Faster (precompiled)          |
| **Use Case**          | Simple, static queries     | Dynamic, repeated queries     |
| **Example**           | `SELECT * FROM STUDENT`    | `INSERT INTO STUDENT VALUES (?,?,?)` |

!NOTE: `PreparedStatement` is recommended over `Statement` for real-world applications.

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: Replace `<DATABASE_NAME>` with your database (e.g., `sampledb`).
- **Target Table**: `STUDENT`:
  - Columns: `sID` (INT, PRIMARY KEY), `sName` (VARCHAR(20)), `CGPA` (FLOAT).
- **Sample Data**:
  ```sql
  INSERT INTO STUDENT VALUES (101, 'Kris', 8.7), (123, 'Sara', 9.5);
  ```
- **Verification**:
  ```sql
  USE <DATABASE_NAME>;
  SHOW TABLES; -- Lists STUDENT
  SELECT * FROM STUDENT; -- Shows initial rows
  ```

### 3.2 Using PreparedStatement in NetBeans

- **Project**: `JDBCDemo` (assumed setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
- **Output** (Example with user input: `200, "Simon", 8.8`):
```
--------------- Connection Successfull ---------------
## Enter Student Details
ID: 200
Name: Simon
CGPA: 8.8
1 Record(s) Inserted.
1 Record(s) Updated.
200	Simon	8.8
123	Sara	9.5
101	Carl	8.7
```

>[!NOTE]
>Assumes `STUDENT` table exists. Update `sID` values (e.g., 200) on re-runs to avoid primary key conflicts.

#### Verification

```sql
SELECT * FROM STUDENT; -- Shows updated rows (e.g., Simon added, Kris → Carl)
```

---

## 4. Practical Application

### 4.1 Best Practices for PreparedStatement

- Use `?` placeholders for all dynamic values.
- Match setter methods to column data types (e.g., `setDouble()` for `CGPA`).
- Reuse `PreparedStatement` objects for multiple executions (e.g., in a loop).
- Close `Connection` and `Scanner` to free resources.

### 4.2 Common Mistakes to Avoid

- **Hardcoding Values**: Defeats the purpose (e.g., `INSERT INTO STUDENT VALUES (200, 'Simon', 8.8)`).
- **Wrong Index**: `setInt(0, 200)` fails—indices start at 1.
- **Primary Key Conflicts**: Reusing `sID` (e.g., 200) causes errors.
- **Type Mismatch**: `setString()` on an `int` column throws exceptions.

### 4.3 Hands-On Exercises

1. **Insert**: Add a student with user input for `sID`, `sName`, and `CGPA`.
2. **Update**: Change a student’s `CGPA` where `sID` matches a user-entered value.
3. **Read**: Fetch students with `CGPA > ?` (user-provided threshold).
4. **Loop Insert**: Insert multiple students in a loop using one `PreparedStatement`.
5. **Combined**: Insert, update, and read in one program, verify with MySQL.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **JDBC PreparedStatement**: [https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html](https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
- **MySQL Reference**: [https://dev.mysql.com/doc/refman/8.0/en/](https://dev.mysql.com/doc/refman/8.0/en/)

### 5.2 Summary of Key Takeaways

This guide demonstrates `PreparedStatement` on the `STUDENT` table:
- **Insert**: Added a student (e.g., `200, "Simon", 8.8`) with user input.
- **Update**: Changed `sName` (e.g., `101, "Kris"` to `"Carl"`).
- **Read**: Retrieved all students securely.
- **Key Insight**: Uses `?` placeholders and setters to prevent SQL injection and boost performance.

>[!TIP]
>Experiment with runtime inputs (e.g., `Scanner`) to see the full power of parameterization.

### 5.3 Complete Code Reference

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatements {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
        String uname = "<USERNAME>";
        String pwd = "<PASSWORD>";

        Connection cn = DriverManager.getConnection(url, uname, pwd);
        if (cn != null) {
            System.out.println("--------------- Connection Successfull ---------------");
        } else {
            System.out.println("--------------- Connection Failure---------------");
        }

        // --------------- INSERT USING Prepared Statements ---------------
        String query = "INSERT INTO STUDENT VALUES (?,?,?)";
        PreparedStatement pst = cn.prepareStatement(query);
        // Assuming data and hard filling (optional)
        // pst.setInt(1, 200);
        // pst.setString(2, "Simon");
        // pst.setDouble(3, 8.8);

        System.out.println("## Enter Student Details");
        System.out.print("\nID: ");
        int sId = in.nextInt();
        System.out.print("\nName: ");
        String sName = in.next();
        System.out.print("\nCGPA: ");
        double cgpa = in.nextDouble();

        // User-entered data
        pst.setInt(1, sId);
        pst.setString(2, sName);
        pst.setDouble(3, cgpa);

        int x = pst.executeUpdate();
        if (x > 0) {
            System.out.println(x + " Record(s) Inserted.");
        }

        // --------------- UPDATE USING Prepared Statements ---------------
        pst = cn.prepareStatement("UPDATE STUDENT SET sName = ? WHERE sId = ?");
        pst.setString(1, "Carl");
        pst.setInt(2, 101);
        int y = pst.executeUpdate();
        if (y > 0) {
            System.out.println(y + " Record(s) Updated.");
        }

        // --------------- READ USING Prepared Statements ---------------
        ResultSet rs = pst.executeQuery("SELECT * FROM STUDENT");
        while (rs.next()) {
            System.out.println(rs.getInt("sID") + "\t" + rs.getString("sName") + "\t" + rs.getDouble(3));
        }

        cn.close();
        in.close();
    }
}
```

---

_This README provides an in-depth illustration of the PreparedStatement Interface using JDBC, mirroring the structure and detail of the 3.2 CRUD README._

---
