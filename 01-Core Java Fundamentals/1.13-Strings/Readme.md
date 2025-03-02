# 1.13: Strings

## Introduction
Welcome to **Section 1.13: Strings** 🚀! Strings in Java are essential for text handling, forming the core of data manipulation, comparison, and display. This guide explores strings—sequences of 16-bit Unicode characters—and their creation, storage, immutability, and comparison methods like `equals()`, `compareTo()`, and `==`. We’ll cover string literals, object creation with `new`, mutable alternatives (`StringBuffer`, `StringBuilder`), and key methods (`length()`, `substring()`, `charAt()`), using examples and a beginner-friendly approach. Get ready to master string operations in Java! 🌟

---

## Table of Contents
1. [Understanding Strings](#1-understanding-strings)
    - [What are Strings?](#11-what-are-strings)
    - [Why Use Strings?](#12-why-use-strings)
2. [Strings in Java](#2-strings-in-java)
    - [Core Concepts](#21-core-concepts)
    - [Implementing Strings](#22-implementing-strings)
        - [Creating Strings with Literals](#221-creating-strings-with-literals)
        - [Creating Strings with New Keyword](#222-creating-strings-with-new-keyword)
        - [Mutable String Alternatives](#223-mutable-string-alternatives)
        - [Comparing Strings](#224-comparing-strings)
    - [Advanced Features](#23-advanced-features)
        - [String Constant Pool vs. Heap](#231-string-constant-pool-vs-heap)
        - [Immutability and Final Class](#232-immutability-and-final-class)
        - [String Methods](#233-string-methods)
3. [Practical Guidance](#3-practical-guidance)
    - [Best Practices](#31-best-practices)
    - [Common Pitfalls](#32-common-pitfalls)
    - [Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [String vs. StringBuffer vs. StringBuilder](#41-string-vs-stringbuffer-vs-stringbuilder)
5. [Resources & Summary](#5-resources--summary)
    - [Resources](#51-resources)
    - [Summary](#52-summary)

---

## 1. Understanding Strings

### 1.1 What are Strings?
A *string* in Java is a sequence of 16-bit Unicode characters (e.g., 'A' is 65), enclosed in double quotes (e.g., `"Java"`), unlike single-quoted characters (e.g., `'J'`). Examples include `"Java"`, `"ABC123"`, or any valid character combination.

In programming, strings are `String` class objects, critical for text storage, manipulation, comparison, and display, foundational to user interaction and data processing.

#### Real-World Example
A string like `"Hello World"` is a fixed sentence on a page—readable and unchangeable—while comparing `"Hello"` and `"hello"` tests their likeness, ignoring case if needed.

#### Key Terms
| Term             | Definition                                   | Example                |
|------------------|----------------------------------------------|------------------------|
| String           | Sequence of 16-bit characters               | `"Java"`               |
| Literal          | Value in double quotes                      | `"ABC"`                |
| Object           | Instance of a class                         | `new String("Java")`   |

### 1.2 Why Use Strings?
Strings manage text efficiently, offering:

- Readability with double-quoted literals.
- Memory optimization via the string constant pool.
- Flexibility with immutable (`String`) and mutable (`StringBuffer`, `StringBuilder`) options.
- Robust comparison for authentication and data validation.

#### Analogy
Strings are like printed books (immutable `String`) or editable notebooks (`StringBuffer`, `StringBuilder`)—fixed for sharing, editable for updates, with comparison tools to match pages.

---

## 2. Strings in Java

### 2.1 Core Concepts
Strings in Java, part of `java.lang`, are 16-bit Unicode character sequences, created via literals or `new`, and are immutable. Mutable alternatives (`StringBuffer`, `StringBuilder`) allow changes, while comparison methods (`equals()`, `compareTo()`) and the `==` operator test equality. Utility methods like `length()`, `substring()`, and `charAt()` enhance manipulation.

### 2.2 Implementing Strings
Java offers ways to create, modify, and compare strings, leveraging different memory areas and behaviors.

#### 2.2.1 Creating Strings with Literals
String literals (e.g., `"Java"`) are objects in the *string constant pool*, a heap area reusing identical literals for efficiency.

##### Snippet: String Literals
```java
class StringDemo {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = "Python";
        String s3 = "Java";
        System.out.println(s1);       // Output: Java
        System.out.println(s2);       // Output: Python
        System.out.println(s3);       // Output: Java
        System.out.println(s1 == s3); // Output: true (same object)
    }
}
// Three references (s1, s2, s3), two objects ("Java", "Python")
```

#### 2.2.2 Creating Strings with New Keyword
`new String()` creates objects in the heap, duplicating literals even if they exist in the pool.

##### Snippet: New Keyword
```java
class StringDemo {
    public static void main(String[] args) {
        String s1 = new String("Java");
        String s2 = new String("Java");
        System.out.println(s1);       // Output: Java
        System.out.println(s2);       // Output: Java
        System.out.println(s1 == s2); // Output: false (different objects)
    }
}
// Four objects: two in heap, one "Java" in pool
```

#### 2.2.3 Mutable String Alternatives
`StringBuffer` (thread-safe) and `StringBuilder` (not thread-safe) allow modifications via `append()`.

##### Snippet: StringBuffer
```java
class StringDemo {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Sachin");
        sb.append(" Tendulkar");
        System.out.println(sb); // Output: Sachin Tendulkar
    }
}
```

##### Snippet: StringBuilder
```java
class StringDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Sourav");
        sb.append(" Ganguly");
        System.out.println(sb); // Output: Sourav Ganguly
    }
}
```

#### 2.2.4 Comparing Strings
Strings are compared using `equals()` (content), `compareTo()` (lexicographic order), and `==` (references).

##### Snippet: Comparison Methods
```java
class StringComparison {
    public static void main(String[] args) {
        String s1 = "Sachin";
        String s2 = "Virat";
        String s3 = new String("Sachin");
        String s5 = "sachin";

        // equals() and equalsIgnoreCase()
        System.out.println(s1.equals(s3));         // Output: true (same content)
        System.out.println(s1.equalsIgnoreCase(s5)); // Output: true (case ignored)
        System.out.println(s1.equals(s2));         // Output: false (different content)

        // compareTo() and compareToIgnoreCase()
        System.out.println(s1.compareTo(s5));      // Output: -32 (S > s)
        System.out.println(s5.compareTo(s1));      // Output: 32 (s < S)
        System.out.println(s1.compareToIgnoreCase(s5)); // Output: 0 (case ignored)

        // == (references)
        System.out.println(s1 == s3);              // Output: false (different references)
    }
}
```

### 2.3 Advanced Features

#### 2.3.1 String Constant Pool vs. Heap
- **String Constant Pool**: Reuses literals (e.g., `"Java"`) in a heap subsection for efficiency.
- **Heap**: Stores `new` objects, duplicating literals regardless of pool presence.

##### Diagram (Textual)
```
Heap Memory:
  - String Constant Pool: ["Java", "Python"]
    - "Java" <- s1, s3
    - "Python" <- s2
  - General Heap: [s1: "Java", s2: "Java"]
    - Each 'new String("Java")' adds a heap object
```

#### 2.3.2 Immutability and Final Class
`String` is a `final` class, making it immutable—modifications (e.g., `concat()`) create new objects.

##### Snippet: Immutability
```java
class StringDemo {
    public static void main(String[] args) {
        String str = "King";
        str.concat(" Kohli");     // Creates new object, no reference
        System.out.println(str);  // Output: King (unchanged)
    }
}
```

#### 2.3.3 String Methods
Key methods enhance string utility:

- **`length()`**: Returns character count.
- **`substring(int)`**: Extracts from index to end (inclusive).
- **`substring(int, int)`**: Extracts from start (inclusive) to end (exclusive).
- **`charAt(int)`**: Retrieves character at index.

##### Snippet: String Methods
```java
import java.util.Scanner;

class StringDemo1 {
    public static void main(String[] args) throws IOException {
        String s1 = "Welcome to Java";
        System.out.println(s1.length());         // Output: 15
        System.out.println(s1.substring(6));     // Output: "e to Java"
        System.out.println(s1.substring(0, 7));  // Output: "Welcome" (0-6)

        // Reading a character with charAt()
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a character: ");
        String str = in.next();                  // Reads as string
        char c = str.charAt(0);                  // Extracts first char
        System.out.println("Character read: " + c);
        in.close();
    }
}
// Input: X
// Output: 15
//         e to Java
//         Welcome
//         Character read: X
```

>[!NOTE] 
>No direct method reads a single character; use `charAt(0)` on a string input.

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use literals (`"Java"`) for constants to optimize memory via the pool.
- Use `new String()` only for intentional heap allocation.
- Prefer `equals()` or `compareTo()` for content comparison, `==` for reference checks.
- Use `StringBuilder` for single-threaded, performance-sensitive edits.
- Leverage `substring()` and `charAt()` for precise text extraction.
- Avoid excessive `String` concatenation; use mutable classes instead.

### 3.2 Common Pitfalls
- Using `==` for content comparison (checks references, not values).
- Overusing `new String()`, duplicating pool literals.
- Expecting `concat()` to modify the original string.
- Ignoring case sensitivity without `equalsIgnoreCase()` or `compareToIgnoreCase()`.
- Misusing `substring()` end index (exclusive).
- Assuming direct character input without `charAt()`.

### 3.3 Practice Exercises
1. Compare `"Java"` and `"java"` with `equals()` and `equalsIgnoreCase()`.
2. Use `compareTo()` on `"Sachin"` and `"Rohit"`, note the integer result.
3. Check `"Java"` literals vs. `new String("Java")` with `==`.
4. Extract `"to"` from `"Welcome to Java"` using `substring()`.
5. Read a character from the user with `Scanner` and `charAt(0)`.

---

## 4. Comparisons

### 4.1 String vs. StringBuffer vs. StringBuilder
| Aspect            | String                  | StringBuffer            | StringBuilder           |
|-------------------|-------------------------|-------------------------|-------------------------|
| Mutability        | Immutable               | Mutable                 | Mutable                 |
| Thread Safety     | Yes (immutable)         | Yes (synchronized)      | No                      |
| Performance       | Slower (new objects)    | Slower (thread-safe)    | Faster (no sync)        |
| Memory            | String constant pool    | Heap                    | Heap                    |
| Use Case          | Fixed text              | Multi-threaded edits    | Single-threaded edits   |

---

## 5. Resources & Summary

### 5.1 Resources
- [Java String Docs](https://docs.oracle.com/javase/tutorial/java/data/strings.html)
- [Java API: String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)

### 5.2 Summary
Strings in Java are 16-bit Unicode sequences, created with literals (pool) or `new` (heap), and are immutable (`final` class). `StringBuffer` and `StringBuilder` offer mutability, with `StringBuffer` thread-safe. Comparison uses `equals()` (content), `compareTo()` (order), and `==` (references), while `length()`, `substring()`, and `charAt()` aid manipulation.

#### Highlights
- **Creation**: Literals optimize memory; `new` creates heap duplicates.
- **Comparison**: `equals()` for values, `compareTo()` for order, `==` for references.
- **Methods**: `charAt(0)` extracts characters from string input.
- **Takeaway**: Master string creation, comparison, and methods for robust text handling in Java! 🎉

