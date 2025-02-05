/*
todo: Commands to execute the file
 
---> java CommandLine.java sam 

this command will execute the java code and the provided argument will be printed on the console screen
*/
        
public class CommandLine {
    public static void main(String[] args) {
        System.out.println("I entered the name: " + args[0]);

    }
}


//todo:  Example code to print sum of two commandline entered numbers:

/*
 * Comment the above code and uncomment this code scetion and then run
 * 
 * ---> java CommandLine.java 1 33
 * 
 * to execute the code and see the results
 * 
 */


// public class  CommandLine{
//     public static void main(String[] params) {
//         int a = Integer.parseInt(params[0]);
//         int b = Integer.parseInt(params[1]);

//         int c = a + b;
//         System.out.println("Sum= " + c);
//     }
// }
