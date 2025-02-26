public class LocalClass {
    private int data = 99;

    public void disp() {

        class Local {
            void msg() {
                System.out.println("Data from outer class " + data);
            }
        } // class end
        Local l = new Local();
        l.msg();

    }

    public static void main(String[] args) {
        LocalClass outer = new LocalClass();

        outer.disp();
    }
}
