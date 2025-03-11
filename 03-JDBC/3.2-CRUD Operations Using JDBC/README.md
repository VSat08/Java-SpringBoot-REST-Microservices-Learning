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
- Create: Add a new student (e.g., Sara).
- Read: View the list of students.
- Update: Change a student’s name (e.g., Bob to Jane).
- Delete: Remove students with low grades.

### 1.2 Why CRUD Matters

- **Foundation**: Core to all database interactions.
- **Flexibility**: Operates on data instances, not schema (structure remains unchanged).
- **Application**: Powers backend logic in software (e.g., student management systems).

#### Example Benefit

Inserting, viewing, updating, and deleting student records like Kris, Sara, and Samuel from a Java program.

### 1.3 Key Terms for Beginners

| Term              | Meaning                                   | Example                 |
|-------------------|-------------------------------------------|-------------------------|
| **CRUD**          | Create, Read, Update, Delete              | Basic DB operations     |
| **INSERT**        | Adds new records                          | `INSERT INTO student`   |
| **SELECT**        | Retrieves data                            | `SELECT * FROM student` |
| **UPDATE**        | Modifies records                          | `UPDATE student SET`    |
| **DELETE**        | Removes records                           | `DELETE FROM student`   |
| **ExecuteUpdate** | Runs non-SELECT queries, returns row count| `stmt.executeUpdate()`  |
| **ExecuteQuery**  | Runs SELECT queries, returns ResultSet    | `stmt.executeQuery()`   |

---

## 2. CRUD Operations with JDBC

### 2.1 Overview of CRUD in JDBC

JDBC performs CRUD by:
- Connecting to MySQL using a URL (e.g., `jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME`).
- Using `Statement` to execute SQL queries.
- Processing results: `ResultSet` for `SELECT`, row counts for `INSERT`, `UPDATE`, `DELETE`.

#### Prerequisites

- A table exists (e.g., `student` created via JDBC).
- Connection steps completed (driver loading, `DriverManager`, etc.).

### 2.2 Create: Inserting Data

#### Definition

Adds new records to a table using `INSERT`.

#### Why Use It?

Populates the database with data (e.g., adding students like Sara and Kris).

#### Instructions

- Write an `INSERT` query with multiple values.
- Use `executeUpdate()`—returns the number of rows inserted (e.g., 6 for six students).
- Check result: `> 0` means success.

#### Example

```java
String iq = "INSERT INTO student VALUES (123, 'Sara', 9.5), (101, 'Kris', 8.7)";
int x = stmt.executeUpdate(iq);
if (x > 0) System.out.println(x + " Record(s) Inserted");
```

### 2.3 Read: Retrieving Data

#### Definition

Fetches data using `SELECT`.

#### Why Use It?

Views database content (e.g., listing all students).

#### Instructions

- Write a `SELECT` query.
- Use `executeQuery()`—returns a `ResultSet`.
- Process with `next()` and getters (e.g., `getInt()`, `getString()`, `getFloat()`).

#### Example

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM student");
while (rs.next()) {
    System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getFloat(3));
}
```

### 2.4 Update: Modifying Data

#### Definition

Changes existing records using `UPDATE`.

#### Why Use It?

Adjusts data (e.g., renaming Bob to Jane).

#### Instructions

- Write an `UPDATE` query with `SET` and `WHERE`.
- Use `executeUpdate()`—returns updated row count.
- Check result: `> 0` means success.

#### Example

```java
String uq = "UPDATE student SET sname = 'Jane' WHERE sid = 212";
int y = stmt.executeUpdate(uq);
if (y > 0) System.out.println(y + " Record(s) Updated");
```

### 2.5 Delete: Removing Data

#### Definition

Removes records using `DELETE`.

#### Why Use It?

Cleans up data (e.g., removing students with CGPA < 7).

#### Instructions

- Write a `DELETE` query with a `WHERE` clause.
- Use `executeUpdate()`—returns deleted row count.
- Check result: `> 0` means success.

#### Example

```java
String dq = "DELETE FROM student WHERE cgpa < 7";
int z = stmt.executeUpdate(dq);
if (z > 0) System.out.println(z + " Record(s) Deleted");
```

### 2.6 Bonus: Creating a Table

#### Definition

Sets up a table for CRUD using `CREATE TABLE`.

#### Why Use It?

Prepares the database structure (e.g., `student` table with `sid`, `sname`, `cgpa`).

#### Instructions

- Write a `CREATE TABLE` query with column definitions.
- Use `executeUpdate()`—returns 0 on success.
- Check result: `>= 0` means success.

#### Example

```java
String cq = "CREATE TABLE student (sid INT PRIMARY KEY, sname VARCHAR(20), cgpa FLOAT)";
int c = stmt.executeUpdate(cq);
if (c >= 0) System.out.println("Table Created!");
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: Replace `YOUR_DATABASE_NAME` with your database name (e.g., `sample_db`).
- **Existing Tables**: Assume others like `account` or `book` may exist.
- **New Table**: `student` (created via JDBC):
  - `sid` (INT, PRIMARY KEY), `sname` (VARCHAR(20)), `cgpa` (FLOAT).
- **Verification**:
  ```sql
  USE YOUR_DATABASE_NAME;
  SHOW TABLES; -- Includes student
  DESCRIBE student; -- sid (int), sname (varchar(20)), cgpa (float)
  ```

### 3.2 Performing CRUD in NetBeans

- **Project**: `JDBCDemo` (assumed from prior setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
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

>[!NOTE]
>On second run, comment out the `CREATE TABLE` code to avoid "table already exists" errors.

#### Verification

```sql
SELECT * FROM student; -- Shows 4 rows: 101 (Kris), 123 (Sara), 212 (Jane), 321 (Samuel)
```

---

## 4. Practical Application

### 4.1 Best Practices for CRUD

- Run `CREATE` only once and comment it out afterward.
- Ensure `sid` values are unique to avoid primary key conflicts.
- Check results: `>= 0` for `CREATE` (0 on success), `> 0` for `INSERT`, `UPDATE`, `DELETE`.
- Always call `con.close()` to free resources.
- Use single quotes for SQL strings (e.g., `'Sara'`).

### 4.2 Common Mistakes to Avoid

- **Duplicate Keys**: Re-running `INSERT` with the same `sid` (e.g., 123) causes errors.
- **Syntax Errors**: Typos (e.g., `SELECT * FROM studnet`) break queries.
- **Missing Table**: CRUD fails if `student` isn’t created first.
- **Unclosed Connections**: Can lock database resources.

### 4.3 Hands-On Exercises

1. **Create**: Insert 2 new students into `student` (e.g., `130, 'Alice', 8.2`).
2. **Read**: Fetch and print all students with CGPA > 8.0.
3. **Update**: Change Samuel’s CGPA to 7.9.
4. **Delete**: Remove students with `sid` < 200.
5. **Full CRUD**: Combine all operations in one program, verify with MySQL.

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **JDBC Tutorial**: [https://docs.oracle.com/javase/tutorial/jdbc/](https://docs.oracle.com/javase/tutorial/jdbc/)
- **MySQL Reference**: [https://dev.mysql.com/doc/refman/8.0/en/](https://dev.mysql.com/doc/refman/8.0/en/)

### 5.2 Summary of Key Takeaways

This guide performs CRUD on the `student` table using your code:
- **Create**: Inserted 6 students (`123, 'Sara', 9.5`, etc.), returned 6 rows.
- **Read**: Retrieved 4 remaining students (Kris, Sara, Jane, Samuel) using `ResultSet`.
- **Update**: Renamed Bob to Jane (1 row).
- **Delete**: Removed Steve and Eon (CGPA < 7, 2 rows).
- **Bonus**: Created `student` table (run once, returns 0).

>[!TIP]
>Adjust `sid` values on re-runs to avoid duplicate key errors.

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

_This readme covers in depth illustration of CRUD Operations using JDBC_ 