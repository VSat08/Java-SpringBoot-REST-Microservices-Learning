
import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;
    transient  private long aadhar;    //aadhar can not be serialized: After De-Serialization, the default value will be returned

    public Person(String name, int age, long aadhar) {
        this.name = name;
        this.age = age;
        this.aadhar = aadhar;
    }

    @Override
    public String toString() {
        return "[ Student:= Name=" + name + " Age=" + age + " Aadhar=" + aadhar + " ]";
    }
}

public class SerializeTest {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("John", 23, 123456789L);
        System.out.println("------ Before De-Serialization -----------------");
        System.out.println(p1);
        System.out.println();

        // Serialization
        FileOutputStream fos = new FileOutputStream("person.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p1);
        

        // De- Serialization
        FileInputStream fis = new FileInputStream("person.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person p2 = (Person) ois.readObject(); // java.lang.Object

        // print Object
        System.out.println("------ After De-Serialization -----------------");
        System.out.println(p2);
    }
}
