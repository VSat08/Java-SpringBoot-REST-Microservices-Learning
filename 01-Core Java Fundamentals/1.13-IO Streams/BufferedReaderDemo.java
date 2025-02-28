
import java.io.*;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {

        // -------------- Writing file using BufferWriter--------------
        FileWriter fw = new FileWriter("demo.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Java Welcomes you!\n");
        bw.write("Let's Learn Java!\n");
        
        System.out.println("---------------------File Writing --------------------");
        System.out.println("File Writtern!");
        bw.close();

        // -------------- Reading file using BufferWriter--------------
        BufferedReader br = new BufferedReader(new FileReader("demo.txt"));
        System.out.println("---------------------File Reading --------------------");
        String line;
        int count = 0;
        while ((line= br.readLine()) != null) {
            System.out.print(line);
            System.out.print("\n");
            count++;

        }
        System.out.println("\nFile contains " + count + " lines");
        br.close();

      
    }
}
