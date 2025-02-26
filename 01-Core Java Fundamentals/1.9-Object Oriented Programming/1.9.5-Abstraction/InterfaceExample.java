// Interface contains only abstract methods
// Interface methods are public by defaut 
// Interface can contain constants
// Interface has 100% abstraction

interface RBI {

    double intRate = 8.5;

    void withdraw();

    void deposite();
}

public class InterfaceExample implements RBI {

    @Override
    public void withdraw() {
        System.out.println("Withdraw Success");
    }

    @Override
    public void deposite() {
        System.out.println("Deposite Success");
    }

    public static void main(String[] args) {
        RBI child = new InterfaceExample();
        child.deposite();
        child.withdraw();
    }
}
