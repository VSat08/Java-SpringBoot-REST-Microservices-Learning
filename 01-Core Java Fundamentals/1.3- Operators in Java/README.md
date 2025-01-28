# 1.3: Operators in Java

## Topics Covered

Java provides a variety of operators to perform different operations. These operators are categorized into several types based on their functionality.

### Arithmetic Operators

Arithmetic operators are used to perform basic mathematical operations such as addition, subtraction, multiplication, division, and modulus.

```java
int a = 10, b = 5;
System.out.println(a + b);  // Addition: 15
System.out.println(a - b);  // Subtraction: 5
System.out.println(a * b);  // Multiplication: 50
System.out.println(a / b);  // Division: 2
System.out.println(a % b);  // Modulus: 0
```

### Relational Operators

Relational operators are used to compare two values. They return a boolean value (true or false).

```java
System.out.println(a > b);  // Greater than: true
System.out.println(a < b);  // Less than: false
System.out.println(a == b); // Equal to: false
System.out.println(a != b); // Not equal to: true
```

### Logical Operators

Logical operators are used to combine two or more conditions and return a boolean value.

```java
boolean x = true, y = false;
System.out.println(x && y);  // AND: false
System.out.println(x || y);  // OR: true
System.out.println(!x);      // NOT: false
```

### Bitwise Operators

Bitwise operators perform operations on binary representations of numbers.

```java
int c = 6, d = 3; // Binary: c = 110, d = 011
System.out.println(c & d);  // AND: 2 (010)
System.out.println(c | d);  // OR: 7 (111)
System.out.println(c ^ d);  // XOR: 5 (101)
System.out.println(~c);     // Complement: -7
```

### Assignment Operators

Assignment operators are used to assign values to variables, often combining with arithmetic operations.

```java
int e = 10;
e += 5; // e = e + 5
System.out.println(e); // 15
e *= 2; // e = e * 2
System.out.println(e); // 30
```

### Unary Operators

Unary operators operate on a single operand. They include increment (++), decrement (--), and logical complement (!).

```java
int f = 5;
System.out.println(++f);  // Pre-increment: 6
System.out.println(f--);  // Post-decrement: 6 (f becomes 5)
```

### Ternary Operator

The ternary operator is a shorthand for if-else conditions. It has the syntax: condition ? value_if_true : value_if_false

```java
int a = 10, b = 5;
int result = (a > b) ? a : b;  // Returns the larger value
System.out.println(result);  // Output: 10
```

## Key Takeaways

-   Java operators are versatile and include arithmetic, relational, logical, bitwise, assignment, unary, and ternary operators.
-   Arithmetic operators are used for mathematical operations.
-   Relational operators compare two values and return a boolean.
-   Logical operators combine boolean conditions.
-   Unary operators modify a single operand's value.
-   Ternary operators provide a shorthand for simple conditional logic.
