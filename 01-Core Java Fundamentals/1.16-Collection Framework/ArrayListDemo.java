import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args) {

        // ArrayList al = new ArrayList(); // heterogeneous items list
        // al.add("Alice");
        // al.add("Bob");
        // al.add(43);
        // System.out.println(al);
        // System.out.println(al.size());
        // System.out.println(al.isEmpty());

        List<Integer> al = new ArrayList<>(); // generics
        al.add(43);
        al.add(21);
        al.add(90);
        al.add(10);
        al.add(1);
        System.out.println(al);
        System.out.println(al.size());
        System.out.println(al.isEmpty());
        Collections.sort(al);
        System.out.println(al);

        for (Object ob : al) {
            System.out.print(ob + " | ");
        }
        
        // Iterator
        Iterator itr = al.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}