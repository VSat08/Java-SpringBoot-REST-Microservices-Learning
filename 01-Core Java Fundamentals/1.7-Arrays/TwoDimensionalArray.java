import java.util.Scanner;
public class TwoDimensionalArray{
	public static void main(String[] args){ 
     Scanner in = new Scanner(System.in);
     //defining arrays of arrays              
     int[][] arr = new int[3][4];  // row 0 - col0 col1 col2 col3
                                   // row 1 - col0 col1 col2 col3
                                   // row 2 - col0 col1 col2 col3
                                   //         2,0  2,1  2,2  2,3
    // two loops - a loop inside other loop
    //  i loop  and j loop
     // initilization	
     int[][] x = {{1,2,3}, {3,4,5}, {5,6,7}};  // 3 * 3
     for(int i=0; i<3; i++){
     	for(int j=0; j<3; j++)
     		System.out.print(x[i][j]+" ");
     	System.out.println();//new line after every row
     	}
     
     //populating array elements	
     System.out.println("Please enter data items to array (3*4)"); 
     for(int i=0; i<3; i++){
     	for(int j=0; j<4; j++)
     		arr[i][j] = in.nextInt();
     }

     //printing array elements - matrix
     for(int i=0; i<3; i++){
     	for(int j=0; j<4; j++)
     		System.out.print(arr[i][j]+" ");
     	System.out.println(); // new line after every row
     }
      
	}
} 