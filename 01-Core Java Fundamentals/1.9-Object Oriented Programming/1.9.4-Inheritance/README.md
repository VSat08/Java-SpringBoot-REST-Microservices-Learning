# 1.9.4: Inheritance

## Introduction

Welcome to `Section 1.9: Concept of Inheritance` 🚀
! This session dives into the fundamental concept of **inheritance** in Java, a cornerstone of object-oriented programming (OOP). We’ll explore what inheritance is, how it works in Java, its advantages, and how to implement it with hands-on examples. In this expanded guide, we’ll cover insights from *"Inheritance - Part 2"*, *"Part 3"*, and *"Run-time Polymorphism"*, including subclass-specific members, method overriding, the `super` keyword, the `@Override` annotation, and the fascinating world of **run-time polymorphism** with upcasting and dynamic method dispatch. Get ready to unlock the power of inheritance and polymorphism in Java! 🌟

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
      - [Static Polymorphism](#static-polymorphism)
      - [Run-time Polymorphism](#run-time-polymorphism)
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
Inheritance is a mechanism in Java where one class **acquires** the properties (data members and methods) of another class. It involves at least two classes: a **parent class** (the provider) and a **child class** (the inheritor). From *"Inheritance - Part 2"* and *"Part 3"*, we’ve seen how a subclass can extend functionality with its own members and redefine behaviors. In *"Run-time Polymorphism"*, we discover that child objects are **specialized versions** of parent objects, enabling powerful concepts like upcasting and dynamic behavior at runtime.

#### **Object-Oriented Principles**
Inheritance is one of the four pillars of OOP:
1. **Encapsulation** - Hiding data, exposing methods.
2. **Inheritance** - Acquiring properties from another class.
3. **Abstraction** - Simplifying complex systems.
4. **Polymorphism** - Enabling multiple forms of behavior.

Inheritance pairs with **polymorphism**, shining through method overriding (from *"Part 2"*), constructor access via `super` (from *"Part 3"*), and run-time polymorphism with upcasting (from *"Run-time Polymorphism"*).

#### **Real-World Analogy**
Inheritance mirrors real-world relationships 🌍. Just as we inherit traits from our parents, a Java class inherits features from its superclass. For example, a **technical employee** inherits general employee traits but adds a bonus—specializing the parent’s role. This specialization allows parent references to point to child objects (upcasting), showcasing flexibility and runtime adaptability.

#### **Terminology**
- **Parent Class**: Also called **superclass** or **base class**, the property provider.
- **Child Class**: Also called **subclass** or **derived class**, the property inheritor.
Synonyms: *parent ↔ child*, *super ↔ sub*, *base ↔ derived*. From *"Part 3"*, we add **super keyword** (accessing parent elements) and **@Override annotation** (validating overrides). From *"Run-time Polymorphism"*, terms like **upcasting** and **run-time polymorphism** emerge, highlighting dynamic behavior.

### Advantages of Inheritance
Inheritance offers compelling benefits:
1. **Code Reusability**: Reuse tested code (e.g., `TechnicalEmployee` leverages `Employee`’s methods).
2. **Faster Development**: Build on existing classes quickly.
3. **Code Sharing**: Share generalized classes across teams, with `super` ensuring seamless parent initialization and polymorphism enabling tailored runtime behavior.

---

## Inheritance in Java

### Core Concepts

#### **Class Hierarchy**
In Java, all classes form a **hierarchy** with the **`Object` class** at the top 🌳. Every class inherits from `Object`, directly or indirectly, supporting chains like A → B → C, where `super` (from *"Part 3"*) refers to the immediate parent and upcasting (from *"Run-time Polymorphism"*) allows any ancestor to reference a descendant.

#### **Object Class as Superclass**
- Located in `java.lang`.
- No parent itself, but the ultimate ancestor.
- Explore more in [Java Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html).

#### **Identifying Inheritance**
Inheritance is spotted via an **"is-a" relationship** (generalization-specialization):
- Examples:
  - A **student** *is a* **person**.
  - A **dog** *is an* **animal**.
  - A **technical employee** *is an* **employee**.
This hierarchy drives inheritance and enables upcasting, where parents reference specialized child objects.

#### **What Gets Inherited?**
- **Non-private members** are inherited:
  - Data members (variables).
  - Methods.
  - Nested classes (if non-private).
  - Static blocks (if applicable).
- **Private members** and **constructors** are NOT inherited, but `super` (from *"Part 3"*) provides constructor access, ensuring child objects include parent data.

From *"Part 2"*, subclasses add members (e.g., `bonus`) and override methods. From *"Run-time Polymorphism"*, this inheritance property allows child objects to be treated as specialized parent objects, facilitating run-time polymorphism.

### Specifying Inheritance

#### **The `extends` Keyword**
- Java uses **`extends`** to establish inheritance.
- Syntax: `class ChildClass extends ParentClass`

#### **Example: Employee and TechnicalEmployee**

##### **Parent Class: Employee**
```java
public class Employee {
    private int empId;           // Private data member
    private String eName;        // Private data member
    private double basic;        // Private data member
    private static String org = "Google"; // Static data member

    // Parameterized constructor
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

    // Getter for basic
    public double getBasic() {
        return basic;
    }
}
```

##### **Child Class: TechnicalEmployee**
```java
public class TechnicalEmployee extends Employee {
    private int bonus; // Child-specific member

    // Constructor using super
    public TechnicalEmployee(int id, String name, double sal, int bonus) {
        super(id, name, sal); // Invoke parent constructor
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
        // Upcasting example (Run-time Polymorphism)
        Employee e1 = new TechnicalEmployee(123, "Asdf", 100000, 10000);
        e1.disp();      // Output: 123 Asdf 100000 Google / Bonus: 10000
        e1.calSalary(); // Output: Total Salary: 190000 (child-specific)

        // Static binding example (no upcasting)
        Employee e = new Employee(1234, "Koti", 100000);
        e.disp();       // Output: 1234 Koti 100000 Google
        e.calSalary();  // Output: Total Salary: 180000 (parent version)

        // Static polymorphism with child object
        TechnicalEmployee t = new TechnicalEmployee(321, "XYZ", 100000, 10000);
        t.disp();       // Output: 321 XYZ 100000 Google / Bonus: 10000
        t.calSalary();  // Output: Total Salary: 190000 (child-specific)

        // Upcasting via assignment
        e = t;          // Parent reference now points to child object
        e.disp();       // Output: 321 XYZ 100000 Google / Bonus: 10000 (run-time)
        e.calSalary();  // Output: Total Salary: 190000 (run-time)
    }
}
```

##### **Explanation**
- **Inheritance**: `TechnicalEmployee` extends `Employee`, inheriting non-private members.
- **Private Members**: `empId`, `eName`, `basic` are private, initialized via `super` and accessed via `getBasic`.
- **Constructor**: `super(id, name, sal)` initializes parent data; `bonus` is child-specific (*"Part 3"*).
- **Overriding**: 
  - `calSalary` includes `bonus` (*"Part 2"*).
  - `disp` uses `super.disp()` and adds `bonus`, validated by `@Override` (*"Part 3"*).
- **Run-time Polymorphism (*"Run-time Polymorphism"*):**
  - `Employee e1 = new TechnicalEmployee(...)`: Upcasting—parent reference to child object. Calls to `disp` and `calSalary` invoke child-specific versions (run-time binding).
  - `Employee e = new Employee(...)`: No upcasting, static binding to parent methods.
  - `TechnicalEmployee t = new TechnicalEmployee(...)`: No upcasting, static polymorphism with child methods.
  - `e = t`: Upcasting via assignment, shifting `e` to reference `t`’s child object, triggering run-time polymorphism.
- **Output**: Demonstrates static vs. dynamic behavior based on reference type and object.

### Advanced Features

#### **Child-Specific Members**
Subclasses can add unique members. `TechnicalEmployee` introduces `bonus`, initialized via its constructor (*"Part 3"*), enhancing specialization beyond inherited traits.

#### **Method Overriding**
Overriding redefines a superclass method’s behavior with the same signature (*"Part 2"*):
- `calSalary` adds `bonus`.
- `disp` extends parent output with `super.disp()`, using `@Override` (*"Part 3"*).

#### **Polymorphism**
Polymorphism allows methods to take multiple forms:
- **Static Polymorphism**: Compile-time resolution (e.g., overloading).
- **Run-time Polymorphism**: Run-time resolution via overriding and upcasting.

##### **Static Polymorphism**
- **Definition**: Binding occurs at compile time (early binding) when reference type and object type match.
- **Example**: `TechnicalEmployee t = new TechnicalEmployee(...)` calls overridden methods statically—no upcasting involved.
- **Behavior**: Predictable, based on the declared type (*"Run-time Polymorphism"*).

##### **Run-time Polymorphism**
- **Definition**: Binding occurs at runtime (dynamic binding, late binding, or dynamic method dispatch) when a parent reference points to a child object (upcasting) and an overridden method is called.
- **Key Property**: Subclass objects are specialized versions of superclass objects, so parents can reference them (*"Run-time Polymorphism"*).
- **Upcasting**: 
  - Direct: `Employee e1 = new TechnicalEmployee(...)`.
  - Assignment: `e = t` (parent reference reassigned to child object).
- **How It Works**: 
  - Compiler skips binding due to type ambiguity (parent reference, child object).
  - JVM resolves at runtime, checking the actual object type (not reference type) and invoking the child-specific method.
- **Example Output**: `e1.disp()` and `e.calSalary()` after `e = t` call `TechnicalEmployee`’s versions, showing bonus-inclusive results (1,90,000 vs. 1,80,000).
- **Why Dynamic?**: Behavior adapts based on the object, not the reference, enabling flexibility (*"Run-time Polymorphism"*).

#### **The `super` Keyword**
- **Definition**: A reference to the immediate parent class (*"Part 3"*).
- **Purpose**: Access parent variables, methods (e.g., `super.disp()`), and constructors (e.g., `super(id, name, sal)`).
- **Rules**: `super()` must be the first constructor statement; incompatible with `this()`; not usable in static contexts.

#### **The `@Override` Annotation**
- **Definition**: Marks a method as overriding a parent method (*"Part 3"*).
- **Purpose**: Ensures signature match; warns if overriding fails (e.g., typo `calsalary` vs. `calSalary`).
- **Benefit**: Avoids treating a method as child-specific when intended to override, ensuring correct run-time behavior.

---

## Practical Guidance

### Best Practices
1. Use inheritance for clear **"is-a"** relationships.
2. Keep parent classes general, subclasses specialized.
3. Use `protected` or getters for private member access; `super` for constructors.
4. Avoid overusing inheritance—favor composition for **"has-a"** cases.
5. Apply `@Override` for method overrides to catch errors.
6. Use `super` to reuse parent logic and initialize parent data first in constructors.
7. Leverage upcasting for flexibility, understanding run-time polymorphism’s power.

### Common Pitfalls
- Misusing inheritance for **"has-a"** instead of **"is-a"**.
- Accessing private members directly—use getters or `super`.
- Forgetting constructors aren’t inherited, needing `super`.
- Altering override signatures (must match exactly).
- Omitting `super` or `@Override`, risking incorrect behavior.
- Misunderstanding upcasting—parent references limit to parent-declared methods unless overridden.

### Practice Exercises
1. Create a `Person` class with `name`, `age`, and a `disp` method, using a constructor.
2. Extend it to `Student` with `studentId`, using `super` in its constructor.
3. Override `disp` in `Student` to add `studentId`, test with `Person p = new Student(...)` for run-time polymorphism.
4. Add `calculateScore` to `Person`, override it in `Student` with a bonus, using `@Override`.
5. Experiment with upcasting: assign a `Student` object to a `Person` reference and observe method calls.

---

## Comparisons

### Tabular Differentiation: Overloading vs. Overriding
A concise comparison of polymorphism types:

| **Aspect**                | **Overloading**                                                                 | **Overriding**                                                                 |
|---------------------------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| **Definition**            | Same name, different parameters in one class.                                   | Same name, signature redefined in subclass.                                    |
| **Scope**                 | Single class.                                                                  | Across classes (inheritance).                                                  |
| **Signature**             | Must differ (parameters).                                                      | Must match exactly.                                                            |
| **Inheritance?**          | No.                                                                            | Yes (`extends`).                                                               |
| **Purpose**               | Flexibility in inputs.                                                         | Customize inherited behavior.                                                  |
| **Polymorphism**          | Compile-time (static).                                                         | Run-time (dynamic).                                                            |
| **Annotation**            | None.                                                                          | `@Override`.                                                                   |
| **Example**               | `add(int, int)` vs. `add(double, double)`                                      | `disp()` in parent vs. child.                                                  |

#### **Detailed Explanation**
- **Overloading**: Static polymorphism; compile-time flexibility (e.g., `Calculator` with multiple `add` methods).
- **Overriding**: Run-time polymorphism; dynamic specialization (e.g., `Dog`’s `sound()` vs. `Animal`’s).

---

## Resources and Summary

### Additional Resources
- [Java Inheritance Docs](https://docs.oracle.com/javase/tutorial/java/IandI/index.html)
- [Object Class](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html)
- [Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html)

### Summary
- **Inheritance**: A subclass acquires non-private properties from a superclass via `extends`.
- **Core Mechanics**: All classes stem from `Object`; `super` accesses constructors (*"Part 3"*).
- **Advantages**: Reusability, speed, sharing, enhanced by polymorphism.
- **Features**: 
  - Child members (e.g., `bonus`) and overrides (*"Part 2"*).
  - `super` for parent access, `@Override` for correctness (*"Part 3"*).
  - **Run-time Polymorphism**: Upcasting (parent referencing child) plus overriding triggers dynamic binding, resolved by JVM based on object type, not reference (*"Run-time Polymorphism"*).
- **Key Takeaway**: Inheritance and polymorphism together enable flexible, dynamic code—master them to write elegant Java! 🎉

