
import java.util.*;

class Student {
    private int id;
    private String name;
    private double gpa;
    private String city;
    private String university;

    public Student(

            int id,
            String name,
            double gpa,
            String city,
            String university) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.city = city;
        this.university = university;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + name + "\tGPA: " + gpa + "\tCity: " + city + "\tUniversity: " + university
                + "\n";
    }
}

public class ListDemo {
    public static void main(String[] args) {
        List<Student> arrList = new ArrayList<>();
        arrList.add(new Student(123, "ABC", 8.6, "Pune", "AAA"));
        arrList.add(new Student(567, "SHS", 8.6, "Pune", "AAA"));
        arrList.add(new Student(224, "ABC", 8.6, "Pune", "AAA"));
        arrList.add(new Student(726, "ISD", 8.6, "Pune", "AAA"));
        arrList.add(new Student(912, "OKT", 8.6, "Pune", "AAA"));

        Iterator itr = arrList.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
            System.out.println("---------------------------------------------------------------------------------------------");
            
        }

    }
}
