import java.util.Scanner;
import java.util.Arrays; // useful utility functions - sort(), shuffle(), reverse()

public class ArrayExample2 {
     public static void main(String[] args) {
          Scanner in = new Scanner(System.in);
          int[] arr = new int[5]; // 10*4=40 Bytes
          int sum = 0;
          // populating array
          System.out.println("enter " + arr.length + " elements");
          for (int i = 0; i < arr.length; i++) {
               // arr[i] = 1 + (int) (Math.random()*100); // random number 1 - 100
               arr[i] = in.nextInt();
               sum += arr[i];
          }
          // printing array - for each
          for (int i : arr)
               System.out.println(i);
          // printing sum and avg
          System.out.println("Sum of arr elements: " + sum);
          System.out.println("Avg of arr elements: " + (sum / arr.length));
          // min and max
          int min = arr[0];
          int max = arr[0];
          for (int i = 0; i < arr.length; i++) {
               if (arr[i] < min)
                    min = arr[i];

               if (arr[i] > max)
                    max = arr[i];
          }
          System.out.println("Min: " + min + "   " + "Max: " + max);
          // search for an element in array
          System.out.println("Please enter the element to search");
          int x = in.nextInt();
          boolean flag = false;
          for (int i = 0; i < arr.length; i++) {
               if (x == arr[i]) {
                    System.out.println(x + " is found at index: " + i);
                    flag = true; // it helps to determine if element is not found
                    break;
               }
          }
          if (flag == false)
               System.out.println(x + " is  not found");
          // sorting the array elements
          Arrays.sort(arr); // sorted natual sorting order -- ASC
          // printing array after sorting - for each
          System.out.println("Sorted Array");
          for (int i : arr)
               System.out.println(i);

          in.close();
     }
}