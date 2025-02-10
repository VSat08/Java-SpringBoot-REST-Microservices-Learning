# 1.8 Java Methods

## Table of Contents
1. [Introduction to Methods](#introduction-to-methods)
2. [Method Structure](#method-structure)
3. [Method Parameters and Arguments](#method-parameters-and-arguments)
4. [Return Types](#return-types)
5. [Method Overloading](#method-overloading)
6. [Method Overriding](#method-overriding)
7. [Static vs Instance Methods](#static-vs-instance-methods)
8. [Variable Arguments (Varargs)](#variable-arguments-varargs)
9. [Recursion in Java](#recursion-in-java)
10. [Best Practices](#best-practices)
11. [Common Mistakes](#common-mistakes)
12. [Exercises](#exercises)
13. [Resources](#resources)

---

## 1. Introduction to Methods
A **method** in Java is a reusable block of code that performs a specific task. It helps in **code reusability**, **modularity**, and **better readability**.

### Why Use Methods?
- **Promotes Code Reusability** - Avoids repetition of code.
- **Improves Readability** - Code is easier to understand.
- **Modular Programming** - Methods make it easier to debug and maintain code.
- **Parameterization** - Allows passing dynamic values.

### Key Terminologies
- **Method Signature**: The combination of method name and parameters.
- **Parameters**: Inputs declared in the method definition.
- **Arguments**: Actual values passed when calling the method.
- **Return Type**: The data type of the value returned by the method (or `void` if nothing is returned).

---

## 2. Method Structure
### Basic Syntax
```java
accessModifier returnType methodName(parameters) {
    // Method body
    return value; // Omit if void
}
```

### Example
```java
public static int sumRange(int start, int end) {
    int total = 0;
    for (int i = start; i <= end; i++) {
        total += i;
    }
    return total;
}
```

### Components Explained
| Component | Description |
|-----------|-------------|
| **Access Modifier** | `public`, `private`, `protected`, or default |
| **Static** | If present, can be called without object creation |
| **Return Type** | The type of value returned (e.g., `int`, `double`) |
| **Parameters** | Input variables |
| **Method Body** | Contains logic to execute |
| **Return Statement** | Returns a value (if applicable) |

---

## 3. Method Parameters and Arguments
### Formal vs Actual Parameters
| **Formal Parameters** | **Actual Parameters** |
|----------------|----------------|
| Declared in method signature | Values passed when calling the method |
| Act as local variables | Determine method behavior |

### Example
```java
public static void printSum(int x, int y) {
    System.out.println(x + y);
}

printSum(5, 3); // Output: 8
```

---

## 4. Return Types
### Void Methods
```java
public static void greet(String name) {
    System.out.println("Hello, " + name + "!");
}
```
### Value-Returning Methods
```java
public static double calculateCircleArea(double radius) {
    return Math.PI * radius * radius;
}
```
### Rules
- The **return type** must match the declared type.
- Return statements **must be included** in non-void methods.
- **Execution stops** after `return` is encountered.

---

## 5. Method Overloading
Method overloading allows multiple methods with the same name but different parameters.

```java
public static int multiply(int a, int b) {
    return a * b;
}

public static double multiply(double a, double b, double c) {
    return a * b * c;
}
```

---

## 6. Method Overriding
Method overriding allows a subclass to provide a different implementation of a method inherited from its parent class.

```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

---

## 7. Static vs Instance Methods
| Feature | Static Methods | Instance Methods |
|---------|---------------|------------------|
| Called on | Class | Object |
| Uses `this` | No | Yes |
| Can access instance variables | No | Yes |

Example:
```java
class Example {
    static void staticMethod() { System.out.println("Static method"); }
    void instanceMethod() { System.out.println("Instance method"); }
}
```

---

## 8. Variable Arguments (Varargs)
Allows passing a variable number of arguments.
```java
public static int sumAll(int... numbers) {
    int total = 0;
    for (int num : numbers) {
        total += num;
    }
    return total;
}
```

### Rules:
- Must be the last parameter.
- Only one varargs per method.

---

## 9. Recursion in Java
Recursion is a technique where a method calls itself.

```java
public static int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```

---

## 10. Best Practices
- **Single Responsibility**: Each method should have one purpose.
- **Descriptive Naming**: e.g., `calculateTax()` vs `doStuff()`.
- **Limit Parameters**: Keep it under 3 if possible.
- **Use Javadoc Comments**:
```java
/**
 * Calculates factorial of a number.
 * @param n Non-negative integer
 * @return Factorial of n
 */
public static long factorial(int n) { ... }
```

---

## 11. Common Mistakes
### Forgetting Return Statements
```java
// Error: Missing return
public static int add(int a, int b) {
    int sum = a + b;
}
```
### Parameter Mismatch
```java
// Method signature: printSum(int, int)
printSum(5); // Error: Wrong argument count
```

---

## 12. Exercises
- **Basic Method**: Implement `isPrime(int n)`.
- **Varargs Challenge**: Implement `joinStrings(String... words)`.
- **Method Overloading**: Create `multiply(int a, int b)` and `multiply(double a, double b, double c)`.
- **Recursion**: Implement a method to calculate Fibonacci numbers.

---

## 13. Resources
- [Oracle Java Methods Documentation](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- *Effective Java* by Joshua Bloch

---

"Methods are the verbs of object-oriented programming – they define actions your objects can perform." – *Joshua Bloch*

