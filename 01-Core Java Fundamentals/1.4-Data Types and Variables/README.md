# 1.4: Data Types and Variables

## Primitive Data Types in Java

Java provides 8 primitive data types, which are the building blocks for all other data types. These primitive types are **fixed size** and **efficient** in terms of memory. Here's a comprehensive overview:

### byte

-   **Size**: 1 byte (8 bits)
-   **Range**: -128 to 127
-   **Use Case**: Useful for saving memory in large arrays, particularly when you know the data fits within this range (e.g., status codes, flags).

```java
byte b = 100;  // Valid, as it is within the range of -128 to 127
```

### short

-   **Size**: 2 bytes (16 bits)
-   **Range**: -32,768 to 32,767
-   **Use Case**: When you need a larger range than byte, but still want to save memory compared to int.

```java
short s = 25000;  // Valid, as it is within the range of -32,768 to 32,767
```

### int

-   **Size**: 4 bytes (32 bits)
-   **Range**: -2^31 to 2^31 - 1 (-2,147,483,648 to 2,147,483,647)
-   **Use Case**: Most commonly used for integer values in Java programs. It covers a wide range of values.

```java
int i = 123456;  // Valid, as it is within the range of an int
```

### long

-   **Size**: 8 bytes (64 bits)
-   **Range**: -2^63 to 2^63 - 1 (-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807)
-   **Use Case**: Used for very large integers, where int is not sufficient.

```java
long l = 9223372036854775807L;  // The L suffix specifies it's a long literal
```

### float

-   **Size**: 4 bytes (32 bits)
-   **Range**: Approx. ±3.4e−38 to ±3.4e+38
-   **Use Case**: Stores fractional numbers with single precision. Used for less precise decimal values (e.g., storing currency values where low precision is acceptable).

```java
float f = 3.14f;  // 'f' suffix is required to denote a float literal
```

### double

-   **Size**: 8 bytes (64 bits)
-   **Range**: Approx. ±1.8e−308 to ±1.8e+308
-   **Use Case**: The default data type for decimal values. Provides double precision and is widely used for precise calculations (e.g., scientific calculations).

```java
double d = 3.141592653589793;  // Double precision
```

### char

-   **Size**: 2 bytes (16 bits)
-   **Range**: 0 to 65,535 (Unicode characters)
-   **Use Case**: Stores a single character (e.g., 'A', 'B', '1').

```java
char c = 'A';  // Stores a character 'A'
```

### boolean

-   **Size**: 1 bit (true or false)
-   **Use Case**: Represents true/false values. Frequently used in conditions like loops and if-statements.

```java
boolean isActive = true;  // Stores the boolean value true
```

## Variable Declaration and Initialization

In Java, variables must be declared with a specific data type, and they must be initialized (given a value) before they are used.

### Basic Declaration

Declaring a variable simply involves specifying the data type followed by the variable name:

```java
int age;  // Declaring an integer variable
```

### Initialization

Variables must be initialized with a value before they can be used. You can initialize a variable during or after declaration:

```java
age = 25;  // Initializing the variable 'age' with the value 25
```

Alternatively, you can declare and initialize a variable in one step:

```java
int age = 25;  // Declaring and initializing 'age' in one line
```

**Important Note**: In Java, uninitialized variables cause errors when used in operations, so always initialize variables before using them.

## Typecasting in Java

Typecasting refers to converting a variable from one data type to another. There are two main types of typecasting in Java:

### 1. Implicit Casting (Widening)

Implicit casting is done automatically by the compiler when you convert a smaller data type to a larger one. This is safe because no data is lost during the conversion.

**Common Implicit Casts**:

-   byte to short
-   int to long
-   float to double

Example:

```java
int num = 10;
double result = num;  // Implicit casting from int to double
System.out.println(result);  // Output: 10.0
```

In this case, Java automatically converts the int type to a double because double has a larger range.

### 2. Explicit Casting (Narrowing)

Explicit casting is required when you convert a larger data type to a smaller one. Since data might be lost (for example, when converting a double to an int), Java requires you to manually specify the cast.

**Common Explicit Casts**:

-   double to int
-   long to int
-   float to byte

Example:

```java
double pi = 3.14159;
int piInt = (int) pi;  // Explicit casting from double to int
System.out.println(piInt);  // Output: 3 (fractional part is discarded)
```

Note that in this example, the fractional part .14159 is lost when converting from double to int because the int type cannot store decimal values.
