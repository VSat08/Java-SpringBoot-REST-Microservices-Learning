package Switch;

// finding the grade using switch
// Grade : >=90-A+, >=70-A, >=60-B, >=50-C, >=40-D, <40 - Fail
public class SwitchBreakExample {
    public static void main(String[] args) {
        int marks = 1 + (int) (Math.random() * 100); // 1 - 100
        System.out.println(marks);
        marks = marks / 10; // 95 -> 9 83->8 46-> 4
        switch (marks) { // 0,1,2,......10
            case 0:
            case 1:
            case 2:
            case 3:
                System.out.println("FAIL");
                break;
            case 4:
                System.out.println("D grade");
                break;
            case 5:
                System.out.println("C grade");
                break;
            case 6:
                System.out.println("B grade");
                break;
            case 7:
            case 8:
                System.out.println("A grade");
                break;
            case 9:
            case 10:
                System.out.println("A+ grade");
                break;
            default:
                System.out.println("Invalid Marks");
        }
    }
}