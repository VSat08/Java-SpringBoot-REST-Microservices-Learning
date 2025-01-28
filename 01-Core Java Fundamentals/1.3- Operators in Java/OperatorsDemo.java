public class OperatorsDemo {
    public static void main(String[] args) {
        // Arithmetic Operators
        int a = 10, b = 5;
        System.out.println("Arithmetic Operators:");
        System.out.println("a + b = " + (a + b)); // Addition
        System.out.println("a - b = " + (a - b)); // Subtraction
        System.out.println("a * b = " + (a * b)); // Multiplication
        System.out.println("a / b = " + (a / b)); // Division
        System.out.println("a % b = " + (a % b)); // Modulus (remainder)

        // Relational Operators
        System.out.println("\nRelational Operators:");
        System.out.println("a > b: " + (a > b)); // Greater than
        System.out.println("a < b: " + (a < b)); // Less than
        System.out.println("a == b: " + (a == b)); // Equal to
        System.out.println("a != b: " + (a != b)); // Not equal to

        // Logical Operators
        boolean x = true, y = false;
        System.out.println("\nLogical Operators:");
        System.out.println("x && y: " + (x && y)); // Logical AND
        System.out.println("x || y: " + (x || y)); // Logical OR
        System.out.println("!x: " + (!x)); // Logical NOT

        // Bitwise Operators
        int c = 6, d = 3; // Binary: c = 110, d = 011
        System.out.println("\nBitwise Operators:");
        System.out.println("c & d: " + (c & d)); // Bitwise AND
        System.out.println("c | d: " + (c | d)); // Bitwise OR
        System.out.println("c ^ d: " + (c ^ d)); // Bitwise XOR
        System.out.println("~c: " + (~c)); // Bitwise Complement

        // Assignment Operators
        System.out.println("\nAssignment Operators:");
        int e = 10;
        e += 5; // e = e + 5
        System.out.println("e += 5: " + e);
        e *= 2; // e = e * 2
        System.out.println("e *= 2: " + e);

        // Unary Operators
        System.out.println("\nUnary Operators:");
        int f = 5;
        System.out.println("++f: " + (++f)); // Pre-increment
        System.out.println("f++: " + (f++)); // Post-increment
        System.out.println("f after post-increment: " + f);
        System.out.println("--f: " + (--f)); // Pre-decrement
        System.out.println("f--: " + (f--)); // Post-decrement
        System.out.println("f after post-decrement: " + f);

        // Ternary Operator
        System.out.println("\nTernary Operator:");
        int result = (a > b) ? a : b;
        System.out.println("Result of (a > b) ? a : b is: " + result);
    }
}
