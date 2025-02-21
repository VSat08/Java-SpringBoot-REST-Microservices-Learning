/*
 * run `javac TechEmp2.java` to compile
 * java `TechEmp2` to run
 * 
 * 
 */

public class TechEmp2 extends Employee {
    // inherited members - non-private -
    // child specific members
    int bonus;

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    // re-define disp()
    @Override
    public void display() {
        // super
        super.display(); // parent's disp()
        System.out.println("Bonus: "+bonus);
    }

    // re-defining - overriding
    @Override // annotations
    public void calSalary() { // method signature must be same
        // local variables - as allowances
        int da = 42;
        int hra = 30;
        int ta = 8;
        double salary = getBasic() + getBasic() * (da + hra + ta) / 100 + bonus; // updated salary
        System.out.println("Total Salary: " + salary);
    }

    public static void main(String[] args) {
        TechEmp2 te = new TechEmp2(); // eid, ename, basic -
        te.setEmp(321, "ASDF", 100000);
        te.setBonus(10000);

        te.display();
        te.calSalary(); //

    }

}