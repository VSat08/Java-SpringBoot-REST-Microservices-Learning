

-- File: joins_demo.sql
-- Purpose: Demonstrates SQL Joins from Lecture 85

-- Create and use my_db
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

-- Setup: Create and Populate Tables
CREATE TABLE customers (
    cid INT PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    order_amount FLOAT,
    order_date DATE,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid)
);

-- Insert Data
INSERT INTO customers (cid, cname, country) VALUES
    (1, 'Ram', 'India'),
    (2, 'David', 'UK'),
    (3, 'Anil', 'Canada'),
    (4, 'Priya', 'US'),
    (5, 'Sofia', 'Spain'),
    (6, 'Liam', 'Australia'),
    (7, 'Emma', 'Germany');
INSERT INTO orders (order_id, order_amount, order_date, customer_id) VALUES
    (13, 45000, '2023-01-15', 2),
    (14, 25000, '2023-02-20', 3),
    (15, 30000, '2023-03-10', 5),
    (16, 40000, '2023-04-05', 104); -- 104 doesn’t exist in customers
UPDATE orders SET customer_id = 5 WHERE order_id = 15; 
UPDATE orders SET customer_id = 3 WHERE order_id = 16; 

-- Section 3.1: INNER JOIN
-- Example 1: Full Data
SELECT *
FROM customers
INNER JOIN orders
ON customers.cid = orders.customer_id;
-- Example 2: Specific Columns
SELECT customers.cid, customers.cname, orders.order_id, orders.order_amount
FROM customers
INNER JOIN orders
ON customers.cid = orders.customer_id;
-- Example 3: With Aliasing
SELECT c.cid, c.cname, o.order_id, o.order_amount
FROM customers c
INNER JOIN orders o
ON c.cid = o.customer_id;

-- Section 3.2: LEFT JOIN
SELECT c.cid, c.cname, o.order_id, o.order_amount
FROM customers c
LEFT JOIN orders o
ON c.cid = o.customer_id;

-- Section 3.3: RIGHT JOIN
SELECT c.cid, c.cname, o.order_id, o.order_amount
FROM customers c
RIGHT JOIN orders o
ON c.cid = o.customer_id;

-- Section 3.4: CROSS JOIN
SELECT *
FROM customers
CROSS JOIN orders;
-- Inner Join Without Condition (same as CROSS JOIN)
SELECT *
FROM customers
INNER JOIN orders;

-- Section 4.2: SELF JOIN (Conceptual)
SELECT A.cid, A.cname, B.cid AS related_cid, B.cname AS related_cname
FROM customers A
INNER JOIN customers B
ON A.cid = B.cid + 1;

-- Cleanup (optional)
-- DROP TABLE orders, customers;
