# 1.15: Multithreading

## Introduction
Welcome to **Section 1.15: Multithreading** 🚀! Multithreading in Java empowers parallel execution, enhancing performance and responsiveness by running multiple threads within a process. This guide explores the essentials—what threads are, how to create them via `Thread` class extension or `Runnable` interface, and their lifecycle. We’ll cover processes vs. threads, advantages, and practical creation, using examples and a beginner-friendly approach. Get set to unlock concurrency in Java! 🌟

---

## Table of Contents
1. [Understanding Multithreading](#1-understanding-multithreading)
    - [What is Multithreading?](#11-what-is-multithreading)
    - [Why Use Multithreading?](#12-why-use-multithreading)
2. [Multithreading in Java](#2-multithreading-in-java)
    - [Core Concepts](#21-core-concepts)
    - [Processes vs. Threads](#22-processes-vs-threads)
    - [Thread Definition and Role](#23-thread-definition-and-role)
    - [Creating Threads](#24-creating-threads)
        - [Extending Thread Class](#241-extending-thread-class)
        - [Implementing Runnable Interface](#242-implementing-runnable-interface)
    - [Thread Lifecycle](#25-thread-lifecycle)
    - [Advantages of Multithreading](#26-advantages-of-multithreading)
3. [Practical Guidance](#3-practical-guidance)
    - [Best Practices](#31-best-practices)
    - [Common Pitfalls](#32-common-pitfalls)
    - [Practice Exercises](#33-practice-exercises)
4. [Comparisons](#4-comparisons)
    - [Multiprocessing vs. Multithreading](#41-multiprocessing-vs-multithreading)
    - [Thread Extension vs. Runnable Interface](#42-thread-extension-vs-runnable-interface)
5. [Resources & Summary](#5-resources--summary)
    - [Resources](#51-resources)
    - [Summary](#52-summary)

---

## 1. Understanding Multithreading

### 1.1 What is Multithreading?
*Multithreading* is a multitasking technique where multiple *threads*—lightweight subunits of a process—execute parallelly within a single *process*. A process is a program under execution, while threads share its resources, enabling efficient concurrency. In Java, multithreading leverages CPU power for simultaneous task execution.

#### Definitions
- **Process**: A program or app under execution, heavyweight with separate memory.
- **Thread**: A lightweight subunit of a process, sharing its address space.

#### Real-World Example
A ticket booking app: one process runs the app, while threads handle concurrent user tasks (selecting seats, paying) in parallel.

#### Key Terms
| Term             | Definition                                   | Example                |
|------------------|----------------------------------------------|------------------------|
| Process          | Program under execution                     | Booking app            |
| Thread           | Lightweight subunit of a process            | Seat selection task    |
| Multithreading   | Parallel thread execution within a process  | Multiple bookings      |

### 1.2 Why Use Multithreading?
Multithreading enhances applications by:

- Enabling parallel task execution within a process.
- Reducing switching overhead vs. processes.
- Maximizing CPU utilization during idle times.
- Improving user responsiveness (e.g., quick ticket bookings).

#### Analogy
Multithreading is like a chef (process) delegating tasks to sous-chefs (threads)—chopping, cooking, plating—simultaneously to serve dishes faster.

---

## 2. Multithreading in Java

### 2.1 Core Concepts
Multithreading in Java, supported by `java.lang.Thread` and `java.lang.Runnable`, allows threads to run concurrently within a process, managed by the JVM’s *thread scheduler*. Each program starts with a *main thread*, and additional threads can be created via two methods: extending `Thread` or implementing `Runnable`.

### 2.2 Processes vs. Threads
#### Process-Based Multitasking (Multiprocessing)
- **Definition**: Multiple processes run parallelly, each with separate memory and resources.
- **Mechanism**: CPU switches between processes, updating memory maps, registers, and process control blocks (PCB).
- **Traits**: Heavyweight, high switching cost (nanoseconds, noticeable in bulk).

#### Thread-Based Multitasking (Multithreading)
- **Definition**: Multiple threads run within one process, sharing its address space.
- **Mechanism**: Threads switch quickly, avoiding memory updates, using process resources.
- **Traits**: Lightweight, low-cost switching.

##### Textual Block Diagram
```
+-------------------------+
| Process (Heavyweight)   |
|  - Memory Space         |
|  - Registers            |
|  - PCB                  |
|                         |
|   +-------+  +-------+  |
|   |Thread1|  |Thread2|  |
|   |(Light)|  |(Light)|  |
|   +-------+  +-------+  |
+-------------------------+
```

### 2.3 Thread Definition and Role
A *thread* is the smallest processing unit within a process, executing a task (critical section) via the `run()` method. Threads share the process’s address space, managed by the JVM’s thread scheduler.

#### Role
- Execute tasks parallelly within a process.
- Controlled by the scheduler, which assigns `run()` execution.

#### Snippet: Main Thread
```java
class MultithreadDemo {
    public static void main(String[] args) {
        System.out.println("Main thread running");
        // JVM creates main thread automatically
    }
}
// Output: Main thread running
```

### 2.4 Creating Threads
Threads in Java are created in two ways: extending `Thread` or implementing `Runnable`.

#### 2.4.1 Extending Thread Class
Extend `Thread`, override `run()`, and call `start()` to make instances threads directly.

##### Snippet: Extending Thread
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("User thread: " + i);
        }
    }
}

class MultiDemo {
    public static void main(String[] args) {
        Multi t1 = new Multi();  // New state
        Multi t2 = new Multi();  // New state
        t1.start();              // Runnable state
        t2.start();              // Runnable state
    }
}
// Output: Interleaved "User thread: 1" to "User thread: 10" from t1 and t2
```

#### 2.4.2 Implementing Runnable Interface
Implement `Runnable`, define `run()`, and pass instances to a `Thread` constructor, then call `start()`.

##### Snippet: Implementing Runnable
```java
class RunnableDemo implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("User thread: " + i);
        }
    }
}

class RunnableTest {
    public static void main(String[] args) {
        Runnable r1 = new RunnableDemo();
        Runnable r2 = new RunnableDemo();
        Thread t1 = new Thread(r1);  // New state
        Thread t2 = new Thread(r2);  // New state
        t1.start();                  // Runnable state
        t2.start();                  // Runnable state
    }
}
// Output: Interleaved "User thread: 1" to "User thread: 10" from t1 and t2
```

### 2.5 Thread Lifecycle
Threads transition through five states, managed by the JVM.

#### States
1. **New**: Thread created (e.g., `new Thread()`), not started.
2. **Runnable (Ready)**: Thread ready after `start()`, awaiting scheduler.
3. **Running**: Thread executes `run()` when scheduled.
4. **Blocked (Non-Runnable)**: Paused (e.g., sleeping, waiting for I/O, locked).
5. **Terminated (Dead)**: Exits `run()` or fails.

#### Flowchart (Textual)
```
+-----------------+
|     New         | --> t1.start()
+-----------------+
         |
         v
+-----------------+
|   Runnable      | --> Scheduler picks
+-----------------+
         |
         v
+-----------------+    Blocked Conditions:
|    Running      | --> Sleep, Wait, I/O, Lock
+-----------------+    |
         |             v
         |    +-----------------+
         +--> |    Blocked      | --> Resources available
              +-----------------+    |
                       |             v
                       +------------> Runnable
         |
         v
+-----------------+
|   Terminated    | <-- run() completes
+-----------------+
```

### 2.6 Advantages of Multithreading
Multithreading boosts applications by:

- **Parallel Processing**: Executes tasks concurrently (e.g., 1000 bookings in 3 minutes).
- **Responsiveness**: Reduces wait times (e.g., e-commerce checkout).
- **CPU Utilization**: Uses idle CPU time efficiently.
- **Priority Control**: Assigns execution order via priorities.

#### Example
1000 users booking tickets in 3 minutes—not 3000—due to parallel thread execution.

---

## 3. Practical Guidance

### 3.1 Best Practices
- Extend `Thread` for simple, standalone tasks; use `Runnable` when extending other classes.
- Call `start()` to enable multithreading, not `run()` directly.
- Define clear `run()` methods for thread tasks.
- Use multiple threads for parallel, independent operations (e.g., bookings).
- Minimize blocking operations to keep threads responsive.

### 3.2 Common Pitfalls
- Calling `run()` instead of `start()`, losing parallelism.
- Assuming thread execution order (scheduler-dependent).
- Over-creating threads, straining resources.
- Neglecting thread termination, causing leaks.
- Ignoring inheritance conflicts with `Thread` extension.

### 3.3 Practice Exercises
1. Create a thread via `Thread` extension printing "Hello" 5 times.
2. Implement `Runnable` for a thread counting 1-10, run two instances.
3. Simulate two threads booking tickets, printing interleaved outputs.
4. Add a delay in `run()` to observe interleaving with the main thread.
5. Compare outputs of `start()` vs. `run()` calls on a thread.

---

## 4. Comparisons

### 4.1 Multiprocessing vs. Multithreading
| Aspect            | Multiprocessing            | Multithreading            |
|-------------------|----------------------------|---------------------------|
| Unit              | Process (program)          | Thread (process subunit)  |
| Weight            | Heavyweight                | Lightweight               |
| Memory            | Separate address spaces    | Shared address space      |
| Switching Cost    | High (memory, PCB updates) | Low (shared resources)    |
| Parallelism Level | Process-level              | Thread-level within process |
| Use Case          | Multiple apps              | Tasks within one app      |

### 4.2 Thread Extension vs. Runnable Interface
| Aspect            | Extending Thread           | Implementing Runnable     |
|-------------------|----------------------------|---------------------------|
| Mechanism         | Inherits `Thread`          | Implements interface      |
| Inheritance       | Limits extension (one parent) | Allows other class extension |
| Complexity        | Direct thread instances    | Requires `Thread` constructor |
| Flexibility       | Less flexible              | More flexible             |
| Use Case          | Simple thread tasks        | Complex class hierarchies |

---

## 5. Resources & Summary

### 5.1 Resources
- [Java Thread Docs](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Java API: Thread](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html)

### 5.2 Summary
Multithreading in Java enables parallel thread execution within a process, using `Thread` extension or `Runnable` implementation. Threads are lightweight, sharing process resources, and transition through New, Runnable, Running, Blocked, and Terminated states. The JVM’s scheduler manages execution, offering high performance and responsiveness.

#### Highlights
- **Creation**: Extend `Thread` or implement `Runnable` for threads.
- **Lifecycle**: Five states, driven by `start()` and `run()`.
- **Advantages**: Parallelism, efficiency, user responsiveness.
- **Takeaway**: Master thread creation for concurrent Java applications! 🎉
