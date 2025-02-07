public class ConstantsExample {
    public static void main(String[] args) {
        final double INTEREST_RATE = 8.5;
        System.out.println("Interest Rate: " + INTEREST_RATE);

        // Uncommenting the next line will cause a compile-time error
        // INTEREST_RATE = 9.0; // Error: cannot assign a value to final variable
    }
}