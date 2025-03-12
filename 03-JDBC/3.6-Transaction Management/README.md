# 3.6 - Transaction Management

## Introduction

Welcome to **3.6 - Transaction Management** 🌟! This chapter explores transaction management in Java Database Connectivity (JDBC), ensuring multiple SQL operations execute as a single, atomic unit. Using a `sampledb` database and an `ACCOUNT` table, we’ll implement a transfer transaction (withdraw and deposit) in NetBeans, leveraging `commit`, `rollback`, and batch processing. Perfect for beginners, this guide offers clear steps, practical code, and real-world examples to master transaction control. Let’s ensure data integrity with JDBC! 🚀

---

## Table of Contents

1. [What Is Transaction Management?](#1-what-is-transaction-management)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why Transaction Management Matters](#12-why-transaction-management-matters)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Transaction Management with JDBC](#2-transaction-management-with-jdbc)
   - [2.1 Overview of Transaction Management in JDBC](#21-overview-of-transaction-management-in-jdbc)
   - [2.2 Disabling Auto-Commit](#22-disabling-auto-commit)
   - [2.3 Batch Processing](#23-batch-processing)
   - [2.4 Commit vs. Rollback](#24-commit-vs-rollback)
   - [2.5 Verifying Results](#25-verifying-results)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Setting Up the Database](#31-setting-up-the-database)
   - [3.2 Managing Transactions in NetBeans](#32-managing-transactions-in-netbeans)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for Transaction Management](#41-best-practices-for-transaction-management)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 Complete Code Reference](#53-complete-code-reference)

---

## 1. What Is Transaction Management?

### 1.1 Definition and Purpose

Transaction management in JDBC handles a group of SQL statements as a single unit of work.

- **Definition**: A transaction is a sequence of operations (e.g., withdraw, deposit) that must all succeed (`commit`) or all fail (`rollback`).
- **Purpose**: Ensures data consistency and integrity by treating multiple steps as atomic.

#### Real-World Analogy

Think of transferring money: deducting from one account and adding to another must both happen, or neither should—otherwise, money is lost or duplicated.

### 1.2 Why Transaction Management Matters

- **Atomicity**: Guarantees all operations complete or none do.
- **Consistency**: Maintains database integrity (e.g., total balance remains accurate).
- **Reliability**: Prevents partial updates during failures.

#### Example Benefit

Transferring `5000` from account `111` to `333`—either both accounts update, or neither does.

### 1.3 Key Terms for Beginners

| Term                 | Meaning                           | Example                   |
| -------------------- | --------------------------------- | ------------------------- |
| **Transaction**      | Group of SQL operations as a unit | Withdraw + Deposit        |
| **Commit**           | Saves all changes permanently     | `cn.commit()`             |
| **Rollback**         | Undoes all changes on failure     | `cn.rollback()`           |
| **Auto-Commit**      | Automatic commit after each query | `cn.setAutoCommit(false)` |
| **Batch Processing** | Executes multiple queries at once | `st.executeBatch()`       |
| **TCL**              | Transaction Control Language      | COMMIT, ROLLBACK          |

---

## 2. Transaction Management with JDBC

### 2.1 Overview of Transaction Management in JDBC

JDBC manages transactions by:

- Disabling auto-commit with `Connection.setAutoCommit(false)`.
- Grouping operations into a batch using `Statement.addBatch()`.
- Executing the batch and checking results to `commit` or `rollback`.

#### Prerequisites

- A table exists (e.g., `ACCOUNT` with `account_number`, `balance`, `account_type`).
- JDBC connection established (`java.sql.*` imported).

### 2.2 Disabling Auto-Commit

#### Definition

Turns off automatic commits after each query.

#### Why Use It?

Allows manual control over when changes are saved or undone.

#### Instructions

- Use `cn.setAutoCommit(false)` (default is `true`).

#### Example

```java
cn.setAutoCommit(false);
```

### 2.3 Batch Processing

#### Definition

Groups multiple SQL queries for execution as a single batch.

#### Why Use It?

Efficiently handles multiple operations in a transaction.

#### Instructions

- Add queries with `st.addBatch(query)`.
- Execute with `st.executeBatch()` (returns an `int[]` of results).

#### Example

```java
st.addBatch("UPDATE ACCOUNT SET balance = balance - 5000 WHERE account_number = 111");
st.addBatch("UPDATE ACCOUNT SET balance = balance + 5000 WHERE account_number = 333");
```

### 2.4 Commit vs. Rollback

#### Definition

Decides whether to save (`commit`) or undo (`rollback`) the transaction.

#### Why Use It?

Ensures atomicity based on success or failure.

#### Instructions

- Check `executeBatch()` results: `> 0` means success, `0` means failure.
- Use `cn.commit()` or `cn.rollback()` accordingly.

#### Example

```java
int[] res = st.executeBatch();
boolean flag = false;
for (int i : res) {
    if (i == 0) flag = true;
}
if (flag) cn.rollback(); else cn.commit();
```

### 2.5 Verifying Results

#### Definition

Displays the updated table to confirm transaction outcomes.

#### Why Use It?

Validates the transaction’s effect on the database.

#### Instructions

- Use `st.executeQuery()` to fetch and print results.

#### Example

```java
ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNT");
while (rs.next()) {
    System.out.println(rs.getInt(1) + "\t" + rs.getDouble(2));
}
```

---

## 3. Practical Demonstration

### 3.1 Setting Up the Database

- **Database**: Replace `<DATABASE_NAME>` with your database (e.g., `sampledb`).
- **Target Table**: `ACCOUNT`
  ```sql
  CREATE TABLE ACCOUNT (
      account_number INT PRIMARY KEY,
      balance DOUBLE,
      account_type VARCHAR(20)
  );
  INSERT INTO ACCOUNT VALUES
      (111, 35000, 'Savings'),
      (333, 40000, 'Checking'),
      (444, 40000, 'Savings');
  ```
- **Verification**:
  ```sql
  SELECT * FROM ACCOUNT; -- Shows initial rows
  ```

### 3.2 Managing Transactions in NetBeans

- **Project**: `JDBCDemo` (assumed setup).
- **Driver**: `mysql-connector-j-8.1.0.jar` in Libraries.
- **Code**: See [5.3 Complete Code Reference](#53-complete-code-reference).
- **Output** (Example: Transfer `5000` from `111` to `333`):

```
#Connection Established
Enter Source Account: 111
Enter Destination Account: 333
Enter Transfer Amount: 5000
#Transaction Successfull --Commitng Changes
111	30000.0	Savings
333	45000.0	Checking
444	40000.0	Savings
```

- **Output** (Failure: Transfer `25000` from `444` to `123`):

```
#Connection Established
Enter Source Account: 444
Enter Destination Account: 123
Enter Amount: 25000
#Transaction Failed --Rolling Back
111	30000.0	Savings
333	45000.0	Checking
444	40000.0	Savings
```

> [!NOTE]
> Invalid accounts (e.g., `123`) trigger rollback, leaving balances unchanged.

#### Verification

```sql
SELECT * FROM ACCOUNT; -- Matches output
```

---

## 4. Practical Application

### 4.1 Best Practices for Transaction Management

- Always set `setAutoCommit(false)` before batch operations.
- Use batch processing for multiple queries in a transaction.
- Check all batch results (`> 0`) before committing.
- Close `Connection` and `Scanner` to free resources.

### 4.2 Common Mistakes to Avoid

- **Forgetting Auto-Commit**: Default `true` commits partial changes.
- **Missing Batch**: Individual `executeUpdate()` lacks atomicity.
- **Ignoring Results**: Not checking `res[]` risks silent failures.
- **Invalid Accounts**: Non-existent IDs (e.g., `123`) fail silently without rollback logic.

### 4.3 Hands-On Exercises

1. **Transfer**: Move `10000` between valid accounts, verify commit.
2. **Rollback**: Attempt a transfer to an invalid account, check rollback.
3. **Multi-Step**: Add a third operation (e.g., log transfer), test atomicity.
4. **User Input**: Use `Scanner` for dynamic amounts and accounts.
5. **Complex Transaction**: Simulate a payroll (deduct from one, distribute to multiple).

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

- **JDBC Transactions**: [https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html](https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html)
- **MySQL TCL**: [https://dev.mysql.com/doc/refman/8.0/en/commit.html](https://dev.mysql.com/doc/refman/8.0/en/commit.html)

### 5.2 Summary of Key Takeaways

This guide demonstrates transaction management:

- **Setup**: Disabled auto-commit, used batch for withdraw/deposit.
- **Success**: Transferred `5000` from `111` to `333`, committed.
- **Failure**: Attempted transfer to `123`, rolled back.
- **Key Insight**: Batch results (`int[]`) determine `commit` or `rollback`.

> [!TIP]
> Test edge cases (e.g., insufficient funds) to enhance rollback logic.

### 5.3 Complete Code Reference

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionManagement {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        // ------------- Step 1: Loading Drivers -------------
        Class.forName("com.mysql.cj.jdbc.Driver");

        // ------------- Step 2: Establishing Connections-------------
        String url = "jdbc:mysql://localhost:3306/<DATABASE_NAME>";
        String uname = "<USERNAME>";
        String pwd = "<PASSWORD>";
        Connection cn = DriverManager.getConnection(url, uname, pwd);

        // ------------- Step 3: Checking Connections-------------
        if (cn != null) {
            System.out.println("#Connection Established");
        } else {
            System.out.println("#Connection Failure");
        }

        // ------------- Step 4: Setting AUTOCOMMIT = FALSE-------------
        cn.setAutoCommit(false);
        System.out.println("Enter Source Account: ");
        int src = in.nextInt();
        System.out.println("Enter Destination Account: ");
        int dst = in.nextInt();
        System.out.println("Enter Transfer Amount: ");
        double amount = in.nextDouble();

        // ------------- Step 5: Atomic TCL Transfer -------------
        Statement st = cn.createStatement();
        String withdraw = "UPDATE ACCOUNT SET balance = balance - " + amount + " WHERE account_number = " + src;
        st.addBatch(withdraw);
        String deposit = "UPDATE ACCOUNT SET balance = balance + " + amount + " WHERE account_number = " + dst;
        st.addBatch(deposit);

        // Execute the batch
        int[] res = st.executeBatch();

        // Logic for commit and rollback
        boolean flag = false;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 0) {
                flag = true; // Query failed, need to rollback
                break;
            }
        }

        if (flag) {
            cn.rollback();
            System.out.println("#Transaction Failed --Rolling Back");
        } else {
            cn.commit();
            System.out.println("#Transaction Successfull --Commitng Changes");
        }

        // ------------- Step 6: Displaying Final Table-------------
        ResultSet rs = st.executeQuery("SELECT * FROM ACCOUNT");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getDouble(2) + "\t" + rs.getString(3));
        }

        // ------------- Final Step: Closing Connections-------------
        cn.close();
        in.close();
    }
}
```

---

_This README provides an in-depth illustration of Transaction Management using JDBC_

---
