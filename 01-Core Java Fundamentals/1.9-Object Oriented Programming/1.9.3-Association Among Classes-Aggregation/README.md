# Mastering Java + Spring Boot: REST APIs and Microservices  
## Lecture 31: Association Among Classes - Aggregation  

## Overview  
This lecture covers the concept of association among classes in Java, specifically focusing on **aggregation** and **composition**. Association is a **structural relationship** between classes, and it can be categorized into two types:

- **Aggregation:** A *"has-a"* relationship where the part can exist independently of the whole.
- **Composition:** A *stronger "has-a"* relationship where the part cannot exist without the whole.

This repository includes example code demonstrating **aggregation** between two classes: `Customer` and `Account`.

---

## Table of Contents  
1. [Key Concepts](#key-concepts)
2. [Code Explanation](#code-explanation)
3. [File Structure](#file-structure)
4. [How to Run](#how-to-run)
5. [Examples](#examples)
6. [Conclusion](#conclusion)

---

## Key Concepts  
### **Association**  
- A **structural relationship** between classes.
- No inherent meaning or semantics (e.g., a pen and a book on a table).
- Two forms: **Aggregation** and **Composition**.

### **Aggregation**  
- A *"has-a"* relationship where the part **can exist independently** of the whole.
- Example: A college has departments, but **departments can exist without** the college.

### **Composition**  
- A *stronger "has-a"* relationship where the part **cannot exist without** the whole.
- Example: A car has an engine, and **without the engine, the car cannot function**.

---

## Code Explanation  
### **Example: Aggregation Between Customer and Account**  
- `Customer` has an `Account`, but the `Account` **can exist independently** of the `Customer`.
- The `Customer` class **uses a reference** to the `Account` class to establish this relationship.

---

## File Structure  
```plaintext
1.9.3-Association Among Classes-Aggregation/
├── Account.java
├── Customer.java
```

---

## How to Run  
1. Navigate to the folder and open it in the terminal.
    ```bash
    cd "Java-SpringBoot-REST-Microservices-Learning/01-Core Java Fundamentals/1.9-Object Oriented Programming/1.9.3-Association Among Classes-Aggregation"

    ``` 

2. Compile the Java files:
   ```bash
   javac Customer.java
   ```
3. Run the `Customer` class:
   ```bash
   java Customer
   ```

---

## Examples  
### **Account.java**  
```java
// Account.java
public class Account {
    // Private instance variables
    private String accountNumber;
    private String accountType;
    private double accountBalance;

    // Constructor
    public Account(String accountNumber, String accountType, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    // Method to display account details
    public void getAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Account Balance: " + accountBalance);
    }
}
```

### **Customer.java**  
```java
// Customer.java
public class Customer {
    // Instance variables
    private int customerId;
    private String customerName;
    private static String bank = "SBI"; // Static variable
    private Account account; // Aggregation: Customer has an Account

    // Constructor
    public Customer(int customerId, String customerName, Account account) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.account = account;
    }

    // Method to display customer details
    public void display() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Bank: " + bank);
        account.getAccount(); // Display account details
    }

    // Main method
    public static void main(String[] args) {
        // Create an Account object
        Account account = new Account("12345", "Savings", 100000);

        // Create a Customer object
        Customer customer = new Customer(1122, "ABC", account);

        // Display customer details
        customer.display();
    }
}
```

---

## Output  
When you run the `Customer` class, the output will be:
```plaintext
Customer ID: 1122
Customer Name: ABC
Bank: SBI
Account Number: 12345
Account Type: Savings
Account Balance: 100000.0
```

---

## Conclusion  
- **Aggregation** is a *"has-a"* relationship where the part **can exist independently** of the whole.
- In this example, the `Customer` class **uses an `Account` object** to demonstrate aggregation.
- This concept is useful for **modeling real-world relationships** in object-oriented programming.

---