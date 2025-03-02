
import java.io.*;

class FileHandling {
    public static void main(String[] args) throws IOException {

        // file created or replaced if already existing
        File f = new File("first.txt");
        FileWriter fw = new FileWriter(f);
        fw.write("Java is OOP Language\n");
        fw.write("Java is dynamic\n");
        fw.write("FIle Ends Here....\n");
        fw.close();

        // file created and contents appended
        FileWriter fwa = new FileWriter("append.txt", true);
        fwa.write("Java is OOP Language\n");
        fwa.write("Java is dynamic\n");
        fwa.write("FIle Ends Here....\n");
        fwa.close();

        // Reading File
        FileReader fwr = new FileReader("append.txt");
        int x, count = 0;

        while ((x = fwr.read()) != -1) {
            System.out.print((char) x); // int to char type cast
            count++;
        }
        System.out.print("Total Characters in the file " + count); // int to char type cast
        fwr.close();

        System.out.println("\n-----------------------------------");
        System.out.println("File Written Successfully!");

    }
}