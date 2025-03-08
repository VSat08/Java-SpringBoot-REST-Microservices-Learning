# 2.3: DML Commands

## Introduction
Welcome to **Section 2.3: DML Commands** 🚀! In this session from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we explore *Data Manipulation Language (DML)* commands—your essential tools for managing data within database tables in MySQL. Covering `INSERT`, `UPDATE`, and `DELETE`, this guide dives into how these commands populate, modify, and remove records, forming the backbone of CRUD operations (Create, Retrieve, Update, Delete). With practical examples using an `employee` table, it’s perfect for beginners looking to manipulate database instances like a pro! 🌟

---

## Table of Contents
1. [Understanding DML Commands](#1-understanding-dml-commands)
    - [What are DML Commands?](#11-what-are-dml-commands)
    - [Why Use DML Commands?](#12-why-use-dml-commands)
2. [DML Commands in SQL](#2-dml-commands-in-sql)
    - [Core Concepts](#21-core-concepts)
    - [Need for DML Commands](#22-need-for-dml-commands)
    - [Key DML Commands](#23-key-dml-commands)
        - [INSERT Command](#231-insert-command)
        - [UPDATE Command](#232-update-command)
        - [DELETE Command](#233-delete-command)
    - [Inserting Data from Another Table](#24-inserting-data-from-another-table)
    - [Implementation Overview](#25-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [Best Practices](#31-best-practices)
    - [Common Pitfalls](#32-common-pitfalls)
    - [Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [DML vs. DDL Commands](#41-dml-vs-ddl-commands)
    - [INSERT vs. UPDATE vs. DELETE](#42-insert-vs-update-vs-delete)
5. [Resources & Summary](#5-resources--summary)
    - [Resources](#51-resources)
    - [Summary](#52-summary)
    - [SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding DML Commands

### 1.1 What are DML Commands?
*Data Manipulation Language (DML)* commands in SQL are used to manipulate the **data** within a database table—known as the database instance—rather than its structure. These commands add, modify, or remove records, enabling you to manage the content of tables after their schema is defined.

#### Definition
- **DML**: A subset of SQL for manipulating data in tables.
- **Database Instance**: The actual data stored within a table’s structure.
- **CRUD**: Create (Insert), Retrieve (Select), Update, Delete—key operations for data management.

#### Real-World Example
Think of a library ledger: DML commands let you add new book entries (`INSERT`), update borrowing details (`UPDATE`), or remove outdated records (`DELETE`), all within the existing ledger structure.

#### Key Terms
| Term            | Definition                                | Example                |
|-----------------|-------------------------------------------|------------------------|
| DML             | Language to manipulate data               | `INSERT INTO`          |
| Database Instance | The data within a table                  | Rows in `employee`     |
| CRUD            | Create, Retrieve, Update, Delete          | `INSERT`, `SELECT`, etc.|

### 1.2 Why Use DML Commands?
DML commands are essential because they:
- Populate tables with new data (e.g., adding employee records).
- Update existing records to reflect changes (e.g., correcting a name).
- Remove unwanted data based on conditions (e.g., deleting a record).
- Enable full CRUD functionality alongside `SELECT` (covered later).

#### Analogy
DML is like managing a guest list: you add names (`INSERT`), update their RSVP status (`UPDATE`), or remove no-shows (`DELETE`)—all within the list’s structure.

---

## 2. DML Commands in SQL

### 2.1 Core Concepts
DML commands focus on data manipulation, not schema definition. Unlike DDL, they don’t use the `TABLE` keyword and operate on the database instance within a MySQL table. They’re part of SQL’s command family (DDL, DML, DCL, TCL, Query) and are crucial for managing table content.

### 2.2 Need for DML Commands
Once a table’s structure is created (via DDL), you need to manage its data. DML commands fulfill this by:
- Inserting new records into tables.
- Updating existing records with new values.
- Deleting records based on specific conditions.

#### Snippet: Without DML
Without DML, your table is an empty shell—like a filing cabinet with no files inside!

### 2.3 Key DML Commands
DML includes three primary commands for manipulating data: `INSERT`, `UPDATE`, and `DELETE`.

#### 2.3.1 INSERT Command
The `INSERT` command adds new records to a table, offering two approaches.

- **Traits**: Populates tables, flexible column selection.
- **Syntax**:
  - Full insert: `INSERT INTO table_name VALUES (value1, value2, ...);`
  - Selective insert: `INSERT INTO table_name (column1, column2) VALUES (value1, value2);`

##### Snippet: Full Insert
```sql
INSERT INTO employee VALUES (100, 'Sam', 'London', 86000, '1996-09-18');
INSERT INTO employee VALUES (200, 'Rita', 'Bangalore', 42000, '1999-01-06');
```
*Output*: Adds two complete records to `employee`.

##### Snippet: Selective Insert
```sql
INSERT INTO employee (emp_id, emp_name, emp_pay) VALUES (201, 'Geetha', 75000);
INSERT INTO employee (emp_id, emp_pay, emp_city) VALUES (211, 85000, 'New York');
```
*Output*: Adds records with specific columns, leaving others `NULL`.

> [!TIP]
> Use selective `INSERT` to add data for only known columns—order doesn’t matter as long as values match the listed columns!

#### 2.3.2 UPDATE Command
The `UPDATE` command modifies existing records in a table, often with a `WHERE` condition.

- **Traits**: Changes data, requires `SET` keyword, conditional with `WHERE`.
- **Syntax**: `UPDATE table_name SET column1 = value1, column2 = value2 WHERE condition;`

##### Snippet: Updating a Single Record
```sql
UPDATE employee SET emp_name = 'Vikas', dob = '2001-02-14' WHERE emp_id = 211;
```
*Output*: Updates `emp_name` and `dob` for `emp_id = 211`.

##### Snippet: Updating Multiple Columns
```sql
UPDATE employee SET emp_city = 'Hyderabad' WHERE emp_id = 201;
UPDATE employee SET dob = '1998-06-15' WHERE emp_id = 201;
```
*Output*: Sets `emp_city` and `dob` for `emp_id = 201` in two steps.

##### Snippet: Updating Multiple Records
```sql
UPDATE employee SET dob = '1990-07-01' WHERE emp_id IN (123, 112, 139);
```
*Output*: Sets `dob` for three employees using `IN`.

> [!WARNING]
> Omitting `WHERE` in `UPDATE` changes all records—always specify a condition (e.g., `WHERE emp_id = 211`) to target specific rows!

#### 2.3.3 DELETE Command
The `DELETE` command removes existing records from a table, typically with a `WHERE` condition.

- **Traits**: Deletes data, uses `FROM`, conditional with `WHERE`.
- **Syntax**: `DELETE FROM table_name WHERE condition;`

##### Snippet: Deleting a Record
```sql
DELETE FROM employee WHERE emp_id = 139;
DELETE FROM employee WHERE emp_name = 'Vikas';
```
*Output*: Removes records for `emp_id = 139` and `emp_name = 'Vikas'`.

##### Snippet: Deleting All Records
```sql
DELETE FROM employee;
```
*Output*: Empties `employee`, keeping the structure (similar to `TRUNCATE`).

> [!NOTE]
> Use `DELETE` with `WHERE` to avoid wiping the entire table—without it, all data is removed, leaving the structure intact.

### 2.4 Inserting Data from Another Table
You can insert data from one table into another using a `SELECT` subquery.

- **Syntax**: `INSERT INTO table_name SELECT * FROM source_table;`

##### Snippet: Inserting from Another Table
```sql
CREATE TABLE emp_backup AS SELECT * FROM employee;
DELETE FROM employee;
INSERT INTO employee SELECT * FROM emp_backup;
```
*Output*: Copies all data from `emp_backup` back into `employee`.

> [!TIP]
> Use `INSERT INTO ... SELECT` to transfer data between tables—no `VALUES` keyword needed when copying all columns!

### 2.5 Implementation Overview
DML commands run in MySQL, manipulating data within tables created by DDL. Key tools include:
- `SELECT * FROM`: Verifies data after manipulation.
- `WHERE`: Targets specific records with conditions (e.g., `emp_id = 211`, `IN (123, 112)`).
- SQL is case-insensitive (e.g., `UPDATE` = `update`).

##### Textual Hierarchy Diagram
```
+-----------------+
| DML Commands    | --> Manipulate Data
+-----------------+
    |
    v
+--------+--------+-----------------+
| INSERT | UPDATE | DELETE          | --> Core Commands
+--------+--------+-----------------+
    |        |         |
    v        v         v
[Add Data] [Modify] [Remove Data] --> Actions
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use selective `INSERT` for partial data entry (e.g., `INSERT INTO employee (emp_id, emp_name)`).
- Always include `WHERE` in `UPDATE` and `DELETE` to avoid affecting all rows.
- Use single quotes for string and date values (e.g., `'Vikas'`, `'1990-07-01'`).
- Verify changes with `SELECT * FROM table_name` after DML operations.
- Backup data (e.g., `CREATE TABLE backup AS SELECT * FROM table`) before mass deletes.

### 3.2 Common Pitfalls
- Forgetting `WHERE` in `UPDATE` or `DELETE`, altering/deleting all records.
- Misordering values in full `INSERT` without column specification.
- Using `NULL` in `WHERE` conditions (e.g., `WHERE dob = NULL`)—it doesn’t work; use `IS NULL` instead.
- Mixing up `DELETE` (removes data) with `DROP` (removes structure).
- Omitting quotes for strings/dates, causing syntax errors.

### 3.3 Practice Exercises
1. Create a table `staff` (`staff_id INT`, `name VARCHAR(20)`, `dept VARCHAR(20)`, `salary FLOAT`) in `my_db`.
2. Insert a full record (e.g., `101, 'John', 'HR', 60000`) and a selective record (e.g., `102, 'Mary', 55000` for `staff_id, name, salary`).
3. Update `dept` to `'IT'` for `staff_id = 102`.
4. Delete the record where `name = 'John'`.
5. Create `staff_backup`, delete all `staff` records, then restore from `staff_backup` using `INSERT INTO ... SELECT`.

---

## 4. Comparisons

### 4.1 DML vs. DDL Commands
| Aspect            | DML                        | DDL                        |
|-------------------|----------------------------|----------------------------|
| Purpose           | Manipulate data            | Define structure           |
| Keyword           | None (`INTO`, `SET`, `FROM`) | `TABLE`                  |
| Examples          | `INSERT`, `UPDATE`         | `CREATE`, `ALTER`          |
| Affects           | Data (instance)            | Schema                     |

### 4.2 INSERT vs. UPDATE vs. DELETE
| Aspect            | INSERT                     | UPDATE                    | DELETE                  |
|-------------------|----------------------------|---------------------------|-------------------------|
| Action            | Adds new records           | Modifies existing records | Removes records         |
| Data Impact       | Increases row count        | Changes values            | Decreases row count     |
| Syntax            | `INSERT INTO ...`          | `UPDATE ... SET ...`      | `DELETE FROM ...`       |
| Use Case          | New data entry             | Data correction           | Data removal            |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
DML commands (`INSERT`, `UPDATE`, `DELETE`) empower you to manipulate data within MySQL tables, supporting CRUD operations. `INSERT` adds records (full or selective), `UPDATE` modifies them with `SET` and `WHERE`, and `DELETE` removes them using `FROM` and conditions. You can even transfer data between tables with `INSERT INTO ... SELECT`. This session builds on DDL, setting the stage for `SELECT` queries and constraints in future lessons.

#### Highlights
- **INSERT**: Two methods—full or selective (e.g., `INSERT INTO employee (emp_id, emp_name)`).
- **UPDATE**: Targets records with `WHERE` (e.g., `emp_id IN (123, 112)`).
- **DELETE**: Removes specific or all data.
- **Tools**: `SELECT` for verification, `WHERE` for precision.
- **Takeaway**: Master DML to manage table data effectively! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `INSERT INTO ... VALUES` | Adds a full record to a table                | `INSERT INTO employee VALUES (100, 'Sam', 'London', 86000, '1996-09-18');` |
| `INSERT INTO (columns)` | Adds a selective record to a table          | `INSERT INTO employee (emp_id, emp_name, emp_pay) VALUES (201, 'Geetha', 75000);` |
| `INSERT INTO ... SELECT` | Inserts data from another table             | `INSERT INTO employee SELECT * FROM emp_backup;` |
| `UPDATE ... SET`     | Modifies existing records                       | `UPDATE employee SET emp_name = 'Vikas', dob = '2001-02-14' WHERE emp_id = 211;` |
| `DELETE FROM`        | Deletes records from a table                    | `DELETE FROM employee WHERE emp_id = 139;`   |
| `SELECT * FROM`      | Retrieves all data (Query, for verification)    | `SELECT * FROM employee;`                    |

---
