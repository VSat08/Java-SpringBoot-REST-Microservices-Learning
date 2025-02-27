/*To run the file :
 * 
 compile: javac -d . ABC.java
 Run: java in.pune.ABC

 
 */

package in.pune;

public class ABC {
    private int a;

    public ABC(int a) {
        this.a = a;
    }

    public void getA() {
        System.out.println("Value of A  from class ABC: " + a);
    }

    public static void main(String[] args) {
        ABC child = new ABC(20);
        child.getA();
    }
}
