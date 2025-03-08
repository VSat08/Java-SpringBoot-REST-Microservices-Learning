
-- Creating a 'student' table
CREATE TABLE student (
    student_id INT,
    student_name VARCHAR(20),
    student_gpa FLOAT,
    student_city VARCHAR(20)
);

-- Creating an 'employee' table
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT
);

-- Creating a table from an existing table
CREATE TABLE emp AS SELECT * FROM employee;

-- Adding a column to the 'student' table
ALTER TABLE student ADD student_grade CHAR(1);

-- Adding a column to 'employee' (dob as INT)
ALTER TABLE employee ADD dob INT;

-- Dropping a column from 'employee'
ALTER TABLE employee DROP COLUMN dob;

-- Modifying a column in 'employee' (dob to DATE)
ALTER TABLE employee MODIFY dob DATE;

-- Dropping the 'student' table
DROP TABLE student;

-- Truncating the 'employee' table (removes data, keeps structure)
TRUNCATE TABLE employee;

-- Show all databases
SHOW DATABASES;

-- Show tables in the current database
SHOW TABLES;

-- Show full tables (includes views)
SHOW FULL TABLES;

-- Describe the 'employee' table structure
DESCRIBE employee;

-- Alternative syntax
DESC employee;

-- Show the creation script for 'employee' table
SHOW CREATE TABLE employee;

-- Show current database
SELECT DATABASE();

-- Create a database and switch to it
CREATE DATABASE my_db;
USE my_db;

-- Create 'employee' table again for insertion demo
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT,
    dob DATE
);

-- Insert single record
INSERT INTO employee VALUES (123, 'Vasis', 'June', 56000, '2002-02-28');

-- Insert multiple records
INSERT INTO employee VALUES 
    (112, 'Ram', 'New Delhi', 78000, NULL),
    (139, 'Sham', 'Hyderabad', 66000, NULL);

-- Retrieve all data (Query command)
SELECT * FROM employee;

-- Delete a record (DML, for ALTER compatibility)
DELETE FROM employee WHERE dob = 2002;

-- Add another column to 'employee'
ALTER TABLE employee ADD employment_type VARCHAR(10);

-- Drop the added column
ALTER TABLE employee DROP COLUMN employment_type;

-- Create a database and table for practice
CREATE DATABASE test_db;
USE test_db;

-- Create 'student' table for exercises
CREATE TABLE student (
    id INT,
    name VARCHAR(20),
    gpa FLOAT
);

-- Exercise: Add a column
ALTER TABLE student ADD dob DATE;

-- Exercise: Insert a record
INSERT INTO student VALUES (100, 'ABC', 8.9, '2000-01-15');

-- Exercise: Modify a column
ALTER TABLE student MODIFY gpa DECIMAL(3,1);

-- Exercise: Drop a column
ALTER TABLE student DROP COLUMN dob;

-- Exercise: Create backup, truncate, and drop
CREATE TABLE student_backup AS SELECT * FROM student;
TRUNCATE TABLE student;
DROP TABLE student_backup;

-- Drop the test database
DROP DATABASE test_db;