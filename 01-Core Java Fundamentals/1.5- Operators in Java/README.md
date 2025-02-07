# 1.5: Operators in Java

## Topics Covered

Java provides a variety of operators to perform different operations. These operators are categorized into several types based on their functionality.

---

## Table of Contents

1. [Arithmetic Operators](#arithmetic-operators)
2. [Relational Operators](#relational-operators)
3. [Logical Operators](#logical-operators)
4. [Bitwise Operators](#bitwise-operators)
5. [Assignment Operators](#assignment-operators)
6. [Unary Operators](#unary-operators)
7. [Ternary Operator](#ternary-operator)
8. [Shift Operators](#shift-operators)
9. [Operator Precedence](#operator-precedence)
10. [Additional Resources](#additional-resources)

---

## Arithmetic Operators

Arithmetic operators are used to perform mathematical operations such as addition, subtraction, multiplication, division, and modulus.

| Operator | Description    | Example (a = 10, b = 5) |
|----------|--------------|-------------------------|
| `+`      | Addition      | `a + b = 15`           |
| `-`      | Subtraction   | `a - b = 5`            |
| `*`      | Multiplication| `a * b = 50`           |
| `/`      | Division      | `a / b = 2`            |
| `%`      | Modulus       | `a % b = 0`            |

```java
int a = 10, b = 5;
System.out.println(a + b);  // Output: 15
System.out.println(a - b);  // Output: 5
System.out.println(a * b);  // Output: 50
System.out.println(a / b);  // Output: 2
System.out.println(a % b);  // Output: 0
```

---

## Relational Operators

Relational operators compare two values and return a boolean result.

| Operator | Description       | Example (a = 10, b = 5) |
|----------|-----------------|-------------------------|
| `>`      | Greater than     | `a > b` → `true`       |
| `<`      | Less than        | `a < b` → `false`      |
| `>=`     | Greater or equal | `a >= b` → `true`      |
| `<=`     | Less or equal    | `a <= b` → `false`     |
| `==`     | Equal to         | `a == b` → `false`     |
| `!=`     | Not equal to     | `a != b` → `true`      |

```java
System.out.println(a > b);  // Output: true
System.out.println(a < b);  // Output: false
System.out.println(a == b); // Output: false
System.out.println(a != b); // Output: true
```

---

## Logical Operators

Logical operators operate on boolean values to combine conditions.

| Operator | Description   | Example (x = true, y = false) |
|----------|-------------|--------------------------------|
| `&&`     | AND         | `x && y` → `false`           |
| `||`     | OR          | `x || y` → `true`            |
| `!`      | NOT         | `!x` → `false`               |

```java
boolean x = true, y = false;
System.out.println(x && y);  // Output: false
System.out.println(x || y);  // Output: true
System.out.println(!x);      // Output: false
```

---

## Bitwise Operators

Bitwise operators perform operations on individual bits of numbers.

| Operator | Description    | Example (c = 6, d = 3) | Binary Representation |
|----------|--------------|----------------------|---------------------|
| `&`      | AND          | `c & d = 2`          | `110 & 011 = 010`  |
| `|`      | OR           | `c | d = 7`          | `110 | 011 = 111`  |
| `^`      | XOR          | `c ^ d = 5`          | `110 ^ 011 = 101`  |
| `~`      | Complement   | `~c = -7`            | `~110 = 001`       |

```java
int c = 6, d = 3;
System.out.println(c & d);  // Output: 2
System.out.println(c | d);  // Output: 7
System.out.println(c ^ d);  // Output: 5
System.out.println(~c);     // Output: -7
```

---

## Assignment Operators

Assignment operators assign values to variables and can combine with arithmetic operators.

```java
int e = 10;
e += 5; // e = e + 5
System.out.println(e); // Output: 15
e *= 2; // e = e * 2
System.out.println(e); // Output: 30
```

---

## Unary Operators

Unary operators operate on a single operand.

```java
int f = 5;
System.out.println(++f);  // Output: 6
System.out.println(f--);  // Output: 6 (f becomes 5)
```

---

## Ternary Operator

The ternary operator is a shorthand for if-else conditions.

```java
int result = (a > b) ? a : b;
System.out.println(result);  // Output: 10
```

---

## Shift Operators

Shift operators move bits left or right in a number.

| Operator | Description             | Example (c = 6) | Binary Representation |
|----------|------------------------|----------------|---------------------|
| `<<`     | Left shift              | `c << 1 = 12`  | `110 << 1 = 1100`  |
| `>>`     | Right shift             | `c >> 1 = 3`   | `110 >> 1 = 011`   |
| `>>>`    | Unsigned right shift    | `c >>> 1 = 3`  | `110 >>> 1 = 011`  |

```java
System.out.println(c << 1);  // Output: 12
System.out.println(c >> 1);  // Output: 3
```

---

## Operator Precedence

Operator precedence determines the order in which operations are performed.

| Operator Type       | Operators                 |
|---------------------|--------------------------|
| Postfix            | `expr++`, `expr--`        |
| Unary              | `+`, `-`, `++`, `--`, `!` |
| Multiplicative     | `*`, `/`, `%`             |
| Additive           | `+`, `-`                  |
| Relational         | `>`, `<`, `>=`, `<=`      |
| Equality           | `==`, `!=`                |
| Logical AND        | `&&`                      |
| Logical OR         | `||`                      |
| Ternary            | `?:`                      |
| Assignment         | `=`, `+=`, `-=`           |

---

## Additional Resources

- [Official Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials - Oracle](https://docs.oracle.com/javase/tutorial/)

