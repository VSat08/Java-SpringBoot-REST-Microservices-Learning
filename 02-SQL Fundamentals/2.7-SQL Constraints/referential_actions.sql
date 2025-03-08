-- File: referential_actions.sql
-- Purpose: Demonstrates FOREIGN KEY referential actions (RESTRICT, CASCADE, SET NULL, NO ACTION, SET DEFAULT)

-- Use the my_db database
USE my_db;

-- Setup: Parent and Child Tables
CREATE TABLE customers (
    cid INT PRIMARY KEY,
    cname VARCHAR(20)
);
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT
);

-- Section 5.2.1: RESTRICT
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE RESTRICT;
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (1, 1);
DELETE FROM customers WHERE cid = 1; -- Fails (RESTRICT prevents deletion)
SELECT * FROM orders;
SELECT * FROM customers;
-- Cleanup
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;

-- Section 5.2.2: CASCADE
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE;
DELETE FROM customers WHERE cid = 1; -- Works (deletes both customer and order)
SELECT * FROM orders; -- Empty
SELECT * FROM customers; -- Empty
-- Test Update Cascade
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (2, 1);
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON UPDATE CASCADE;
UPDATE customers SET cid = 2 WHERE cid = 1; -- Updates customer_id in orders
SELECT * FROM orders; -- (2, 2)
SELECT * FROM customers; -- (2, 'Ram')

-- Section 5.2.3: SET NULL
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE SET NULL;
INSERT INTO customers VALUES (2, 'David');
INSERT INTO orders VALUES (3, 2);
DELETE FROM customers WHERE cid = 2; -- Sets customer_id to NULL
SELECT * FROM orders; -- (3, NULL)
SELECT * FROM customers; -- Empty

-- Section 5.2.4: NO ACTION (Default)
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid); -- No action by default
INSERT INTO customers VALUES (7, 'Anil');
INSERT INTO orders VALUES (7, 7);
DELETE FROM customers WHERE cid = 7; -- Fails (NO ACTION prevents deletion)
SELECT * FROM orders;
SELECT * FROM customers;

-- Section 5.2.5: SET DEFAULT (Conceptual, MySQL limited support)
-- Note: MySQL doesn’t fully support SET DEFAULT; shown for completeness
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE SET DEFAULT;
-- Hypothetical behavior: customer_id would reset to a default value (e.g., 0) if supported

-- Section 5.3: Combining Actions
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orders ADD CONSTRAINT customers_orders 
    FOREIGN KEY (customer_id) REFERENCES customers(cid) 
    ON DELETE SET NULL ON UPDATE CASCADE;
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (4, 1);
DELETE FROM customers WHERE cid = 1; -- Sets customer_id to NULL
SELECT * FROM orders; -- (4, NULL)
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (5, 1);
UPDATE customers SET cid = 2 WHERE cid = 1; -- Updates customer_id to 2
SELECT * FROM orders; -- (5, 2)