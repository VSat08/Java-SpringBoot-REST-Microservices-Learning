# 2.9: SQL Views - A Beginner's Manual to Virtual Tables in MySQL

## Introduction
Welcome to **2.9: SQL Views** 🌟! This section explores MySQL views—powerful tools that create virtual tables from queries. Using examples from the `world` database (`country`, `city`), this manual teaches you how to create, replace, and drop views step-by-step. Designed for beginners, it simplifies the process of working with views, showing how they make data access easier and more secure. Whether you're new to SQL or refining your skills, this guide will help you master virtual tables with practical examples! 🚀

---

## Table of Contents
1. [What Are SQL Views?](#1-what-are-sql-views)
    - [1.1 Definition and Purpose](#11-definition-and-purpose)
    - [1.2 Why Views Matter](#12-why-views-matter)
    - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Getting Started with Views in MySQL](#2-getting-started-with-views-in-mysql)
    - [2.1 How Views Work](#21-how-views-work)
    - [2.2 The Problem Without Views](#22-the-problem-without-views)
3. [Core Operations with Views](#3-core-operations-with-views)
    - [3.1 Creating a View](#31-creating-a-view)
    - [3.2 Replacing a View](#32-replacing-a-view)
    - [3.3 Dropping a View](#33-dropping-a-view)
4. [Advanced Uses of Views](#4-advanced-uses-of-views)
    - [4.1 Views on Multiple Tables](#41-views-on-multiple-tables)
    - [4.2 Views with Referential Actions](#42-views-with-referential-actions)
5. [Practical Application](#5-practical-application)
    - [5.1 Best Practices for Using Views](#51-best-practices-for-using-views)
    - [5.2 Common Mistakes to Avoid](#52-common-mistakes-to-avoid)
    - [5.3 Hands-On Exercises](#53-hands-on-exercises)
6. [Comparing Views and Tables](#6-comparing-views-and-tables)
    - [6.1 Views vs. Base Tables](#61-views-vs-base-tables)
7. [Wrapping Up](#7-wrapping-up)
    - [7.1 Resources for Further Learning](#71-resources-for-further-learning)
    - [7.2 Summary of Key Takeaways](#72-summary-of-key-takeaways)
    - [7.3 Complete SQL Commands Reference](#73-complete-sql-commands-reference)

---

## 1. What Are SQL Views?

### 1.1 Definition and Purpose
SQL views are virtual tables created from the result of a query. Unlike regular tables, they don’t store data themselves—they pull it from underlying tables when you use them.

- **Definition**: A view is a saved query that acts like a table, displaying data from one or more real tables.
- **Purpose**: Simplify complex queries, hide unnecessary details, and control what data is visible.
- **Examples**: A view showing only Asian countries or a list of Indian cities with their country details.

#### Real-World Analogy
Imagine a view as a window:
- The room (real table) holds all the items.
- The window (view) shows only the part you want to see, like a specific corner.

### 1.2 Why Views Matter
Views make working with databases more efficient and secure:
- **Ease of Use**: Replace long queries with a simple `SELECT`.
- **Security**: Show only certain columns or rows, keeping sensitive data hidden.
- **Focus**: Present just the data you need, avoiding clutter.

#### Example Benefit
Instead of querying a table with 15 columns, a view can show just 4 key ones, like `code`, `name`, `continent`, and `population`.

### 1.3 Key Terms for Beginners
| Term             | Meaning                                   | Example                |
|------------------|-------------------------------------------|------------------------|
| **View**         | Virtual table from a query                | `country_view`         |
| **Base Table**   | Real table storing data                   | `country`              |
| **Virtual Table**| Table-like result, no data stored         | View of African countries |
| **Query**        | SQL statement defining the view           | `SELECT * FROM country WHERE continent = 'Asia'` |

---

## 2. Getting Started with Views in MySQL

### 2.1 How Views Work
In MySQL, views are created with a query that runs whenever you access them. They:
- Fetch data live from base tables (e.g., `country`, `city`).
- Look and act like tables for `SELECT` queries.
- Can include filters, joins, or functions.

#### Basic Syntax
```sql
CREATE VIEW view_name AS
SELECT columns
FROM table_name
[WHERE condition];
```

#### Key Features
- **No Storage**: Views don’t hold data—just the query.
- **Flexibility**: Build from one or multiple tables.
- **Simplicity**: Query with `SELECT * FROM view_name` like a table.

### 2.2 The Problem Without Views
Without views, you’re stuck repeating complex queries or sifting through too much data.

#### Example: No View
```sql
SELECT code, name, continent, population
FROM country
WHERE continent = 'Asia';
```
- **Issue**: Long, repetitive queries; hard to manage with many columns or tables.

---

## 3. Core Operations with Views

### 3.1 Creating a View
#### Definition
`CREATE VIEW` sets up a new virtual table based on a query from one or more tables.

#### Why Use It?
To save a query for quick, repeated use (e.g., showing only certain columns).

#### Instructions
- Write `CREATE VIEW view_name AS` followed by a `SELECT` statement.
- Pick specific columns or use `*`.
- Test it with `SELECT * FROM view_name`.

#### Example 1: Single Table
```sql
CREATE VIEW country_view AS
SELECT code, name, continent, region, population, life_expectancy, capital
FROM country;
```
- **Output**: A view with 7 columns (out of 15), showing all rows from `country`.

#### Example 2: Filtered View
```sql
CREATE VIEW african_countries AS
SELECT *
FROM country
WHERE continent = 'Africa';
```
- **Output**: A view with 58 rows (African countries only).

#### Querying a View
```sql
SELECT name, capital
FROM african_countries;
```
- **Output**: 58 rows with country names and capital city IDs.

### 3.2 Replacing a View
#### Definition
`CREATE OR REPLACE VIEW` updates an existing view with a new query, overwriting the old one.

#### Why Use It?
To change a view’s columns or conditions without deleting it first.

#### Instructions
- Use `CREATE OR REPLACE VIEW` instead of `CREATE VIEW`.
- Adjust the `SELECT` statement.
- Check the update with `SELECT`.

#### Example 1: Update Columns
```sql
CREATE OR REPLACE VIEW country_view AS
SELECT code, name, continent, population
FROM country;
```
- **Output**: Updated view with 4 columns (down from 7).

#### Example 2: Fix a Mistake
```sql
CREATE OR REPLACE VIEW african_countries AS
SELECT *
FROM country
WHERE continent = 'Africa';
```
- **Output**: Corrected view, still showing 58 rows.

#### Querying Updated View
```sql
SELECT COUNT(*) FROM african_countries;
```
- **Output**: 58 (total African countries).

### 3.3 Dropping a View
#### Definition
`DROP VIEW` deletes a view from the database, leaving the base tables untouched.

#### Why Use It?
To remove outdated or incorrect views.

#### Instructions
- Run `DROP VIEW view_name`.
- Confirm removal with `SHOW FULL TABLES`.

#### Example
```sql
DROP VIEW african_countries;
```
- **Output**: View gone; `SHOW FULL TABLES` no longer shows it.

---

## 4. Advanced Uses of Views

### 4.1 Views on Multiple Tables
#### Definition
A view can merge data from multiple tables using joins, acting as a single table.

#### Why Use It?
To combine related data (e.g., country names with their cities).

#### Instructions
- Use a `JOIN` in the `SELECT` with an `ON` condition.
- Pick columns from each table.
- Test the view with `SELECT`.

#### Example: Country and City
```sql
CREATE OR REPLACE VIEW indian_city_name_view AS
SELECT country.code, country.name AS country_name, city.id, city.name AS city_name
FROM country
JOIN city
ON country.code = city.countrycode
WHERE country.code = 'IND';
```
- **Output**: 341 rows with India’s code, name, city IDs, and city names.

#### Updated Example: Add Region
```sql
CREATE OR REPLACE VIEW indian_city_name_view AS
SELECT country.code, country.name AS country_name, city.id, city.name AS city_name, country.region
FROM country
JOIN city
ON country.code = city.countrycode
WHERE country.code = 'IND';
```
- **Output**: 341 rows, now including `region` (e.g., "Southern and Central Asia").

#### Querying
```sql
SELECT * FROM indian_city_name_view LIMIT 25;
```
- **Output**: 25 rows with code, country name, city ID, city name, and region.

### 4.2 Views with Referential Actions
#### Definition
Views can reflect referential actions (e.g., `ON DELETE CASCADE`) from base tables, defining how changes propagate, though this is advanced and limited in MySQL views.

#### Why Use It?
To ensure views stay consistent with base table updates or deletes.

#### Instructions
- Set actions in base tables (not directly in the view).
- Note MySQL limits: views are often read-only for complex queries.

#### Example (Conceptual)
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE
);
CREATE VIEW order_view AS
SELECT * FROM orders;
```
- **Guide**: Deleting a customer removes related orders, and `order_view` shows the updated result.

#### Note
- Views with joins or filters are typically read-only; simple views may support updates.

---

## 5. Practical Application

### 5.1 Best Practices for Using Views
- **Clear Names**: Use descriptive names (e.g., `african_countries`, `indian_city_name_view`).
- **Minimal Columns**: Include only what’s needed for clarity.
- **Use Replace**: Update with `CREATE OR REPLACE` instead of dropping.
- **Verify Type**: Check with `SHOW FULL TABLES` to distinguish views from tables.
- **Hide Sensitive Data**: Omit columns like financial details.

### 5.2 Common Mistakes to Avoid
- **Too Many Columns**: Overcomplicates views and slows queries.
- **Duplicate Names**: `CREATE VIEW` fails if the view exists—use `OR REPLACE`.
- **Base Table Changes**: Views break if underlying tables are altered.
- **Expecting Updates**: Complex views (e.g., with joins) can’t be updated directly.

### 5.3 Hands-On Exercises
1. **Create a View**: Build a view of European countries from `country`.
2. **Replace a View**: Update it to show only `name` and `population`.
3. **Drop a View**: Delete it and check with `SHOW FULL TABLES`.
4. **Multi-Table View**: Merge `country` and `city` for US cities.
5. **Query a View**: Count rows in a view of African countries.

---

## 6. Comparing Views and Tables

### 6.1 Views vs. Base Tables
| Feature          | Views                     | Base Tables              |
|------------------|---------------------------|--------------------------|
| **Data Storage** | None (virtual)            | Holds actual data        |
| **Creation**     | From a query              | Defined with `CREATE TABLE` |
| **Columns**      | Subset or combined        | All defined columns      |
| **Use Case**     | Simplified access         | Raw data storage         |
| **Type Check**   | `SHOW FULL TABLES` (VIEW) | `SHOW FULL TABLES` (BASE_TABLE) |

---

## 7. Wrapping Up

### 7.1 Resources for Further Learning
- MySQL Views Documentation: https://dev.mysql.com/doc/refman/8.0/en/views.html
- W3Schools SQL Views: https://www.w3schools.com/sql/sql_view.asp

### 7.2 Summary of Key Takeaways
This guide covers SQL views in MySQL using the `world` database:
- **Creating**: Made `country_view` (7 columns) and `african_countries` (58 rows).
- **Replacing**: Updated `indian_city_name_view` to add `region`.
- **Dropping**: Removed incorrect views like `indian_`.
- **Multi-Table**: Combined `country` and `city` for 341 Indian city rows.
- **Actions**: Noted referential actions (e.g., `CASCADE`) for base table effects.

#### Highlights
- **Virtual Power**: Views fetch data without storing it.
- **Versatility**: Work with one or multiple tables.
- **Tip**: Start simple, then experiment with joins in views.

### 7.3 Complete SQL Commands Reference
| Command              | Purpose                                      | Example                                      |
|----------------------|----------------------------------------------|----------------------------------------------|
| `CREATE VIEW`        | Creates a new view                           | `CREATE VIEW country_view AS SELECT code, name FROM country` |
| `CREATE OR REPLACE VIEW` | Updates an existing view                 | `CREATE OR REPLACE VIEW african_countries AS SELECT * FROM country WHERE continent = 'Africa'` |
| `DROP VIEW`          | Removes a view                               | `DROP VIEW african_countries`                |
| `SHOW FULL TABLES`   | Shows tables and views with types            | `SHOW FULL TABLES`                           |
| `SELECT ... FROM view_name` | Queries a view                        | `SELECT * FROM country_view`                 |

---
