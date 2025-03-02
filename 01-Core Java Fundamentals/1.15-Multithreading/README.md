# 1.15: Multithreading

## Introduction
Welcome to **Section 1.15: Multithreading** 🚀! Multithreading in Java unleashes concurrent execution, boosting performance and responsiveness by running multiple threads within a process. This guide explores threads—lightweight process subunits—covering their creation via `Thread` extension or `Runnable` interface, lifecycle, and control methods like `sleep()`, `join()`, and `currentThread()`. We’ll dive into processes vs. threads, advantages, and practical thread management, using examples and a beginner-friendly approach. Master concurrency in Java! 🌟

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
    - [Thread Control Methods](#26-thread-control-methods)
        - [Sleep Method](#261-sleep-method)
        - [Join Method](#262-join-method)
        - [CurrentThread and Attributes](#263-currentthread-and-attributes)
    - [Advantages of Multithreading](#27-advantages-of-multithreading)
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
*Multithreading* is a multitasking approach where multiple *threads*—lightweight subunits of a *process*—run parallelly within a single process. A process is a program under execution, while threads share its resources, enabling efficient concurrency. In Java, multithreading leverages CPU power for simultaneous task execution.

#### Definitions
- **Process**: A program or app under execution, heavyweight with separate memory.
- **Thread**: A lightweight subunit of a process, sharing its address space.

#### Real-World Example
A ticket booking app: one process runs the app, with threads handling concurrent user tasks (seat selection, payment).

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
- Improving responsiveness (e.g., quick bookings).

#### Analogy
Multithreading is like a chef (process) delegating tasks to sous-chefs (threads)—chopping, cooking, plating—simultaneously to serve faster.

---

## 2. Multithreading in Java

### 2.1 Core Concepts
Multithreading in Java, supported by `java.lang.Thread` and `java.lang.Runnable`, allows threads to run concurrently within a process, managed by the JVM’s *thread scheduler*. Each program starts with a *main thread*, and additional threads are created via `Thread` extension or `Runnable` implementation, controlled with methods like `sleep()`, `join()`, and `currentThread()`.

### 2.2 Processes vs. Threads
#### Process-Based Multitasking (Multiprocessing)
- **Definition**: Multiple processes run parallelly, each with separate memory/resources.
- **Mechanism**: CPU switches, updating memory maps, registers, and process control blocks (PCB).
- **Traits**: Heavyweight, high switching cost (nanoseconds, noticeable in bulk).

#### Thread-Based Multitasking (Multithreading)
- **Definition**: Multiple threads run within one process, sharing its address space.
- **Mechanism**: Threads switch quickly, avoiding memory updates.
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
A *thread* is the smallest processing unit within a process, executing a task (critical section) via the `run()` method, managed by the JVM’s scheduler.

#### Role
- Execute tasks parallelly within a process.
- Controlled by the scheduler for `run()` execution.

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
Threads are created via two methods: extending `Thread` or implementing `Runnable`.

#### 2.4.1 Extending Thread Class
Extend `Thread`, override `run()`, and call `start()` to make instances threads directly.

##### Snippet: Extending Thread
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("User thread: " + i);
        }
    }
}

class MultiDemo {
    public static void main(String[] args) {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.start();
        t2.start();
    }
}
// Output: Interleaved "User thread: 1" to "User thread: 5" from t1 and t2
```

#### 2.4.2 Implementing Runnable Interface
Implement `Runnable`, define `run()`, and pass instances to a `Thread` constructor, then call `start()`.

##### Snippet: Implementing Runnable
```java
class RunnableDemo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("User thread: " + i);
        }
    }
}

class RunnableTest {
    public static void main(String[] args) {
        Runnable r1 = new RunnableDemo();
        Runnable r2 = new RunnableDemo();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
// Output: Interleaved "User thread: 1" to "User thread: 5" from t1 and t2
```

### 2.5 Thread Lifecycle
Threads transition through five states, managed by the JVM.

#### States
1. **New**: Created (e.g., `new Thread()`), not started.
2. **Runnable (Ready)**: Ready after `start()`, awaiting scheduler.
3. **Running**: Executes `run()` when scheduled.
4. **Blocked (Non-Runnable)**: Paused (e.g., sleeping, waiting, locked).
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

### 2.6 Thread Control Methods
Java provides methods to manage thread execution: `sleep()`, `join()`, and `currentThread()`, plus attribute getters/setters.

#### 2.6.1 Sleep Method
`sleep()` pauses the current thread for a specified time (milliseconds or milliseconds + nanoseconds), moving it to the Blocked state. It’s static and throws `InterruptedException`.

##### Snippet: Sleep Method
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(750); // Sleep 750ms
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}

class MultiDemo {
    public static void main(String[] args) {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.start();
        t2.start();
    }
}
// Output: Thread-0: 1, Thread-1: 1, Thread-0: 2, Thread-1: 2, ... (uniform interleaving)
```

#### 2.6.2 Join Method
`join()` makes other threads wait until the joined thread completes (indefinite) or for a specified time (milliseconds or milliseconds + nanoseconds). It’s non-static and throws `InterruptedException`.

##### Snippet: Join Method
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}

class MultiDemo {
    public static void main(String[] args) throws InterruptedException {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.start();
        t1.join(2500); // Wait 2500ms
        t2.start();
    }
}
// Output: Thread-0: 1-3, then Thread-1: 1-5, Thread-0: 4-5 (t2 waits 2500ms)
```

#### 2.6.3 CurrentThread and Attributes
- **`currentThread()`**: Static, returns the currently executing thread.
- **`getName()` / `setName()`**: Get or set thread name (default: "Thread-0", "Thread-1", ...).
- **`getPriority()` / `setPriority()`**: Get or set priority (1-10, default 5: `NORM_PRIORITY`).

##### Snippet: CurrentThread and Attributes
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " (Priority " +
                              Thread.currentThread().getPriority() + "): " + i);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}

class MultiDemo {
    public static void main(String[] args) throws InterruptedException {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.setPriority(Thread.MAX_PRIORITY); // 10
        t2.setName("Second");
        t1.start();
        t2.start();
    }
}
// Output: Thread-0 (Priority 10): 1, Second (Priority 5): 1, ... (interleaved)
```

>[!NOTE] 
>`sleep()` and `join()` throw `InterruptedException`; `run()` overrides cannot declare exceptions.

### 2.7 Advantages of Multithreading
Multithreading enhances applications by:

- **Parallel Processing**: Executes tasks concurrently (e.g., 1000 bookings in 3 minutes).
- **Responsiveness**: Reduces wait times (e.g., e-commerce checkout).
- **CPU Utilization**: Uses idle CPU time efficiently.
- **Priority Control**: Orders execution with `join()` or priorities.

#### Example
1000 users book tickets in 3 minutes—not 3000—due to parallel thread execution.

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use `sleep()` for uniform thread access (e.g., ticket booking delays).
- Apply `join()` to prioritize critical threads (e.g., payment completion).
- Leverage `currentThread()` for dynamic logic based on thread identity.
- Set thread names for debugging clarity.
- Adjust priorities sparingly (1-10) to influence scheduling.

### 3.2 Common Pitfalls
- Calling `run()` instead of `start()`, losing parallelism.
- Starting a thread twice, causing `IllegalThreadStateException`.
- Ignoring `InterruptedException` in `sleep()` or `join()`.
- Overusing high priorities, skewing scheduler fairness.
- Assuming predictable interleaving without control methods.

### 3.3 Practice Exercises
1. Create two threads with `sleep(500)`, printing names and numbers.
2. Use `join()` to make one thread wait for another, printing sequentially.
3. Set custom names and priorities, display with `currentThread()`.
4. Simulate three threads with `sleep()`, observe uniform output.
5. Call `run()` vs. `start()`, compare sequential vs. parallel output.

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
Multithreading in Java enables parallel thread execution within a process, created by extending `Thread` or implementing `Runnable`. Threads transition through New, Runnable, Running, Blocked, and Terminated states, controlled by methods like `sleep()` (pauses), `join()` (waits), and `currentThread()` (identifies). It offers performance, responsiveness, and CPU efficiency.

#### Highlights
- **Creation**: `Thread` extension or `Runnable` for threads.
- **Control**: `sleep()`, `join()`, `currentThread()` manage execution.
- **Lifecycle**: Five states, driven by `start()` and `run()`.
- **Takeaway**: Master thread creation and control for concurrent Java apps! 🎉

