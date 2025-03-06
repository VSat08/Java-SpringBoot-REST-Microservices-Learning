-- dql_commands.sql
-- SQL file containing code snippets from the "2.3: DQL Commands" README
-- Generated based on the transcripts from Udemy course "Mastering Java + Spring Boot: REST APIs and Microservices" 
-- (Lecture 75: The Query Command and Lecture 76: Select Where Clause)
-- Current Date: March 05, 2025

-- Create 'employee' table and populate with data (assumed structure from transcripts)
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT,
    dob DATE
);

-- Insert initial records (from Lecture 75)
INSERT INTO employee VALUES (123, 'Ram', 'New Delhi', 78000, '1990-07-01');
INSERT INTO employee VALUES (112, 'Sita', 'Mumbai', 56000, '1990-07-01');
INSERT INTO employee VALUES (100, 'Sam', 'Mumbai', 86000, '1996-09-18');
INSERT INTO employee VALUES (200, 'Rita', 'Bangalore', 42000, '1999-01-06');
INSERT INTO employee VALUES (201, 'Geetha', 'Hyderabad', 75000, '1998-06-15');
INSERT INTO employee VALUES (211, 'Vikas', 'New York', 85000, '2001-02-14');
INSERT INTO employee VALUES (139, 'Vasu', 'Bangalore', 56000, '1990-07-01');
INSERT INTO employee VALUES (111, 'Anil', 'Hyderabad', 78000, '1982-06-25');
INSERT INTO employee VALUES (129, 'Ramesh', 'Hyderabad', 50000, '1980-12-11');
INSERT INTO employee VALUES (140, 'Suresh', 'Pune', 65000, NULL);

-- Basic SELECT: Fetch all records
SELECT * FROM employee;

-- Basic SELECT: Fetch specific columns
SELECT emp_id, emp_name FROM employee;
SELECT emp_city FROM employee;

-- WHERE Clause: Simple conditions
SELECT * FROM employee WHERE emp_city = 'Hyderabad';
SELECT * FROM employee WHERE emp_pay >= 75000;

-- WHERE Clause: Multiple conditions
SELECT * FROM employee WHERE emp_pay > 75000 AND emp_city = 'Hyderabad';

-- WHERE Clause: IN operator
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');

-- WHERE Clause: BETWEEN operator
SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;

-- WHERE Clause: LIKE operator
SELECT * FROM employee WHERE emp_name LIKE '_i%';
SELECT * FROM employee WHERE emp_name LIKE 'A%';
SELECT * FROM employee WHERE emp_name LIKE '%a';
SELECT * FROM employee WHERE emp_name LIKE '%i%';

-- WHERE Clause: IS NULL
SELECT * FROM employee WHERE emp_pay IS NULL;

-- ORDER BY: Sort by name ascending
SELECT * FROM employee ORDER BY emp_name ASC;

-- ORDER BY: Sort by pay descending
SELECT * FROM employee ORDER BY emp_pay DESC;

-- DISTINCT: Unique pay values
SELECT DISTINCT emp_pay FROM employee;

-- DISTINCT: Unique cities
SELECT DISTINCT emp_city FROM employee;

-- LIMIT: Restrict to 5 records
SELECT * FROM employee LIMIT 5;

-- Combined: WHERE, ORDER BY, LIMIT (using world database)
USE world;
SELECT * FROM city WHERE country_code = 'IND' ORDER BY name ASC LIMIT 50;

-- Advanced: Calculations and aliases
SELECT 4 * 3;
SELECT emp_name, emp_pay * 12 AS annual_salary FROM employee;
SELECT 22 / 7 AS pi;

-- Utility commands
SHOW DATABASES;
SHOW TABLES;
DESCRIBE employee;
SELECT DATABASE();

-- Practice Exercises
-- 1. Select all and specific columns
SELECT * FROM employee;
SELECT emp_name, emp_city FROM employee;

-- 2. Employees with pay > 70000, ordered by pay descending
SELECT * FROM employee WHERE emp_pay > 70000 ORDER BY emp_pay DESC;

-- 3. Unique cities
SELECT DISTINCT emp_city FROM employee;

-- 4. Employees from Hyderabad or Mumbai, limited to 3
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Mumbai') LIMIT 3;

-- 5. Names starting with 'A' or ending with 's'
SELECT * FROM employee WHERE emp_name LIKE 'A%' OR emp_name LIKE '%s';