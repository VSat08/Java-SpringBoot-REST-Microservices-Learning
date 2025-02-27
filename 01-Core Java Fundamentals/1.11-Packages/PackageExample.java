
// import java.util.*;         // implicit import
import java.util.Date; // explicit import
import java.util.Scanner;

public class PackageExample {

    public static void main(String[] args) {

        // java.util.Date d = new java.util.Date(); // Fully Qualified Name
        Date d = new Date();
        System.out.println(d); // d.toString()


        Scanner in = new Scanner(System.in);
        System.out.println("Enter your good Name");
        String name = in.next();

        System.out.println("Hello Mr/Ms." + name);
        
        in.close();
    }
}