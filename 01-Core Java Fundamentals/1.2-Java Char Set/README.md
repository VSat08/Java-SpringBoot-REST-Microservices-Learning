# Chapter 1.2: ASCII Values and Java Character Set

### Table of Contents
- [Introduction](#introduction)
- [ASCII Character Encoding](#ascii-character-encoding)
- [Java Character Set](#java-character-set)
- [Unicode Compatibility](#unicode-compatibility)
- [Example: Printing ASCII Values](#example-printing-ascii-values)
- [Working with Characters](#working-with-characters)
- [Error Handling](#error-handling)
- [Best Practices](#best-practices)
- [Advanced Example: ASCII Table Generator](#advanced-example-ascii-table-generator)

### Introduction

Characters in Java are internally stored as numbers, following the ASCII and Unicode standards. This chapter explores ASCII values, Java’s character set, and how they relate to Unicode compatibility.

### ASCII Character Encoding

ASCII (American Standard Code for Information Interchange) assigns numerical values to characters. It includes:
- Printable characters (letters, digits, symbols)
- Control characters (newline, tab, etc.)
- 7-bit encoding (0-127 values)

#### Common ASCII Values:
| Character | ASCII Code |
|-----------|------------|
| A | 65 |
| B | 66 |
| a | 97 |
| b | 98 |
| 0 | 48 |
| 1 | 49 |
| Space | 32 |
| Enter (Newline) | 10 |

### Java Character Set

Java follows the Unicode standard and supports a wider range of characters beyond ASCII. Each character in Java is represented using `char`, which stores Unicode values.

#### Declaring and Using Characters:
```java
public class CharExample {
    public static void main(String[] args) {
        char letter = 'A';
        char number = '1';
        char symbol = '@';
        
        System.out.println("Letter: " + letter);
        System.out.println("Number: " + number);
        System.out.println("Symbol: " + symbol);
    }
}
```

### Unicode Compatibility

Unicode extends ASCII and includes:
- Latin, Greek, Arabic, Hindi, Chinese, etc.
- Uses `\u` escape sequences to represent characters.
- Java’s `char` uses 16-bit (UTF-16) encoding.

#### Unicode Example:
```java
public class UnicodeExample {
    public static void main(String[] args) {
        char hindiChar = '\u0905';  // Unicode for 'अ'
        System.out.println("Hindi Letter: " + hindiChar);
    }
}
```

### Example: Printing ASCII Values

The following program prints ASCII values of characters:
```java
public class AsciiValues {
    public static void main(String[] args) {
        char ch = 'A';
        int ascii = (int) ch;
        System.out.println("ASCII Value of " + ch + " is: " + ascii);
    }
}
```

#### Execution:
```bash
javac AsciiValues.java
java AsciiValues
# Output: ASCII Value of A is: 65
```

### Working with Characters

#### Converting Character to ASCII:
```java
public class CharToAscii {
    public static void main(String[] args) {
        char ch = 'z';
        System.out.println("ASCII value of " + ch + " is " + (int) ch);
    }
}
```

#### ASCII to Character Conversion:
```java
public class AsciiToChar {
    public static void main(String[] args) {
        int ascii = 100;
        System.out.println("Character for ASCII " + ascii + " is " + (char) ascii);
    }
}
```

### Error Handling

#### Handling Out of Range ASCII Values:
```java
public class SafeAscii {
    public static void main(String[] args) {
        try {
            int ascii = Integer.parseInt(args[0]);
            if (ascii < 0 || ascii > 127) {
                System.out.println("Error: Value out of ASCII range (0-127)");
            } else {
                System.out.println("Character: " + (char) ascii);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Enter a valid integer");
        }
    }
}
```

### Best Practices

1. **Use Unicode for Multi-language Support**
   - Prefer Unicode (`\u` format) for international text handling.
2. **Avoid Hardcoded ASCII Values**
   - Use `Character.getNumericValue()` instead of direct ASCII conversions.
3. **Validate Character Inputs**
   - Ensure user input is within a valid character range.
4. **Use UTF-8 Encoding**
   - Java strings use UTF-16, but input/output should be UTF-8 compatible.

### Advanced Example: ASCII Table Generator

This program prints the ASCII table for characters 32-126:
```java
public class AsciiTable {
    public static void main(String[] args) {
        System.out.println("ASCII Table: Characters from 32 to 126");
        for (int i = 32; i <= 126; i++) {
            System.out.println(i + " : " + (char) i);
        }
    }
}
```

#### Execution:
```bash
javac AsciiTable.java
java AsciiTable
```

#### Sample Output:
```
ASCII Table: Characters from 32 to 126
32 :  
33 : !
34 : "
35 : #
...
126 : ~
```

---
**Note**: This is part of Chapter 1.2 of the Java Programming course. For more chapters and examples, please check the repository's main page.

