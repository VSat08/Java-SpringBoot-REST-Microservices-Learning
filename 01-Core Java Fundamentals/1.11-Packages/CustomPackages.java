/*To run the file :
 * 
 compile:  javac -d . CustomPackages.java
 Run: java in.CustomPackages

 
 */


 package in;     // if want to import custom packages, we should be in a package 
import in.pune.ABC;



public class CustomPackages {
    public static void main(String[] args) {

        ABC abc = new ABC(0);
        abc.getA();
        
    }
}
