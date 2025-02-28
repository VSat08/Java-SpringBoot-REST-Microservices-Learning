import java.util.*;

public class ExceptionDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter two nos:");
        int a = 0, b = 0;
        try {
            System.out.println("Enter Number 1: ");
            a = in.nextInt(); // int - java.util.InputMismatchException
            System.out.println("Enter Number 2: ");
            b = in.nextInt(); // int -

            int res = a / b; // throw new java.lang.ArithmeticException();
            System.out.println("Result = " + res);
        } catch (Exception e) {
            System.err.println("------------------------------------");//
            System.err.println("Oops, I think we you  and we landed into a serious error.");//
            System.err.println("Error Type: " + e);//
            System.err.println("------------------------------------");//
        }

        int sum = a + b;
        System.out.println("Sum = " + sum);

        int prod = a * b;
        System.out.println("Prodcuct = " + prod);

        System.out.println("Rest of the code....follows!");

        in.close();
    }
}