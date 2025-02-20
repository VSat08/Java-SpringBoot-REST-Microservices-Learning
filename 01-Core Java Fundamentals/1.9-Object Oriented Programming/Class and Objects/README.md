# 1.9 Object-Oriented Programming (OOP) Concepts

## Table of Contents
1. [Introduction](#1-introduction)
2. [Object & Class](#2-object--class)
3. [OOP Principles](#3-oop-principles)
4. [OOP in Java](#4-oop-in-java) 
5. [Class Members](#5-class-members)
6. [Constructors & Constructor Overloading](#6-constructors--constructor-overloading)
7. [Static Block & Static Methods](#7-static-block--static-methods)
8. [Java Code Examples](#8-java-code-examples)
9. [Advantages of OOP](#9-advantages-of-oop)
10. [Summary](#10-summary)
11. [Resources](#11-resources)

---

## 1. Introduction
Object-Oriented Programming (OOP) is a programming paradigm that organizes software design around objects rather than functions and logic. It helps in modular, reusable, and scalable code development.

---

## 2. Object & Class

| Concept  | Description |
|----------|------------|
| **Object** | A real-world entity with state, behavior, and identity. Example: A student with roll number, name, GPA, and city. |
| **State** | The data or attributes an object possesses. Example: Roll No: 101, Name: ABC, GPA: 8.9, City: Hyderabad. |
| **Behavior** | Methods that allow interaction with object data. Example: `getGPA()`, `updateGPA()`, `getStudentData()`. |
| **Identity** | A unique identifier distinguishing one object from another. Example: S1, S2, S3 for different students. |
| **Class** | A blueprint or template to create objects. Defines properties and behaviors but does not store actual data. Example: "Student" class. |

---

## 3. OOP Principles

| Principle | Description | Real-World Example |
|-----------|-------------|---------------------|
| **Encapsulation** | Wrapping data (attributes) and methods (behaviors) in a single unit (class). Restricts direct access to data. | In a bank system, account balance is private and accessed via `getBalance()` method. |
| **Inheritance** | A class (child) acquires properties and behaviors from another class (parent). Promotes code reuse. | Parent class: `Vehicle` → Child classes: `Car`, `Bike`. `Car` & `Bike` inherit `speed`, `fuelType` from `Vehicle`. |
| **Polymorphism** | "Many forms" - same method behaves differently for different objects. | `speak()` method: `Cat`: "Meow", `Dog`: "Bark", `Duck`: "Quack". |
| **Abstraction** | Hiding implementation details and showing only essential features. Achieved via abstract classes and interfaces. | ATM Machine: We see options (Withdraw, Deposit), but internal processing (database updates, security checks) is hidden. |

---

## 4. OOP in Java

| Feature | Implementation in Java |
|---------|-------------------------|
| **Encapsulation** | Private attributes + public getter/setter methods. |
| **Inheritance** | `class Car extends Vehicle` (Car inherits Vehicle properties). |
| **Polymorphism** | Method Overloading (same method, different parameters) & Method Overriding (same method in parent and child class, but different behavior). |
| **Abstraction** | Implemented via Abstract Classes (`abstract class Vehicle`) and Interfaces (`interface Engine`). |

---

## 5. Class Members

### 5.1 Instance Variables (Non-Static)
- Variables declared inside a class but outside a method.
- They are unique for each object.
- Stored in the heap memory.

### 5.2 Class Variables (Static)
- Declared using the `static` keyword.
- Shared among all instances of a class.
- Stored in the class area (method area).

---

## 6. Constructors & Constructor Overloading

| Type | Description |
|------|-------------|
| **Default Constructor** | A constructor with no parameters. |
| **Parameterized Constructor** | A constructor with parameters to initialize instance variables. |
| **Constructor Overloading** | Defining multiple constructors in the same class with different parameters. |

---

## 7. Static Block & Static Methods

### 7.1 Static Block
- A block of code that runs **once** when the class is loaded.
- Used for static variable initialization.
- Executed **before** the `main()` method.

### 7.2 Static Methods
- Declared using the `static` keyword.
- Can only access static variables and other static methods.

Example:
```java
class Example {
    static int count;
    static {
        count = 100;
        System.out.println("Static block executed!");
    }
}
```

---

## 8. Java Code Examples

### 8.1 Encapsulation Example
```java
class Student {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

### 8.2 Inheritance Example
```java
class Animal {
    void makeSound() { System.out.println("Animal makes a sound"); }
}

class Dog extends Animal {
    void makeSound() { System.out.println("Dog barks"); }
}
```

### 8.3 Polymorphism Example
```java
class Shape {
    void draw() { System.out.println("Drawing a shape"); }
}

class Circle extends Shape {
    void draw() { System.out.println("Drawing a circle"); }
}
```

### 8.4 Abstraction Example
```java
abstract class Vehicle {
    abstract void start();
}

class Car extends Vehicle {
    void start() { System.out.println("Car is starting"); }
}
```

---

## 9. Advantages of OOP

| Benefit | Description |
|---------|-------------|
| **Modularity** | Code is organized into self-contained objects. |
| **Reusability** | Existing code can be reused via inheritance. |
| **Scalability** | Large projects can be managed effectively. |
| **Security** | Data is protected via encapsulation. |
| **Maintainability** | Code is easier to update and manage. |

---

## 10. Summary
- **Objects**: Have state, behavior, and identity.
- **Classes**: Logical blueprint to create objects.
- **Encapsulation**: Data hiding using private variables and public methods.
- **Inheritance**: Code reuse by acquiring properties from another class.
- **Polymorphism**: Same method behaving differently in different classes.
- **Abstraction**: Hiding implementation details using abstract classes and interfaces.

---

## 11. Resources
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Java OOP Concepts - GeeksForGeeks](https://www.geeksforgeeks.org/java-oop-concepts/)
- [Java Tutorials - W3Schools](https://www.w3schools.com/java/)
