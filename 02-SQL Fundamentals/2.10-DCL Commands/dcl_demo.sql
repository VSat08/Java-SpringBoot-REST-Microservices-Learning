-- File: dcl_demo.sql
-- Purpose: Demonstrates DCL Commands and Non-Root Login in MySQL

-- Section 4.1: Creating Users
CREATE USER 'scott'@'localhost' IDENTIFIED BY 'tiger';
CREATE USER 'kevin'@'localhost' IDENTIFIED BY 'pass123';

-- Verify Users
SELECT host, user FROM mysql.user;

-- Create a Sample Database and Table
CREATE DATABASE IF NOT EXISTS sample_db;
USE sample_db;
CREATE TABLE book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(20),
    price FLOAT,
    author VARCHAR(20)
);
INSERT INTO book (book_name, price, author) VALUES
    ('Java', 500, 'Shieldt'),
    ('Latest Python', 400, 'Anon'),
    ('C in Depth', 350, 'Sree');

-- Section 3.1: GRANT Command
-- Example 1: All Privileges
GRANT ALL ON *.* TO 'scott'@'localhost' WITH GRANT OPTION;
SHOW GRANTS FOR 'scott'@'localhost';

-- Example 2: Specific Privileges
GRANT SELECT, INSERT, CREATE, DROP ON sample_db.* TO 'kevin'@'localhost';
SHOW GRANTS FOR 'kevin'@'localhost';

-- Section 3.2: REVOKE Command
-- Example 1: Remove Specific Privilege
REVOKE UPDATE ON *.* FROM 'scott'@'localhost';
SHOW GRANTS FOR 'scott'@'localhost';

-- Section 2.3: Login as Non-Root User (Run in Command Prompt/Terminal)
-- Command: mysql -u scott -p
-- Password: tiger
-- Then run these to test:
-- USE sample_db;
-- SELECT * FROM book;
-- INSERT INTO book (book_name, price, author) VALUES ('SQL Basics', 300, 'Doe');
-- DELETE FROM book WHERE book_id = 2; -- Works
-- UPDATE book SET book_name = 'Java Complete Reference' WHERE book_id = 1; -- Denied

-- Example 2: Remove All (Conceptual)
-- REVOKE ALL ON *.* FROM 'kevin'@'localhost';

-- Section 4.2: Altering and Dropping Users
-- Alter Password
ALTER USER 'scott'@'localhost' IDENTIFIED BY 'newtiger';

-- Drop User
DROP USER 'kevin'@'localhost';
SELECT host, user FROM mysql.user; -- Verify kevin gone

-- Section 4.3: Viewing Users and Privileges
SHOW GRANTS FOR 'scott'@'localhost';
SELECT host, user FROM mysql.user;

-- Apply Changes
FLUSH PRIVILEGES;

-- Cleanup (optional)
-- DROP DATABASE sample_db;
-- DROP USER 'scott'@'localhost';
