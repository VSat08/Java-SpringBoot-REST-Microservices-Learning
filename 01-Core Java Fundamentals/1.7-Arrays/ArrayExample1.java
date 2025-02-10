
public class ArrayExample1 {

    public static void main(String[] args) {

        // array initilization
        int[] marks = { 6, 7, 9, 8, 10, 7, 6, 8, 9, 5 }; // 10
        System.out.println(marks.length); // size = 10

        // printing array elements
        for (int i = 0; i < marks.length; i++)
            System.out.println(marks[i]);

        // read data to an array using Math.random()
        double[] randomNumbers = new double[10];

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = Math.random(); // 0.0 - 0.99
        }

        for (double d : randomNumbers)
            System.out.println(d);

        String[] cities = { "Pune", "Hyd", "Che", "Blr", "Del", "Mum" }; // 6
        System.out.println(cities[1]); // Hyd
        System.out.println(cities[5]); // Mum
        System.out.println(cities[3]); // Blr

        // print cities - for each
        for (String city : cities)
            System.out.println(city);

    }
}
