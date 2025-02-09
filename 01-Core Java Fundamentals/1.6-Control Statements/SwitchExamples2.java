public class SwitchExamples2 {
    public static void main(String[] args) {

        String color = args[0]; // CMD line

        switch (color.toLowerCase()) { // string

            case "red":
                System.out.println("Roses are red");
                System.out.println(" and your fav color is red!");
                break;

            case "green":
                System.out.println("Earth is green");
                System.out.println(" and your fav color is green!");
                break;

            case "blue":
                System.out.println("Sky is blue");
                System.out.println(" and your fav color is Blue!");
                break;

            default:
                System.out.println("Please enter RGB colors only!");

        }
    }
}
