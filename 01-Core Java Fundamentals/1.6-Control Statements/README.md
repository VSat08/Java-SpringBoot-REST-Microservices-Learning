# 1.6: Control Flow Statements in Java

## Introduction

Control flow statements in Java determine the order in which instructions are executed in a program. These statements allow a program to make decisions, repeat tasks, and handle conditions efficiently. Without control flow, a program would execute sequentially from top to bottom without any logic or decision-making.

Control flow statements are divided into three main types:

1. **Decision-Making Statements**: Used to execute different blocks of code based on conditions (`if`, `if-else`, `if-else-if ladder`, `switch`).
2. **Looping Statements**: Used to execute a block of code multiple times (`for`, `while`, `do-while`).
3. **Branching Statements**: Used to change the flow of execution (`break`, `continue`, `return`).

---

## Table of Contents

1. [Decision-Making Statements](#decision-making-statements)
   - [if Statement](#1-if-statement)
   - [if-else Statement](#2-if-else-statement)
   - [Nested if Statement](#3-nested-if-statement)
   - [if-else-if Ladder](#4-if-else-if-ladder)
   - [Switch Statement](#5-switch-statement)
2. [Looping Statements](#looping-statements)
   - [for Loop](#1-for-loop)
   - [while Loop](#2-while-loop)
   - [do-while Loop](#3-do-while-loop)
3. [Branching Statements](#branching-statements)
   - [break Statement](#1-break-statement)
   - [continue Statement](#2-continue-statement)
   - [return Statement](#3-return-statement)
4. [Common Use Cases and Best Practices](#common-use-cases-and-best-practices)
5. [Additional Resources](#additional-resources)

---

## Decision-Making Statements

### 1. if Statement

**Definition**: The `if` statement executes a block of code only if a specified condition is `true`.

**Syntax:**
```java
if (condition) {
    // Code to execute if condition is true
}
```

**Example:**
```java
int num = 10;
if (num > 0) {
    System.out.println("Positive number");
}
```

### 2. if-else Statement

**Definition**: Executes one block of code if the condition is `true` and another if it is `false`.

**Example:**
```java
int num = -10;
if (num > 0) {
    System.out.println("Positive number");
} else {
    System.out.println("Negative number");
}
```

### 3. Nested if Statement

**Definition**: An `if` statement inside another `if` statement.

**Example:**
```java
int num = 10;
if (num > 0) {
    if (num % 2 == 0) {
        System.out.println("Positive even number");
    }
}
```

### 4. if-else-if Ladder

**Definition**: Used when multiple conditions need to be tested sequentially.

**Example:**
```java
int marks = 85;
if (marks >= 85) {
    System.out.println("Grade: A+");
} else if (marks >= 70) {
    System.out.println("Grade: A");
} else {
    System.out.println("FAIL");
}
```

### 5. Switch Statement

**Definition**: The `switch` statement allows a variable to be tested against multiple values.

**Example:**
```java
int day = 2;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    default:
        System.out.println("Invalid day");
}
```

---

## Looping Statements

### 1. for Loop

**Definition**: A loop that runs a fixed number of times.

**Example:**
```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Iteration: " + i);
}
```

### 2. while Loop

**Definition**: A loop that executes while the condition is `true`.

**Example:**
```java
int i = 1;
while (i <= 5) {
    System.out.println("Iteration: " + i);
    i++;
}
```

### 3. do-while Loop

**Definition**: A loop that executes at least once before checking the condition.

**Example:**
```java
int i = 1;
do {
    System.out.println("Iteration: " + i);
    i++;
} while (i <= 5);
```

---

## Branching Statements

### 1. break Statement

**Definition**: Used to exit from a loop or switch statement.

**Example:**
```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        break;
    }
    System.out.println(i);
}
```

### 2. continue Statement

**Definition**: Skips the current iteration and proceeds to the next iteration.

**Example:**
```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;
    }
    System.out.println(i);
}
```

### 3. return Statement

**Definition**: Exits from a method and optionally returns a value.

**Example:**
```java
public static void main(String[] args) {
    System.out.println("Before return");
    return;
}
```

---

## Common Use Cases and Best Practices

### When to Use Decision-Making Statements
- Use `if` for simple conditions.
- Use `if-else` when there are two possible outcomes.
- Use `switch` for multiple fixed-value conditions.

### When to Use Loops
- Use `for` when the number of iterations is known.
- Use `while` for unknown iterations based on a condition.
- Use `do-while` when at least one iteration must execute.

### Best Practices
- Avoid infinite loops by ensuring loop conditions will eventually be false.
- Use `break` carefully to avoid unintended exits.
- Optimize switch cases using `default` to handle unexpected values.

---

## Additional Resources

- [Official Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials - Oracle](https://docs.oracle.com/javase/tutorial/)
