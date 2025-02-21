/*
 * run `javac TechEmp.java` to compile
 * java `TechEmp` to run
 * 
 * 
 */

 
public class TechEmp  extends Employee{
    // all non- private members are avaialiable

    public static void main(String[] args) {
        TechEmp t = new TechEmp();
        t.setEmp(2211, "Kris", 32000);
        t.display();
        
    }
}
