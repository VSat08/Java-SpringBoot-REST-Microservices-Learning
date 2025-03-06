# 2.3: DQL Commands

## Introduction
Welcome to **Section 2.3: DQL Commands** 🚀!

 In this session from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we dive into *Data Query Language (DQL)* commands—centered around the powerhouse `SELECT` command in MySQL. Across two lectures (75 and 76), we explore how `SELECT` retrieves data from tables, using clauses like `WHERE`, `ORDER BY`, `DISTINCT`, and `LIMIT`, along with a rich set of operators (relational, logical, membership, etc.). From fetching all records to filtering with wildcards and sorting results, this README covers syntax, examples, and practical insights using `employee` and `student` tables—ideal for beginners mastering SQL querying! 🌟

---

## Table of Contents
1. [Understanding DQL Commands](#1-understanding-dql-commands)
    - [What are DQL Commands?](#11-what-are-dql-commands)
    - [Why Use DQL Commands?](#12-why-use-dql-commands)
2. [DQL Commands in SQL](#2-dql-commands-in-sql)
    - [Core Concepts](#21-core-concepts)
    - [Need for DQL Commands](#22-need-for-dql-commands)
    - [Key DQL Command: SELECT](#23-key-dql-command-select)
        - [Basic SELECT](#231-basic-select)
        - [SELECT with WHERE Clause](#232-select-with-where-clause)
        - [SELECT with ORDER BY](#233-select-with-order-by)
        - [SELECT with DISTINCT](#234-select-with-distinct)
        - [SELECT with LIMIT](#235-select-with-limit)
    - [Advanced Features](#24-advanced-features)
    - [Implementation Overview](#25-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [Best Practices](#31-best-practices)
    - [Common Pitfalls](#32-common-pitfalls)
    - [Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [DQL vs. DML Commands](#41-dql-vs-dml-commands)
    - [WHERE vs. ORDER BY vs. DISTINCT vs. LIMIT](#42-where-vs-order-by-vs-distinct-vs-limit)
5. [Resources & Summary](#5-resources--summary)
    - [Resources](#51-resources)
    - [Summary](#52-summary)
    - [SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding DQL Commands

### 1.1 What are DQL Commands?
*Data Query Language (DQL)* commands in SQL are designed to retrieve data from a database. The primary command, `SELECT`, fetches records (rows/tuples) from one or more tables based on specified conditions, making it the cornerstone of SQL querying.

#### Definition
- **DQL**: A subset of SQL for querying data, often synonymous with the `SELECT` command.
- **Records**: Rows of data in a table, also called tuples or instances.
- **Joins**: Combining data from multiple tables (introduced conceptually, detailed later).

#### Real-World Example
Imagine a library catalog: DQL (`SELECT`) lets you list all books, filter by author, or sort by publication date—extracting exactly what you need from the collection.

#### Key Terms
| Term          | Definition                                | Example                |
|---------------|-------------------------------------------|------------------------|
| DQL           | Language to query data                    | `SELECT`               |
| Records       | Rows of data in a table                   | Rows in `employee`     |
| Joins         | Linking multiple tables                   | `SELECT ... FROM t1, t2` |

### 1.2 Why Use DQL Commands?
DQL commands are vital because they:
- Retrieve data from tables after schema (DDL) and data (DML) are set.
- Filter records based on conditions (e.g., city or salary).
- Sort and limit results for analysis or display.
- Support everything from simple lookups to complex multi-table queries.

#### Analogy
DQL is like a librarian: it finds and organizes books (data) from the shelves (tables) based on your request—whether all titles or just those by a specific author.

---

## 2. DQL Commands in SQL

### 2.1 Core Concepts
DQL focuses on data retrieval, using `SELECT` as its sole command within SQL’s command family (DDL, DML, DCL, TCL, DQL). Executed in MySQL, it operates on tables, leveraging clauses (`WHERE`, `ORDER BY`) and operators (e.g., `=`, `>`) to refine results. It’s case-insensitive and foundational to SQL usage.

### 2.2 Need for DQL Commands
After defining tables (DDL) and populating them (DML), you need to access the data. DQL fulfills this by:
- Fetching all or specific records from tables.
- Applying conditions to filter data (e.g., `WHERE city = 'Hyderabad'`).
- Ordering or limiting output for usability.

#### Snippet: Without DQL
Without DQL, your data is locked in tables—like a library with no way to check out books!

### 2.3 Key DQL Command: SELECT
`SELECT` is the heart of DQL, with versatile syntax and clauses for data retrieval.

#### 2.3.1 Basic SELECT
Fetches data from a table, either all columns or specific ones.

- **Traits**: Retrieves records, uses `FROM` keyword.
- **Syntax**:
  - All columns: `SELECT * FROM table_name;`
  - Specific columns: `SELECT column1, column2 FROM table_name;`

##### Snippet: Fetch All Records
```sql
SELECT * FROM employee;
```
*Output*: Displays all 8–10 records (depending on inserts) with `emp_id`, `emp_name`, `emp_city`, `emp_pay`, `dob`.

##### Snippet: Fetch Specific Columns
```sql
SELECT emp_id, emp_name FROM employee;
SELECT emp_city FROM employee;
```
*Output*: Shows only `emp_id` and `emp_name`, or just `emp_city`.

#### 2.3.2 SELECT with WHERE Clause
Filters records based on conditions using operators.

- **Traits**: Conditional retrieval, uses relational, logical, and other operators.
- **Syntax**: `SELECT * FROM table_name WHERE condition;`
- **Operators**:
  - **Relational**: `=`, `!=` (or `<>`), `>`, `<`, `>=`, `<=`.
  - **Logical**: `AND`, `OR`, `NOT`.
  - **Membership**: `IN`, `NOT IN`.
  - **Range**: `BETWEEN ... AND ...`.
  - **Pattern**: `LIKE` with `%` (any substring) and `_` (single character).
  - **Identity**: `IS NULL`, `IS NOT NULL`.

##### Snippet: Simple WHERE
```sql
SELECT * FROM employee WHERE emp_city = 'Hyderabad';
SELECT * FROM employee WHERE emp_pay >= 75000;
```
*Output*: Lists employees from Hyderabad (2 rows) or with pay ≥ 75,000 (6 rows).

##### Snippet: Multiple Conditions
```sql
SELECT * FROM employee WHERE emp_pay > 75000 AND emp_city = 'Hyderabad';
```
*Output*: Shows employees with pay > 75,000 from Hyderabad (1 row).

##### Snippet: IN Operator
```sql
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');
```
*Output*: Lists employees from Hyderabad or Bangalore (6 rows).

##### Snippet: BETWEEN Operator
```sql
SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;
```
*Output*: Shows employees with pay between 60,000 and 80,000 (6 rows).

##### Snippet: LIKE Operator
```sql
SELECT * FROM employee WHERE emp_name LIKE '_i%';
SELECT * FROM employee WHERE emp_name LIKE 'A%';
SELECT * FROM employee WHERE emp_name LIKE '%a';
```
*Output*: 
- `_i%`: Names with second letter 'i' (e.g., Sita, Rita, Geetha, Vikas).
- `A%`: Names starting with 'A' (e.g., Anil).
- `%a`: Names ending with 'a' (e.g., Geetha, Rita).

##### Snippet: IS NULL
```sql
UPDATE employee SET emp_pay = NULL WHERE emp_id = 140;
SELECT * FROM employee WHERE emp_pay IS NULL;
```
*Output*: Lists employees with `NULL` pay (1 row after update).

> [!WARNING]
> Don’t use `= NULL`—it fails! Use `IS NULL` or `IS NOT NULL` for null checks.

#### 2.3.3 SELECT with ORDER BY
Sorts retrieved records by a column in ascending (`ASC`) or descending (`DESC`) order.

- **Traits**: Orders results, defaults to `ASC`.
- **Syntax**: `SELECT * FROM table_name ORDER BY column [ASC|DESC];`

##### Snippet: Order by Name
```sql
SELECT * FROM employee ORDER BY emp_name ASC;
```
*Output*: Lists employees alphabetically (e.g., Anil, Geetha, Ram...).

##### Snippet: Order by Pay
```sql
SELECT * FROM employee ORDER BY emp_pay DESC;
```
*Output*: Lists employees by pay, highest first (e.g., Sam 86,000...).

#### 2.3.4 SELECT with DISTINCT
Removes duplicate values from results.

- **Traits**: Ensures uniqueness, applied to a column.
- **Syntax**: `SELECT DISTINCT column FROM table_name;`

##### Snippet: Distinct Pay
```sql
SELECT DISTINCT emp_pay FROM employee;
```
*Output*: Shows unique pay values (e.g., 5 rows from 8 due to duplicates like 75,000).

##### Snippet: Distinct City
```sql
SELECT DISTINCT emp_city FROM employee;
```
*Output*: Lists unique cities (e.g., 7 rows, Hyderabad appears once).

#### 2.3.5 SELECT with LIMIT
Restricts the number of returned records.

- **Traits**: Limits output, useful for large datasets.
- **Syntax**: `SELECT * FROM table_name LIMIT n;`

##### Snippet: Limit Records
```sql
SELECT * FROM employee LIMIT 5;
```
*Output*: Returns first 5 employee records.

##### Snippet: Combined Example
```sql
SELECT * FROM city WHERE country_code = 'IND' ORDER BY name ASC LIMIT 50;
```
*Output*: Lists first 50 Indian cities alphabetically from the `world` database.

> [!TIP]
> Use `LIMIT` with large tables (e.g., `city` with 4,079 rows) to manage output efficiently!

### 2.4 Advanced Features
`SELECT` supports additional capabilities for enhanced querying.

- **Calculations**: Perform arithmetic directly.
- **Aliases**: Rename output columns with `AS`.

##### Snippet: Calculations and Aliases
```sql
SELECT 4 * 3;
SELECT emp_name, emp_pay * 12 AS annual_salary FROM employee;
SELECT 22 / 7 AS pi;
```
*Output*: 
- `12` for `4 * 3`.
- Employee names with annual salaries (e.g., Ram, 936,000).
- `3.142857` as `pi`.

> [!NOTE]
> Aliases like `AS annual_salary` improve readability in results—no table changes needed.

### 2.5 Implementation Overview
DQL runs in MySQL, querying tables in databases like `my_db` or `world`. Utility commands include:
- `SHOW DATABASES`: Lists databases.
- `SHOW TABLES`: Lists tables.
- `DESCRIBE`: Shows table structure.
- `SELECT DATABASE()`: Confirms current database.

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
| WHERE  | ORDER BY | DISTINCT/LIMIT | --> Clauses
+--------+--------+-----------------+
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use specific columns (e.g., `SELECT emp_name, emp_city`) over `*` for clarity.
- Add `WHERE` conditions to target precise data (e.g., `WHERE emp_pay > 60000`).
- Use `DISTINCT` to eliminate duplicates when needed.
- Order results with `ORDER BY` for readability (e.g., `ASC` for names).
- Limit large datasets with `LIMIT` (e.g., `LIMIT 100`).
- Quote strings in conditions (e.g., `'Mumbai'`).

### 3.2 Common Pitfalls
- Using `*` unnecessarily, fetching unneeded data.
- Forgetting quotes in `WHERE` (e.g., `city = Mumbai` vs. `city = 'Mumbai'`).
- Using `= NULL` instead of `IS NULL` for null checks.
- Omitting `ORDER BY`, leaving results unsorted.
- Overlooking duplicates without `DISTINCT`.

### 3.3 Practice Exercises
1. From `employee` in `my_db`, select all records and then just `emp_name` and `emp_city`.
2. Find employees with `emp_pay > 70000`, ordered by `emp_pay` descending.
3. List unique `emp_city` values from `employee`.
4. Select employees from Hyderabad or Mumbai with `IN`, limited to 3 records.
5. Find employees whose `emp_name` starts with 'A' or ends with 's' using `LIKE`.

---

## 4. Comparisons

### 4.1 DQL vs. DML Commands
| Aspect            | DQL                        | DML                        |
|-------------------|----------------------------|----------------------------|
| Purpose           | Retrieve data              | Manipulate data            |
| Keyword           | `FROM`                     | `INTO`, `SET`, `FROM`      |
| Examples          | `SELECT`                   | `INSERT`, `UPDATE`         |
| Affects           | Output only                | Table data                 |

### 4.2 WHERE vs. ORDER BY vs. DISTINCT vs. LIMIT
| Aspect            | WHERE                      | ORDER BY                  | DISTINCT                | LIMIT                   |
|-------------------|----------------------------|---------------------------|-------------------------|-------------------------|
| Action            | Filters records            | Sorts records             | Removes duplicates      | Caps record count       |
| Data Impact       | Reduces rows              | Reorders rows             | Reduces rows            | Reduces rows            |
| Syntax            | `WHERE condition`          | `ORDER BY column`         | `DISTINCT column`       | `LIMIT n`               |
| Use Case          | Conditional retrieval      | Organized output          | Unique values           | Controlled output size  |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
DQL, powered by `SELECT`, is SQL’s data retrieval engine. Across two sessions, we’ve covered fetching all or specific columns, filtering with `WHERE` (using operators like `=`, `IN`, `LIKE`), sorting with `ORDER BY`, removing duplicates with `DISTINCT`, and capping results with `LIMIT`. Enhanced with calculations and aliases, `SELECT` queries tables like `employee` and `city` in MySQL databases (`my_db`, `world`). Future lessons will explore joins, aggregates, and subqueries.

#### Highlights
- **SELECT**: Fetches all (`*`) or specific data (e.g., `emp_name`).
- **WHERE**: Filters with operators (e.g., `BETWEEN`, `LIKE '_i%'`).
- **ORDER BY**: Sorts (e.g., `emp_pay DESC`).
- **DISTINCT/LIMIT**: Ensures uniqueness, controls output.
- **Takeaway**: Master `SELECT` to unlock database insights! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `SELECT * FROM`      | Fetches all columns from a table                 | `SELECT * FROM employee;`                    |
| `SELECT column`      | Fetches specific columns                         | `SELECT emp_id, emp_name FROM employee;`     |
| `SELECT DISTINCT`    | Fetches unique values                            | `SELECT DISTINCT emp_pay FROM employee;`     |
| `SELECT ... WHERE`   | Filters records by condition                     | `SELECT * FROM employee WHERE emp_city = 'Hyderabad';` |
| `SELECT ... WHERE IN` | Filters by multiple values                      | `SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');` |
| `SELECT ... WHERE BETWEEN` | Filters by a range                         | `SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;` |
| `SELECT ... WHERE LIKE` | Filters by pattern                          | `SELECT * FROM employee WHERE emp_name LIKE '_i%';` |
| `SELECT ... WHERE IS NULL` | Filters for null values                  | `SELECT * FROM employee WHERE emp_pay IS NULL;` |
| `SELECT ... ORDER BY` | Sorts results                                   | `SELECT * FROM employee ORDER BY emp_name ASC;` |
| `SELECT ... LIMIT`   | Limits number of records                         | `SELECT * FROM employee LIMIT 5;`            |
| `SELECT ... AS`      | Aliases a column or expression                   | `SELECT emp_pay * 12 AS annual_salary FROM employee;` |
| `SHOW DATABASES`     | Lists all databases                              | `SHOW DATABASES;`                            |
| `SHOW TABLES`        | Lists tables in current database                 | `SHOW TABLES;`                               |
| `DESCRIBE`           | Displays table structure                         | `DESCRIBE employee;`                         |
| `SELECT DATABASE()`  | Shows current database                           | `SELECT DATABASE();`                         |
| `UPDATE ... SET`     | Modifies data (DML, for context)                 | `UPDATE employee SET emp_pay = NULL WHERE emp_id = 140;` |

---
