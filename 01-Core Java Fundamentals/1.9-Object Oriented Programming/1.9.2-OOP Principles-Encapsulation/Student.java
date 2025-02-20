public class Student {
    private static String college;
    private int id;
    private String name;
    private double age;

    static {
        college = "ABC";
    }

    public Student() {
    }

    public void setStudent(int id, String name, double age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void getStudent() {
        System.out.println(this.id + " " + this.name + " " + this.age + " " + college);
    }
}
