# 2.4: DQL Commands

## Introduction
Welcome to **Section 2.4: DQL Commands** 🚀! In this session from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we explore *Data Query Language (DQL)* commands—your essential tools for retrieving data from MySQL tables. Centered on the `SELECT` command, this guide covers querying techniques to fetch, filter, and sort records, forming the backbone of data retrieval operations. With practical examples, it’s perfect for beginners aiming to master SQL querying! 🌟

---

## Table of Contents
1. [Understanding DQL Commands](#1-understanding-dql-commands)
    - [1.1 What are DQL Commands?](#11-what-are-dql-commands)
    - [1.2 Why Use DQL Commands?](#12-why-use-dql-commands)
2. [DQL Commands in SQL](#2-dql-commands-in-sql)
    - [2.1 Core Concepts](#21-core-concepts)
    - [2.2 Need for DQL Commands](#22-need-for-dql-commands)
    - [2.3 Key DQL Commands](#23-key-dql-commands)
        - [2.3.1 Basic SELECT](#231-basic-select)
        - [2.3.2 SELECT with WHERE Clause](#232-select-with-where-clause)
        - [2.3.3 SELECT with ORDER BY Clause](#233-select-with-order-by-clause)
        - [2.3.4 SELECT with Calculations and Aliases](#234-select-with-calculations-and-aliases)
    - [2.4 Implementation Overview](#24-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [3.1 Best Practices](#31-best-practices)
    - [3.2 Common Pitfalls](#32-common-pitfalls)
    - [3.3 Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [4.1 DQL vs. DML Commands](#41-dql-vs-dml-commands)
    - [4.2 Basic SELECT vs. WHERE vs. ORDER BY](#42-basic-select-vs-where-vs-order-by)
5. [Resources & Summary](#5-resources--summary)
    - [5.1 Resources](#51-resources)
    - [5.2 Summary](#52-summary)
    - [5.3 SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding DQL Commands

### 1.1 What are DQL Commands?
*Data Query Language (DQL)* commands in SQL are used to retrieve data from database tables. The primary command, `SELECT`, fetches records based on specified conditions, enabling you to query and analyze table content.

#### Definition
- **DQL**: A subset of SQL for querying data.
- **SELECT**: The core command to retrieve records.
- **Records**: Rows of data returned from a table.

#### Real-World Example
Think of a library catalog: DQL commands let you list all books, filter by author, or sort by title—extracting exactly what you need from the collection.

#### Key Terms
| Term          | Definition                                | Example                |
|---------------|-------------------------------------------|------------------------|
| DQL           | Language to query data                    | `SELECT`               |
| SELECT        | Retrieves data from tables                | `SELECT * FROM employee` |
| Records       | Rows of data in a table                   | Employees in `employee` |

### 1.2 Why Use DQL Commands?
DQL commands are essential because they:
- Fetch specific or all data from tables (e.g., employee details).
- Filter and sort results for analysis (e.g., by city or pay).
- Enable data-driven insights after tables are populated.

#### Analogy
DQL is like a librarian: it finds and organizes books (data) from the shelves (tables) based on your request.

---

## 2. DQL Commands in SQL

### 2.1 Core Concepts
DQL centers on the `SELECT` command, part of SQL’s command family (DDL, DML, DCL, TCL, DQL). Executed in MySQL, it retrieves data using clauses like `WHERE` and `ORDER BY`, operating case-insensitively on tables like `employee` in `my_db`.

### 2.2 Need for DQL Commands
After defining tables (DDL) and adding data (DML), you need to retrieve it. DQL fulfills this by:
- Fetching all or filtered records.
- Sorting results for usability.
- Supporting analysis and reporting.

#### Snippet: Without DQL
Without DQL, your data is locked in tables—like a library with no way to check out books!

### 2.3 Key DQL Commands
DQL revolves around `SELECT`, enhanced with clauses and features for versatile querying.

#### 2.3.1 Basic SELECT
Fetches data from a table, either all columns or specific ones.

- **Traits**: Retrieves records, uses `FROM`.
- **Syntax**:
  - All columns: `SELECT * FROM table_name;`
  - Specific columns: `SELECT column1, column2 FROM table_name;`

##### Snippet: Fetch All Records
```sql
SELECT * FROM employee;
```
*Output*: All 10 records (e.g., Ram, Sita).

##### Snippet: Fetch Specific Columns
```sql
SELECT emp_id, emp_name FROM employee;
```
*Output*: Only `emp_id` and `emp_name` for all employees.

#### 2.3.2 SELECT with WHERE Clause
Filters records using operators: relational (`=`, `>`), logical (`AND`, `OR`), membership (`IN`), range (`BETWEEN`), pattern (`LIKE`), identity (`IS NULL`).

- **Traits**: Conditional retrieval.
- **Syntax**: `SELECT * FROM table_name WHERE condition;`

##### Snippet: Relational Operator
```sql
SELECT * FROM employee WHERE emp_city = 'Mumbai';
```
*Output*: Sita, Sam (2 rows).

##### Snippet: Logical Operator
```sql
SELECT emp_name, emp_city FROM student WHERE emp_city != 'Mumbai' AND class > 10;
```
*Output*: Students not from Mumbai in classes > 10.

##### Snippet: Membership Operator
```sql
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');
```
*Output*: 6 employees.

##### Snippet: Range Operator
```sql
SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;
```
*Output*: 6 employees.

##### Snippet: Pattern Matching with LIKE
```sql
SELECT * FROM employee WHERE emp_name LIKE '_i%';
```
*Output*: Sita, Rita, Geetha, Vikas.

##### Snippet: Identity Operator
```sql
UPDATE employee SET emp_pay = NULL WHERE emp_id = 129;
SELECT * FROM employee WHERE emp_pay IS NULL;
```
*Output*: Ramesh with `NULL` pay.

#### 2.3.3 SELECT with ORDER BY Clause
Sorts results in ascending (`ASC`) or descending (`DESC`) order.

- **Traits**: Organizes output; defaults to `ASC`.
- **Syntax**: `SELECT * FROM table_name WHERE condition ORDER BY column [ASC|DESC];`

##### Snippet: Order by City
```sql
SELECT * FROM employee WHERE emp_pay > 60000 ORDER BY emp_city ASC;
```
*Output*: 8 employees, sorted by city.

##### Snippet: Order by Pay
```sql
SELECT * FROM employee WHERE emp_city = 'Hyderabad' ORDER BY emp_pay DESC;
```
*Output*: Anil, Geetha, Ramesh.

#### 2.3.4 SELECT with Calculations and Aliases
Performs arithmetic and renames output columns.

- **Traits**: Computes values, uses `AS` for aliases.
- **Syntax**: `SELECT expression AS alias FROM table_name;`

##### Snippet: Calculations and Aliases
```sql
SELECT 4 * 3;
SELECT emp_name, emp_pay * 12 AS annual_salary FROM employee;
SELECT 22 / 7 AS pi;
```
*Output*: `12`, annual salaries (e.g., Ram, 936,000), `3.142857`.

### 2.4 Implementation Overview
Runs in `my_db` with `employee` (10 rows). Utility commands:
- `SHOW DATABASES`: Lists databases.
- `SHOW TABLES`: Lists tables.
- `DESCRIBE`: Shows structure.
- `SELECT DATABASE()`: Confirms database.

##### Textual Hierarchy Diagram
```
+-----------------+
| DQL Commands    | --> Retrieve Data
+-----------------+
    |
    v
+--------------------+
| SELECT             | --> Core Command
+--------------------+
    |
    v
+--------+--------+-----------------+
| WHERE  | ORDER BY | Calculations   | --> Key Features
+--------+--------+-----------------+
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use specific columns over `*` (e.g., `SELECT emp_name`).
- Quote strings (e.g., `'Mumbai'`).
- Combine `WHERE` and `ORDER BY` for clarity.
- Test queries with small datasets first.

### 3.2 Common Pitfalls
- Omitting quotes (e.g., `city = Mumbai` vs. `'Mumbai'`).
- Using `= NULL` instead of `IS NULL`.
- Forgetting `ORDER BY` when order matters.
- Over-fetching with `*` unnecessarily.

### 3.3 Practice Exercises
1. Fetch all records from `employee`.
2. Select employees with pay > 60,000, ordered by pay descending.
3. List names and cities from `student` where class > 10.
4. Find employees from Hyderabad or Bangalore with `IN`.
5. Filter names starting with 'A', sorted alphabetically.

---

## 4. Comparisons

### 4.1 DQL vs. DML Commands
| Aspect            | DQL                        | DML                        |
|-------------------|----------------------------|----------------------------|
| Purpose           | Retrieve data              | Manipulate data            |
| Keyword           | `FROM`                     | `INTO`, `SET`, `FROM`      |
| Examples          | `SELECT`                   | `INSERT`, `UPDATE`         |
| Affects           | Output only                | Table data                 |

### 4.2 Basic SELECT vs. WHERE vs. ORDER BY
| Aspect            | Basic SELECT               | WHERE                     | ORDER BY                |
|-------------------|----------------------------|---------------------------|-------------------------|
| Action            | Retrieves all data         | Filters data              | Sorts data              |
| Data Impact       | All rows                   | Reduces rows              | Reorders rows           |
| Syntax            | `SELECT * FROM ...`        | `WHERE condition`         | `ORDER BY column`       |
| Use Case          | Full data fetch            | Targeted retrieval        | Organized output        |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
DQL commands, led by `SELECT`, retrieve data from MySQL tables like `employee` in `my_db`. Basic `SELECT` fetches all or specific columns, `WHERE` filters with operators (e.g., `IN`, `LIKE`), `ORDER BY` sorts results, and calculations/aliases enhance output. This builds on DML, preparing for aggregates next.

#### Highlights
- **SELECT**: Fetches data (e.g., `SELECT * FROM employee`).
- **WHERE**: Filters precisely (e.g., `emp_pay > 60000`).
- **ORDER BY**: Sorts output (e.g., `ORDER BY emp_city ASC`).
- **Takeaway**: Master DQL for effective data retrieval! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `SELECT * FROM`      | Fetches all columns                             | `SELECT * FROM employee;`                    |
| `SELECT columns`     | Fetches specific columns                        | `SELECT emp_id, emp_name FROM employee;`     |
| `WHERE ... =`        | Equal comparison                                | `SELECT * FROM employee WHERE emp_city = 'Mumbai';` |
| `WHERE ... AND`      | Combines conditions                             | `SELECT * FROM employee WHERE emp_pay > 60000 AND emp_city = 'Hyderabad';` |
| `WHERE ... IN`       | Matches set of values                           | `SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');` |
| `WHERE ... BETWEEN`  | Matches range                                   | `SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;` |
| `WHERE ... LIKE`     | Matches pattern                                 | `SELECT * FROM employee WHERE emp_name LIKE '_i%';` |
| `WHERE ... IS NULL`  | Filters null values                             | `SELECT * FROM employee WHERE emp_pay IS NULL;` |
| `WHERE ... ORDER BY` | Filters and sorts                               | `SELECT * FROM employee WHERE emp_pay > 60000 ORDER BY emp_city ASC;` |
| `SELECT ... AS`      | Aliases output                                  | `SELECT emp_pay * 12 AS annual_salary FROM employee;` |
| `SHOW DATABASES`     | Lists databases                                 | `SHOW DATABASES;`                            |
| `SHOW TABLES`        | Lists tables                                    | `SHOW TABLES;`                               |
| `DESCRIBE`           | Shows structure                                 | `DESCRIBE employee;`                         |
| `SELECT DATABASE()`  | Shows current database                          | `SELECT DATABASE();`                         |
| `UPDATE ... SET`     | Modifies data (DML, context)                    | `UPDATE employee SET emp_pay = NULL WHERE emp_id = 129;` |

