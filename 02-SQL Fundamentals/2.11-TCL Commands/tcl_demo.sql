-- File: tcl_demo.sql
-- Purpose: Demonstrates TCL Commands in MySQL

-- Setup: Create sample_db and account table
CREATE DATABASE IF NOT EXISTS sample_db;
USE sample_db;
CREATE TABLE account (
    account_number INT PRIMARY KEY,
    balance DECIMAL(10,2),
    account_type VARCHAR(20)
);
INSERT INTO account (account_number, balance, account_type) VALUES
    (111, 35000, 'Savings'),
    (222, 15000, 'Checking'),
    (444, 50000, 'Savings');

-- Section 2.3: Setting Up Transactions
SET autocommit = 0;
SELECT * FROM account;

-- Section 3.1: COMMIT Command
UPDATE account SET balance = balance - 5000 WHERE account_number = 444; -- 50,000 to 45,000
UPDATE account SET balance = balance + 5000 WHERE account_number = 222; -- 15,000 to 20,000
SELECT * FROM account;
COMMIT;
SELECT * FROM account; -- Changes permanent
ROLLBACK; -- No effect post-commit
SELECT * FROM account;

-- Section 3.2: ROLLBACK Command
UPDATE account SET balance = balance - 5000 WHERE account_number = 444; -- 45,000 to 40,000
UPDATE account SET balance = balance + 5000 WHERE account_number = 222; -- 20,000 to 25,000
SELECT * FROM account;
ROLLBACK;
SELECT * FROM account; -- Back to 45,000 and 20,000

-- Section 3.3: SAVEPOINT Command
COMMIT; -- Set current state (444: 45,000, 222: 20,000)
UPDATE account SET balance = balance - 5000 WHERE account_number = 444; -- 45,000 to 40,000
SAVEPOINT a;
UPDATE account SET balance = balance + 5000 WHERE account_number = 222; -- 20,000 to 25,000
SELECT * FROM account;
ROLLBACK TO a;
SELECT * FROM account; -- 444: 40,000, 222: 15,000
ROLLBACK; -- Back to last commit (444: 45,000, 222: 20,000)
SELECT * FROM account;

-- Cleanup (optional)
-- DROP TABLE account;
-- DROP DATABASE sample_db;
-- SET autocommit = 1;
