-- ddl_commands.sql
-- SQL file containing code snippets from the "2.2: DDL Commands" README
-- Generated based on the transcript from Udemy course "Mastering Java + Spring Boot: REST APIs and Microservices" (Lecture 72: DDL Create)
-- Current Date: March 05, 2025

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

-- Adding a column to the 'student' table
ALTER TABLE student ADD student_grade CHAR(1);

-- Dropping the 'student' table
DROP TABLE student;

-- Truncating the 'employee' table (removes data, keeps structure)
TRUNCATE TABLE employee;

-- Show all databases
SHOW DATABASES;

-- Show tables in the current database
SHOW TABLES;

-- Describe the 'employee' table structure
DESCRIBE employee;
-- Alternative syntax
DESC employee;

-- Show the creation script for 'employee' table
SHOW CREATE TABLE employee;

-- Create a database and table for practice
CREATE DATABASE school_db;
USE school_db;
CREATE TABLE student (
    id INT,
    name VARCHAR(20),
    gpa FLOAT
);

-- Insert a record (DML, included for completeness)
INSERT INTO student VALUES (123, 'ABC', 8.9);

-- Verify structure
DESCRIBE student;

-- Create 'employee' table and show creation
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_pay FLOAT
);
SHOW CREATE TABLE employee;

-- Drop the database
DROP DATABASE school_db;

-- Additional Examples from Transcript (Inserting Data for Context)
-- Switch to a sample database (e.g., my_db)
CREATE DATABASE my_db;
USE my_db;

-- Create 'employee' table again for insertion demo
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT
);

-- Insert single record
INSERT INTO employee VALUES (123, 'Vasis', 'June', 56000);

-- Insert multiple records
INSERT INTO employee VALUES 
    (112, 'Ram', 'New Delhi', 78000),
    (139, 'Sham', 'Hyderabad', 66000);

-- Retrieve all data (Query command for verification)
SELECT * FROM employee;