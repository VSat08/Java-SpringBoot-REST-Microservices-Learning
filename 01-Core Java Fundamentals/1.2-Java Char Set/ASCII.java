public class ASCII {

    public static void main(String[] args) {

        for (int i = 0; i < 127; i++) {
            System.out.println(i + "->" + " " + (char) i + " "); // eliminating non-printable (34)
        }

        System.out.println();
    }

}