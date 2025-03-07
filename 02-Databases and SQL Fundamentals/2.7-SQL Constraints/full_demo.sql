-- File: full_demo.sql
-- Purpose: Full demonstration of SQL constraints from Lectures 79-84

-- Create and use my_db
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

-- Core Constraints
-- NOT NULL, UNIQUE, PRIMARY KEY
CREATE TABLE customers (
    cid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO customers (cname, country) VALUES ('Ram', 'India');
INSERT INTO customers (cname, country) VALUES ('David', 'UK');
SELECT * FROM customers;

-- Advanced Constraints
-- FOREIGN KEY, CHECK, DEFAULT
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    order_date DATE DEFAULT CURRENT_DATE,
    customer_id INT,
    CONSTRAINT customers_orders FOREIGN KEY (customer_id) REFERENCES customers(cid),
    CHECK (order_amount >= 10000)
);
INSERT INTO orders (order_amount, customer_id) VALUES (45000, 1); -- Works
INSERT INTO orders (order_amount, customer_id) VALUES (5000, 2);  -- Fails (CHECK)
INSERT INTO orders (order_amount, customer_id) VALUES (25000, 3); -- Fails (FOREIGN KEY)
SELECT * FROM orders;

-- Referential Actions
-- RESTRICT (default behavior test)
DELETE FROM customers WHERE cid = 1; -- Fails (RESTRICT/NO ACTION by default)

-- CASCADE
ALTER TABLE orders DROP FOREIGN KEY customers_orders;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE;
DELETE FROM customers WHERE cid = 1; -- Deletes customer and order
SELECT * FROM orders;
SELECT * FROM customers;

-- SET NULL
INSERT INTO customers (cname, country) VALUES ('Anil', 'Canada');
INSERT INTO orders (order_amount, customer_id) VALUES (30000, 3);
ALTER TABLE orders DROP FOREIGN KEY customers_orders;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE SET NULL;
DELETE FROM customers WHERE cid = 3; -- Sets customer_id to NULL
SELECT * FROM orders;

-- UPDATE CASCADE
INSERT INTO customers (cname, country) VALUES ('Ram', 'India');
INSERT INTO orders (order_amount, customer_id) VALUES (40000, 4);
ALTER TABLE orders DROP FOREIGN KEY customers_orders;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON UPDATE CASCADE;
UPDATE customers SET cid = 10 WHERE cid = 4; -- Updates customer_id in orders
SELECT * FROM orders;
SELECT * FROM customers;