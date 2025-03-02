# 1.15: Multithreading

## Introduction
Welcome to **Section 1.15: Multithreading** 🚀

 Multithreading in Java enables concurrent execution, boosting performance and responsiveness by running multiple threads within a process. This guide explores thread fundamentals—creation via `Thread` or `Runnable`, lifecycle, control methods (`sleep()`, `join()`, `currentThread()`), synchronization for order, and inter-thread communication with `wait()` and `notify()`. We’ll cover processes vs. threads, advantages, and practical techniques, using examples and a beginner-friendly approach. Master concurrency, thread safety, and communication in Java! 

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
    - [Synchronization in Multithreading](#27-synchronization-in-multithreading)
        - [Synchronization Basics](#271-synchronization-basics)
        - [Implementing Synchronization](#272-implementing-synchronization)
    - [Inter-Thread Communication](#28-inter-thread-communication)
        - [Inter-Thread Communication Basics](#281-inter-thread-communication-basics)
        - [Implementing Inter-Thread Communication](#282-implementing-inter-thread-communication)
    - [Advantages of Multithreading](#29-advantages-of-multithreading)
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
*Multithreading* is a multitasking technique where multiple *threads*—lightweight subunits of a *process*—execute parallelly within a single process. A process is a program under execution, while threads share its resources, enabling efficient concurrency. In Java, multithreading leverages CPU power for simultaneous tasks, with synchronization and inter-thread communication ensuring order and cooperation.

#### Definitions
- **Process**: A program or app under execution, heavyweight with separate memory.
- **Thread**: A lightweight subunit of a process, sharing its address space.

#### Real-World Example
A ticket booking app: one process runs the app, with threads handling concurrent tasks (seat selection, payment) in sync, communicating to deposit funds before withdrawal.

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
- Ensuring order and cooperation with synchronization and communication.

#### Analogy
Multithreading is like a chef (process) delegating tasks to sous-chefs (threads)—chopping, cooking, plating—in sync and communicating to ensure ingredients are ready before cooking.

---

## 2. Multithreading in Java

### 2.1 Core Concepts
Multithreading in Java, via `java.lang.Thread` and `java.lang.Runnable`, allows threads to run concurrently within a process, managed by the JVM’s *thread scheduler*. It starts with a *main thread*, with additional threads created via `Thread` or `Runnable`, controlled by methods like `sleep()`, `join()`, and synchronized with `wait()` and `notify()` for communication.

### 2.2 Processes vs. Threads
#### Process-Based Multitasking (Multiprocessing)
- **Definition**: Multiple processes run parallelly, each with separate memory/resources.
- **Mechanism**: CPU switches, updating memory maps, registers, and process control blocks (PCB).
- **Traits**: Heavyweight, high switching cost.

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
A *thread* is the smallest processing unit within a process, executing a task (critical section) via `run()`, managed by the scheduler.

#### Role
- Execute tasks parallelly within a process.
- Communicate and synchronize for ordered execution.

#### Snippet: Main Thread
```java
class MultithreadDemo {
    public static void main(String[] args) {
        System.out.println("Main thread running");
    }
}
// Output: Main thread running
```

### 2.4 Creating Threads
Threads are created via two methods: extending `Thread` or implementing `Runnable`.

#### 2.4.1 Extending Thread Class
Extend `Thread`, override `run()`, and call `start()` to create threads directly.

##### Snippet: Extending Thread
```java
class Multi extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
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
// Output: Interleaved "Thread-0: 1" to "Thread-1: 5"
```

#### 2.4.2 Implementing Runnable Interface
Implement `Runnable`, define `run()`, pass to a `Thread` constructor, and call `start()`.

##### Snippet: Implementing Runnable
```java
class RunnableDemo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

class RunnableTest {
    public static void main(String[] args) {
        Runnable r1 = new RunnableDemo();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
    }
}
// Output: Interleaved "Thread-0: 1" to "Thread-1: 5"
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
         +--> |    Blocked      | --> Resources available / Notify
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
`sleep()` pauses the current thread for a specified time (milliseconds or milliseconds + nanoseconds), moving it to Blocked state. It’s static and throws `InterruptedException`.

##### Snippet: Sleep Method
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
    public static void main(String[] args) {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.start();
        t2.start();
    }
}
// Output: Thread-0: 1, Thread-1: 1, Thread-0: 2, ... (uniform interleaving)
```

#### 2.6.2 Join Method
`join()` makes other threads wait until the joined thread completes (indefinite) or for a specified time. It’s non-static and throws `InterruptedException`.

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
// Output: Thread-0: 1-3, then Thread-1: 1-5, Thread-0: 4-5
```

#### 2.6.3 CurrentThread and Attributes
- **`currentThread()`**: Static, returns the executing thread.
- **`getName()` / `setName()`**: Get/set thread name (default: "Thread-0").
- **`getPriority()` / `setPriority()`**: Get/set priority (1-10, default 5: `NORM_PRIORITY`).

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
    public static void main(String[] args) {
        Multi t1 = new Multi();
        Multi t2 = new Multi();
        t1.setPriority(Thread.MAX_PRIORITY); // 10
        t2.setName("Second");
        t1.start();
        t2.start();
    }
}
// Output: Thread-0 (Priority 10): 1, Second (Priority 5): 1, ...
```

### 2.7 Synchronization in Multithreading
Synchronization ensures ordered thread access to shared resources, preventing interference.

#### 2.7.1 Synchronization Basics
*Synchronization* controls access to shared resources using the `synchronized` keyword, implementing a locking protocol. Only one thread holds the lock, others wait until released.

- **Purpose**: Prevent thread interference, ensure consistency.
- **Mechanism**: Lock acquisition/release for resource access.

#### 2.7.2 Implementing Synchronization
Use `synchronized` on methods or blocks to enforce order.

##### Snippet: Synchronized Method
```java
class Table {
    synchronized void printTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " * " + i + " = " + (n * i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}

class First extends Thread {
    Table t;
    First(Table t) { this.t = t; }
    public void run() { t.printTable(19); }
}

class Second extends Thread {
    Table t;
    Second(Table t) { this.t = t; }
    public void run() { t.printTable(17); }
}

class SyncTest {
    public static void main(String[] args) {
        Table t = new Table();
        First t1 = new First(t);
        Second t2 = new Second(t);
        t1.start();
        t2.start();
    }
}
// Output: 19 * 1 = 19 to 19 * 10 = 190, then 17 * 1 = 17 to 17 * 10 = 170 (ordered)
```

### 2.8 Inter-Thread Communication
Inter-thread communication enables synchronized threads to cooperate using `wait()`, `notify()`, and `notifyAll()`.

#### 2.8.1 Inter-Thread Communication Basics
*Inter-thread communication* (cooperation) allows synchronized threads to signal each other, avoiding starvation. Defined in `Object`, it uses:

- **`wait()`**: Releases lock, waits indefinitely or for a time (milliseconds or milliseconds + nanoseconds).
- **`notify()`**: Wakes one waiting thread.
- **`notifyAll()`**: Wakes all waiting threads.

#### 2.8.2 Implementing Inter-Thread Communication
Use `wait()` to pause a thread and `notify()`/`notifyAll()` to signal completion, within synchronized blocks.

##### Snippet: Inter-Thread Communication
```java
class Customer {
    int balance;
    Customer(int balance) { this.balance = balance; }
    int getBalance() { return balance; }

    synchronized void withdraw(int amount) {
        System.out.println("Going to withdraw: " + amount);
        if (amount > balance) {
            System.out.println("Less balance; waiting for deposit...");
            try {
                wait(); // Release lock, wait
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        balance -= amount;
        System.out.println("Withdraw completed. New balance: " + balance);
    }

    synchronized void deposit(int amount) {
        System.out.println("Going to deposit: " + amount);
        balance += amount;
        System.out.println("Deposit completed. New balance: " + balance);
        notify(); // Wake one waiting thread
    }
}

class InterThreadDemo {
    public static void main(String[] args) {
        Customer c1 = new Customer(25000);
        new Thread() {
            public void run() { c1.withdraw(30000); }
        }.start();
        new Thread() {
            public void run() { c1.deposit(25000); }
        }.start();
    }
}
// Output: Going to withdraw: 30000
//         Less balance; waiting for deposit...
//         Going to deposit: 25000
//         Deposit completed. New balance: 50000
//         Withdraw completed. New balance: 20000
```

>[!NOTE] 
>`wait()`, `notify()`, and `notifyAll()` must be called within synchronized blocks; they throw `InterruptedException`.

### 2.9 Advantages of Multithreading
Multithreading enhances applications by:

- **Parallel Processing**: Executes tasks concurrently (e.g., 1000 bookings in 3 minutes).
- **Responsiveness**: Reduces wait times (e.g., e-commerce checkout).
- **CPU Utilization**: Uses idle CPU time efficiently.
- **Order Control**: Ensures consistency and cooperation with synchronization and communication.

#### Example
1000 users book tickets in 3 minutes—not 3000—due to parallel, synchronized, and communicating threads.

---

## 3. Practical Guidance

### 3.1 Best Practices
- Use `sleep()` for uniform access; `join()` for prioritization.
- Leverage `currentThread()` for dynamic thread logic.
- Synchronize shared resources for order and safety.
- Use `wait()` and `notify()` for thread cooperation (e.g., producer-consumer).
- Prefer anonymous inner classes for concise thread creation when inheritance isn’t needed.

### 3.2 Common Pitfalls
- Calling `run()` instead of `start()`, losing parallelism.
- Starting a thread twice, causing `IllegalThreadStateException`.
- Ignoring `InterruptedException` in `sleep()`, `join()`, or `wait()`.
- Over-synchronizing, reducing performance.
- Missing communication, leading to thread starvation.

### 3.3 Practice Exercises
1. Create two threads with `sleep(500)`, synchronized, printing names and numbers.
2. Use `join()` to order three threads printing tables sequentially.
3. Implement a producer-consumer scenario with `wait()` and `notify()`.
4. Use anonymous inner classes to print A-Z and a-z, synchronized with communication.
5. Set priorities and names, display with `currentThread()` in a synchronized block.

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
Multithreading in Java enables parallel thread execution within a process, created via `Thread` extension or `Runnable`, transitioning through five states. Control methods (`sleep()`, `join()`, `currentThread()`) manage execution, `synchronized` ensures order, and `wait()`/`notify()` facilitate cooperation, preventing interference and starvation.

#### Highlights
- **Creation**: `Thread` or `Runnable` for threads.
- **Control**: `sleep()`, `join()`, `currentThread()` for timing and identity.
- **Synchronization**: `synchronized` for order and safety.
- **Communication**: `wait()`, `notify()` for thread cooperation.
- **Takeaway**: Master thread creation, control, sync, and communication for robust Java concurrency! 🎉

