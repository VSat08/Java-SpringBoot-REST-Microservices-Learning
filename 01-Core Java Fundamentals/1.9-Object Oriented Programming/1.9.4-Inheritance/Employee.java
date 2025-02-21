// fully encapsulated
public class Employee {
    private int id;
    private String name;
    // protected  double basic;        //method 1:  so that only child classes can get access
    private double basic; // so that only child classes can get access
    private static String org = "Google";

    public void setEmp(int id, String name, double basic) {
        this.id = id;
        this.name = name;
        this.basic = basic;
    }

    public double getBasic() {
        return basic;
    }
    public void display() {
        System.out.println(id + "  " + name + "  " + basic + " " + org);
    }

    public void calSalary() {
        int da = 42;
        int hra = 30;
        int ta = 8;
        double salary = basic + basic * (da + hra + ta) / 100;
        System.out.println("Total Salary:  " + salary);
    }
}
