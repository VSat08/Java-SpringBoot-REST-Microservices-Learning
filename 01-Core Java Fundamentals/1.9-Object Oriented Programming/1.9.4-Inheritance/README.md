# 1.9: Inheritance

## Introduction

Welcome to `Section 1.9: Concept of Inheritance`. This session introduces the fundamental concept of inheritance in Java, a core pillar of object-oriented programming (OOP). We’ll explore what inheritance is, how it works in Java, its advantages, and how to implement it with examples. In this expanded version, we’ll also cover advanced aspects from "Inheritance - Part 2" and "Inheritance - Part 3," including how a subclass can have its own members, redefine behaviors (overriding), introduce polymorphism, and utilize the `super` keyword and `@Override` annotation effectively.

---

### Table of Contents
- [Understanding Inheritance](#understanding-inheritance)
  - [What is Inheritance?](#what-is-inheritance)
    - [Definition of Inheritance](#definition-of-inheritance)
    - [Object-Oriented Principles](#object-oriented-principles)
    - [Real-World Analogy](#real-world-analogy)
    - [Terminology](#terminology)
  - [Advantages of Inheritance](#advantages-of-inheritance)
- [Inheritance in Java](#inheritance-in-java)
  - [Core Concepts](#core-concepts)
    - [Class Hierarchy](#class-hierarchy)
    - [Object Class as Superclass](#object-class-as-superclass)
    - [Identifying Inheritance](#identifying-inheritance)
    - [What Gets Inherited?](#what-gets-inherited)
  - [Specifying Inheritance](#specifying-inheritance)
    - [The `extends` Keyword](#the-extends-keyword)
    - [Example: Employee and TechnicalEmployee](#example-employee-and-technicalemployee)
  - [Advanced Features](#advanced-features)
    - [Child-Specific Members](#child-specific-members)
    - [Method Overriding](#method-overriding)
    - [Polymorphism](#polymorphism)
    - [The `super` Keyword](#the-super-keyword)
    - [The `@Override` Annotation](#the-override-annotation)
- [Practical Guidance](#practical-guidance)
  - [Best Practices](#best-practices)
  - [Common Pitfalls](#common-pitfalls)
  - [Practice Exercises](#practice-exercises)
- [Comparisons](#comparisons)
  - [Tabular Differentiation: Overloading vs. Overriding](#tabular-differentiation-overloading-vs-overriding)
- [Resources and Summary](#resources-and-summary)
  - [Additional Resources](#additional-resources)
  - [Summary](#summary)

---

## Understanding Inheritance

### What is Inheritance?

#### **Definition of Inheritance**
Inheritance is a mechanism in Java where one class acquires the properties (data members and methods) of another class. It requires at least two classes: a **parent class** (the class whose properties are inherited) and a **child class** (the class that inherits). As explored in "Inheritance - Part 2" and "Part 3," a subclass can not only inherit but also extend functionality by adding its own members, redefining inherited behaviors, and accessing parent constructors using the `super` keyword.

#### **Object-Oriented Principles**
Inheritance is one of the four key principles of OOP:
1. **Encapsulation** - Hiding data and exposing methods.
2. **Inheritance** - Acquiring properties from another class.
3. **Abstraction** - Simplifying complex systems.
4. **Polymorphism** - Allowing multiple forms of behavior.

Inheritance often works closely with polymorphism, which will be explored in later sessions. In "Inheritance - Part 2," we see polymorphism through method overriding, and in "Part 3," the `super` keyword enhances this by enabling access to parent class constructors and methods.

#### **Real-World Analogy**
Inheritance mirrors real-world relationships. For example, just as we inherit traits from our parents, a class in Java can inherit features from another class. This analogy extends to specialization: a technical employee inherits general employee traits but adds specific bonuses or behaviors unique to their role, with `super` ensuring proper initialization of inherited traits.

#### **Terminology**
- **Parent Class**: Also called **superclass** or **base class**, this is the class providing properties.
- **Child Class**: Also called **subclass** or **derived class**, this is the class inheriting properties.
These terms are synonymous: parent ↔ child, super ↔ sub, base ↔ derived. In advanced discussions from "Part 3," terms like "super keyword" (for accessing immediate parent class elements) and "override annotation" (for method redefinition) also emerge.

### Advantages of Inheritance
Inheritance provides several benefits:
1. **Code Reusability**: Reuse existing, tested code without rewriting it. For example, `TechnicalEmployee` reuses `Employee`’s `setEmp` and `disp` methods.
2. **Faster Development**: Build on existing classes for rapid application development.
3. **Code Sharing**: Share generalized classes across projects or teams. Overriding allows tailored functionality without altering the original class, and `super` ensures seamless integration with parent constructors.

---

## Inheritance in Java

### Core Concepts

#### **Class Hierarchy**
In Java, all classes form a hierarchy. At the top of this hierarchy is the **`Object` class**, which acts as the superclass for all Java classes. Every class, directly or indirectly, inherits from `Object`. In "Part 3," we see this hierarchy in action with `super` referring to the immediate parent class in a chain (e.g., A → B → C).

#### **Object Class as Superclass**
- Found in the `java.lang` package.
- Has no parent itself but serves as the parent to all other classes.
- Check the Java documentation for more details on `Object`.

#### **Identifying Inheritance**
Inheritance is identified by an **"is-a" relationship** (also called generalization-specialization):
- Examples:
  - A **student** *is a* **person**.
  - A **dog** *is an* **animal**.
  - A **technical employee** *is an* **employee**.
- This relationship indicates a hierarchy suitable for inheritance modeling.

#### **What Gets Inherited?**
- **Non-private members** of the parent class are inherited:
  - Data members (variables).
  - Methods.
  - Nested classes (if non-private).
  - Static blocks (if applicable).
- **Private members** and **constructors** are NOT inherited. However, as detailed in "Part 3," the `super` keyword allows access to parent constructors, ensuring child objects include parent data.

In "Inheritance - Part 2," a subclass like `TechnicalEmployee` introduces child-specific members (e.g., `bonus`) and overrides methods like `calSalary` or `disp`. In "Part 3," we extend this by using `super` to invoke constructors for proper initialization.

### Specifying Inheritance

#### **The `extends` Keyword**
- Java uses the `extends` keyword to specify inheritance.
- Syntax: `class ChildClass extends ParentClass`

#### **Example: Employee and TechnicalEmployee**

##### **Parent Class: Employee**
```java
public class Employee {
    private int empId;           // Private data member
    private String eName;        // Private data member
    private double basic;        // Private data member
    private static String org = "Google"; // Static data member

    // Parameterized constructor (added from Part 3)
    public Employee(int id, String name, double sal) {
        empId = id;
        eName = name;
        basic = sal;
    }

    // Getter method
    public void disp() {
        System.out.println(empId + " " + eName + " " + basic + " " + org);
    }

    // Calculate salary method
    public void calSalary() {
        double salary = basic + (basic * (42 + 30 + 8) / 100); // 80% allowances
        System.out.println("Total Salary: " + salary);
    }

    // Getter for basic (added for overriding example)
    public double getBasic() {
        return basic;
    }
}
```

##### **Child Class: TechnicalEmployee**
```java
public class TechnicalEmployee extends Employee {
    private int bonus; // Child-specific member

    // Constructor using super (added from Part 3)
    public TechnicalEmployee(int id, String name, double sal, int bonus) {
        super(id, name, sal); // Invoke parent constructor first
        this.bonus = bonus;   // Initialize child-specific member
    }

    // Overridden calSalary method
    @Override
    public void calSalary() {
        double salary = getBasic() + (getBasic() * (42 + 30 + 8) / 100) + bonus; // Include bonus
        System.out.println("Total Salary: " + salary);
    }

    // Overridden disp method
    @Override
    public void disp() {
        super.disp(); // Call parent’s disp method
        System.out.println("Bonus: " + bonus);
    }

    public static void main(String[] args) {
        TechnicalEmployee t1 = new TechnicalEmployee(1234, "Koti", 100000, 10000);
        t1.disp();      // Output: 1234 Koti 100000 Google / Bonus: 10000
        t1.calSalary(); // Output: Total Salary: 190000
    }
}
```

##### **Explanation**
- **Inheritance**: `TechnicalEmployee` extends `Employee`, inheriting its non-private members (`disp`, `calSalary`, `getBasic`).
- **Private Members**: `empId`, `eName`, and `basic` are private and not directly accessible, but can be initialized via the parent constructor using `super` and accessed via getters (e.g., `getBasic`).
- **Constructor Usage**: From "Part 3," `Employee` now has a parameterized constructor, and `TechnicalEmployee` uses `super(id, name, sal)` as the first statement to invoke it, ensuring parent data is constructed before child-specific `bonus`.
- **Child-Specific Members**: `TechnicalEmployee` adds a `bonus` variable, initialized in its constructor.
- **Overriding**: 
  - `calSalary` is redefined to include the `bonus`.
  - `disp` uses `super.disp()` to call the parent’s method and appends `bonus`, with `@Override` ensuring correctness.
- **Output**: 
  - `disp`: Shows employee details and bonus.
  - `calSalary`: Computes salary as 100,000 (basic) + 80,000 (80% allowances) + 10,000 (bonus) = 190,000.

### Advanced Features

#### **Child-Specific Members**
A subclass can introduce its own members beyond what it inherits. For example, `TechnicalEmployee` adds a `bonus` variable, initialized via its constructor in "Part 3," eliminating the need for a separate setter.

#### **Method Overriding**
Overriding allows a subclass to redefine a superclass method’s behavior while keeping the same signature. In the example:
- `calSalary` is overridden to include the `bonus`.
- `disp` is overridden to append the `bonus` using `super.disp()` for the parent’s functionality, with `@Override` validating the override.

#### **Polymorphism**
Polymorphism, a key OOP principle, manifests in inheritance through overriding. It allows methods to take multiple forms:
- **Overloading**: Same method name with different parameters (compile-time).
- **Overriding**: Same method redefined in a subclass (run-time).

#### **The `super` Keyword**
- **Definition**: `super` is a reference variable that refers to the immediate parent class in the inheritance hierarchy (e.g., A → B, `super` in A refers to B).
- **Purpose**: 
  - Access parent class variables (if non-private or via getters).
  - Invoke parent class methods (e.g., `super.disp()`).
  - Call parent class constructors (e.g., `super(id, name, sal)`).
- **Key Rules**:
  - When used to call a constructor (`super()` or `super(params)`), it must be the first statement in the child constructor.
  - Required when the parent class has a constructor, as default constructors are not inherited or supplied automatically.
  - Cannot coexist with `this()` in the same constructor (both compete for the first line).
  - Not usable in static contexts (refers to instance objects only).
- **Use Case**: Ensures parent data is initialized before child-specific members, critical for proper object construction in inheritance chains.

#### **The `@Override` Annotation**
- **Definition**: The `@Override` annotation indicates that a method in the child class is intended to override a parent class method.
- **Purpose**: 
  - Ensures the method signature matches the parent’s (name, parameters, return type).
  - Compiler warns if the method does not actually override (e.g., due to a typo like `cal salary` vs. `calSalary`).
- **Benefit**: Prevents subtle bugs where a method is treated as child-specific instead of overriding, as shown in "Part 3" when a typo led to incorrect salary calculation (1,80,000 vs. 1,90,000).
- **Example**:
  ```java
  // Incorrect override without @Override
  public void calsalary() { // Typo: small 's'
      // Treated as new method, not overriding
  }
  // Correct override with @Override
  @Override
  public void calSalary() { // Matches parent’s signature
      // Properly overrides
  }
  ```

---

## Practical Guidance

### Best Practices
1. Use inheritance to model clear "is-a" relationships.
2. Keep parent classes generalized and subclasses specialized.
3. Use `protected` visibility for members intended for inheritance, or provide getters for private members to maintain encapsulation.
4. Avoid overusing inheritance when composition ("has-a") is more appropriate.
5. When overriding, use the `@Override` annotation to ensure correctness and readability.
6. Leverage `super` to reuse parent class behavior in overridden methods and initialize parent data via constructors.
7. Always place `super()` or `super(params)` as the first statement in a child constructor when the parent has a constructor.

### Common Pitfalls
- Misidentifying relationships (e.g., using inheritance instead of association).
- Attempting to access private members directly in the subclass—use getters or `protected` instead.
- Overlooking the fact that constructors are not inherited, requiring `super` for access.
- Changing method signatures during overriding (signatures must match exactly).
- Not using `super` when needing parent class behavior in overridden methods or constructors.
- Omitting `@Override`, leading to unintended child-specific methods instead of overrides.
- Placing `super()` or `this()` incorrectly (must be first in constructor).

### Practice Exercises
1. Create a `Person` class with attributes like `name` and `age`, and a method to display them, using a parameterized constructor.
2. Extend `Person` into a `Student` class, adding a `studentId` attribute, and use `super` in its constructor to initialize `name` and `age`.
3. Test the inheritance by creating a `Student` object and calling inherited and new methods.
4. Add a `calculateScore` method to `Person` and override it in `Student` to include an extra credit bonus, using `@Override`.
5. Override the display method in `Student` to include `studentId` along with inherited details, using `super.disp()`.

---

## Comparisons

### Tabular Differentiation: Overloading vs. Overriding
Below is a detailed comparison between **overloading** and **overriding**, two key aspects of polymorphism in Java, with explanations and use cases.

| **Aspect**                | **Overloading**                                                                 | **Overriding**                                                                 |
|---------------------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| **Definition**            | Multiple methods with the same name but different parameters (number, type, or order) within the same class. | A subclass redefines a superclass method with the same name, return type, and parameters. |
| **Scope**                 | Occurs within a single class.                                                  | Occurs across classes (requires inheritance).                                  |
| **Method Signature**      | Must differ (different parameters: number, type, or order).                    | Must be identical (same name, return type, and parameters).                    |
| **Inheritance Required?** | No, can be done without inheritance.                                           | Yes, requires a parent-child class relationship via `extends`.                 |
| **Purpose**               | Provides flexibility to call the same method with different inputs.            | Customizes or extends the behavior of an inherited method.                     |
| **Polymorphism Type**     | Compile-time (static) polymorphism—resolved during compilation.                | Run-time (dynamic) polymorphism—resolved during execution.                     |
| **Annotation**            | No specific annotation required.                                               | Uses `@Override` to indicate intent and catch errors.                          |
| **Example**               | `add(int a, int b)` and `add(double a, double b)`                              | Parent: `disp()`; Child: `@Override disp()` with added behavior.               |
| **Access Modifiers**      | Can vary freely (e.g., `public`, `private`).                                   | Child method cannot reduce visibility (e.g., `public` cannot become `private`).|
| **Return Type**           | Can differ (covariant returns not required).                                   | Must match or be a subtype (covariant returns allowed since Java 5).           |

#### **Detailed Explanation**

##### **Overloading**
- **What It Is**: Overloading allows a class to have multiple methods with the same name but different parameter lists. The compiler decides which method to call based on the arguments provided (compile-time resolution).
- **Key Characteristics**:
  - Happens within a single class.
  - Method signatures must differ (e.g., `add(int, int)` vs. `add(double, double)`).
  - Return type or access modifier doesn’t affect overloading decisions.
- **Use Case**:
  ```java
  public class Calculator {
      public int add(int a, int b) {
          return a + b;
      }
      public double add(double a, double b) {
          return a + b;
      }
      public int add(int a, int b, int c) {
          return a + b + c;
      }
  }
  // Usage
  Calculator calc = new Calculator();
  System.out.println(calc.add(2, 3));        // Output: 5
  System.out.println(calc.add(2.5, 3.5));    // Output: 6.0
  System.out.println(calc.add(1, 2, 3));     // Output: 6
  ```
  - Useful for providing flexibility in method calls (e.g., handling different data types or numbers of arguments).

##### **Overriding**
- **What It Is**: Overriding allows a subclass to redefine a method inherited from a superclass, keeping the same method signature. The JVM decides which method to execute at runtime based on the object’s actual type (run-time resolution).
- **Key Characteristics**:
  - Requires inheritance (`extends`).
  - Method signature (name, parameters, return type) must match exactly.
  - Use `@Override` to ensure correctness and improve readability.
  - Can use `super` to invoke the parent’s version.
- **Use Case**:
  ```java
  class Animal {
      public void sound() {
          System.out.println("Generic animal sound");
      }
  }
  class Dog extends Animal {
      @Override
      public void sound() {
          System.out.println("Woof Woof");
      }
  }
  // Usage
  Animal myDog = new Dog();
  myDog.sound();  // Output: Woof Woof (run-time polymorphism)
  ```
  - Ideal for customizing behavior (e.g., a `Dog` barking instead of a generic sound).

#### **Useful Information**
- **When to Use Overloading**: Use when you need a method to handle different types or numbers of inputs within the same class (e.g., constructors with varying parameters).
- **When to Use Overriding**: Use when a subclass needs to modify or enhance a superclass method’s behavior (e.g., adjusting salary calculations for specific employee types).
- **Key Difference**: Overloading is about flexibility in a single class; overriding is about specialization across a hierarchy.
- **Runtime vs. Compile-time**: Overloading is resolved at compile time (static binding), while overriding leverages dynamic binding for polymorphic behavior.

---

## Resources and Summary

### Additional Resources
- [Java Documentation: Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/index.html)
- [Object Class in Java](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html)
- [Java Annotations (e.g., @Override)](https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html)

### Summary
- **Inheritance** is a core OOP principle where a class (subclass) acquires properties from another class (superclass).
- In Java, all classes inherit from the `Object` class, forming a hierarchy.
- Only **non-private members** are inherited; private members and constructors are not, but `super` enables constructor access.
- Use the **`extends`** keyword to specify inheritance.
- **Advantages**: Code reusability, faster development, and code sharing.
- Example: `TechnicalEmployee` extends `Employee`, reusing its methods without rewriting code.
- A subclass can add its own members (e.g., `bonus` in `TechnicalEmployee`).
- A subclass can redefine superclass methods (overriding), such as `calSalary` and `disp`, using the same method signature and `@Override` for validation.
- Overriding is a form of **polymorphism**, where methods exhibit multiple behaviors across classes.
- Use `protected` or getters for private member access, and `super` to call parent methods or constructors (must be the first statement in a constructor).
- The `@Override` annotation ensures correct method overriding, preventing errors like incorrect salary calculations.

