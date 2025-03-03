import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        List<String> ll = new LinkedList<>();
        ll.add("Aa");
        ll.add("Bb");
        ll.add("Cc");
        ll.add("Dd");

        ListIterator<String> itr = ll.listIterator();

        while (itr.hasNext()) {
            String str = itr.next();
            if (str.equals("Cc")) {
                itr.remove();
            }
            if (str.equals("Dd"))
                itr.set("Zz");
        }
        System.out.println(ll);
    }
}