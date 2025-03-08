# 2.11: TCL Commands - A Beginner's Manual to Transaction Management in MySQL

## Introduction
Welcome to **2.11: TCL Commands** 🌟! This section explores MySQL’s Transaction Control Language (TCL) commands—`COMMIT`, `ROLLBACK`, and `SAVEPOINT`—tools for managing database transactions. Using the `sample_db` database, this guide walks you through handling transactions as single units of work, ensuring data consistency with practical examples like money transfers. Perfect for beginners, it breaks down how to save, undo, or partially revert changes step-by-step. Let’s master transaction control! 🚀

---

## Table of Contents
1. [What Are TCL Commands?](#1-what-are-tcl-commands)
    - [1.1 Definition and Purpose](#11-definition-and-purpose)
    - [1.2 Why TCL Commands Matter](#12-why-tcl-commands-matter)
    - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Getting Started with TCL in MySQL](#2-getting-started-with-tcl-in-mysql)
    - [2.1 How TCL Works](#21-how-tcl-works)
    - [2.2 The Problem Without TCL](#22-the-problem-without-tcl)
    - [2.3 Setting Up Transactions](#23-setting-up-transactions)
3. [Core TCL Commands](#3-core-tcl-commands)
    - [3.1 COMMIT Command](#31-commit-command)
    - [3.2 ROLLBACK Command](#32-rollback-command)
    - [3.3 SAVEPOINT Command](#33-savepoint-command)
4. [Practical Application](#4-practical-application)
    - [4.1 Best Practices for TCL](#41-best-practices-for-tcl)
    - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
    - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Comparing TCL Commands](#5-comparing-tcl-commands)
    - [5.1 COMMIT vs. ROLLBACK vs. SAVEPOINT](#51-commit-vs-rollback-vs-savepoint)
6. [Wrapping Up](#6-wrapping-up)
    - [6.1 Resources for Further Learning](#61-resources-for-further-learning)
    - [6.2 Summary of Key Takeaways](#62-summary-of-key-takeaways)
    - [6.3 Complete SQL Commands Reference](#63-complete-sql-commands-reference)

---

## 1. What Are TCL Commands?

### 1.1 Definition and Purpose
TCL, or Transaction Control Language, includes SQL commands that manage transactions in a MySQL database. The main commands are `COMMIT`, `ROLLBACK`, and `SAVEPOINT`.

- **Definition**: TCL commands control a transaction—a sequence of SQL statements treated as one unit of work.
- **Purpose**: Ensure data changes are saved permanently (`COMMIT`), undone completely (`ROLLBACK`), or partially reverted (`SAVEPOINT`).
- **Example**: Transferring money between accounts with debit and credit steps.

#### Real-World Analogy
Think of a transaction as mailing a package:
- `COMMIT`: Package delivered and accepted.
- `ROLLBACK`: Package returned to sender.
- `SAVEPOINT`: Mark a checkpoint (e.g., at the post office) to return to if needed.

### 1.2 Why TCL Commands Matter
TCL ensures data integrity and reliability:
- **Atomicity**: All steps succeed, or none do.
- **Control**: Decide when changes stick or revert.
- **Flexibility**: Undo parts of a transaction with savepoints.

#### Example Benefit
Debiting 5,000 from one account and crediting another only saves if both steps work—otherwise, it rolls back.

### 1.3 Key Terms for Beginners
| Term             | Meaning                                   | Example                |
|------------------|-------------------------------------------|------------------------|
| **Transaction**  | Sequence of SQL statements as one unit    | Debit and credit       |
| **Commit**       | Save changes permanently                  | `COMMIT;`              |
| **Rollback**     | Undo changes to last commit               | `ROLLBACK;`            |
| **Savepoint**    | Checkpoint within a transaction           | `SAVEPOINT a;`         |
| **Autocommit**   | Automatic commit of each statement        | `SET autocommit = 0;`  |

---

## 2. Getting Started with TCL in MySQL

### 2.1 How TCL Works
TCL commands manage transactions by:
- Grouping SQL statements (e.g., `UPDATE`, `INSERT`) into a single unit.
- Using `COMMIT` to save, `ROLLBACK` to undo, or `SAVEPOINT` to mark revert points.
- Requiring manual control when `autocommit` is disabled.

#### Basic Flow
```sql
SET autocommit = 0;
UPDATE table SET column = value;
COMMIT; -- or ROLLBACK;
```

#### Key Features
- **Permanent**: Committed changes can’t be undone.
- **Reversible**: Rollback restores the pre-transaction state.
- **Segmented**: Savepoints allow partial rollbacks.

### 2.2 The Problem Without TCL
Without TCL, every SQL statement commits instantly (with `autocommit = 1`), risking partial updates.

#### Example: No TCL
- Debit 5,000 succeeds, but credit fails—data is inconsistent.
- **Issue**: No way to undo the debit alone.

### 2.3 Setting Up Transactions
#### Definition
Transactions need `autocommit` disabled to use TCL commands effectively.

#### Why Use It?
To control commits and rollbacks manually, avoiding automatic saves.

#### Instructions
1. **Check Autocommit**:
   ```sql
   SHOW VARIABLES LIKE 'autocommit';
   ```
   - Default: `ON` (1).

2. **Disable Autocommit**:
   ```sql
   SET autocommit = 0;
   ```
   - Now, changes wait for `COMMIT` or `ROLLBACK`.

3. **Start Transaction** (optional):
   - Use `START TRANSACTION;` for clarity (not required with `autocommit = 0`).

#### Example
```sql
SET autocommit = 0;
UPDATE account SET balance = balance - 5000 WHERE account_number = 444;
```

---

## 3. Core TCL Commands

### 3.1 COMMIT Command
#### Definition
`COMMIT` saves all changes in the current transaction permanently.

#### Why Use It?
To finalize a successful transaction (e.g., money transfer).

#### Instructions
- Run SQL statements.
- End with `COMMIT;`.
- Verify with `SELECT`.

#### Example: Money Transfer
```sql
SET autocommit = 0;
UPDATE account SET balance = balance - 5000 WHERE account_number = 444;
UPDATE account SET balance = balance + 5000 WHERE account_number = 222;
COMMIT;
```
- **Output**: 444 drops from 50,000 to 45,000; 222 rises from 15,000 to 20,000—permanent.

#### Verify
```sql
SELECT * FROM account;
```
- **Output**: Changes saved.

### 3.2 ROLLBACK Command
#### Definition
`ROLLBACK` undoes all changes since the last `COMMIT` or transaction start.

#### Why Use It?
To cancel a failed or unwanted transaction.

#### Instructions
- Run SQL statements.
- Use `ROLLBACK;` if needed.
- Check with `SELECT`.

#### Example: Undo Transfer
```sql
SET autocommit = 0;
UPDATE account SET balance = balance - 5000 WHERE account_number = 444;
UPDATE account SET balance = balance + 5000 WHERE account_number = 222;
SELECT * FROM account; -- 444: 40,000, 222: 20,000
ROLLBACK;
```
- **Output**: Back to 444: 45,000, 222: 15,000 (post-last `COMMIT`).

#### Verify
```sql
SELECT * FROM account;
```
- **Output**: Changes reverted.

### 3.3 SAVEPOINT Command
#### Definition
`SAVEPOINT` marks a point in a transaction to roll back to, instead of undoing everything.

#### Why Use It?
To undo part of a transaction while keeping earlier steps.

#### Instructions
- Set `SAVEPOINT name;`.
- Run more statements.
- Use `ROLLBACK TO name;` to revert to that point.

#### Example: Partial Rollback
```sql
SET autocommit = 0;
UPDATE account SET balance = balance - 5000 WHERE account_number = 444; -- 45,000 to 40,000
SAVEPOINT a;
UPDATE account SET balance = balance + 5000 WHERE account_number = 222; -- 15,000 to 20,000
SELECT * FROM account; -- 444: 40,000, 222: 20,000
ROLLBACK TO a;
```
- **Output**: 444: 40,000 (kept), 222: 15,000 (reverted to before savepoint).

#### Verify
```sql
SELECT * FROM account;
```
- **Output**: Partial changes undone.

---

## 4. Practical Application

### 4.1 Best Practices for TCL
- **Disable Autocommit**: Always `SET autocommit = 0` for TCL.
- **Commit Wisely**: Only commit when all steps succeed.
- **Use Savepoints**: Mark key points for complex transactions.
- **Verify Changes**: Check with `SELECT` before committing.
- **Keep Transactions Short**: Minimize uncommitted changes.

### 4.2 Common Mistakes to Avoid
- **Forgetting Autocommit**: Default `ON` commits everything instantly.
- **Committing Too Early**: Can’t rollback after `COMMIT`.
- **Missing Savepoints**: Limits rollback flexibility.
- **Long Transactions**: Risks locking or performance issues.

### 4.3 Hands-On Exercises
1. **Commit a Transfer**: Debit 5,000 from 111, credit 222, and commit.
2. **Rollback a Change**: Debit 5,000 from 444, then rollback.
3. **Use Savepoint**: Debit 5,000 from 444, set a savepoint, credit 222, rollback to savepoint.
4. **Test Autocommit**: Run an update with `autocommit = 1`, then try rollback.
5. **Multi-Step Transaction**: Debit and credit twice, rollback to an earlier savepoint.

---

## 5. Comparing TCL Commands

### 5.1 COMMIT vs. ROLLBACK vs. SAVEPOINT
| Feature          | COMMIT                  | ROLLBACK               | SAVEPOINT              |
|------------------|-------------------------|------------------------|------------------------|
| **Action**       | Saves permanently       | Undoes to last commit  | Marks a revert point   |
| **Syntax**       | `COMMIT;`               | `ROLLBACK;`            | `SAVEPOINT name;`      |
| **Scope**        | Entire transaction      | Entire transaction     | Partial transaction    |
| **Undo Possible**| No                      | Yes (pre-commit)       | Yes (to savepoint)     |
| **Use Case**     | Finalize success        | Cancel failure         | Partial rollback       |

---

## 6. Wrapping Up

### 6.1 Resources for Further Learning
- MySQL TCL Documentation: https://dev.mysql.com/doc/refman/8.0/en/commit.html
- W3Schools SQL Transactions: https://www.w3schools.com/sql/sql_transaction.asp

### 6.2 Summary of Key Takeaways
This guide explores TCL with `sample_db`’s `account` table:
- **COMMIT**: Saved a 5,000 transfer (444: 45,000, 222: 20,000).
- **ROLLBACK**: Undid a 5,000 transfer back to 45,000 and 15,000.
- **SAVEPOINT**: Reverted a credit after a debit, keeping 444 at 40,000.

#### Highlights
- **Atomicity**: TCL ensures all-or-nothing changes.
- **Control**: Manage transactions with precision.
- **Tip**: Always disable `autocommit` first.

### 6.3 Complete SQL Commands Reference
| Command              | Purpose                                      | Example                                      |
|----------------------|----------------------------------------------|----------------------------------------------|
| `COMMIT`             | Saves transaction permanently                | `COMMIT;`                                    |
| `ROLLBACK`           | Undoes to last commit                        | `ROLLBACK;`                                  |
| `SAVEPOINT`          | Sets a rollback point                        | `SAVEPOINT a;`                               |
| `ROLLBACK TO`        | Undoes to a savepoint                        | `ROLLBACK TO a;`                             |
| `SET autocommit = 0` | Disables automatic commits                   | `SET autocommit = 0;`                        |

---
_This README and SQL file are ready as your own resource for learning TCL commands!_