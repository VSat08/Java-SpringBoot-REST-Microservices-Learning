
// Rule: If a If a class contains at least one method, it must be made (mandatory)

// Rule: Abstract Class classes can't be instantiated


abstract class AbstractClassExample {

    // percentage of abstraction =  1/2 = 50%
    
    abstract  void greet();

    void disp() {
        System.out.println("This is a example of Abstract Class");
    }
}

public class AbstractClass extends AbstractClassExample {
    @Override
    void greet() {
        System.out.println("Hello From Abstract Class implementation!");
    }
    public static void main(String[] args) {
        AbstractClassExample child = new AbstractClass();
        child.greet();
        child.disp();
    }
}

