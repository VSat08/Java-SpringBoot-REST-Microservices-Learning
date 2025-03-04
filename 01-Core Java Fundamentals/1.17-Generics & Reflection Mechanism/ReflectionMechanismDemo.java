
// String - methods & fields
import java.lang.reflect.*;

public class ReflectionMechanismDemo {

    private int id = 10;
    private String env = "password"; // secret

    public static void main(String[] args) throws Exception {

        int mcount = 0; // method count
        int fcount = 0; // field count

        Object obj = new String("Welcome to the Reflection Mechanism Concept"); // java.lang.String
        Thread t = new Thread();

        Class c = obj.getClass(); // entry point for reflection
        // Class c = t.getClass(); // entry point for reflection

        // getting private data
        System.out.println("FQN of class: " + c.getName()); // java.lang.String for Object String and java.lang.Thread
        // for Thread

        Method[] m = c.getDeclaredMethods();
        Field[] f = c.getDeclaredFields();

        System.out.println("====================== Getting Method ======================");
        for (Method m1 : m) {
            mcount++;
            System.out.println(m1.getName());

            Class<?> parameterType[] = m1.getParameterTypes();
            for (int i = 0; i < parameterType.length; i++) {
                System.out.println("Parameter " + (i + 1) + " parameter type: " + parameterType[i].getName());
            }
        }
        System.out.println("No. Of Methods: " + mcount);

        System.out.println("====================== Getting Field ======================");

        for (Field f1 : f) {
            fcount++;
            System.out.println(f1.getName());
        }
        System.out.println("No. Of Fields: " + fcount);

        // getting private members
        System.out.println("====================== Getting private members ======================");
        ReflectionMechanismDemo child = new ReflectionMechanismDemo();
        Class c1 = child.getClass();
        System.out.println(c1.getName());

        Field[] fields = c1.getDeclaredFields();
        for (Field fd : fields) {
            System.out.println(fd.getName() + "- --> " + Modifier.toString(fd.getModifiers()));
        }

        // getting private data
        System.out.println("====================== Getting private Data ======================");
        Field str = c1.getDeclaredField("env");
        str.setAccessible(true);
        String privateData = (String) str.get(child);
        System.out.println("Informational Hiding in env is: " + privateData);

    }
}
