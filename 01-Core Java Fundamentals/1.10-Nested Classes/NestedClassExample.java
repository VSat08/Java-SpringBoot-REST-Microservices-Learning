public class NestedClassExample {

    private int data = 99;

    class Inner {
        void msg() {
            System.out.println("Data of outer class is " + data);
        }
    }

    void disp() {
        System.out.println("I am outer class");
    }

    public static void main(String[] args) {
        NestedClassExample outerClass = new NestedClassExample();

        NestedClassExample.Inner innerClass = outerClass.new Inner();

        innerClass.msg();

        outerClass.disp();

    }

}
