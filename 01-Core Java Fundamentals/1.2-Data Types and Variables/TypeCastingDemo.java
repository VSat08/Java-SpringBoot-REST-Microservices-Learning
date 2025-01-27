public class TypeCastingDemo {
    public static void main(String[] args) {
        // Implicit Casting (Widening)
        int num = 10;
        double doubleNum = num; // int to double
        System.out.println("Implicit Casting (int to double): " + doubleNum);

        // Explicit Casting (Narrowing)
        double decimalValue = 9.78;
        int intValue = (int) decimalValue; // double to int
        System.out.println("Explicit Casting (double to int): " + intValue);
    }
}
