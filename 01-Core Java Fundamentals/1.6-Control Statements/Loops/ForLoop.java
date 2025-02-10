// printing a math table using for loop
//n=19 => 19*1=19....19*10=190
public class ForLoop{
	public static void main(String[] args) {
	 
	 int number = 11 + (int) (Math.random()*90); // 11 - 100 
	 System.out.println(number);

      for(int i=1; i<=10; i++)
      	System.out.println(number+" * "+i+" = "+ (number*i));

	}
}