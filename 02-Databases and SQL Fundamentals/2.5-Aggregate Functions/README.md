# 2.5: Aggregate Functions

## Introduction
Welcome to **Section 2.5: Aggregate Functions** 🚀! In this session from "Introduction to Databases and SQL" (part of *Mastering Java + Spring Boot: REST APIs and Microservices*), we explore aggregate functions in SQL—powerful tools for summarizing data within MySQL tables. Covering `COUNT`, `SUM`, `AVG`, `MIN`, and `MAX`, along with aliases and subqueries, this guide provides practical examples to analyze table data, perfect for beginners aiming to master SQL querying techniques! 🌟

---

## Table of Contents
1. [Understanding Aggregate Functions](#1-understanding-aggregate-functions)
    - [1.1 What are Aggregate Functions?](#11-what-are-aggregate-functions)
    - [1.2 Why Use Aggregate Functions?](#12-why-use-aggregate-functions)
2. [Aggregate Functions in SQL](#2-aggregate-functions-in-sql)
    - [2.1 Core Concepts](#21-core-concepts)
    - [2.2 Need for Aggregate Functions](#22-need-for-aggregate-functions)
    - [2.3 Key Aggregate Functions](#23-key-aggregate-functions)
        - [2.3.1 COUNT Function](#231-count-function)
        - [2.3.2 SUM Function](#232-sum-function)
        - [2.3.3 AVG Function](#233-avg-function)
        - [2.3.4 MIN and MAX Functions](#234-min-and-max-functions)
    - [2.4 Using Aliases with Aggregate Functions](#24-using-aliases-with-aggregate-functions)
    - [2.5 Using Subqueries with SELECT](#25-using-subqueries-with-select)
    - [2.6 Brief on ORDER BY with Aggregates](#26-brief-on-order-by-with-aggregates)
    - [2.7 Implementation Overview](#27-implementation-overview)
3. [Practical Guidance](#3-practical-guidance)
    - [3.1 Best Practices](#31-best-practices)
    - [3.2 Common Pitfalls](#32-common-pitfalls)
    - [3.3 Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [4.1 Aggregate Functions vs. Basic SELECT](#41-aggregate-functions-vs-basic-select)
    - [4.2 COUNT vs. SUM vs. AVG](#42-count-vs-sum-vs-avg)
5. [Resources & Summary](#5-resources--summary)
    - [5.1 Resources](#51-resources)
    - [5.2 Summary](#52-summary)
    - [5.3 SQL Commands Table](#53-sql-commands-table)

---

## 1. Understanding Aggregate Functions

### 1.1 What are Aggregate Functions?
Aggregate functions in SQL are used within the `SELECT` query to summarize or aggregate numeric data from a table. They include `COUNT`, `SUM`, `AVG`, `MIN`, and `MAX`, processing rows to return a single value (e.g., total pay, number of records).

#### Definition
- **Aggregate Functions**: Functions that compute a single result from multiple rows.
- **Supported Functions**: `COUNT` (rows), `SUM` (total), `AVG` (average), `MIN` (minimum), `MAX` (maximum).
- **Numeric Data**: Operates on number-based columns (e.g., pay, employee ID).

#### Real-World Example
Think of a payroll report: aggregate functions let you count employees, sum their salaries, or find the highest earner—all from one table.

#### Key Terms
| Term            | Definition                                | Example                |
|-----------------|-------------------------------------------|------------------------|
| Aggregate       | Summarizes data across rows               | `SUM(pay)`             |
| Numeric Data    | Number-based column values                | Pay values in `employee` |
| Single Value    | One result from many rows                 | Total pay of employees |

### 1.2 Why Use Aggregate Functions?
Aggregate functions are essential because they:
- Summarize numeric data (e.g., total pay of employees).
- Count records or unique values (e.g., number of cities).
- Find extremes (e.g., minimum or maximum pay).

#### Analogy
Aggregate functions are like a calculator for a table: they tally, average, or pinpoint key figures from a list of numbers.

---

## 2. Aggregate Functions in SQL

### 2.1 Core Concepts
Aggregate functions are part of the `SELECT` query in SQL, supported across RDBMS like MySQL and Oracle as standard features. They work on numeric columns (e.g., `pay`, `emp_id`) in tables like `employee`, often paired with aliases and subqueries.

### 2.2 Need for Aggregate Functions
After retrieving data with `SELECT`, you often need to analyze it. Aggregate functions fulfill this by:
- Counting rows or distinct values.
- Summing or averaging numeric data.
- Identifying minimum and maximum values.

#### Snippet: Without Aggregates
```sql
SELECT * FROM employee;
```
*Output*: Lists all 10 rows, but no summary—aggregate functions provide insights like totals or counts.

### 2.3 Key Aggregate Functions
The five key aggregate functions process numeric data in a table.

#### 2.3.1 COUNT Function
Counts the number of rows or distinct values in a column.

- **Traits**: Counts all rows (`COUNT(*)`), or specific/distinct column values.
- **Syntax**: `SELECT COUNT(*) FROM table_name;` or `SELECT COUNT(DISTINCT column) FROM table_name;`

##### Snippet: Count All Records
```sql
SELECT COUNT(*) FROM employee;
```
*Output*: `10` (total records).

##### Snippet: Count with Alias
```sql
SELECT COUNT(*) AS number_of_records FROM employee;
```
*Output*: `number_of_records: 10` (aliased for readability).

##### Snippet: Count Distinct Cities
```sql
SELECT COUNT(DISTINCT emp_city) FROM employee;
```
*Output*: `4` (Mumbai, Bangalore, Hyderabad, New Delhi).

##### Snippet: Count Distinct Pay
```sql
SELECT COUNT(DISTINCT emp_pay) FROM employee;
```
*Output*: `7` (56,000 and 78,000 repeat).

> [!NOTE]
> `COUNT(*)` counts all rows; `COUNT(column)` counts non-null values; `DISTINCT` ensures uniqueness.

#### 2.3.2 SUM Function
Calculates the total of a numeric column.

- **Traits**: Sums all values in a column.
- **Syntax**: `SELECT SUM(column) FROM table_name;`

##### Snippet: Total Pay
```sql
SELECT SUM(emp_pay) AS total_pay FROM employee;
```
*Output*: `total_pay: 717000` (sum of all pays).

#### 2.3.3 AVG Function
Computes the average of a numeric column.

- **Traits**: Averages all values in a column.
- **Syntax**: `SELECT AVG(column) FROM table_name;`

##### Snippet: Average Pay
```sql
SELECT AVG(emp_pay) AS average_pay FROM employee;
```
*Output*: `average_pay: 71700` (total pay / 10).

#### 2.3.4 MIN and MAX Functions
Finds the minimum and maximum values in a numeric column.

- **Traits**: Identifies extremes (smallest/largest).
- **Syntax**: `SELECT MIN(column), MAX(column) FROM table_name;`

##### Snippet: Min and Max Employee ID and Pay
```sql
SELECT MIN(emp_id), MAX(emp_id), MIN(emp_pay), MAX(emp_pay) FROM employee;
```
*Output*: `100, 211, 56000, 86000`.

##### Snippet: Combined Min, Avg, Max Pay
```sql
SELECT MIN(emp_pay) AS minimum_pay, AVG(emp_pay) AS average_pay, MAX(emp_pay) AS max_pay FROM employee;
```
*Output*: `minimum_pay: 56000, average_pay: 71700, max_pay: 86000`.

### 2.4 Using Aliases with Aggregate Functions
Aliases rename aggregate function results for better readability.

- **Traits**: Uses `AS` to give a new name to output.
- **Syntax**: `SELECT aggregate_function(column) AS alias FROM table_name;`

##### Snippet: Aliased Distinct Pay
```sql
SELECT COUNT(DISTINCT emp_pay) AS unique_pay FROM employee;
```
*Output*: `unique_pay: 7`.

##### Snippet: Aliased Distinct Cities
```sql
SELECT COUNT(DISTINCT emp_city) AS unique_cities FROM employee;
```
*Output*: `unique_cities: 4`.

> [!TIP]
> Aliases (e.g., `total_pay`, `number_of_records`) make results more understandable.

### 2.5 Using Subqueries with SELECT
Subqueries embed a `SELECT` within another `SELECT` to filter or compare data.

- **Traits**: Inner query provides values for the outer query (e.g., with `IN` or `=`).
- **Syntax**: `SELECT * FROM table_name WHERE column IN (SELECT column FROM table_name WHERE condition);`

##### Snippet: Employees with 's' in Name
```sql
SELECT * FROM employee WHERE emp_name IN (SELECT emp_name FROM employee WHERE emp_name LIKE '%s%');
```
*Output*: Sam, Vasu, Ramesh (names with 's').

##### Snippet: City with Maximum Pay
```sql
SELECT emp_city FROM employee WHERE emp_pay = (SELECT MAX(emp_pay) FROM employee);
```
*Output*: `Mumbai` (Sam’s pay is 86,000).

##### Snippet: Employees with Max Pay in Hyderabad
```sql
SELECT emp_id, emp_name, emp_city FROM employee WHERE emp_pay = (SELECT MAX(emp_pay) FROM employee WHERE emp_city = 'Hyderabad');
```
*Output*: `111, Anil, Hyderabad` (max pay in Hyderabad is 78,000).

##### Snippet: Cities with 'a' Using IN
```sql
SELECT * FROM employee WHERE emp_city IN (SELECT emp_city FROM employee WHERE emp_city LIKE '%a%');
```
*Output*: Employees from Mumbai, Bangalore, Hyderabad (cities with 'a').

> [!NOTE]
> Subqueries require understanding the table’s data instance to write effectively.

### 2.6 Brief on ORDER BY with Aggregates
`ORDER BY` sorts `SELECT` results, mentioned as a prior concept, usable with aggregates.

- **Traits**: Sorts ascending (`ASC`, default) or descending (`DESC`).
- **Syntax**: `SELECT * FROM table_name ORDER BY column [ASC|DESC];`

##### Snippet: Order by Pay
```sql
SELECT * FROM employee ORDER BY emp_pay ASC;
```
*Output*: Starts with 56,000, ends with 86,000.

##### Snippet: Order by Pay Descending
```sql
SELECT * FROM employee ORDER BY emp_pay DESC;
```
*Output*: Starts with 86,000, ends with 56,000.

##### Snippet: Order by Employee ID
```sql
SELECT * FROM employee ORDER BY emp_id DESC;
```
*Output*: 211 to 100.

##### Snippet: Order by City
```sql
SELECT * FROM employee ORDER BY emp_city ASC;
```
*Output*: Bangalore, Hyderabad, Mumbai, New Delhi.

### 2.7 Implementation Overview
Runs in `my_db` with `employee` (10 rows: Ram, Sita, Sam, Rita, Geetha, Vikas, Vasu, Anil, Ramesh, Suresh). Numeric columns like `emp_id` and `emp_pay` (some repeats: 56,000 twice, 78,000 twice) are aggregated.

##### Textual Hierarchy Diagram
```
+-----------------+
| SELECT Command  | --> Retrieve Data
+-----------------+
    |
    v
+--------------------+
| Aggregate Functions| --> Summarize Data
+--------------------+
    |
    v
+--------+--------+-----------------+
| COUNT  | SUM, AVG | MIN, MAX       | --> Key Functions
+--------+--------+-----------------+
    |
    v
+--------+--------+
| Aliases | Subqueries | --> Enhancements
+--------+--------+
```

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use `DISTINCT` with `COUNT` for unique values (e.g., `COUNT(DISTINCT emp_city)`).
- Alias results for clarity (e.g., `AS total_pay`).
- Understand table data before writing subqueries.
- Use `ORDER BY` to present results logically.

### 3.2 Common Pitfalls
- Forgetting `DISTINCT` in `COUNT`, overcounting repeats (e.g., 10 cities vs. 4 unique).
- Misspelling in subqueries (e.g., ‘Hyderbad’ vs. ‘Hyderabad’).
- Using aggregates without numeric columns (e.g., `SUM(emp_name)` fails).
- Not pairing subqueries with proper operators (`IN`, `=`).

### 3.3 Practice Exercises
1. Count total records in `employee` with an alias.
2. Find the sum of all pays as `total_pay`.
3. Calculate min, avg, and max pay in one query.
4. Count distinct cities and pays with aliases.
5. Select employees from cities with ‘a’ using a subquery.

---

## 4. Comparisons

### 4.1 Aggregate Functions vs. Basic SELECT
| Aspect            | Aggregate Functions        | Basic SELECT              |
|-------------------|----------------------------|---------------------------|
| Purpose           | Summarize data             | Retrieve raw data         |
| Output            | Single value               | Multiple rows             |
| Examples          | `COUNT(*)`, `SUM(pay)`     | `SELECT * FROM employee`  |
| Use Case          | Analysis                   | Data listing              |

### 4.2 COUNT vs. SUM vs. AVG
| Aspect            | COUNT                      | SUM                       | AVG                     |
|-------------------|----------------------------|---------------------------|-------------------------|
| Action            | Counts rows/values         | Totals values             | Averages values         |
| Result            | Integer (e.g., 10)         | Sum (e.g., 717000)        | Average (e.g., 71700)   |
| Syntax            | `COUNT(*)`                 | `SUM(column)`             | `AVG(column)`           |
| Use Case          | Record tally               | Total calculation         | Mean value              |

---

## 5. Resources & Summary

### 5.1 Resources
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [SQL Tutorial](https://www.w3schools.com/sql/)

### 5.2 Summary
Aggregate functions (`COUNT`, `SUM`, `AVG`, `MIN`, `MAX`) summarize numeric data in MySQL’s `employee` table (10 rows). `COUNT` tallies records or unique values, `SUM` and `AVG` process totals and averages, and `MIN`/`MAX` find extremes. Aliases enhance readability, subqueries filter dynamically, and `ORDER BY` sorts results. This sets the stage for `GROUP BY` and `HAVING` next.

#### Highlights
- **COUNT**: Counts rows or distinct values (e.g., 4 cities).
- **SUM/AVG**: Totals and averages pay (e.g., 717,000, 71,700).
- **Subqueries**: Embed queries (e.g., max pay city).
- **Takeaway**: Master aggregates for data analysis! 🎉

### 5.3 SQL Commands Table
| Command              | Description                                      | Example                                      |
|----------------------|--------------------------------------------------|----------------------------------------------|
| `SELECT COUNT(*)`    | Counts all rows                                 | `SELECT COUNT(*) FROM employee;`             |
| `SELECT COUNT(DISTINCT)` | Counts unique values                        | `SELECT COUNT(DISTINCT emp_city) FROM employee;` |
| `SELECT SUM`         | Totals a column                                 | `SELECT SUM(emp_pay) AS total_pay FROM employee;` |
| `SELECT AVG`         | Averages a column                               | `SELECT AVG(emp_pay) AS average_pay FROM employee;` |
| `SELECT MIN/MAX`     | Finds min/max values                            | `SELECT MIN(emp_pay), MAX(emp_pay) FROM employee;` |
| `SELECT ... AS`      | Aliases output                                  | `SELECT COUNT(*) AS number_of_records FROM employee;` |
| `SELECT ... IN (SELECT)` | Uses subquery with IN                       | `SELECT * FROM employee WHERE emp_city IN (SELECT emp_city FROM employee WHERE emp_city LIKE '%a%');` |
| `SELECT ... = (SELECT)` | Uses subquery with =                         | `SELECT emp_city FROM employee WHERE emp_pay = (SELECT MAX(emp_pay) FROM employee);` |
| `SELECT ... ORDER BY` | Sorts results                                  | `SELECT * FROM employee ORDER BY emp_pay DESC;` |
| `UPDATE ... SET`     | Modifies data (context)                         | `UPDATE employee SET emp_pay = 62000 WHERE emp_id = 129;` |

