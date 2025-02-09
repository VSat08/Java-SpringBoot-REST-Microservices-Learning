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
   - [if Statement](#if-statement)
   - [if-else Statement](#if-else-statement)
   - [Nested if Statement](#nested-if-statement)
   - [if-else-if Ladder](#if-else-if-ladder)
   - [Switch Statement](#switch-statement)
2. [Looping Statements](#looping-statements)
   - [for Loop](#for-loop)
   - [while Loop](#while-loop)
   - [do-while Loop](#do-while-loop)
3. [Branching Statements](#branching-statements)
   - [break Statement](#break-statement)
   - [continue Statement](#continue-statement)
   - [return Statement](#return-statement)
4. [Additional Resources](#additional-resources)

---

## Decision-Making Statements

Decision-making statements allow a program to execute different code based on conditions. These statements help the program make logical choices.

### 1. if Statement

**Definition**: The `if` statement executes a block of code only if a specified condition is `true`.

**Usage**: Used when we want to execute a statement only when a certain condition holds true.

```java
int num = 10;
if (num > 0) {
    System.out.println("Positive number");
}
```

### 2. if-else Statement

**Definition**: Executes one block of code if the condition is `true` and another if it is `false`.

**Usage**: Used when we need to choose between two possible outcomes.

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

**Usage**: Used when multiple conditions need to be checked in a hierarchy.

```java
int num = 10;
if (num > 0) {
    if (num % 2 == 0) {
        System.out.println("Positive even number");
    }
}
```

### 4. if-else-if Ladder

**Definition**: Used when we need to test multiple conditions one after another.

**Usage**: Helps in situations where more than two conditions need to be evaluated.

```java
int marks = 85;
if (marks >= 85) {
    System.out.println("Grade: A+");
} else if (marks >= 70) {
    System.out.println("Grade: A");
} else if (marks >= 60) {
    System.out.println("Grade: B");
} else if (marks >= 50) {
    System.out.println("Grade: C");
} else if (marks >= 40) {
    System.out.println("Grade: D");
} else {
    System.out.println("FAIL");
}
```

### 5. Switch Statement

**Definition**: The `switch` statement allows a variable to be tested for equality against multiple values.

**Usage**: Used when we need to execute one of many code blocks based on a single variable’s value.

```java
int day = 2;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    default:
        System.out.println("Invalid day");
}
```

---

## Looping Statements

Looping statements are used to execute a block of code multiple times.

### 1. for Loop

**Definition**: A loop that runs a fixed number of times.

**Usage**: Used when the number of iterations is known beforehand.

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Iteration: " + i);
}
```

### 2. while Loop

**Definition**: A loop that executes while the condition is true.

**Usage**: Used when the number of iterations is unknown and based on a condition.

```java
int i = 1;
while (i <= 5) {
    System.out.println("Iteration: " + i);
    i++;
}
```

### 3. do-while Loop

**Definition**: A loop that executes at least once and then repeats while the condition is `true`.

**Usage**: Ensures that the block runs at least once before checking the condition.

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

**Usage**: Helps in terminating a loop early when a condition is met.

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

**Usage**: Used when certain iterations need to be skipped.

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

**Usage**: Used to terminate execution of a function and return control to the caller.

```java
public static void main(String[] args) {
    System.out.println("Before return");
    return;
}
```

---

## Additional Resources

- [Official Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials - Oracle](https://docs.oracle.com/javase/tutorial/)
