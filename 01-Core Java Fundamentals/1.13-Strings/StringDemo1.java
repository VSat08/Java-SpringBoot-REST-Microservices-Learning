class StringFunctions {

    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        String s1 = "Welcome to Java";
        System.out.println(s1.length()); // 15

        System.out.println(s1.substring(6)); // e to Java
        System.out.println(s1.substring(0)); // whole string // start index - include

        System.out.println(s1.substring(0, 7));// Welcome // end index - exclusive
        System.out.println(s1.substring(11, 14));// Jav

        // read a character from the KB or user

        System.out.println("enter a character");
        String str = in.next(); // read a string token - "x" -> 'x'
        char ch = str.charAt(0); // 'x'
        System.out.println("character read is: " + ch);

    }
}