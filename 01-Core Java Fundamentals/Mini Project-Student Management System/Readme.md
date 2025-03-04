# Student Management System - Project Manual

## Overview
The **Student Management System (SMS)** is a console-based Java application designed to manage student records using Core Java features. It loads student data from a text file (`student_data.txt`) and offers a menu-driven interface to add, remove, update, and display student details, sorted by name (ascending) or GPA (descending). This project leverages collections, file I/O, lambda expressions, and packages, serving as a practical demonstration of Java fundamentals.

>[!NOTE]  
>Before starting, ensure you’ve completed **Core Java Fundamentals** (collections, file handling, packages, lambda expressions) to understand and work with this project effectively.

---

## Project Features
- **Data Loading**: Reads student records (ID, name, GPA, city) from `student_data.txt`.
- **Menu Options**:
  1. Add a new student.
  2. Remove a student by ID.
  3. Update a student’s name, GPA, or city.
  4. Display students by name (ascending).
  5. Display students by GPA (descending).
  6. Exit the application.
- **Sorting**: Uses lambda expressions for name and GPA ordering.
- **Structure**: Organized in the `edu.mit.sms` package with a POJO (`Student.java`) and main class (`StudentManagement.java`).

---

## Prerequisites
- **Java Version**: JDK 8 or higher (e.g., Java 17 recommended).
- **Tools**: Command-line tools (e.g., `javac`, `java`) or an IDE (e.g., IntelliJ IDEA, Eclipse).
- **Knowledge**: Basic understanding of Java collections (`ArrayList`), file I/O (`BufferedReader`), lambda expressions, and packages.

---

## Setup Instructions
1. **Directory Setup**:
   - Create a folder: `Mini Project-Student Management System`.
   - Inside, create a subfolder: `edu/sms` for the package structure.
   - Place `student_data.txt` in the root folder.

2. **File Structure**:
   ```
   Mini Project-Student Management System/
   ├── edu/
   │   └── sms/
   │       ├── Student.java
   │       └── StudentManagement.java
   └── student_data.txt
   ```

3. **Create `student_data.txt`**:
   - Format: Comma-separated values (ID, name, GPA, city).
   - Example content:
     ```
     123,ABC,8.5,Pune
     101,LJ,6.9,Noida
     110,OR,7.1,Hyderabad
     100,MVR,9.5,Mumbai
     ```

4. **Source Files**:
   - **`Student.java`** (POJO):
     ```java
     package edu.mit.sms;

     public class Student {
         private int id;
         private String name;
         private double gpa;
         private String city;
         private String university = "MIT";

         public Student(int id, String name, double gpa, String city) {
             this.id = id;
             this.name = name;
             this.gpa = gpa;
             this.city = city;
         }

         public int getId() { return id; }
         public void setId(int id) { this.id = id; }
         public String getName() { return name; }
         public void setName(String name) { this.name = name; }
         public double getGpa() { return gpa; }
         public void setGpa(double gpa) { this.gpa = gpa; }
         public String getCity() { return city; }
         public void setCity(String city) { this.city = city; }
         public String getUniversity() { return university; }

         @Override
         public String toString() {
             return "ID: " + id + ", Name: " + name + ", GPA: " + gpa + ", City: " + city + ", University: " + university;
         }
     }
     ```
   - **`StudentManagement.java`** (Main Class):
     ```java
     package edu.mit.sms;

     import java.util.*;
     import java.io.*;

     public class StudentManagement {
         private static List<Student> students = new ArrayList<>();
         private static Scanner sc = new Scanner(System.in);

         public static void main(String[] args) {
             String fileName = "student_data.txt";
             readStudentData(fileName);
             while (true) {
                 displayMenu();
                 int choice = sc.nextInt();
                 sc.nextLine(); // Consume newline
                 switch (choice) {
                     case 1: addStudent(sc); break;
                     case 2: removeStudent(sc); break;
                     case 3: updateStudent(sc); break;
                     case 4: displayStudentsByName(); break;
                     case 5: displayStudentsByGpa(); break;
                     case 6: System.out.println("Thank you for using SMS"); return;
                     default: System.out.println("Invalid choice, try again");
                 }
             }
         }

         private static void displayMenu() {
             System.out.println("1. Add Student\n2. Remove Student\n3. Update Student\n4. Display by Name\n5. Display by GPA\n6. Exit\nEnter choice:");
         }

         private static void readStudentData(String fileName) {
             try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                 String line;
                 while ((line = br.readLine()) != null) {
                     String[] data = line.split(",");
                     int id = Integer.parseInt(data[0]);
                     String name = data[1];
                     double gpa = Double.parseDouble(data[2]);
                     String city = data[3];
                     students.add(new Student(id, name, gpa, city));
                 }
                 System.out.println("Student data loaded successfully");
             } catch (IOException e) {
                 System.out.println("Error reading file: " + e.getMessage());
             }
         }

         private static void addStudent(Scanner sc) {
             System.out.println("Enter ID:");
             int id = sc.nextInt();
             sc.nextLine();
             System.out.println("Enter Name:");
             String name = sc.nextLine();
             System.out.println("Enter GPA:");
             double gpa = sc.nextDouble();
             sc.nextLine();
             System.out.println("Enter City:");
             String city = sc.nextLine();
             students.add(new Student(id, name, gpa, city));
             System.out.println("Student added successfully");
         }

         private static void removeStudent(Scanner sc) {
             System.out.println("Enter ID of student to remove:");
             int id = sc.nextInt();
             students.removeIf(s -> s.getId() == id);
             System.out.println("Student removed successfully");
         }

         private static void updateStudent(Scanner sc) {
             System.out.println("Enter ID of student to update:");
             int id = sc.nextInt();
             sc.nextLine();
             for (Student s : students) {
                 if (s.getId() == id) {
                     System.out.println("Enter new Name:");
                     s.setName(sc.nextLine());
                     System.out.println("Enter new GPA:");
                     s.setGpa(sc.nextDouble());
                     sc.nextLine();
                     System.out.println("Enter new City:");
                     s.setCity(sc.nextLine());
                     System.out.println("Student updated successfully");
                     return;
                 }
             }
             System.out.println("Student not found");
         }

         private static void displayStudentsByName() {
             Collections.sort(students, Comparator.comparing(Student::getName));
             students.forEach(System.out::println);
         }

         private static void displayStudentsByGpa() {
             Collections.sort(students, Comparator.comparingDouble(Student::getGpa).reversed());
             students.forEach(System.out::println);
         }
     }
     ```

---

## Building and Running

### Compilation
From the project root directory:
```bash
javac -d .  Student.java
javac -d . StudentManagement.java
```

### Execution
```bash
java edu.mit.sms.StudentManagement
```

### Expected Output
- Initial: "Student data loaded successfully" with `student_data.txt` data.
- Menu: Displays options 1-6; input a number to proceed.
- Example:
  - Choice 4: Lists students alphabetically by name.
  - Choice 5: Lists students by GPA (highest first).

---

## Usage Guide
1. **Start**: Run the program; it loads data from `student_data.txt`.
2. **Menu Options**:
   - **1 - Add**: Enter ID, name, GPA, city to add a student.
   - **2 - Remove**: Enter an ID to remove a student.
   - **3 - Update**: Enter an ID, then new name, GPA, city to update.
   - **4 - Display by Name**: Shows students sorted alphabetically.
   - **5 - Display by GPA**: Shows students sorted by GPA (descending).
   - **6 - Exit**: Closes the application.
3. **Input Format**: Follow prompts (e.g., integer for ID, double for GPA).

---

## Troubleshooting
- **"File Not Found"**: Ensure `student_data.txt` is in the root directory with correct format (ID,name,GPA,city).
- **"Class Not Found"**: Verify compilation and use `java edu.mit.sms.StudentManagement` (no spaces, use dots).
- **Input Errors**: Enter correct types (e.g., `123` for ID, not text; `9.8` for GPA, not letters).
- **No Output**: Check `student_data.txt` exists and isn’t empty.

---

## Project Notes
- **Package**: `edu.mit.sms` (Educational Student Management System, default university: MIT).
- **Data**: Stored in an `ArrayList<Student>`, manipulates dynamically.
- **Sorting**: Uses `Collections.sort()` with lambda-based `Comparator` for name and GPA.
- **Limitations**: Console-based, no persistence beyond runtime (updates don’t save to file).

---

## Additional Resources
- **Java Collections**: [Docs](https://docs.oracle.com/javase/tutorial/collections/)
- **File I/O**: [Docs](https://docs.oracle.com/javase/tutorial/essential/io/)
- **Lambda Expressions**: [Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)

---

## About This Project
This mini project integrates Core Java concepts—collections, file handling, lambda expressions, and packages—into a functional Student Management System. It’s a stepping stone to apply theoretical knowledge practically, preparing you for more complex Java applications.

