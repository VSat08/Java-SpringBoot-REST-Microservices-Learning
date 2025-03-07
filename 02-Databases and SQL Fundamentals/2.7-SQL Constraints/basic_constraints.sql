-- File: basic_constraints.sql
-- Purpose: Demonstrates NOT NULL, UNIQUE, and PRIMARY KEY constraints

-- Select the database (create if not exists)
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

-- Section 2.2: The Problem Without Constraints
-- Example: Table with no constraints (messy data)
CREATE TABLE emp (
    emp_id INT,
    emp_name VARCHAR(20)
);
INSERT INTO emp VALUES (NULL, NULL); -- Allowed, but bad!
INSERT INTO emp VALUES (1, 'Alice');
INSERT INTO emp VALUES (1, 'Bob');   -- Duplicate ID, no error!
SELECT * FROM emp;
-- Cleanup
DROP TABLE emp;

-- Section 3.1: NOT NULL Constraint
-- Example: Mandatory fields
CREATE TABLE persons (
    id INT NOT NULL,          -- Must have an ID
    lname VARCHAR(20) NOT NULL, -- Must have a last name
    fname VARCHAR(20) NOT NULL, -- Must have a first name
    age INT                   -- Can be empty
);
-- Test NOT NULL
INSERT INTO persons (id, fname, age) VALUES (1, 'Ratan', 78); -- Fails (missing lname)
INSERT INTO persons (id, lname, fname, age) VALUES (1, 'Tata', 'Ratan', 78); -- Works
SELECT * FROM persons;
-- Modify existing column
ALTER TABLE persons MODIFY age INT NOT NULL;

-- Section 3.2: UNIQUE Constraint
-- Example 1: Single column
CREATE TABLE person (
    id INT NOT NULL UNIQUE,    -- IDs must be unique
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(10) NOT NULL,
    age INT UNIQUE             -- Ages must be unique (NULL allowed)
);
INSERT INTO person VALUES (1, 'Tata', 'Ratan', 80);
INSERT INTO person VALUES (2, 'Bala', 'Jaan', 90);
INSERT INTO person VALUES (3, 'Tata', 'Ravi', 80); -- Fails (duplicate age)
SELECT * FROM person;
-- Example 2: Multiple columns
CREATE TABLE person_multi (
    id INT NOT NULL,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    CONSTRAINT uc_person UNIQUE (id, lname) -- ID + last name combo must be unique
);
INSERT INTO person_multi (id, lname, fname) VALUES (1, 'Tata', 'Ratan');
INSERT INTO person_multi (id, lname, fname) VALUES (1, 'Tata', 'Ravi'); -- Fails
SELECT * FROM person_multi;

-- Section 3.3: PRIMARY KEY Constraint
-- Example 1: Simple Primary Key with AUTO_INCREMENT
CREATE TABLE customers (
    cid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- Auto-numbers from 1
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);
INSERT INTO customers (cname, country) VALUES ('Ram', 'India');
INSERT INTO customers (cname, country) VALUES ('David', 'UK');
SELECT * FROM customers;
-- Example 2: Table-Level Primary Key
CREATE TABLE customers_table (
    cid INT NOT NULL AUTO_INCREMENT,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL,
    PRIMARY KEY (cid)
);
-- Example 3: Multi-Column Primary Key
ALTER TABLE customers ADD CONSTRAINT pk_customers PRIMARY KEY (cid, cname);
INSERT INTO customers (cid, cname, country) VALUES (103, 'Anil', 'India');
INSERT INTO customers (cid, cname, country) VALUES (103, 'Anil', 'US'); -- Fails
-- Adjust AUTO_INCREMENT
ALTER TABLE customers AUTO_INCREMENT = 101;
INSERT INTO customers (cname, country) VALUES ('Anshu', 'Canada');
SELECT * FROM customers;
-- Dropping PRIMARY KEY
ALTER TABLE customers MODIFY cid INT NOT NULL; -- Remove AUTO_INCREMENT first
ALTER TABLE customers DROP PRIMARY KEY;