-- dql_where_clause.sql
-- SQL file containing code snippets from the "2.4: DQL Where Clause" README
-- Generated based on the transcript from Udemy course "Mastering Java + Spring Boot: REST APIs and Microservices" 
-- (Lecture 76: Select Where Clause)
-- Current Date: March 05, 2025

-- Create 'employee' table and populate with data (10 rows from transcript)
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT,
    dob DATE
);

-- Insert records
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

-- Create 'student' table for examples (assumed structure)
CREATE TABLE student (
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    class INT,
    grade VARCHAR(1)
);

-- Insert sample student data
INSERT INTO student VALUES ('Amit', 'Delhi', 11, 'B');
INSERT INTO student VALUES ('Priya', 'Mumbai', 9, 'A');
INSERT INTO student VALUES ('Rohan', 'Chennai', 12, 'C');

-- Basic SELECT without WHERE
SELECT * FROM employee;

-- Relational Operators: Equal To
SELECT * FROM employee WHERE emp_city = 'Mumbai';

-- Relational Operators: Greater Than or Equal
SELECT * FROM employee WHERE emp_pay >= 70000;

-- Logical Operators: AND
SELECT emp_name, emp_city FROM student WHERE emp_city != 'Mumbai' AND class > 10;

-- Logical Operators: OR (assuming job column doesn’t exist, using pay as example)
SELECT * FROM employee WHERE emp_pay > 10000 OR emp_pay = 42000;

-- Membership Operators: IN
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');

-- Range Operators: BETWEEN
SELECT * FROM employee WHERE emp_pay BETWEEN 60000 AND 80000;

-- Pattern Matching with LIKE: Second Character 'i'
SELECT * FROM employee WHERE emp_name LIKE '_i%';

-- Pattern Matching with LIKE: Starts with 'A'
SELECT * FROM employee WHERE emp_name LIKE 'A%';

-- Pattern Matching with LIKE: Ends with 'a'
SELECT * FROM employee WHERE emp_name LIKE '%a';

-- Pattern Matching with LIKE: Contains 'i'
SELECT * FROM employee WHERE emp_name LIKE '%i%';

-- Identity Operators: IS NULL
UPDATE employee SET emp_pay = NULL WHERE emp_id = 129;
SELECT * FROM employee WHERE emp_pay IS NULL;

-- ORDER BY Clause: Order by City Ascending with WHERE
SELECT * FROM employee WHERE emp_pay > 60000 ORDER BY emp_city ASC;

-- ORDER BY Clause: Order by Pay Descending with WHERE
SELECT * FROM employee WHERE emp_city = 'Hyderabad' ORDER BY emp_pay DESC;

-- Advanced Features: Calculations and Aliases
SELECT 4 * 3;
SELECT emp_name, emp_pay * 12 AS annual_salary FROM employee;
SELECT 22 / 7 AS pi;

-- Utility Commands
SHOW DATABASES;
SHOW TABLES;
DESCRIBE employee;
SELECT DATABASE();

-- Practice Exercises
-- 1. Employees from Mumbai
SELECT * FROM employee WHERE emp_city = 'Mumbai';

-- 2. Pay > 60,000, ordered by pay descending
SELECT * FROM employee WHERE emp_pay > 60000 ORDER BY emp_pay DESC;

-- 3. Employees from Hyderabad or Bangalore
SELECT * FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore');

-- 4. Pay between 50,000 and 70,000
SELECT * FROM employee WHERE emp_pay BETWEEN 50000 AND 70000;

-- 5. Names starting with 'A' or containing 'i', ordered by name
SELECT * FROM employee WHERE emp_name LIKE 'A%' OR emp_name LIKE '%i%' ORDER BY emp_name ASC;