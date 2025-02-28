import java.io.*;

public class TryWithResourcesDemo {

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {// try-with-resources
            System.out.println("Enter Name:");
            String name = br.readLine(); // IOException
            System.out.println("Hi " + name);
        } // try end 
        // resources automtically closed outside try block
   
        // finally no longer required..
        System.out.println("rest of the code follows.....!");
    }
}

// try() - resources - without catch or finally is possible