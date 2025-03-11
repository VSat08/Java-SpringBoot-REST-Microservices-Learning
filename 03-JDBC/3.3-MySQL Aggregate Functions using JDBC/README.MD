# 3.3 - MySQL Aggregate Functions using JDBC

## Introduction

Welcome to **3.3 - MySQL Aggregate Functions using JDBC** 🌟! This chapter dives into the power of MySQL aggregate functions (`COUNT`, `MIN`, `MAX`, `AVG`, `SUM`) and how to harness them through Java Database Connectivity (JDBC). Building on our database connection skills, we’ll calculate totals, extremes, and averages from a `STUDENT` table in a `sampledb` database—all from a Java program in NetBeans. Perfect for beginners, this guide provides clear steps, practical code, and real-world examples to master these essential SQL tools. Let’s aggregate some data with JDBC! 🚀

---

## Table of Contents

1. [What Are Aggregate Functions?](#1-what-are-aggregate-functions)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why Aggregate Functions Matter](#12-why-aggregate-functions-matter)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Aggregate Functions with JDBC](#2-aggregate-functions-with-jdbc)
   - [2.1 Overview of Aggregate Functions in JDBC](#21-overview-of-aggregate-functions-in-jdbc)
   - [2.2 COUNT: Total Records](#22-count-total-records)
   - [2.3 MIN and MAX: Extremes](#23-min-and-max-extremes)
   - [2.4 AVG: Average Values](#24-avg-average-values)
   - [2.5 SUM: Total Sum](#25-sum-total-sum)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Using Aggregate Functions in NetBeans](#32-using-aggregate-functions-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for Aggregate Functions](#41-best-practices-for-aggregate-functions)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Are Aggregate Functions?

### 1.1 Definition and Purpose

Aggregate functions in MySQL perform calculations on a set of rows and return a single value.

- **Definition**:
  - **COUNT**: Counts rows (e.g., total students).
  - **MIN**: Finds the smallest value (e.g., lowest CGPA).
  - **MAX**: Finds the largest value (e.g., highest CGPA).
  - **AVG**: Computes the average (e.g., mean CGPA).
  - **SUM**: Adds up values (e.g., total CGPA).
- **Purpose**: Summarizes data for analysis, executed via JDBC in Java.

#### Real-World Analogy

Imagine a classroom:
- COUNT: How many students are present?
- MIN: Who has the lowest grade?
- MAX: Who has the highest grade?
- AVG: What’s the class average?
- SUM: What’s the total of all grades?

### 1.2 Why Aggregate Functions Matter

- **Insight**: Reveal trends and summaries (e.g., average student performance).
- **Efficiency**: Process large datasets into single values.
- **Application**: Drive reporting in apps (e.g., student analytics).

#### Example Benefit

Calculating the number of students, their lowest/highest CGPAs, average CGPA, and total CGPA in one query.

### 1.3 Key Terms for Beginners

| Term             | Meaning                                      | Example                     |
|------------------|----------------------------------------------|-----------------------------|
| **Aggregate**    | Combines multiple rows into one result       | `COUNT(*)`                  |
| **COUNT**        | Counts rows                                  | `SELECT COUNT(*) FROM STUDENT` |
| **MIN**          | Minimum value in a column                    | `MIN(CGPA)`                 |
| **MAX**          | Maximum value in a column                    | `MAX(CGPA)`                 |
| **AVG**          | Average of a column                          | `AVG(CGPA)`                 |
| **SUM**          | Sum of a column                              | `SUM(CGPA)`                 |
| **ResultSet**    | Holds query results                          | `rs.getInt(1)`              |

---

## 2. Aggregate Functions with JDBC

### 2.1 Overview of Aggregate Functions in JDBC

JDBC uses aggregate functions by:
- Connecting to MySQL with a URL (e.g., `jdbc:mysql://localhost:3306/<DATABASE_NAME>`).
- Executing a `SELECT` query with aggregates via `Statement`.
- Retrieving results from a `ResultSet` using indices (not column names).

#### Prerequisites

- A table exists (e.g., `STUDENT` with `CGPA`).
- Connection established (driver loaded, `DriverManager` used).

### 2.2 COUNT: Total Records

#### Definition

Counts the number of rows in a table or result set.

#### Why Use It?

Tracks totals (e.g., number of students).

#### Instructions

- Use `COUNT(*)` in a `SELECT` query.
- Execute with `executeQuery()`, fetch with `getInt(1)`.

#### Example

```java
ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM STUDENT");
rs.next();
System.out.println("Total Students: " + rs.getInt(1));
```

### 2.3 MIN and MAX: Extremes

#### Definition

Finds the minimum and maximum values in a column.

#### Why Use It?

Identifies extremes (e.g., lowest/highest CGPA).

#### Instructions

- Use `MIN(column)` and `MAX(column)` in a `SELECT`.
- Fetch with `getDouble(2)` and `getDouble(3)` (indices depend on query order).

#### Example

```java
ResultSet rs = st.executeQuery("SELECT MIN(CGPA), MAX(CGPA) FROM STUDENT");
rs.next();
System.out.println("Min CGPA: " + rs.getDouble(1) + ", Max CGPA: " + rs.getDouble(2));
```

### 2.4 AVG: Average Values

#### Definition

Calculates the mean of a numeric column.

#### Why Use It?

Summarizes data (e.g., average CGPA).

#### Instructions

- Use `AVG(column)` in a `SELECT`.
- Fetch with `getDouble(index)` (e.g., 4th position).

#### Example

```java
ResultSet rs = st.executeQuery("SELECT AVG(CGPA) FROM STUDENT");
rs.next();
System.out.println("Average CGPA: " + rs.getDouble(1));
```

### 2.5 SUM: Total Sum

#### Definition

Adds up all values in a numeric column.

#### Why Use It?

Totals data (e.g., sum of all CGPAs).

#### Instructions

- Use `SUM(column)` in a `SELECT`.
- Fetch with `getDouble(index)` (e.g., 5th position).

#### Example

```java
ResultSet rs = st.executeQuery("SELECT SUM(CGPA) FROM STUDENT");
rs.next();
System.out.println("Total CGPA: " + rs.getDouble(1));
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: Replace `<DATABASE_NAME>` with your database (e.g., `sampledb`).
- **Existing Tables**: May include `account`, `book`, etc.
- **Target Table**: `STUDENT`:
  - Columns: `sID` (INT, PRIMARY KEY), `sName` (VARCHAR(20)), `CGPA` (FLOAT).
- **Sample Data**:
  ```sql
  INSERT INTO STUDENT VALUES (101, 'Kris', 8.7), (123, 'Sara', 9.5), (212, 'Jane', 7.8), (321, 'Samuel', 7.4);
  ```
- **Verification**:
  ```sql
  USE <DATABASE_NAME>;
  SHOW TABLES; -- Lists STUDENT
  SELECT * FROM STUDENT; -- Shows 4 rows
  ```

### 3.2 Using Aggregate Functions in NetBeans

- **Project**: `JDBCDemo` (assumed setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
- **Output**:
```
------------- Connection Established -------------
Student'sCount	MIN(CGPA)	MAX(CGPA)	AVG(CGPA)	SUM(CGPA)
4		7.4		9.5		8.35		33.4
------------- Connection Closed -------------
```

>[!NOTE]
>Assumes `STUDENT` table is pre-created with data. Add a `CREATE TABLE` step if starting fresh, but comment it out after the first run.

#### Verification

```sql
SELECT COUNT(*), MIN(CGPA), MAX(CGPA), AVG(CGPA), SUM(CGPA) FROM STUDENT; -- Matches output
```

---

## 4. Practical Application

### 4.1 Best Practices for Aggregate Functions

- Use indices (1-based) to fetch results, as aggregates lack column names.
- Ensure numeric columns (e.g., `CGPA`) exist for `MIN`, `MAX`, `AVG`, `SUM`.
- Test queries in MySQL first to verify syntax and results.
- Close `Connection` with `con.close()` to free resources.

### 4.2 Common Mistakes to Avoid

- **Wrong Indices**: `getDouble(0)` fails—indices start at 1.
- **Non-Numeric Columns**: `AVG(sName)` errors—use numeric fields only.
- **Empty Table**: Returns 0 for `COUNT`, null for others—handle edge cases.
- **Missing Driver**: Causes `ClassNotFoundException`.

### 4.3 Hands-On Exercises

1. **COUNT**: Count students with CGPA > 8.0.
2. **MIN/MAX**: Find the lowest and highest CGPAs for students with `sID` > 200.
3. **AVG**: Calculate the average CGPA for students named starting with 'S'.
4. **SUM**: Sum CGPAs for students with CGPA < 9.0.
5. **Combined**: Run all aggregates in one query, print formatted results.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **MySQL Aggregate Functions**: [https://dev.mysql.com/doc/refman/8.0/en/aggregate-functions.html](https://dev.mysql.com/doc/refman/8.0/en/aggregate-functions.html)
- **JDBC Basics**: [https://docs.oracle.com/javase/tutorial/jdbc/](https://docs.oracle.com/javase/tutorial/jdbc/)

### 5.2 Summary of Key Takeaways

This guide uses aggregate functions on the `STUDENT` table:
- **COUNT**: Total students (e.g., 4).
- **MIN/MAX**: Lowest (7.4) and highest (9.5) CGPAs.
- **AVG**: Average CGPA (8.35).
- **SUM**: Total CGPA (33.4).
- **Key Insight**: Results are accessed via indices (1-5), not names.

>[!TIP]
>Test with varied data (e.g., add students via `INSERT`) to see dynamic updates.

### 5.3 Complete Code Reference

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AggFunc {

    public static void main(String[] args) throws Exception {
        // ------------- Step 1: Loading Drivers -------------
        Class.forName("com.mysql.cj.jdbc.Driver");

        // ------------- Step 2: Establishing Connections -------------
        String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
        String uname = "<USERNAME>";
        String pwd = "<PASSWORD>";

        Connection con = DriverManager.getConnection(url, uname, pwd);

        // ------------- Step 3: Checking Connections -------------
        if (con != null) {
            System.out.println("------------- Connection Established -------------");
        } else {
            System.out.println("------------- Connection Failure -------------");
        }

        // ------------- Step 4: Creating Statements -------------
        Statement st = con.createStatement();

        // ------------- Step 5: Aggregate Functions -------------
        // !NOTE: Table must be created first and populated with data prior to running aggregates.
        String query = "SELECT COUNT(*), MIN(CGPA), MAX(CGPA), AVG(CGPA), SUM(CGPA) FROM STUDENT";
        System.out.println("Student'sCount\tMIN(CGPA)\tMAX(CGPA)\tAVG(CGPA)\tSUM(CGPA)");
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t\t" + rs.getDouble(2) + "\t\t" + rs.getDouble(3) + "\t\t"
                    + rs.getDouble(4) + "\t" + rs.getDouble(5));
        }

        System.out.println("------------- Connection Closed -------------");
        con.close();
    }
}
```

---

_This README provides an in-depth illustration of MySQL Aggregate Functions using JDBC, mirroring the structure and detail of the 3.2 CRUD README._

---
