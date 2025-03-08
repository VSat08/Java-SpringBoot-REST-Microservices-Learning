
-- Create 'employee' table for demonstration (assumed structure from transcript)
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT,
    dob DATE
);

-- Insert full records
INSERT INTO employee VALUES (100, 'Sam', 'London', 86000, '1996-09-18');
INSERT INTO employee VALUES (200, 'Rita', 'Bangalore', 42000, '1999-01-06');

-- Insert selective records
INSERT INTO employee (emp_id, emp_name, emp_pay) VALUES (201, 'Geetha', 75000);
INSERT INTO employee (emp_id, emp_pay, emp_city) VALUES (211, 85000, 'New York');

-- Verify data
SELECT * FROM employee;

-- Update a single record
UPDATE employee SET emp_name = 'Vikas', dob = '2001-02-14' WHERE emp_id = 211;

-- Update multiple columns (one at a time)
UPDATE employee SET emp_city = 'Hyderabad' WHERE emp_id = 201;
UPDATE employee SET dob = '1998-06-15' WHERE emp_id = 201;

-- Update multiple records
UPDATE employee SET dob = '1990-07-01' WHERE emp_id IN (123, 112, 139);

-- Verify updates
SELECT * FROM employee;

-- Delete a specific record
DELETE FROM employee WHERE emp_id = 139;
DELETE FROM employee WHERE emp_name = 'Vikas';

-- Create backup table and delete all records
CREATE TABLE emp_backup AS SELECT * FROM employee;
DELETE FROM employee;

-- Insert data from another table
INSERT INTO employee SELECT * FROM emp_backup;

-- Verify restored data
SELECT * FROM employee;

-- Create 'staff' table for practice exercises
CREATE DATABASE my_db;
USE my_db;

CREATE TABLE staff (
    staff_id INT,
    name VARCHAR(20),
    dept VARCHAR(20),
    salary FLOAT
);

-- Exercise: Insert full and selective records
INSERT INTO staff VALUES (101, 'John', 'HR', 60000);
INSERT INTO staff (staff_id, name, salary) VALUES (102, 'Mary', 55000);

-- Exercise: Update a record
UPDATE staff SET dept = 'IT' WHERE staff_id = 102;

-- Exercise: Delete a record
DELETE FROM staff WHERE name = 'John';

-- Exercise: Backup, delete all, and restore
CREATE TABLE staff_backup AS SELECT * FROM staff;
DELETE FROM staff;
INSERT INTO staff SELECT * FROM staff_backup;

-- Verify final state
SELECT * FROM staff;