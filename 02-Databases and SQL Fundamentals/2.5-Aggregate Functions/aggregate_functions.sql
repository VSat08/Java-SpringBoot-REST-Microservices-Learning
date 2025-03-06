-- aggregate_functions.sql
-- SQL file for "2.5: Aggregate Functions" README
-- Based on Lecture 77 ("Aggregate Functions") from "Mastering Java + Spring Boot: REST APIs and Microservices"
-- Current Date: March 06, 2025

-- Create 'employee' table
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT
);

-- Insert 10 rows as per transcript
INSERT INTO employee VALUES (123, 'Ram', 'New Delhi', 78000);
INSERT INTO employee VALUES (112, 'Sita', 'Mumbai', 56000);
INSERT INTO employee VALUES (100, 'Sam', 'Mumbai', 86000);
INSERT INTO employee VALUES (200, 'Rita', 'Bangalore', 42000);
INSERT INTO employee VALUES (201, 'Geetha', 'Hyderabad', 75000);
INSERT INTO employee VALUES (211, 'Vikas', 'New York', 85000);
INSERT INTO employee VALUES (139, 'Vasu', 'Bangalore', 56000);
INSERT INTO employee VALUES (111, 'Anil', 'Hyderabad', 78000);
INSERT INTO employee VALUES (129, 'Ramesh', 'Hyderabad', NULL);
INSERT INTO employee VALUES (140, 'Suresh', 'Pune', 65000);

-- Update Ramesh's pay (transcript says 150, but 129 matches context)
UPDATE employee SET emp_pay = 62000 WHERE emp_id = 129;

-- Basic SELECT for context
SELECT * FROM employee;

-- COUNT Function: Count all records
SELECT COUNT(*) FROM employee;

-- COUNT with alias
SELECT COUNT(*) AS number_of_records FROM employee;

-- COUNT distinct cities
SELECT COUNT(DISTINCT emp_city) FROM employee;

-- COUNT distinct pay
SELECT COUNT(DISTINCT emp_pay) FROM employee;

-- SUM Function: Total pay
SELECT SUM(emp_pay) AS total_pay FROM employee;

-- AVG Function: Average pay
SELECT AVG(emp_pay) AS average_pay FROM employee;

-- MIN and MAX Functions: Min and Max employee ID and pay
SELECT MIN(emp_id), MAX(emp_id), MIN(emp_pay), MAX(emp_pay) FROM employee;

-- Combined Min, Avg, Max pay
SELECT MIN(emp_pay) AS minimum_pay, AVG(emp_pay) AS average_pay, MAX(emp_pay) AS max_pay FROM employee;

-- Aliases: Distinct pay
SELECT COUNT(DISTINCT emp_pay) AS unique_pay FROM employee;

-- Aliases: Distinct cities
SELECT COUNT(DISTINCT emp_city) AS unique_cities FROM employee;

-- Subqueries: Employees with 's' in name
SELECT * FROM employee WHERE emp_name IN (SELECT emp_name FROM employee WHERE emp_name LIKE '%s%');

-- Subquery: City with maximum pay
SELECT emp_city FROM employee WHERE emp_pay = (SELECT MAX(emp_pay) FROM employee);

-- Subquery: Employees with max pay in Hyderabad
SELECT emp_id, emp_name, emp_city FROM employee WHERE emp_pay = (SELECT MAX(emp_pay) FROM employee WHERE emp_city = 'Hyderabad');

-- Subquery: Cities with 'a'
SELECT * FROM employee WHERE emp_city IN (SELECT emp_city FROM employee WHERE emp_city LIKE '%a%');

-- ORDER BY: Order by pay ascending
SELECT * FROM employee ORDER BY emp_pay ASC;

-- ORDER BY: Order by pay descending
SELECT * FROM employee ORDER BY emp_pay DESC;

-- ORDER BY: Order by employee ID descending
SELECT * FROM employee ORDER BY emp_id DESC;

-- ORDER BY: Order by city ascending
SELECT * FROM employee ORDER BY emp_city ASC;