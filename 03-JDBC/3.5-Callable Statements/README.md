# 3.5 - Callable Statements

## Introduction

Welcome to **3.5 - Callable Statements** 🌟! This chapter dives into the `CallableStatement` interface in Java Database Connectivity (JDBC), designed to execute stored procedures and functions in a MySQL database. Building on our JDBC knowledge, we’ll call a stored procedure (`first_proc`) and a stored function (`add_ab`) from a `sampledb` database using NetBeans. Tailored for beginners, this guide provides step-by-step instructions, practical code, and examples to master this advanced JDBC feature. Let’s unlock the power of stored logic with `CallableStatement`! 🚀

---

## Table of Contents

1. [What Is the CallableStatement Interface?](#1-what-is-the-callablestatement-interface)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why CallableStatement Matters](#12-why-callablestatement-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [CallableStatement with JDBC](#2-callablestatement-with-jdbc)
   - [2.1 Overview of CallableStatement in JDBC](#21-overview-of-callablestatement-in-jdbc)
   - [2.2 Stored Procedures vs. Functions](#22-stored-procedures-vs-functions)
   - [2.3 Creating a CallableStatement](#23-creating-a-callablestatement)
   - [2.4 Setting Parameters](#24-setting-parameters)
   - [2.5 Executing and Retrieving Results](#25-executing-and-retrieving-results)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Using CallableStatement in NetBeans](#32-using-callablestatement-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for CallableStatement](#41-best-practices-for-callablestatement)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Is the CallableStatement Interface?

### 1.1 Definition and Purpose

The `CallableStatement` interface in JDBC enables calling stored procedures and functions stored in a database.

- **Definition**: Extends `PreparedStatement` to execute precompiled, reusable SQL logic (procedures/functions) with input/output parameters.
- **Purpose**: Runs complex database tasks (e.g., squaring a number, summing values) defined in PL/SQL, integrating them into Java programs.

#### Real-World Analogy

Think of `CallableStatement` as a remote control for pre-programmed database routines—like pressing "square" or "add" to get results without rewriting the logic.

### 1.2 Why CallableStatement Matters

- **Reusability**: Stored logic can be called multiple times without redefinition.
- **Efficiency**: Executes server-side, reducing network overhead.
- **Complexity**: Handles tasks (e.g., loops, conditionals) beyond basic SQL.

#### Example Benefit

Calling a procedure to square `25` (returns `625`) or a function to add `123 + 321` (returns `444`) directly from Java.

### 1.3 Key Terms for Beginners

| Term                  | Meaning                                      | Example                     |
|-----------------------|----------------------------------------------|-----------------------------|
| **CallableStatement** | Executes stored procedures/functions         | `{call first_proc(?,?)}`    |
| **Stored Procedure**  | SQL task set, may not return a value         | `first_proc` (squares `a`)  |
| **Stored Function**   | SQL task set, returns a value                | `add_ab` (sums `a + b`)     |
| **Delimiter**         | Marks multi-line SQL boundaries              | `$$`                        |
| **registerOutParameter()** | Registers an output parameter            | `cst.registerOutParameter(1, Types.INTEGER)` |
| **execute()**         | Runs stored logic                            | `cst.execute()`             |

---

## 2. CallableStatement with JDBC

### 2.1 Overview of CallableStatement in JDBC

`CallableStatement` enhances JDBC by:
- Using `Connection.prepareCall()` to invoke stored procedures/functions.
- Setting input parameters (e.g., `setInt()`) and registering output parameters (e.g., `registerOutParameter()`).
- Executing via `execute()` and retrieving results with getters (e.g., `getInt()`).

#### Prerequisites

- Stored procedures/functions defined in the database.
- JDBC connection established (`java.sql.*` imported).

### 2.2 Stored Procedures vs. Functions

| Feature              | Stored Procedure            | Stored Function            |
|----------------------|-----------------------------|----------------------------|
| **Return Value**     | Optional (may not return)   | Mandatory (always returns) |
| **Purpose**          | Executes tasks              | Computes and returns a value |
| **Example**          | `first_proc` (squares `a`)  | `add_ab` (sums `a + b`)    |
| **Syntax**           | `CALL proc_name(?,?)`       | `? = CALL func_name(?,?)`  |

!NOTE: Procedures use `{call name(?,?)}`; functions use `{? = call name(?,?)}` with the return value as the first `?`.

### 2.3 Creating a CallableStatement

#### Definition

Prepares a call to a stored procedure or function using a specific syntax.

#### Why Use It?

Links Java to database-stored logic.

#### Instructions

- Use `{call name(?,?)}` for procedures, `{? = call name(?,?)}` for functions.
- Pass to `Connection.prepareCall()`.

#### Example

```java
String procQuery = "{call first_proc(?,?)}"; // Procedure
String funcQuery = "{? = call add_ab(?,?)}"; // Function
CallableStatement cst = cn.prepareCall(funcQuery);
```

### 2.4 Setting Parameters

#### Definition

Configures input and output parameters for the call.

#### Why Use It?

Passes data to and retrieves results from stored logic.

#### Instructions

- Use `setType(position, value)` for inputs (e.g., `setInt(2, 123)`).
- Use `registerOutParameter(position, type)` for outputs (e.g., `Types.INTEGER`).

#### Example

```java
cst.setInt(2, 123); // Input 1
cst.setInt(3, 321); // Input 2
cst.registerOutParameter(1, Types.INTEGER); // Output
```

### 2.5 Executing and Retrieving Results

#### Definition

Runs the stored logic and fetches output parameters.

#### Why Use It?

Executes database tasks and gets results in Java.

#### Instructions

- Use `execute()` (not `executeUpdate()` or `executeQuery()`).
- Fetch outputs with `getType(position)` (e.g., `getInt(1)`).

#### Example

```java
cst.execute();
int result = cst.getInt(1); // Function output
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: Replace `<DATABASE_NAME>` with your database (e.g., `sampledb`).
- **Stored Procedure**: `first_proc`
  ```sql
  DELIMITER $$
  CREATE PROCEDURE first_proc(a INT, OUT b INT)
  BEGIN
      SET b = (a * a);
  END
  $$
  DELIMITER ;
  ```
- **Stored Function**: `add_ab`
  ```sql
  DELIMITER $$
  CREATE FUNCTION add_ab(a INT, b INT)
  RETURNS INT DETERMINISTIC
  BEGIN
      DECLARE c INT;
      SET c = (a + b);
      RETURN c;
  END
  $$
  DELIMITER ;
  ```
- **Verification**:
  ```sql
  SHOW PROCEDURE STATUS WHERE Db = '<DATABASE_NAME>';
  SHOW FUNCTION STATUS WHERE Db = '<DATABASE_NAME>';
  ```

### 3.2 Using CallableStatement in NetBeans

- **Project**: `JDBCDemo` (assumed setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
- **Output** (Using `add_ab` with `123, 321`):
```
--------------- Connection Successfull ---------------
Sum: 444
```

>[!NOTE]
>Comment/uncomment procedure vs. function sections in the code to switch between them. Ensure stored logic exists in the database.

#### Verification

```sql
CALL first_proc(25, @b); SELECT @b; -- Returns 625
SELECT add_ab(123, 321); -- Returns 444
```

---

## 4. Practical Application

### 4.1 Best Practices for CallableStatement

- Use delimiters (e.g., `$$`) for multi-line stored logic creation.
- Register output parameters before `execute()`.
- Match parameter types (e.g., `Types.INTEGER` for `INT`).
- Close `Connection` to free resources.

### 4.2 Common Mistakes to Avoid

- **Wrong Syntax**: Omitting `{}` or `? =` for functions causes errors.
- **Missing Delimiter**: Single-line SQL fails without proper delimiters.
- **Unregistered Output**: Skipping `registerOutParameter()` returns null.
- **Incorrect Index**: `getInt(0)` fails—indices start at 1.

### 4.3 Hands-On Exercises

1. **Procedure**: Call `first_proc` with user input for `a`, print the square.
2. **Function**: Call `add_ab` with two user inputs, print the sum.
3. **New Procedure**: Create one to double a number, call it.
4. **New Function**: Create one to multiply two numbers, call it.
5. **Combined**: Call both a procedure and function, compare outputs.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **JDBC CallableStatement**: [https://docs.oracle.com/javase/tutorial/jdbc/basics/storedprocedures.html](https://docs.oracle.com/javase/tutorial/jdbc/basics/storedprocedures.html)
- **MySQL Stored Procedures**: [https://dev.mysql.com/doc/refman/8.0/en/stored-programs-defining.html](https://dev.mysql.com/doc/refman/8.0/en/stored-programs-defining.html)

### 5.2 Summary of Key Takeaways

This guide demonstrates `CallableStatement` with:
- **Procedure**: `first_proc` squares an input (e.g., `25` → `625`).
- **Function**: `add_ab` sums two inputs (e.g., `123 + 321` → `444`).
- **Key Insight**: Uses `{call}` syntax, `set`/`registerOutParameter`, and `execute()` for stored logic.

>[!TIP]
>Test with varied inputs via `Scanner` to explore dynamic behavior.

### 5.3 Complete Code Reference

```java
/*
 * Stored Procedure:
 * DELIMITER $$
 * CREATE PROCEDURE first_proc(a INT, OUT b INT)
 * BEGIN
 *     SET b = (a * a);
 * END
 * $$
 * DELIMITER ;
 *
 * Stored Function:
 * DELIMITER $$
 * CREATE FUNCTION add_ab(a INT, b INT)
 * RETURNS INT DETERMINISTIC
 * BEGIN
 *     DECLARE c INT;
 *     SET c = (a + b);
 *     RETURN c;
 * END
 * $$
 * DELIMITER ;
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class CallableStmDemo {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlString = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
        String uname = "<USERNAME>";
        String pwd = "<PASSWORD>";
        Connection cn = DriverManager.getConnection(urlString, uname, pwd);
        if (cn != null) {
            System.out.println("--------------- Connection Successfull ---------------");
        } else {
            System.out.println("--------------- Connection Failure---------------");
        }

        // Calling Stored Procedure (commented out example)
        // String procQuery = "{call first_proc(?,?)}";
        // CallableStatement cst = cn.prepareCall(procQuery);
        // cst.setInt(1, 25);
        // cst.registerOutParameter(2, Types.INTEGER);
        // cst.execute();
        // int result = cst.getInt(2);
        // System.out.println("Square of 25 is: " + result);

        // Calling Stored Function
        String funcQuery = "{? = call add_ab(?,?)}";
        CallableStatement cst = cn.prepareCall(funcQuery);
        cst.setInt(2, 123);
        cst.setInt(3, 321);
        cst.registerOutParameter(1, Types.INTEGER);
        cst.execute();
        int result = cst.getInt(1);
        System.out.println("Sum: " + result);

        cn.close();
        in.close();
    }
}
```

---

_This README provides an in-depth illustration of Callable Statements using JDBC, mirroring the structure and detail of the 3.2 CRUD README._

---
