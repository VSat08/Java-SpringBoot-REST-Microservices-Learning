# 3.2-CRUD Operations Using JDBC - A Beginner's Guide to Database Manipulation in Java

## Introduction

Welcome to **3.2-CRUD Operations Using JDBC** 🌟! This chapter explores how to perform Create, Read, Update, and Delete (CRUD) operations on a MySQL database using Java Database Connectivity (JDBC). Building on connection basics, we’ll create a `student` table in `sample_db`, then insert, retrieve, modify, and remove records—all from a Java program in NetBeans. Designed for beginners, this guide offers step-by-step instructions, practical code, and real-world examples to master these fundamental database tasks. Let’s dive into CRUD with JDBC! 🚀

---

## Table of Contents

1. [What Are CRUD Operations?](#1-what-are-crud-operations)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why CRUD Matters](#12-why-crud-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [CRUD Operations with JDBC](#2-crud-operations-with-jdbc)
   - [2.1 Overview of CRUD in JDBC](#21-overview-of-crud-in-jdbc)
   - [2.2 Create: Inserting Data](#22-create-inserting-data)
   - [2.3 Read: Retrieving Data](#23-read-retrieving-data)
   - [2.4 Update: Modifying Data](#24-update-modifying-data)
   - [2.5 Delete: Removing Data](#25-delete-removing-data)
   - [2.6 Bonus: Creating a Table](#26-bonus-creating-a-table)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Performing CRUD in NetBeans](#32-performing-crud-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for CRUD](#41-best-practices-for-crud)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Are CRUD Operations?

### 1.1 Definition and Purpose

CRUD stands for Create, Read, Update, and Delete—fundamental operations for managing data in a database.

- **Definition**:
  - **Create**: Adds new records (e.g., `INSERT`).
  - **Read**: Retrieves data (e.g., `SELECT`).
  - **Update**: Modifies existing records (e.g., `UPDATE`).
  - **Delete**: Removes records (e.g., `DELETE`).
- **Purpose**: Enables full control over database content via SQL statements executed through JDBC.

#### Real-World Analogy

Think of CRUD as managing a student roster:

- Create: Add a new student.
- Read: View the list.
- Update: Change a grade.
- Delete: Remove a dropout.

### 1.2 Why CRUD Matters

- **Foundation**: Core to all database interactions.
- **Flexibility**: Operates on data instances, not schema (structure remains unchanged).
- **Application**: Powers backend logic in software (e.g., student management systems).

#### Example Benefit

Inserting, viewing, updating, and deleting student records in `sample_db` from Java.

### 1.3 Key Terms for Beginners

| Term              | Meaning                      | Example                 |
| ----------------- | ---------------------------- | ----------------------- |
| **CRUD**          | Create, Read, Update, Delete | Basic DB operations     |
| **INSERT**        | Adds new records             | `INSERT INTO student`   |
| **SELECT**        | Retrieves data               | `SELECT * FROM student` |
| **UPDATE**        | Modifies records             | `UPDATE student SET`    |
| **DELETE**        | Removes records              | `DELETE FROM student`   |
| **ExecuteUpdate** | Runs non-SELECT queries      | `stmt.executeUpdate()`  |

---

## 2. CRUD Operations with JDBC

### 2.1 Overview of CRUD in JDBC

JDBC performs CRUD by:

- Connecting to MySQL (e.g., `sample_db`).
- Using `Statement` to execute SQL queries.
- Processing results (`ResultSet` for `SELECT`, row counts for others).

#### Prerequisites

- Table exists (e.g., `student` created via JDBC).
- Connection steps from "3.1" (driver, `DriverManager`, etc.).

### 2.2 Create: Inserting Data

#### Definition

Adds new records to a table using `INSERT`.

#### Why Use It?

Populates the database with data (e.g., new students).

#### Instructions

- Write an `INSERT` query.
- Use `executeUpdate()`—returns the number of rows inserted.
- Check result (e.g., `> 0` means success).

#### Example

```java
String insertQuery = "INSERT INTO student VALUES (101, 'Sarah', 7.5)";
int rows = stmt.executeUpdate(insertQuery);
if (rows > 0) System.out.println(rows + " records inserted");
```

### 2.3 Read: Retrieving Data

#### Definition

Fetches data using `SELECT`.

#### Why Use It?

Views database content (e.g., student details).

#### Instructions

- Write a `SELECT` query.
- Use `executeQuery()`—returns a `ResultSet`.
- Process with `next()`, `getInt()`, `getString()`, etc.

#### Example

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM student");
while (rs.next()) {
    System.out.println(rs.getInt("sid") + " " + rs.getString("sname") + " " + rs.getDouble("cgpa"));
}
```

### 2.4 Update: Modifying Data

#### Definition

Changes existing records using `UPDATE`.

#### Why Use It?

Adjusts data (e.g., renaming a student).

#### Instructions

- Write an `UPDATE` query with `SET` and `WHERE`.
- Use `executeUpdate()`—returns updated row count.

#### Example

```java
String updateQuery = "UPDATE student SET sname = 'John' WHERE sid = 111";
int rows = stmt.executeUpdate(updateQuery);
if (rows > 0) System.out.println(rows + " records updated");
```

### 2.5 Delete: Removing Data

#### Definition

Removes records using `DELETE`.

#### Why Use It?

Cleans up data (e.g., removing low-CGPA students).

#### Instructions

- Write a `DELETE` query with `WHERE` (or all rows if omitted).
- Use `executeUpdate()`—returns deleted row count.

#### Example

```java
String deleteQuery = "DELETE FROM student WHERE cgpa <= 7.5";
int rows = stmt.executeUpdate(deleteQuery);
if (rows > 0) System.out.println(rows + " records deleted");
```

### 2.6 Bonus: Creating a Table

#### Definition

Sets up a table for CRUD using `CREATE TABLE`.

#### Why Use It?

Prepares the database structure (run once).

#### Instructions

- Write a `CREATE TABLE` query.
- Use `executeUpdate()`—returns 0 on success.

#### Example

```java
String createQuery = "CREATE TABLE student (sid INT PRIMARY KEY, sname VARCHAR(20), cgpa FLOAT)";
int result = stmt.executeUpdate(createQuery);
if (result >= 0) System.out.println("Table created");
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: `sample_db`.
- **Existing Tables**: `account`, `book`.
- **New Table**: `student` (created via JDBC):
  - `sid` (INT, PRIMARY KEY), `sname` (VARCHAR(20)), `cgpa` (FLOAT).
- **Verification**:
  ```sql
  USE sample_db;
  SHOW TABLES; -- Includes student
  DESCRIBE student; -- sid (int), sname (varchar), cgpa (float)
  ```

### 3.2 Performing CRUD in NetBeans

- **Project**: `JDBCDemo` (from prior setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**:
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUDOps {
	public static void main(String[] args) throws Exception {
//		------------- Step 1: Loading Drivers -------------
		Class.forName("com.mysql.cj.jdbc.Driver");

//		------------- Step 2: Establishing Connections-------------
		String url = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
		String uname = "DATABASE_USERNAME";
		String pwd = "DATABASE_PASSWORD";

		Connection con = DriverManager.getConnection(url, uname, pwd);

//		------------- Step 3: Checking Connections-------------
		if (con != null) {
			System.out.println("------------- Connection Established -------------");
		} else {
			System.out.println("------------- Connection Failure -------------");
		}

//		------------- Step 4:Creating Statements -------------
		Statement st = con.createStatement();

//		------------- Step 5:Creating Table(only be run once) -------------
		String cq = "CREATE TABLE STUDENT(sID INT PRIMARY KEY, sName VARCHAR(20), CGPA FLOAT)";
		int c = st.executeUpdate(cq);
		if (c >= 0) {
			System.out.println("Table Created!");
		} else {
			System.out.println("Table Creation Failure");
		}

//		------------- Step 6:CURD Operations -------------
//		Note:Table must be created first and only once and table should be available prior to CRUD Operations.

//		------------- INSERT -------------
		String iq = "INSERT INTO STUDENT VALUES(123, 'Sara', 9.5), (212, 'Bob', 7.8), (111, 'Steve', 6.5), (101, 'Kris', 8.7), (321, 'Samuel', 7.4),(411, 'Eon', 5)";
		int x = st.executeUpdate(iq);
		if (x > 0) {
			System.out.println(x + " Record(s) Inserted");
		}

//		------------- UPDATE -------------
		String uq = "UPDATE STUDENT SET sName = 'Jane' WHERE sID = 212";
		int y = st.executeUpdate(uq);

		if (y > 0) {
			System.out.println(y + " Record(s) Updated");
		}

//		------------- DELETE -------------
		String dq = "DELETE FROM STUDENT WHERE CGPA<7";
		int z = st.executeUpdate(dq);

		if (z > 0) {
			System.out.println(z + " Record(s) Deleted");
		}

//		------------- READ -------------
		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		System.out.println("-------------------------------- Student Records --------------------------------");
		System.out.println("ID" + "\t" + "Name" + "\t" + "CGPA");

		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getFloat(3));
		}
		con.close();
	}
}
```
- **Output** (First Run):
```
------------- Connection Established -------------
Table Created!
6 Record(s) Inserted
1 Record(s) Updated
2 Record(s) Deleted
-------------------------------- Student Records --------------------------------
ID	Name	CGPA
101	Kris	8.7
123	Sara	9.5
212	Jane	7.8
321	Samuel	7.4
```

> [!Note]
> Second run comment down `CREATE TABLE` code  to avoid   existing  table error.


#### Verification

```sql
SELECT * FROM student; -- Shows 4 rows (e.g., 101, 105, 111, 122)
```

---

## 4. Practical Application

### 4.1 Best Practices for CRUD

- **Run `CREATE` Once**: Comment out after table creation.
- **Unique Keys**: Avoid primary key conflicts (e.g., increment `sid`).
- **Check Results**: Use `> 0` for `INSERT`, `UPDATE`, `DELETE`; `>= 0` for `CREATE`.
- **Close Connections**: Always call `conn.close()` to free resources.
- **Quote Strings**: Use single quotes in SQL (e.g., `'Sarah'`).

### 4.2 Common Mistakes to Avoid

- **Duplicate Keys**: Re-running `INSERT` without changing `sid` causes errors.
- **Syntax Errors**: Typos (e.g., `SELECT star from studentn`) break queries.
- **Missing Table**: CRUD fails if `student` isn’t created first.
- **Unclosed Connections**: Locks database resources.

### 4.3 Hands-On Exercises

1. **Create**: Insert 2 new students into `student`.
2. **Read**: Fetch and print all students with CGPA > 8.0.
3. **Update**: Change `Diva`’s CGPA to 8.5.
4. **Delete**: Remove students with `sid` < 110.
5. **Full CRUD**: Combine all operations in one program, verify with MySQL.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- JDBC Tutorial: https://docs.oracle.com/javase/tutorial/jdbc/
- MySQL Reference: https://dev.mysql.com/doc/refman/8.0/en/

### 5.2 Summary of Key Takeaways

This guide performs CRUD on `sample_db`’s `student` table:

- **Create**: Inserted 3 students (`sid`, `sname`, `cgpa`), returned 3 rows.
- **Read**: Retrieved remaining students using `ResultSet`.
- **Update**: Renamed Jane to John (1 row).
- **Delete**: Removed Sarah (CGPA ≤ 7.5, 1 row).
- **Bonus**: Created `student` table (run once).

#### Highlights

- **Versatility**: JDBC handles all CRUD via `executeUpdate` and `executeQuery`.
- **Practicality**: NetBeans demo shows real-time results.
- **Tip**: Adjust `sid` to avoid duplicates on re-runs.

### 5.3 Complete Code Reference

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUDOps {
	public static void main(String[] args) throws Exception {
//		------------- Step 1: Loading Drivers -------------
		Class.forName("com.mysql.cj.jdbc.Driver");

//		------------- Step 2: Establishing Connections-------------
		String url = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
		String uname = "DATABASE_USERNAME";
		String pwd = "DATABASE_PASSWORD";

		Connection con = DriverManager.getConnection(url, uname, pwd);

//		------------- Step 3: Checking Connections-------------
		if (con != null) {
			System.out.println("------------- Connection Established -------------");
		} else {
			System.out.println("------------- Connection Failure -------------");
		}

//		------------- Step 4:Creating Statements -------------
		Statement st = con.createStatement();

//		------------- Step 5:Creating Table(only be run once) -------------
		String cq = "CREATE TABLE STUDENT(sID INT PRIMARY KEY, sName VARCHAR(20), CGPA FLOAT)";
		int c = st.executeUpdate(cq);
		if (c >= 0) {
			System.out.println("Table Created!");
		} else {
			System.out.println("Table Creation Failure");
		}

//		------------- Step 6:CURD Operations -------------
//		Note:Table must be created first and only once and table should be available prior to CRUD Operations.

//		------------- INSERT -------------
		String iq = "INSERT INTO STUDENT VALUES(123, 'Sara', 9.5), (212, 'Bob', 7.8), (111, 'Steve', 6.5), (101, 'Kris', 8.7), (321, 'Samuel', 7.4),(411, 'Eon', 5)";
		int x = st.executeUpdate(iq);
		if (x > 0) {
			System.out.println(x + " Record(s) Inserted");
		}

//		------------- UPDATE -------------
		String uq = "UPDATE STUDENT SET sName = 'Jane' WHERE sID = 212";
		int y = st.executeUpdate(uq);

		if (y > 0) {
			System.out.println(y + " Record(s) Updated");
		}

//		------------- DELETE -------------
		String dq = "DELETE FROM STUDENT WHERE CGPA<7";
		int z = st.executeUpdate(dq);

		if (z > 0) {
			System.out.println(z + " Record(s) Deleted");
		}

//		------------- READ -------------
		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		System.out.println("-------------------------------- Student Records --------------------------------");
		System.out.println("ID" + "\t" + "Name" + "\t" + "CGPA");

		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getFloat(3));
		}
		con.close();
	}
}
```

---

_This Readme covers the CRUD Operations using JDBC and MYSQL Server_
