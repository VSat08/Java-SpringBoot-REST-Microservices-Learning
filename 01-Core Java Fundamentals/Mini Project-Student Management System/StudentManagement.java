package edu.mit.sms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudentManagement {
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "StudentData.txt";
        readStudentData(fileName);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students by Name (Ascending)");
            System.out.println("5. Display Students by GPA (Descending)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    removeStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    displayStudentsByName();
                    break;
                case 5:
                    displayStudentsByGPA();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Student Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void readStudentData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double gpa = Double.parseDouble(data[2].trim());
                String city = data[3].trim();
                String university = "MIT";

                Student student = new Student(id, name, gpa, city, university);
                students.add(student);
            }
            System.out.println("Student data loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the student data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the student data: " + e.getMessage());
        }
    }

    public static void addStudent(Scanner scanner) {
        System.out.println("----- Add Student -----");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        Student student = new Student(id, name, gpa, city, "MIT");
        students.add(student);

        System.out.println("Student added successfully.");
    }

    public static void removeStudent(Scanner scanner) {
        System.out.println("----- Remove Student -----");
        System.out.print("Enter the ID of the student to remove: ");
        int id = scanner.nextInt();

        boolean found = false;
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("----- Update Student -----");
        System.out.print("Enter the ID of the student to update: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter Name: ");
                String name = scanner.next();
                if (!name.isEmpty()) {
                    student.setName(name);
                }
                System.out.print("Enter GPA: ");
                String gpaInput = scanner.next();
                if (!gpaInput.isEmpty()) {
                    double gpa = Double.parseDouble(gpaInput);
                    student.setGpa(gpa);
                }
                System.out.print("Enter City: ");
                String city = scanner.next();
                if (!city.isEmpty()) {
                    student.setCity(city);
                }
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void displayStudentsByName() {
        System.out.println("----- Students by Name (Ascending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparing(Student::getName));
        System.out.println("ID\tName\tGPA\tCity\tUniverity");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudentsByGPA() {
        System.out.println("----- Students by GPA (Descending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparingDouble(Student::getGpa).reversed());
        System.out.println("ID\tName\tGPA\tCity\tUniverity");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
