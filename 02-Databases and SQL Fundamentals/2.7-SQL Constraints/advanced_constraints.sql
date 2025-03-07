-- File: advanced_constraints.sql
-- Purpose: Demonstrates FOREIGN KEY, CHECK, and DEFAULT constraints

-- Use the my_db database
USE my_db;

-- Section 4.1: FOREIGN KEY Constraint
-- Example 1: Simple Link
CREATE TABLE customers (
    cid INT NOT NULL PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);
CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid)
);
INSERT INTO customers (cid, cname, country) VALUES (1, 'Ram', 'India');
INSERT INTO orders (order_amount, customer_id) VALUES (45000, 1); -- Works
INSERT INTO orders (order_amount, customer_id) VALUES (25000, 2); -- Fails (no customer 2)
SELECT * FROM orders;
-- Example 2: Named Constraint
CREATE TABLE orders_named (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    CONSTRAINT customers_orders FOREIGN KEY (customer_id) REFERENCES customers(cid)
);
-- Adding Later
ALTER TABLE orders ADD CONSTRAINT customers_orders_new FOREIGN KEY (customer_id) REFERENCES customers(cid);
-- Dropping It
ALTER TABLE orders DROP FOREIGN KEY customers_orders_new;
INSERT INTO orders (order_amount, customer_id) VALUES (15000, 106); -- Works after drop
SELECT * FROM orders;

-- Section 4.2: CHECK Constraint
-- Example 1: Single Column
CREATE TABLE orders_check (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    CHECK (order_amount >= 10000)
);
INSERT INTO orders_check (order_amount) VALUES (25000); -- Works
INSERT INTO orders_check (order_amount) VALUES (5000);  -- Fails
SELECT * FROM orders_check;
-- Example 2: Adding Later
ALTER TABLE orders ADD CHECK (order_amount >= 10000);
-- Example 3: Table-Level (Conceptual)
CREATE TABLE persons_check (
    id INT NOT NULL,
    age INT,
    city VARCHAR(20),
    CONSTRAINT check_person CHECK (age >= 18 AND city = 'New Delhi')
);

-- Section 4.3: DEFAULT Constraint
-- Example 1: Default Date
CREATE TABLE orders_default (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_amount FLOAT,
    order_date DATE DEFAULT CURRENT_DATE
);
INSERT INTO orders_default (order_amount) VALUES (50000); -- Uses today’s date
SELECT * FROM orders_default;
-- Example 2: Default Text
CREATE TABLE persons_one (
    id INT NOT NULL,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) DEFAULT '',
    city VARCHAR(20) DEFAULT 'New Delhi'
);
INSERT INTO persons_one (id, lname, age) VALUES (1, 'Tata', 80);
SELECT * FROM persons_one;
-- Changing It
ALTER TABLE orders_default MODIFY order_date DATE DEFAULT CURRENT_DATE;