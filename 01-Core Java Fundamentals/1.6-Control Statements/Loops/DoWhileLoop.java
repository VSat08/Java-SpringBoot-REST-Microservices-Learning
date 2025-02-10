// print first 10 numbers using while and 10 to 1 using do - while 
public class DoWhileLoop {
    public static void main(String[] args) {

        int i = 1; // 1.init

        while (i <= 10) { // 2. test cond / exp
            System.out.println(i); // 1 2 ....9 10
            i++; // 3. update statement
        }

        System.out.println("****************");

        int x = 10; // 1.init
        do {
            System.out.println(x); // 10
            x--; // 2. update
        } while (x >= 1); // 3. test cond

    }
}