public class StrCmp {

    public static void main(String[] args) {


        // Using Equals method: Returns boolean
        // Using comparedTo method: Returns int
        /*
         * S1>S2 = +ve
         * S1<S2 = -ve
         * S1=S2 = 0
         */
        
        // Using == operator: Returns boolean

        String s1 = "Sachin"; // SCP
        String s2 = "Virat"; // SCP
        String s3 = new String("Sachin"); // heap -1
        String s4 = new String("Virat"); // heap - 1
        String s5 = "sachin"; // SCP
        String s6 = new String("Rohit"); // heap - 1, SCP -1
        String s7 = "Sachin"; // no object in SCP - already exists

        System.out.println(s1.length()); // 6
        System.out.println(s4.length()); // 5
        System.out.println(s6.charAt(2)); // h

        System.out.println(s1.equals(s3)); // true
        System.out.println(s1.equalsIgnoreCase(s5)); // true
        System.out.println(s1.equals(s2)); // false

        System.out.println(s1.compareTo(s5)); // -32
        System.out.println(s5.compareTo(s1)); // 32
        System.out.println(s2.compareTo(s4)); // 0
        System.out.println(s3.compareTo(s6)); // 1
        System.out.println(s1.compareToIgnoreCase(s5)); // 0

        System.out.println(s1 == s3); // false
        System.out.println(s2 == s4); // false
        System.out.println(s1 == s7); // true

    }

}
