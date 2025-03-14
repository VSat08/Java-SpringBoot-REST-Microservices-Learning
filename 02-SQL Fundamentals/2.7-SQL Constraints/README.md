# 2.7: SQL Constraints - A Beginner's Manual to Mastering Data Integrity in MySQL

## Introduction
Welcome to **Section 2.7: SQL Constraints** 🚀! This section is your gateway to understanding how MySQL ensures data stays accurate, consistent, and reliable. We'll explore six key constraints—`NOT NULL`, `UNIQUE`, `PRIMARY KEY`, `FOREIGN KEY`, `CHECK`, and `DEFAULT`—across six lectures (79–84). Using practical examples from the `my_db` and `world` databases, this manual is designed for beginners, breaking down complex ideas like referential actions into simple, digestible pieces. Whether you're new to SQL or brushing up, you'll master table design by the end! 🌟

---

## Table of Contents
1. [What Are SQL Constraints?](#1-what-are-sql-constraints)
    - [1.1 Definition and Purpose](#11-definition-and-purpose)
    - [1.2 Why Constraints Matter](#12-why-constraints-matter)
    - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Getting Started with MySQL Constraints](#2-getting-started-with-mysql-constraints)
    - [2.1 How Constraints Work in MySQL](#21-how-constraints-work-in-mysql)
    - [2.2 The Problem Without Constraints](#22-the-problem-without-constraints)
3. [Core SQL Constraints](#3-core-sql-constraints)
    - [3.1 NOT NULL Constraint](#31-not-null-constraint)
    - [3.2 UNIQUE Constraint](#32-unique-constraint)
    - [3.3 PRIMARY KEY Constraint](#33-primary-key-constraint)
4. [Advanced SQL Constraints](#4-advanced-sql-constraints)
    - [4.1 FOREIGN KEY Constraint](#41-foreign-key-constraint)
    - [4.2 CHECK Constraint](#42-check-constraint)
    - [4.3 DEFAULT Constraint](#43-default-constraint)
5. [Deep Dive: Referential Actions with FOREIGN KEY](#5-deep-dive-referential-actions-with-foreign-key)
    - [5.1 What Are Referential Actions?](#51-what-are-referential-actions)
    - [5.2 The Five Referential Actions Explained](#52-the-five-referential-actions-explained)
    - [5.3 Applying Referential Actions](#53-applying-referential-actions)
6. [Practical Application](#6-practical-application)
    - [6.1 Best Practices for Using Constraints](#61-best-practices-for-using-constraints)
    - [6.2 Common Mistakes to Avoid](#62-common-mistakes-to-avoid)
    - [6.3 Hands-On Exercises](#63-hands-on-exercises)
7. [Comparing Constraints](#7-comparing-constraints)
    - [7.1 Column-Level vs. Table-Level Constraints](#71-column-level-vs-table-level-constraints)
    - [7.2 PRIMARY KEY vs. UNIQUE](#72-primary-key-vs-unique)
8. [Wrapping Up](#8-wrapping-up)
    - [8.1 Resources for Further Learning](#81-resources-for-further-learning)
    - [8.2 Summary of Key Takeaways](#82-summary-of-key-takeaways)
    - [8.3 Complete SQL Commands Reference](#83-complete-sql-commands-reference)

---

## 1. What Are SQL Constraints?

### 1.1 Definition and Purpose
SQL constraints are rules you set in a database to control what data can go into your tables. Think of them as "guardians" that keep your data honest, preventing mistakes like missing values, duplicates, or broken links between tables.

- **Definition**: A constraint is a condition or rule applied to a column or table to enforce data integrity—ensuring data is accurate, consistent, and reliable.
- **Purpose**: They stop invalid data from sneaking in, making your database trustworthy for storing and retrieving information.
- **Examples**: `NOT NULL` (no empty values), `UNIQUE` (no repeats), `PRIMARY KEY` (unique IDs), `FOREIGN KEY` (table links), `CHECK` (value limits), `DEFAULT` (backup values).

#### Real-World Analogy
Imagine a school registration form:
- **NOT NULL**: You *must* fill in your name.
- **UNIQUE**: Your student ID can’t match anyone else’s.
- **FOREIGN KEY**: Your class code must match an existing class.

### 1.2 Why Constraints Matter
Without constraints, your database is like a messy notebook—full of errors, duplicates, or missing info. Constraints:
- **Ensure Accuracy**: Only valid data gets in (e.g., no negative ages).
- **Maintain Consistency**: Related data stays connected (e.g., orders link to real customers).
- **Save Time**: They catch mistakes automatically, so you don’t have to fix them later.

#### Example Problem
If you store orders without linking them to customers properly, you might end up with orders for nonexistent people—chaos!

### 1.3 Key Terms for Beginners
| Term             | Meaning                                   | Example                |
|------------------|-------------------------------------------|------------------------|
| **Constraint**   | A rule to enforce data quality            | `NOT NULL` on a name   |
| **Integrity**    | Keeping data correct and linked           | Orders match customers |
| **Violation**    | Breaking a rule (triggers an error)       | Duplicate ID           |
| **Parent Table** | The main table with a `PRIMARY KEY`       | `customers`            |
| **Child Table**  | The table referencing the parent          | `orders`               |

---

## 2. Getting Started with MySQL Constraints

### 2.1 How Constraints Work in MySQL
MySQL, a popular database system, uses constraints to enforce rules when you create or modify tables. You define them with:
- **`CREATE TABLE`**: Set rules when making a new table.
- **`ALTER TABLE`**: Add or change rules later.
- **Levels**: Apply to one column (column-level) or multiple columns (table-level).

#### Four Ways to Apply Constraints
1. **Column-Level Simple**: Add the rule right after the column (e.g., `id INT NOT NULL`).
2. **Table-Level with CONSTRAINT**: Name the rule separately (e.g., `CONSTRAINT pk_id PRIMARY KEY (id)`).
3. **ALTER TABLE ADD**: Add a rule to an existing table (e.g., `ALTER TABLE orders ADD CHECK (...)`).
4. **ALTER TABLE with CONSTRAINT**: Add a named rule later (e.g., `ALTER TABLE orders ADD CONSTRAINT fk FOREIGN KEY ...`).

#### What Happens When Rules Are Broken?
MySQL stops the action (e.g., `INSERT`, `DELETE`) and shows an error message like "Constraint violated!"

### 2.2 The Problem Without Constraints
Without rules, your data can become unreliable. Let’s see an example:

#### Example: No Constraints
```sql
CREATE TABLE emp (
    emp_id INT,
    emp_name VARCHAR(20)
);
INSERT INTO emp VALUES (NULL, NULL); -- Allowed!
INSERT INTO emp VALUES (1, 'Alice');
INSERT INTO emp VALUES (1, 'Bob');   -- Duplicate ID!
```
- **Issues**: Missing IDs, missing names, duplicate IDs—total mess!
- **Fix**: Add `NOT NULL`, `UNIQUE`, or `PRIMARY KEY`.

---

## 3. Core SQL Constraints

### 3.1 NOT NULL Constraint
#### Definition
`NOT NULL` means a column *must* have a value—no blanks allowed. Without it, columns can be empty (NULL) by default.

#### Why Use It?
To ensure critical data isn’t missing (e.g., a customer’s name).

#### Instructions
- Add `NOT NULL` after the data type in `CREATE TABLE` or `ALTER TABLE`.
- Test by trying to skip the value—MySQL will block it.

#### Example: Mandatory Fields
```sql
CREATE TABLE persons (
    id INT NOT NULL,          -- Must have an ID
    lname VARCHAR(20) NOT NULL, -- Must have a last name
    fname VARCHAR(20) NOT NULL, -- Must have a first name
    age INT                   -- Can be empty
);
INSERT INTO persons (id, fname, age) VALUES (1, 'Ratan', 78); -- Fails (missing lname)
INSERT INTO persons (id, lname, fname, age) VALUES (1, 'Tata', 'Ratan', 78); -- Works
```
- **Output**: Error: 'lname' cannot be null; Row added: (1, 'Tata', 'Ratan', 78).

#### Changing It Later
```sql
ALTER TABLE persons MODIFY age INT NOT NULL; -- Now age must have a value
```

### 3.2 UNIQUE Constraint
#### Definition
`UNIQUE` ensures all values in a column (or combination of columns) are different. Unlike `NOT NULL`, it allows empty (NULL) values.

#### Why Use It?
To prevent duplicates (e.g., no two people with the same ID).

#### Instructions
- Add `UNIQUE` after the data type or as a separate `CONSTRAINT`.
- Test by inserting duplicates—MySQL will reject them.

#### Example 1: Single Column
```sql
CREATE TABLE person (
    id INT NOT NULL UNIQUE,    -- IDs must be unique
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(10) NOT NULL,
    age INT UNIQUE             -- Ages must be unique (NULL allowed)
);
INSERT INTO person VALUES (1, 'Tata', 'Ratan', 80);
INSERT INTO person VALUES (2, 'Bala', 'Jaan', 90);
INSERT INTO person VALUES (3, 'Tata', 'Ravi', 80); -- Fails (duplicate age)
```
- **Output**: Error: Duplicate entry '80' for key 'age'.

#### Example 2: Multiple Columns
```sql
CREATE TABLE person (
    id INT NOT NULL,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    CONSTRAINT uc_person UNIQUE (id, lname) -- ID + last name combo must be unique
);
INSERT INTO person (id, lname, fname) VALUES (1, 'Tata', 'Ratan');
INSERT INTO person (id, lname, fname) VALUES (1, 'Tata', 'Ravi'); -- Fails
```
- **Output**: Error: Duplicate entry '1-Tata'.

### 3.3 PRIMARY KEY Constraint
#### Definition
`PRIMARY KEY` is a special rule that makes a column (or columns) both `NOT NULL` and `UNIQUE`. Each table gets *one* `PRIMARY KEY` to identify every row uniquely, often paired with `AUTO_INCREMENT` for automatic numbering.

#### Why Use It?
To give every row a unique "fingerprint" (e.g., customer ID).

#### Instructions
- Add `PRIMARY KEY` after the column or as a separate clause.
- Use `AUTO_INCREMENT` for auto-numbering.
- Test duplicates or nulls—MySQL blocks them.

#### Example 1: Simple Primary Key
```sql
CREATE TABLE customers (
    cid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- Auto-numbers from 1
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);
INSERT INTO customers (cname, country) VALUES ('Ram', 'India');
INSERT INTO customers (cname, country) VALUES ('David', 'UK');
```
- **Output**: Rows: (1, 'Ram', 'India'), (2, 'David', 'UK').

#### Example 2: Table-Level
```sql
CREATE TABLE customers (
    cid INT NOT NULL AUTO_INCREMENT,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL,
    PRIMARY KEY (cid)
);
```

#### Example 3: Multi-Column Primary Key
```sql
ALTER TABLE customers ADD CONSTRAINT pk_customers PRIMARY KEY (cid, cname);
INSERT INTO customers (cid, cname, country) VALUES (103, 'Anil', 'India');
INSERT INTO customers (cid, cname, country) VALUES (103, 'Anil', 'US'); -- Fails
```
- **Output**: Error: Duplicate entry '103-Anil'.

#### Adjusting Auto Increment
```sql
ALTER TABLE customers AUTO_INCREMENT = 101; -- Next ID starts at 101
INSERT INTO customers (cname, country) VALUES ('Anshu', 'Canada');
```
- **Output**: Row: (101, 'Anshu', 'Canada').

#### Dropping It
```sql
ALTER TABLE customers MODIFY cid INT NOT NULL; -- Remove AUTO_INCREMENT first
ALTER TABLE customers DROP PRIMARY KEY;
```

---

## 4. Advanced SQL Constraints

### 4.1 FOREIGN KEY Constraint
#### Definition
`FOREIGN KEY` links a column in one table (child) to a `PRIMARY KEY` in another (parent), ensuring data in the child matches an existing parent value. It’s also called a *referential integrity constraint* because it keeps relationships intact.

#### Why Use It?
To connect tables logically (e.g., orders to customers) and prevent "orphaned" data (e.g., orders without customers).

#### Instructions
- Create a parent table with a `PRIMARY KEY`.
- Add `FOREIGN KEY` in the child table, referencing the parent.
- Test by inserting invalid references—MySQL stops it.

#### Example 1: Simple Link
```sql
CREATE TABLE customers (
    cid INT NOT NULL PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid)
);
INSERT INTO customers (cid, cname, country) VALUES (1, 'Ram', 'India');
INSERT INTO orders (order_amount, customer_id) VALUES (45000, 1); -- Works
INSERT INTO orders (order_amount, customer_id) VALUES (25000, 2); -- Fails
```
- **Output**: Error: Foreign key constraint fails (no customer 2).

#### Example 2: Named Constraint
```sql
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    CONSTRAINT customers_orders FOREIGN KEY (customer_id) REFERENCES customers(cid)
);
```

#### Adding Later
```sql
ALTER TABLE orders ADD CONSTRAINT customers_orders FOREIGN KEY (customer_id) REFERENCES customers(cid);
```

#### Dropping It
```sql
ALTER TABLE orders DROP FOREIGN KEY customers_orders;
INSERT INTO orders (order_amount, customer_id) VALUES (15000, 106); -- Works after drop
```

### 4.2 CHECK Constraint
#### Definition
`CHECK` limits what values a column can hold based on a condition (e.g., `age >= 18`). It can apply to one column or multiple (table-level).

#### Why Use It?
To enforce rules (e.g., orders must be at least $10,000).

#### Instructions
- Add `CHECK` with a condition in `CREATE TABLE` or `ALTER TABLE`.
- Test invalid values—MySQL rejects them.

#### Example 1: Single Column
```sql
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    CHECK (order_amount >= 10000)
);
INSERT INTO orders (order_amount) VALUES (25000); -- Works
INSERT INTO orders (order_amount) VALUES (5000);  -- Fails
```
- **Output**: Error: Check constraint violated.

#### Example 2: Adding Later
```sql
ALTER TABLE orders ADD CHECK (order_amount >= 10000);
```

#### Example 3: Table-Level (Conceptual)
```sql
ALTER TABLE persons ADD CONSTRAINT check_person CHECK (age >= 18 AND city = 'New Delhi');
```
- **Guide**: Ensures `age` and `city` meet combined conditions.

### 4.3 DEFAULT Constraint
#### Definition
`DEFAULT` sets a fallback value for a column if you don’t provide one (e.g., today’s date). Without it, the default is `NULL`.

#### Why Use It?
To fill in optional fields automatically (e.g., default city).

#### Instructions
- Add `DEFAULT` with a value in `CREATE TABLE` or `ALTER TABLE`.
- Test by skipping the column—MySQL uses the default.

#### Example 1: Default Date
```sql
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    order_date DATE DEFAULT CURRENT_DATE
);
INSERT INTO orders (order_amount) VALUES (50000); -- Uses today’s date
```
- **Output**: Row: (1, 50000, '2023-08-20').

#### Example 2: Default Text
```sql
CREATE TABLE persons_one (
    id INT NOT NULL,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) DEFAULT '',
    city VARCHAR(20) DEFAULT 'New Delhi'
);
INSERT INTO persons_one (id, lname, age) VALUES (1, 'Tata', 80);
```
- **Output**: Row: (1, 'Tata', '', 80, 'New Delhi').

#### Changing It
```sql
ALTER TABLE orders MODIFY order_date DATE DEFAULT CURRENT_DATE;
```

---

## 5. Deep Dive: Referential Actions with FOREIGN KEY

### 5.1 What Are Referential Actions?
Referential actions are extra rules for `FOREIGN KEY` that decide what happens to child table data when you *delete* or *update* a parent row. They answer: "If I remove a customer, what happens to their orders?"

#### Why They Matter
Without actions, deleting a customer with orders could leave "orphans" or cause errors. Actions keep data consistent.

#### When Do They Apply?
- **ON DELETE**: Triggers when deleting a parent row.
- **ON UPDATE**: Triggers when updating a parent’s `PRIMARY KEY`.

#### Default Behavior
If you don’t specify an action, MySQL uses `NO ACTION` (blocks changes if child rows exist).

### 5.2 The Five Referential Actions Explained
Here’s a breakdown of each action, with examples to make it crystal clear:

#### 1. RESTRICT
- **Definition**: Blocks deletion or update of a parent row if child rows reference it.
- **Use Case**: Protect data (e.g., don’t delete a customer with orders).
- **Behavior**: Shows an error and aborts the action.

##### Example
```sql
CREATE TABLE customers (
    cid INT PRIMARY KEY,
    cname VARCHAR(20)
);
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE RESTRICT
);
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (1, 1);
DELETE FROM customers WHERE cid = 1; -- Fails
```
- **Output**: Error: Cannot delete parent row (foreign key constraint).

#### 2. CASCADE
- **Definition**: Automatically deletes or updates child rows when the parent changes.
- **Use Case**: Clean up related data (e.g., delete orders when a customer is removed).
- **Behavior**: Propagates the action to the child table.

##### Example: Delete Cascade
```sql
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE;
DELETE FROM customers WHERE cid = 1; -- Works
```
- **Output**: `customers` row (1, 'Ram') and `orders` row (1, 1) both deleted.

##### Example: Update Cascade
```sql
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON UPDATE CASCADE;
UPDATE customers SET cid = 2 WHERE cid = 1; -- Works
```
- **Output**: `customers` becomes (2, 'Ram'), `orders` updates to (1, 2).

#### 3. SET NULL
- **Definition**: Sets the child’s foreign key to `NULL` when the parent is deleted or updated.
- **Use Case**: Keep child data but unlink it (e.g., orders stay but lose customer link).
- **Requirement**: The foreign key column must allow `NULL`.

##### Example
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE SET NULL
);
INSERT INTO customers VALUES (2, 'David');
INSERT INTO orders VALUES (2, 2);
DELETE FROM customers WHERE cid = 2; -- Works
```
- **Output**: `orders` row becomes (2, NULL).

#### 4. NO ACTION
- **Definition**: Same as `RESTRICT`—blocks parent changes if child rows exist. It’s the default if no action is specified.
- **Use Case**: Same as `RESTRICT`, but depends on database internals (usually identical in MySQL).
- **Behavior**: Errors out like `RESTRICT`.

##### Example
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) -- No action by default
);
INSERT INTO customers VALUES (7, 'Anil');
INSERT INTO orders VALUES (7, 7);
DELETE FROM customers WHERE cid = 7; -- Fails
```
- **Output**: Error: Cannot delete parent row.

#### 5. SET DEFAULT
- **Definition**: Sets the child’s foreign key to its `DEFAULT` value when the parent is deleted or updated.
- **Use Case**: Reset to a fallback (e.g., a default customer ID).
- **Note**: Rarely used in MySQL (requires a default value; not widely supported).

##### Example (Conceptual)
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE SET DEFAULT
);
```
- **Guide**: If `cid=1` is deleted, `customer_id` becomes 0 (if supported).

### 5.3 Applying Referential Actions
#### Instructions
- Add actions after `REFERENCES` in `CREATE TABLE` or `ALTER TABLE`.
- Use `ON DELETE` and/or `ON UPDATE` (e.g., `ON DELETE CASCADE ON UPDATE SET NULL`).
- Test by deleting or updating parent rows and checking child effects.

#### Example: Combining Actions
```sql
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) 
    ON DELETE SET NULL ON UPDATE CASCADE;
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (3, 1);
DELETE FROM customers WHERE cid = 1; -- Sets customer_id to NULL
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (4, 1);
UPDATE customers SET cid = 2 WHERE cid = 1; -- Updates customer_id to 2
```
- **Output**: After delete: (3, NULL); After update: (4, 2).

#### Changing Actions
```sql
ALTER TABLE orders DROP FOREIGN KEY customers_orders;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE;
```

---

## 6. Practical Application

### 6.1 Best Practices for Using Constraints
- **NOT NULL**: Use for must-have data (e.g., names).
- **UNIQUE**: Apply to identifiers (e.g., email).
- **PRIMARY KEY**: Always add to tables for unique IDs, with `AUTO_INCREMENT` for ease.
- **FOREIGN KEY**: Link related tables; use `CASCADE` for cleanup, `RESTRICT` for safety.
- **CHECK**: Set business rules (e.g., minimum order value).
- **DEFAULT**: Fill optional fields (e.g., today’s date).
- **Naming**: Use clear `CONSTRAINT` names (e.g., `customers_orders`) for easy management.

### 6.2 Common Mistakes to Avoid
- **Skipping NOT NULL**: Leads to missing data (e.g., blank names).
- **Duplicate PRIMARY KEYs**: Causes errors—check data first.
- **Invalid FOREIGN KEYs**: Insert child data before parent fails.
- **SET NULL on NOT NULL**: Errors out—ensure column allows `NULL`.
- **Forgetting to Drop**: Can’t change actions without dropping `FOREIGN KEY` first.

### 6.3 Hands-On Exercises
1. **NOT NULL**: Create a table requiring name and ID; try inserting without one.
2. **UNIQUE**: Make a table with unique emails; test duplicates.
3. **PRIMARY KEY**: Set up a table with `AUTO_INCREMENT`; insert rows and check IDs.
4. **FOREIGN KEY**: Link `customers` and `orders`; test invalid customer IDs.
5. **CHECK**: Add a rule for ages ≥ 18; try underage values.
6. **DEFAULT**: Set a default city; insert without specifying it.
7. **Referential Actions**: Test `CASCADE`, `SET NULL`, and `RESTRICT` with delete/update.

---

## 7. Comparing Constraints

### 7.1 Column-Level vs. Table-Level Constraints
| Feature          | Column-Level              | Table-Level              |
|------------------|---------------------------|--------------------------|
| **Scope**        | One column                | Multiple columns/table   |
| **Syntax**       | `id INT PRIMARY KEY`      | `CONSTRAINT pk PRIMARY KEY (id)` |
| **Examples**     | `age INT CHECK (age >= 18)` | `CHECK (age >= 18 AND city = 'Delhi')` |
| **When to Use**  | Simple rules              | Complex or multi-column rules |

### 7.2 PRIMARY KEY vs. UNIQUE
| Feature          | PRIMARY KEY               | UNIQUE                   |
|------------------|---------------------------|--------------------------|
| **Uniqueness**   | Yes                       | Yes                      |
| **Nulls Allowed**| No                        | Yes                      |
| **Limit**        | One per table             | Multiple allowed         |
| **Use Case**     | Row identifier (e.g., `cid`) | Optional unique fields (e.g., `email`) |

---

## 8. Wrapping Up

### 8.1 Resources for Further Learning
- [MySQL Official Documentation](https://dev.mysql.com/doc/)
- [W3Schools SQL Tutorial](https://www.w3schools.com/sql/)

### 8.2 Summary of Key Takeaways
This manual covers six lectures:
- **Lecture 79**: Overview of all constraints with `world` (e.g., `country`).
- **Lecture 80**: `NOT NULL` and `UNIQUE` with `persons`, four methods.
- **Lecture 81**: `PRIMARY KEY` and `AUTO_INCREMENT` with `customers`.
- **Lecture 82**: `DEFAULT` (e.g., `CURRENT_DATE`) and `CHECK` (e.g., `order_amount >= 10000`).
- **Lecture 83**: `FOREIGN KEY` with `customers` and `orders`.
- **Lecture 84**: Referential actions (`RESTRICT`, `CASCADE`, etc.) with practical demos.

#### Highlights
- **Core Rules**: `NOT NULL`, `UNIQUE`, `PRIMARY KEY` build the foundation.
- **Advanced Rules**: `FOREIGN KEY`, `CHECK`, `DEFAULT` add control.
- **Referential Actions**: Manage relationships dynamically.
- **Beginner Tip**: Start simple, test often, and constraints will make sense!

### 8.3 Complete SQL Commands Reference
| Command              | Purpose                                      | Example                                      |
|----------------------|----------------------------------------------|----------------------------------------------|
| `NOT NULL`           | Requires a value                            | `cname VARCHAR(50) NOT NULL`                 |
| `UNIQUE`             | Ensures no duplicates                       | `age INT UNIQUE`                             |
| `PRIMARY KEY`        | Unique, non-null row ID                     | `cid INT PRIMARY KEY`                        |
| `FOREIGN KEY`        | Links tables                                | `FOREIGN KEY (customer_id) REFERENCES customers(cid)` |
| `CHECK`              | Limits values                               | `CHECK (order_amount >= 10000)`              |
| `DEFAULT`            | Sets fallback value                         | `order_date DATE DEFAULT CURRENT_DATE`       |
| `AUTO_INCREMENT`     | Auto-numbers rows                           | `order_id INT AUTO_INCREMENT`                |
| `CONSTRAINT`         | Names a rule                                | `CONSTRAINT customers_orders FOREIGN KEY ...` |
| `ON DELETE CASCADE`  | Deletes child rows                          | `ON DELETE CASCADE`                          |
| `ON DELETE SET NULL` | Nullifies child foreign key                 | `ON DELETE SET NULL`                         |
| `ON UPDATE CASCADE`  | Updates child rows                          | `ON UPDATE CASCADE`                          |
| `ALTER TABLE ... MODIFY` | Changes column rules                    | `ALTER TABLE orders MODIFY order_date DATE DEFAULT CURRENT_DATE` |
| `ALTER TABLE ... ADD` | Adds a constraint                           | `ALTER TABLE orders ADD CHECK (...)`         |
| `ALTER TABLE ... DROP FOREIGN KEY` | Removes foreign key           | `ALTER TABLE orders DROP FOREIGN KEY customers_orders` |
| `ALTER TABLE ... AUTO_INCREMENT =` | Sets next ID                  | `ALTER TABLE customers AUTO_INCREMENT = 101` |

---


This README is a standalone learning tool—newcomers can read it like a manual, experiment with the examples, and master SQL constraints confidently!