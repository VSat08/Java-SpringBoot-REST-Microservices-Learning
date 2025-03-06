# 2.5: GROUP BY and HAVING Clause

## Introduction
Welcome to **Section 2.5: GROUP BY and HAVING Clause** 🚀! In this session from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we explore the `GROUP BY` and `HAVING` clauses in SQL—essential tools for grouping and filtering data within MySQL queries. Covering their use with `SELECT`, `WHERE`, `ORDER BY`, and aggregate functions, this guide provides practical examples to summarize table data, perfect for beginners aiming to master SQL querying! 🌟

---

## Table of Contents
1. [Understanding GROUP BY and HAVING](#1-understanding-group-by-and-having)
    - [1.1 What are GROUP BY and HAVING Clauses?](#11-what-are-group-by-and-having-clauses)
    - [1.2 Why Use GROUP BY and HAVING Clauses?](#12-why-use-group-by-and-having-clauses)
2. [GROUP BY and HAVING in SQL](#2-group-by-and-having-in-sql)
    - [2.1 Core Concepts](#21-core-concepts)
    - [2.2 Need for GROUP BY and HAVING](#22-need-for-group-by-and-having)
    - [2.3 Using GROUP BY Clause](#23-using-group-by-clause)
    - [2.4 Using HAVING Clause](#24-using-having-clause)
    - [2.5 Combining with WHERE and ORDER BY](#25-combining-with-where-and-order-by)
    - [2.6 Implementation Overview](#26-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [3.1 Best Practices](#31-best-practices)
    - [3.2 Common Pitfalls](#32-common-pitfalls)
    - [3.3 Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [4.1 GROUP BY vs. Basic SELECT](#41-group-by-vs-basic-select)
    - [4.2 HAVING vs. WHERE](#42-having-vs-where)
5. [Resources & Summary](#5-resources--summary)
    - [5.1 Resources](#51-resources)
    - [5.2 Summary](#52-summary)
    - [5.3 SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding GROUP BY and HAVING

### 1.1 What are GROUP BY and HAVING Clauses?
The `GROUP BY` clause in SQL groups rows with identical values in specified columns into summary rows, while the `HAVING` clause filters these groups based on aggregate conditions.

#### Definition
- **GROUP BY**: Organizes rows into groups based on column values.
- **HAVING**: Restricts grouped results using conditions on aggregate functions.
- **Aggregate Functions**: Summarize data within groups (e.g., `COUNT`, `SUM`).

#### Real-World Example
Imagine a sales report: `GROUP BY` totals sales by region, and `HAVING` shows only regions with sales above $10,000.

#### Key Terms
| Term            | Definition                                | Example                |
|-----------------|-------------------------------------------|------------------------|
| GROUP BY        | Groups rows by same values                | `GROUP BY emp_city`    |
| HAVING          | Filters grouped results                   | `HAVING COUNT(*) > 2`  |
| Summary Rows    | Aggregated data per group                 | Employees per city     |

### 1.2 Why Use GROUP BY and HAVING Clauses?
These clauses are crucial because they:
- Summarize data into meaningful categories (e.g., employees per city).
- Filter grouped data with aggregate conditions `WHERE` can’t address.
- Enable sorting with `ORDER BY` for clear presentation.

#### Analogy
`GROUP BY` is like sorting books by genre, and `HAVING` keeps only genres with multiple titles.

---

## 2. GROUP BY and HAVING in SQL

### 2.1 Core Concepts
`GROUP BY` and `HAVING` extend the `SELECT` command in MySQL, integrating with `WHERE` and `ORDER BY`. `GROUP BY` consolidates rows by column values, and `HAVING` applies post-grouping filters using aggregates, necessitated by `WHERE`’s inability to handle aggregate functions.

### 2.2 Need for GROUP BY and HAVING
Raw data lacks structure without grouping. These clauses:
- Aggregate rows with matching values (e.g., cities, pay bands).
- Refine groups with conditions (e.g., count ≥ 3).
- Build comprehensive queries with `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`.

#### Snippet: Without GROUP BY
```sql
SELECT * FROM employee;
```
*Output*: Lists all 10 rows—grouping condenses this into summaries (e.g., employees per city).

### 2.3 Using GROUP BY Clause
#### Definition
The `GROUP BY` clause groups rows in a table by one or more columns with identical values, producing summary rows typically paired with aggregate functions like `COUNT`, `SUM`, `MAX`, `MIN`, or `AVG`.

#### Usage Guide
- **Purpose**: Summarize data by categories (e.g., count employees per city).
- **Syntax**: `SELECT column, aggregate_function(column) FROM table_name GROUP BY column;`
- **Steps**:
  1. Identify the column to group by (e.g., `emp_city`).
  2. Choose an aggregate function (e.g., `COUNT(emp_id)`).
  3. Include only grouped columns or aggregates in `SELECT`.

##### Example 1: Employees per City
```sql
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city;
```
*Guide*:
- **Step 1**: Group by `emp_city` to categorize employees by location.
- **Step 2**: Use `COUNT(emp_id)` to tally employees per city.
- **Step 3**: Alias as `number_of_employees` for clarity.
*Output*: Bangalore: 3, Hyderabad: 3, New Delhi: 2, Mumbai: 2.

##### Example 2: Employees per Pay Band
```sql
SELECT COUNT(emp_id), emp_pay FROM employee GROUP BY emp_pay;
```
*Guide*:
- **Step 1**: Group by `emp_pay` to aggregate by salary levels.
- **Step 2**: Count employees (`COUNT(emp_id)`) per pay band.
- **Step 3**: Include `emp_pay` to show each group’s value.
*Output*: 56,000: 2, 66,000: 2, 75,000: 2, 78,000: 2, 86,000: 2.

##### Example 3: Cities per Country (World DB)
```sql
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode;
```
*Guide*:
- **Step 1**: Group by `countrycode` to summarize cities per country.
- **Step 2**: Use `COUNT(name)` to count cities in each group.
- **Step 3**: Select `countrycode` as the grouping key.
*Output*: 232 rows (e.g., CHN: 363, IND: 341).

> [!NOTE]
> Only columns in `GROUP BY` or aggregate functions can appear in `SELECT`.

### 2.4 Using HAVING Clause
#### Definition
The `HAVING` clause filters grouped results based on conditions applied to aggregate functions, used because `WHERE` cannot filter aggregates directly.

#### Usage Guide
- **Purpose**: Restrict groups post-`GROUP BY` (e.g., only groups with count > 2).
- **Syntax**: `SELECT column, aggregate_function(column) FROM table_name GROUP BY column HAVING condition;`
- **Steps**:
  1. Group data with `GROUP BY`.
  2. Define an aggregate condition (e.g., `COUNT(emp_id) >= 3`).
  3. Apply `HAVING` to filter groups.

##### Example 1: Cities with ≥ 3 Employees
```sql
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city HAVING COUNT(emp_id) >= 3;
```
*Guide*:
- **Step 1**: Group by `emp_city`.
- **Step 2**: Count employees per city with `COUNT(emp_id)`.
- **Step 3**: Filter for cities with 3 or more employees using `HAVING`.
*Output*: Bangalore: 3, Hyderabad: 3.

##### Example 2: Countries with > 100 Cities
```sql
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode HAVING COUNT(name) > 100;
```
*Guide*:
- **Step 1**: Group by `countrycode`.
- **Step 2**: Count cities per country with `COUNT(name)`.
- **Step 3**: Keep only countries with over 100 cities.
*Output*: 8 rows (e.g., CHN: 363, IND: 341).

##### Example 3: Countries with > 50 Cities (Limited)
```sql
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode HAVING COUNT(name) > 50 LIMIT 15;
```
*Guide*:
- **Step 1**: Group by `countrycode`.
- **Step 2**: Count cities with `COUNT(name)`.
- **Step 3**: Filter for > 50 cities and limit to top 15.
*Output*: Top 15 (e.g., TUR: 62).

### 2.5 Combining with WHERE and ORDER BY
#### Definition
This combines `WHERE` to filter rows before grouping, `GROUP BY` to aggregate, `HAVING` to filter groups, and `ORDER BY` to sort the results, forming a full-fledged SQL query.

#### Usage Guide
- **Purpose**: Build complex queries with row filtering, grouping, group filtering, and sorting.
- **Syntax**: `SELECT column, aggregate_function(column) FROM table_name WHERE condition GROUP BY column HAVING condition ORDER BY column [ASC|DESC];`
- **Steps**:
  1. Filter rows with `WHERE`.
  2. Group with `GROUP BY`.
  3. Filter groups with `HAVING`.
  4. Sort with `ORDER BY`.

##### Example 1: Employees per City with ORDER BY
```sql
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city ORDER BY COUNT(emp_id) DESC;
```
*Guide*:
- **Step 1**: No `WHERE` (all rows included).
- **Step 2**: Group by `emp_city`.
- **Step 3**: Count employees per city.
- **Step 4**: Sort by count descending (highest first).
*Output*: Bangalore: 3, Hyderabad: 3, New Delhi: 2, Mumbai: 2.

##### Example 2: Full Query with WHERE and HAVING
```sql
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore', 'Mumbai', 'New Delhi') GROUP BY emp_city HAVING COUNT(emp_id) >= 2 ORDER BY COUNT(emp_id) DESC, emp_city ASC;
```
*Guide*:
- **Step 1**: Filter to specific cities with `WHERE emp_city IN (...)`.
- **Step 2**: Group by `emp_city`.
- **Step 3**: Keep groups with ≥ 2 employees using `HAVING`.
- **Step 4**: Sort by count descending, then city ascending.
*Output*: Bangalore: 3, Hyderabad: 3, Mumbai: 2, New Delhi: 2.

##### Example 3: Countries with 'i' in Code
```sql
SELECT COUNT(name), countrycode FROM city WHERE countrycode LIKE '%i%' GROUP BY countrycode HAVING COUNT(name) >= 10 ORDER BY COUNT(name) DESC;
```
*Guide*:
- **Step 1**: Filter countries with ‘i’ in `countrycode` using `WHERE`.
- **Step 2**: Group by `countrycode`.
- **Step 3**: Filter for ≥ 10 cities with `HAVING`.
- **Step 4**: Sort by count descending.
*Output*: IND: 341, IDN: 85, IRN: 67, ITA: 58, IRQ: 26, ISR: 17.

### 2.6 Implementation Overview
Runs in `my_db` with `employee` (10 rows, updated pays: 86,000 twice, 66,000 once) and `world` database (`city` table, 4079 rows, 232 countries). Builds on aggregate functions from prior session.

##### Textual Hierarchy Diagram
```
+-----------------+
| SELECT Command  | --> Retrieve Data
+-----------------+
    |
    v
+--------+--------+-----------------+
| WHERE  | GROUP BY | HAVING          | --> Filter and Group
+--------+--------+-----------------+
    |
    v
+-------------+
| ORDER BY     | --> Sort Results
+-------------+
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Pair `GROUP BY` with aggregates (e.g., `COUNT(emp_id)`).
- Use `HAVING` for aggregate conditions, reserving `WHERE` for row filters.
- Sort with `ORDER BY` for readability (e.g., `COUNT(emp_id) DESC`).
- Limit large result sets with `LIMIT` (e.g., top 15 countries).

### 3.2 Common Pitfalls
- Including non-grouped, non-aggregated columns (e.g., `emp_name` with `GROUP BY emp_city`).
- Using `WHERE` for aggregates (e.g., `WHERE COUNT(*) > 2` fails).
- Misordering `ORDER BY` (e.g., sorting by `emp_id` instead of `COUNT(emp_id)`).
- Omitting `LIMIT` on large tables (e.g., `city` with 4079 rows).

### 3.3 Practice Exercises
1. Group employees by city and count them.
2. Find pay bands with exactly 2 employees.
3. List cities with ≥ 3 employees, ordered by count descending.
4. Group `city` by countrycode, filter for > 50 cities, limit to 10.
5. Count cities with ‘d’ in countrycode, filter for ≥ 10, order by count.

---

## 4. Comparisons

### 4.1 GROUP BY vs. Basic SELECT
| Aspect            | GROUP BY                   | Basic SELECT              |
|-------------------|----------------------------|---------------------------|
| Purpose           | Groups and summarizes      | Retrieves raw data        |
| Output            | Summary rows               | All rows                  |
| Examples          | `GROUP BY emp_city`        | `SELECT * FROM employee`  |
| Use Case          | Aggregation                | Data listing              |

### 4.2 HAVING vs. WHERE
| Aspect            | HAVING                     | WHERE                     |
|-------------------|----------------------------|---------------------------|
| Action            | Filters groups             | Filters rows              |
| Applies To        | Aggregate functions        | Individual rows           |
| Syntax            | `HAVING COUNT(*) > 2`      | `WHERE emp_city = 'Mumbai'` |
| Use Case          | Post-group conditions      | Pre-group conditions      |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
`GROUP BY` groups rows by column values (e.g., `emp_city`, `emp_pay`) in `employee` (10 rows) and `city` (4079 rows) tables, using aggregates like `COUNT`. `HAVING` filters these groups (e.g., `COUNT >= 3`), complementing `WHERE` and `ORDER BY` for full queries. Next: integrity constraints.

#### Highlights
- **GROUP BY**: Summarizes data (e.g., 3 in Bangalore).
- **HAVING**: Filters aggregates (e.g., `COUNT > 100`).
- **Full Query**: Combines `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`.
- **Takeaway**: Master grouping for data analysis! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `GROUP BY`           | Groups rows by column values                    | `SELECT COUNT(emp_id), emp_city FROM employee GROUP BY emp_city;` |
| `HAVING`             | Filters groups based on aggregate conditions    | `SELECT COUNT(emp_id), emp_city FROM employee GROUP BY emp_city HAVING COUNT(emp_id) >= 3;` |
| `LIMIT`              | Restricts number of output rows                 | `SELECT COUNT(name), countrycode FROM city GROUP BY countrycode LIMIT 10;` |

