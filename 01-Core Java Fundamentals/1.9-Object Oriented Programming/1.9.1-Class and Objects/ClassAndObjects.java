// Three types of memory:
// Heap
// Class Area
// Stack Area

// Two types of Constructors:
// 1. defaut constructors
// 2.parametrized constructor

// -> more than one constructor= constructor overloading

class Student { // default specific //package level

    // data members
    private int id; // instance variable //Heap
    private double CGP; // instance variable //Heap
    private String name; // class variable
    private static String university; // class variable

    // static block - executed during class loading (before main)
    static {
        university = "ABC";
    }
    // Constructors

    public Student() {
        System.out.println("Object Created!");
    }

    // constructor overloading
    public Student(int id, String name, double CGP) {
        this.id = id;
        this.CGP = CGP;
        this.name = name;
    }

    // methods
    // setters and getters

    // setters
    // public void setStudent(int id, String name, double CGP) { // local variable
    // //stack
    // this.id = id;
    // this.CGP = CGP;
    // this.name = name;
    // }

    // getters
    public void getStudent() { // local variable //stack

        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.CGP);
        System.out.println(university);

    }

    // Static methods
    public static void ChangeUniversiy() {
        university = "XYZ";
    }
}

public class ClassAndObjects {

    public static void main(String[] args) {

        Student newStudent = new Student(1, "Sam", 9.7); // new object
        // newStudent.setStudent(1, "Sam", 9.7);
        newStudent.getStudent();
        // call of static methods
        Student.ChangeUniversiy();
        newStudent.getStudent();
    }
}
