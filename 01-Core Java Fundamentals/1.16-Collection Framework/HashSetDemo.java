import java.util.*;
 class MyComp implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

public class HashSetDemo {
   
    
    public static void main(String[] args) {
        Set<Object> s = new HashSet<>();
        s.add(123);
        s.add('s');
        s.add("ABC");
        s.add(3.65);
        s.add(false);
        s.add(99);
        s.add('s');

        System.out.println("Hash Set Example");
        System.out.println(s);

        Iterator<Object> itr = s.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // Linked Hashset // insertion order is followed
        Set<Object> sh = new LinkedHashSet<>();
        sh.add(123);
        sh.add('s');
        sh.add("ABC");
        sh.add(3.65);
        sh.add(false);
        sh.add(99);
        sh.add('s');

        System.out.println("Linked Hash Set Example");
        System.out.println(sh);

        Iterator<Object> itr2 = sh.iterator();
        while (itr2.hasNext()) {
            System.out.println(itr2.next());
        }

        // Tree Hashset // Sorted order is followed
        Set<String> st = new TreeSet<>(new MyComp());     //reverses the order
        st.add("Ab");
        st.add("Kl");
        st.add("kL");
        st.add("J");
        st.add("A");
        st.add("z");
        st.add("BC");

        System.out.println("Tree  Set Example");
        System.out.println(st);

        Iterator<String> itr3 = st.iterator();
        while (itr3.hasNext()) {
            System.out.println(itr3.next());
        }
    }


}
