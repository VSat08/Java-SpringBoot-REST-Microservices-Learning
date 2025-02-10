// Source code is decompiled from a .class file using FernFlower decompiler.
package Switch;

public class SwitchExample {

    public static void main(String[] var0) {
        String var1 = "red";
        switch (var1.toLowerCase()) {
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
