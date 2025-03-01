
/*
* Four Ways of Reading Data
    1. Command Line
    2. Scanner
    3. BufferedReader
    4. Console
    */

import java.io.*;

public class ReadingData {
    public static void main(String[] args) throws IOException {

        // Reading From Command Line
        System.out.println("Your Good name: " + args[0] );


        // // Reading using Scanner
        // Scanner in = new Scanner(System.in);
        // System.out.println("Enter name: ");
        // String name = in.next();
        // System.out.println("Entered Name:" + name);

        // // Reading using Scanner
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // System.out.println("Enter age: ");
        // int age = Integer.parseInt(br.readLine()); // for reading integers
        // System.out.println("Entered Age:" + age);

        // // Reading using Console
        // Console c = System.console(); // no new keyword
        // System.out.println("Enter City");
        // String City = c.readLine();
        // System.out.println("Enter pincode");
        // int pincode = Integer.parseInt(c.readLine()); // does not throw IOException
        // System.out.println("Enter password");
        // char password[] = c.readPassword();
        // System.out.println("Entered City:" + City);
        // System.out.println("Entered pincode:" + pincode);
        // System.out.println("Password:" + password );
    }
}
