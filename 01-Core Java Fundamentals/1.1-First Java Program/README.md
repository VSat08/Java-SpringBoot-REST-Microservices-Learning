# Chapter 1.1: First Java Program & Command Line Arguments

### Table of Contents
- [Program Structure](#program-structure)
- [Command Line Arguments](#command-line-arguments)
- [Working with Arguments](#working-with-arguments)
- [Error Handling](#error-handling)
- [Best Practices](#best-practices)
- [Advanced Example: Sum Calculator](#advanced-example-sum-calculator)

### Program Structure

Java programs are built around classes that contain data and methods. Every executable Java application requires a `main` method as its entry point.

#### Basic Program Structure
```java
public class FirstJava {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

#### Compilation and Execution
```bash
# Compile the Java file
javac FirstJava.java

# Run the compiled program
java FirstJava
```

### Command Line Arguments

Command line arguments allow you to pass data to your program during execution. They provide a flexible way to modify program behavior without changing the code.

#### Basic Usage Example
```java
public class CommandLine {
    public static void main(String[] args) {
        // Access first argument passed to program
        System.out.println("I entered the name: " + args[0]);
    }
}
```

To run with arguments:
```bash
java CommandLine.java Sam
# Output: I entered the name: Sam
```

### Working with Arguments

#### Numerical Arguments
When working with numbers passed as arguments, you'll need to convert them from strings to numerical types.

```java
public class Calculator {
    public static void main(String[] args) {
        // Convert string arguments to integers
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        
        int sum = a + b;
        System.out.println("Sum = " + sum);
    }
}
```

#### Type Conversion Methods
- `Integer.parseInt()`: Convert to integer
- `Double.parseDouble()`: Convert to decimal
- `Boolean.parseBoolean()`: Convert to boolean

### Error Handling

#### 1. Array Index Out of Bounds
Handle missing arguments gracefully:

```java
public class SafeArgAccess {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("First argument: " + args[0]);
        } else {
            System.out.println("No arguments provided");
        }
    }
}
```

#### 2. Number Format Exceptions
Handle invalid number formats:

```java
public class SafeNumberConversion {
    public static void main(String[] args) {
        try {
            int number = Integer.parseInt(args[0]);
            System.out.println("Number entered: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
    }
}
```

### Best Practices

1. **Argument Validation**
   - Always check `args.length` before accessing arguments
   - Validate argument formats before conversion
   - Provide clear error messages

2. **Program Usage**
   - Include usage instructions when no arguments are provided
   - Use descriptive argument names
   - Document expected argument formats

3. **Error Handling**
   - Implement try-catch blocks for numeric conversions
   - Provide meaningful error messages
   - Handle edge cases (empty input, invalid formats)

4. **Code Organization**
   - Keep argument parsing separate from business logic
   - Use constants for fixed values
   - Comment code appropriately

### Advanced Example: Sum Calculator

Here's a program that demonstrates how to work with multiple command line arguments using a loop:

```java
public class Add {
    public static void main(String[] args) {
        // Program to read many numbers from keyboard and find sum of all those numbers
        int sum = 0;
        
        // Loop through all arguments using array length
        for(int i = 0; i < args.length; i++) {
            // Convert each string argument to integer and add to sum
            sum = sum + Integer.parseInt(args[i]);
        }
        
        // Print the sum along with the count of numbers
        System.out.println("Sum of all the " + args.length + " numbers is " + sum);
    }
}
```

#### How to Use the Sum Calculator:
```bash
# Compile the program
javac Add.java

# Run with any number of integer arguments
java Add 1 2 3 4 5 6 7 8 9 10
# Output: Sum of all the 10 numbers is 55

# Try with different numbers
java Add 1 2 3 4 5 6
# Output: Sum of all the 6 numbers is 21

# You can pass any number of arguments
java Add 10 20 30 40 50 60 70 80 90 100
# Output: Sum of all the 10 numbers is 550
```

#### Key Points:
1. The program uses `args.length` to determine how many arguments were passed
2. Each argument is converted from string to integer using `Integer.parseInt()`
3. The loop processes all arguments automatically, regardless of how many are provided
4. The program displays both the count of numbers and their sum

#### Important Notes:
- All arguments must be valid integers
- If any argument cannot be converted to an integer, the program will throw a `NumberFormatException`
- You can pass any number of arguments; the program will sum them all

---
**Note**: This is part of Chapter 1.1 of the Java Programming course. For more chapters and examples, please check the repository's main page.