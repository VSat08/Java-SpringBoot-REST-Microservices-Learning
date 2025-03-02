public class StringsExample {
    public static void main(String[] args) {

        // Immutable Strings

        // String uisng String literals
        String s1 = "Java"; // stored in String Constant Pool
        System.out.println("String Creating using literal: " + s1);

        // String uisng new Keyword
        String s2 = new String("Python"); // stored inHeap and String Constant Pool
        System.out.println("String Creating using new keyword: " + s2.concat(s2));

        // Mutable Strings

        // String using StringBuffer
        // Thread safe
        StringBuffer sb = new StringBuffer("Javascript");
        sb.append(" and Java");
        System.out.println("String Creating using String Buffer: " + sb.toString());

        // String using StringBuilder
        // Not Thread safe
        StringBuilder sb1 = new StringBuilder("Rust");
        sb.append(" Programming");
        System.out.println("String Creating using String Builder: " + sb1.toString());
    }
}
