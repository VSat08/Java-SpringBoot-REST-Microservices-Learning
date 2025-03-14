# 2.2: DDL Commands

## Introduction
Welcome to **Section 2.2: DDL Commands** 🚀! In this comprehensive guide from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we dive into *Data Definition Language (DDL)* commands—your foundational toolkit for shaping database structures in MySQL. Spanning two sessions (Lectures 72 and 73), we’ll explore `CREATE`, `ALTER`, `DROP`, and `TRUNCATE`, covering their roles in managing metadata (schema). From building tables like `student` and `employee` to modifying columns, dropping structures, and handling data, this README offers syntax, examples, and practical insights—perfect for beginners mastering SQL database design! 🌟

---

## Table of Contents
1. [Understanding DDL Commands](#1-understanding-ddl-commands)
    - [What are DDL Commands?](#11-what-are-ddl-commands)
    - [Why Use DDL Commands?](#12-why-use-ddl-commands)
2. [DDL Commands in SQL](#2-ddl-commands-in-sql)
    - [Core Concepts](#21-core-concepts)
    - [Need for DDL Commands](#22-need-for-ddl-commands)
    - [Key DDL Commands](#23-key-ddl-commands)
        - [CREATE Command](#231-create-command)
        - [ALTER Command](#232-alter-command)
        - [DROP Command](#233-drop-command)
        - [TRUNCATE Command](#234-truncate-command)
    - [Data Types in DDL](#24-data-types-in-ddl)
    - [Database Creation and Management](#25-database-creation-and-management)
    - [Table Creation and Verification](#26-table-creation-and-verification)
    - [Inserting Data (DML Context)](#27-inserting-data-dml-context)
    - [Retrieving Data (Query Context)](#28-retrieving-data-query-context)
    - [Implementation Overview](#29-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [Best Practices](#31-best-practices)
    - [Common Pitfalls](#32-common-pitfalls)
    - [Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [DDL vs. Other SQL Commands](#41-ddl-vs-other-sql-commands)
    - [CREATE vs. ALTER vs. DROP vs. TRUNCATE](#42-create-vs-alter-vs-drop-vs-truncate)
5. [Resources & Summary](#5-resources--summary)
    - [Resources](#51-resources)
    - [Summary](#52-summary)
    - [SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding DDL Commands

### 1.1 What are DDL Commands?
*Data Definition Language (DDL)* commands in SQL define and manage the **metadata**—data about data—or the **schema** (structure) of a database. Unlike commands that manipulate data, DDL focuses on creating, altering, or deleting frameworks (e.g., tables, databases) that hold data.

#### Definition
- **DDL**: A subset of SQL for specifying database structure.
- **Metadata**: Information about data, such as column names and types.
- **Schema**: The organizational blueprint of a database.

#### Real-World Example
Imagine a filing cabinet: DDL builds the drawers (tables) and labels them (columns) like "Employee ID" or "Name" before filing papers (data).

#### Key Terms
| Term          | Definition                                | Example                |
|---------------|-------------------------------------------|------------------------|
| DDL           | Language to define database structure     | `CREATE TABLE`         |
| Metadata      | Data describing the data                  | Column names, types    |
| Schema        | Structure of database objects             | Table layout           |

### 1.2 Why Use DDL Commands?
DDL commands are crucial because they:
- Establish structures (e.g., tables) before data entry.
- Enable modifications as needs evolve (e.g., adding/dropping columns).
- Allow removal of obsolete structures or data.
- Work across DBMS like MySQL, Oracle, and SQL Server.

#### Analogy
DDL is like drafting a house blueprint: you define rooms (tables) and features (columns) before adding furniture (data).

---

## 2. DDL Commands in SQL

### 2.1 Core Concepts
DDL commands target the database’s structure, not its contents. Part of SQL’s command family (DDL, DML, DCL, TCL, Query), they use the `TABLE` keyword to manipulate schemas. Executed in MySQL, they define how data is stored and organized, often with engines like InnoDB or MyISAM.

### 2.2 Need for DDL Commands
Data needs a structured container before storage or manipulation. DDL commands address this by:
- Creating databases and tables.
- Defining columns and data types (e.g., `INT`, `VARCHAR`).
- Supporting updates or deletions as requirements change.

#### Snippet: Without DDL
Without DDL, there’s no table to hold data—like organizing files without folders!

### 2.3 Key DDL Commands
DDL includes four primary commands, each with a unique role in schema management.

#### 2.3.1 CREATE Command
The `CREATE` command builds new databases or tables, establishing their schema.

- **Traits**: Defines structure, requires column data types.
- **Syntax**: `CREATE TABLE table_name (column1 datatype, ...);`
- **Advanced Usage**: Create a table from another table using `AS SELECT`.

##### Snippet: Creating a Table
```sql
CREATE TABLE student (
    student_id INT,
    student_name VARCHAR(20),
    student_gpa FLOAT,
    student_city VARCHAR(20)
);
```
*Output*: A table structure is ready for data.

##### Snippet: Creating from Existing Table
```sql
CREATE TABLE emp AS SELECT * FROM employee;
```
*Output*: Creates `emp` with the same structure and data as `employee`.

> [!TIP]
> Use `CREATE TABLE ... AS SELECT` to duplicate a table’s structure and data—great for backups or testing!

#### 2.3.2 ALTER Command
The `ALTER` command modifies an existing table’s structure (e.g., adding, dropping, or modifying columns).

- **Traits**: Updates schema, preserves compatible data.
- **Operations**:
  - Add: `ALTER TABLE table_name ADD column_name datatype;`
  - Drop: `ALTER TABLE table_name DROP COLUMN column_name;`
  - Modify: `ALTER TABLE table_name MODIFY column_name datatype;`
- **Future Scope**: Can add/drop constraints (e.g., primary keys, not null)—to be covered in advanced sessions.

##### Snippet: Adding a Column
```sql
ALTER TABLE employee ADD dob INT;
```
*Output*: Adds `dob` (year) to `employee`, defaulting to `NULL`.

##### Snippet: Dropping a Column
```sql
ALTER TABLE employee DROP COLUMN dob;
```
*Output*: Removes `dob` permanently from `employee`.

##### Snippet: Modifying a Column
```sql
ALTER TABLE employee MODIFY dob DATE;
```
*Output*: Changes `dob` from `INT` to `DATE` (requires data consistency).

> [!WARNING]
> Modifying a column’s data type (e.g., `INT` to `DATE`) fails if existing data doesn’t match the new format. Delete incompatible data first (e.g., `DELETE FROM employee WHERE dob = 2002;`).

#### 2.3.3 DROP Command
The `DROP` command deletes entire databases or tables, including structure and data.

- **Traits**: Permanent, no recovery without backups.
- **Syntax**: `DROP TABLE table_name;`

##### Snippet: Dropping a Table
```sql
DROP TABLE employee;
```
*Output*: Removes `employee` entirely.

> [!NOTE]
> Before dropping a table with data, copy it elsewhere (e.g., `CREATE TABLE emp AS SELECT * FROM employee`) to avoid data loss.

#### 2.3.4 TRUNCATE Command
The `TRUNCATE` command clears all data from a table, keeping its structure intact.

- **Traits**: Deletes data only, faster than row-by-row deletion.
- **Syntax**: `TRUNCATE TABLE table_name;`

##### Snippet: Truncating a Table
```sql
TRUNCATE TABLE employee;
```
*Output*: Empties `employee`, leaving the structure.

### 2.4 Data Types in DDL
DDL commands rely on data types to define columns. MySQL examples include:
- **Strings**: 
  - `CHAR` (fixed length, e.g., grade 'A').
  - `VARCHAR(n)` (variable length, e.g., `VARCHAR(20)` for names).
  - `TEXT` (large text).
- **Numerics**: 
  - `INT` (whole numbers, up to 11 digits).
  - `FLOAT` (floating-point, e.g., pay).
  - `DECIMAL` (precise decimals).
- **Boolean**: `BOOL` (0 = false, 1 = true).
- **Dates**: `DATE` (e.g., `YYYY-MM-DD`), `TIME`, `TIMESTAMP`.
- **Binary**: `BLOB` (e.g., images).

#### Snippet: Data Types in Action
```sql
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT,
    dob DATE
);
```
*Explanation*: `INT` for ID, `VARCHAR` for strings, `FLOAT` for pay, `DATE` for date of birth.

> [!TIP]
> Use single quotes for `DATE` values (e.g., `'2002-02-28'`) to match MySQL’s `YYYY-MM-DD` format.

> [!NOTE]
> Data types vary slightly across DBMS (e.g., MySQL vs. Oracle); check specific docs.

### 2.5 Database Creation and Management
Databases are the foundation for tables. Steps include:
- Create: `CREATE DATABASE db_name;`.
- Switch: `USE db_name;`.
- Verify: `SHOW DATABASES;` or `SELECT DATABASE();`.
- Drop: `DROP DATABASE db_name;`.

##### Snippet: Database Workflow
```sql
CREATE DATABASE my_db;
USE my_db;
SHOW DATABASES;
SELECT DATABASE();
DROP DATABASE my_db;
```

### 2.6 Table Creation and Verification
Tables are created within databases, with tools to verify structure:
- **Creation**: `CREATE TABLE table_name (column datatype, ...);`.
- **Verification**:
  - `DESCRIBE table_name;` or `DESC table_name;`.
  - `SHOW CREATE TABLE table_name;`.

##### Snippet: Creating and Checking
```sql
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT
);
DESCRIBE employee;
SHOW CREATE TABLE employee;
```
*Output*: Shows columns (e.g., `emp_id INT NULL`) and engine (e.g., InnoDB).

### 2.7 Inserting Data (DML Context)

- **Syntax**: `INSERT INTO table_name VALUES (value1, value2, ...);`.

##### Snippet: Inserting Records
```sql
INSERT INTO employee VALUES (123, 'Vasis', 'June', 56000, '2002-02-28');
INSERT INTO employee VALUES 
    (112, 'Ram', 'New Delhi', 78000, NULL),
    (139, 'Sham', 'Hyderabad', 66000, NULL);
```
*Output*: Adds records to `employee`.

> [!WARNING]
> Omitting single quotes for strings or dates (e.g., `2002-02-28` vs. `'2002-02-28'`) causes errors in MySQL.

### 2.8 Retrieving Data (Query Context)
`SELECT` (Query) verifies data, included for completeness:
- **Syntax**: `SELECT * FROM table_name;` (`*` = all columns).

##### Snippet: Retrieving Data
```sql
SELECT * FROM employee;
```
*Output*: Shows all records (e.g., `123, 'Vasis', 'June', 56000, '2002-02-28'`).

### 2.9 Implementation Overview
DDL runs in MySQL with engines like InnoDB. Utility commands include:
- `SHOW DATABASES`: Lists databases.
- `SHOW TABLES`: Lists tables.
- `SHOW FULL TABLES`: Includes views.
- `DESCRIBE`/`DESC`: Shows structure.
- `SHOW CREATE TABLE`: Shows creation script.

##### Textual Hierarchy Diagram
```
+-----------------+
| DDL Commands    | --> Define Schema
+-----------------+
    |
    v
+--------+--------+-----------------+
| CREATE | ALTER  | DROP/TRUNCATE   | --> Core Commands
+--------+--------+-----------------+
    |        |         |
    v        v         v
[Database,  [Add/Drop/   [Remove Structure/Data]
 Tables]     Modify]     --> Actions
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Start with `CREATE DATABASE` to organize tables.
- Use explicit data types (e.g., `VARCHAR(20)` over `TEXT`).
- End statements with `;` for clarity.
- Use single quotes for strings and dates (e.g., `'Vasis'`, `'2002-02-28'`).
- Verify changes with `DESCRIBE` or `SHOW CREATE TABLE`.
- Backup data before `DROP` (e.g., `CREATE TABLE emp AS SELECT * FROM employee`).

### 3.2 Common Pitfalls
- Forgetting `TABLE` or `COLUMN` keywords in DDL syntax.
- Mixing `DROP` (structure) with `TRUNCATE` (data only).
- Using double quotes instead of single quotes for values.
- Modifying data types without adjusting existing data (e.g., `INT` to `DATE` with `2002`).
- Not selecting a database with `USE` before operations.

### 3.3 Practice Exercises
1. Create a database `test_db` and table `student` (`id INT`, `name VARCHAR(20)`, `gpa FLOAT`).
2. Add a column `dob DATE` to `student` using `ALTER TABLE`.
3. Insert a record into `student` (e.g., `100, 'ABC', 8.9, '2000-01-15'`).
4. Modify `gpa` to `DECIMAL(3,1)` and drop `dob` from `student`.
5. Create `student_backup` from `student`, truncate `student`, then drop `student_backup`.

---

## 4. Comparisons

### 4.1 DDL vs. Other SQL Commands
| Aspect            | DDL                        | DML (e.g., INSERT)       | Query (e.g., SELECT)    |
|-------------------|----------------------------|--------------------------|-------------------------|
| Purpose           | Define structure           | Manipulate data          | Retrieve data           |
| Keyword           | `TABLE`                    | None                     | None                    |
| Examples          | `CREATE`, `ALTER`          | `INSERT`, `DELETE`       | `SELECT`                |
| Affects           | Schema                     | Data                     | Output                  |

### 4.2 CREATE vs. ALTER vs. DROP vs. TRUNCATE
| Aspect            | CREATE                     | ALTER                    | DROP                    | TRUNCATE                |
|-------------------|----------------------------|--------------------------|-------------------------|-------------------------|
| Action            | Builds new structure       | Modifies structure       | Deletes structure       | Deletes data only       |
| Data Impact       | None (empty structure)     | Preserves (if compatible)| Deletes all             | Deletes all             |
| Syntax            | `CREATE TABLE ...`         | `ALTER TABLE ...`        | `DROP TABLE ...`        | `TRUNCATE TABLE ...`    |
| Use Case          | New table setup            | Add/drop/modify column   | Remove table            | Reset table data        |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
DDL commands (`CREATE`, `ALTER`, `DROP`, `TRUNCATE`) are the backbone of database structure management in SQL. Across two sessions, we’ve covered creating databases (`my_db`) and tables (`employee`), altering them (adding/dropping/modifying columns like `dob`), and removing structures or data. Executed in MySQL, these commands use data types like `INT`, `VARCHAR`, and `DATE`, with tools like `SHOW` and `DESCRIBE` for verification. Future sessions will explore constraints (e.g., primary keys).

#### Highlights
- **CREATE**: Builds tables/databases, including from existing tables.
- **ALTER**: Adds (e.g., `employment_type`), drops, or modifies columns (e.g., `dob` to `DATE`).
- **DROP/TRUNCATE**: Removes structures or data.
- **Tools**: `SHOW`, `DESCRIBE` for inspection.
- **Takeaway**: Master DDL to design and refine database frameworks! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `CREATE DATABASE`    | Creates a new database                           | `CREATE DATABASE my_db;`                     |
| `CREATE TABLE`       | Creates a new table                              | `CREATE TABLE employee (emp_id INT, ...);`   |
| `CREATE TABLE ... AS`| Creates a table from another table’s data        | `CREATE TABLE emp AS SELECT * FROM employee;`|
| `ALTER TABLE ... ADD`| Adds a column to a table                         | `ALTER TABLE employee ADD dob INT;`          |
| `ALTER TABLE ... DROP`| Drops a column from a table                     | `ALTER TABLE employee DROP COLUMN dob;`      |
| `ALTER TABLE ... MODIFY`| Modifies a column’s data type                 | `ALTER TABLE employee MODIFY dob DATE;`      |
| `DROP DATABASE`      | Deletes an entire database                       | `DROP DATABASE my_db;`                       |
| `DROP TABLE`         | Deletes a table and its data                     | `DROP TABLE employee;`                       |
| `TRUNCATE TABLE`     | Removes all data, keeps structure                | `TRUNCATE TABLE employee;`                   |
| `USE`                | Switches to a database                           | `USE my_db;`                                 |
| `SHOW DATABASES`     | Lists all databases                              | `SHOW DATABASES;`                            |
| `SHOW TABLES`        | Lists tables in current database                 | `SHOW TABLES;`                               |
| `SHOW FULL TABLES`   | Lists tables and views                           | `SHOW FULL TABLES;`                          |
| `DESCRIBE` / `DESC`  | Displays table structure                         | `DESCRIBE employee;`                         |
| `SHOW CREATE TABLE`  | Shows table creation script                      | `SHOW CREATE TABLE employee;`                |
| `SELECT DATABASE()`  | Shows current database                           | `SELECT DATABASE();`                         |
| `INSERT INTO`        | Adds data (DML)                                  | `INSERT INTO employee VALUES (123, 'Vasis', 'June', 56000, '2002-02-28');` |
| `DELETE FROM`        | Removes data (DML)                               | `DELETE FROM employee WHERE dob = 2002;`     |
| `SELECT * FROM`      | Retrieves all data (Query)                       | `SELECT * FROM employee;`                    |

