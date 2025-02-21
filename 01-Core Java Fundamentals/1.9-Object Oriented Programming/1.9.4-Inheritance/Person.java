/*
 * run `javac Person.java` to compile
 * java `Person` to run
 * 
 * 
 */
public class Person {
    

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setEmp(1122, "Sam", 48000);

        e1.display();
    }
}
