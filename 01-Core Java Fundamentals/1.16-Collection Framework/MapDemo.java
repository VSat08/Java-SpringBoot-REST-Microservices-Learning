import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(123, "ABC");
        map.put(29, "FKC");
        map.put(1, "SYG");
        map.put(99, "PR");
        map.put(123, "XYZ"); // ignored

        System.out.println(map);

        Collection c1 = map.keySet();
        System.out.println(c1);

        Collection c2 = map.values();
        System.out.println(c2);

        Set s = map.entrySet();
        Iterator it = s.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }
}
