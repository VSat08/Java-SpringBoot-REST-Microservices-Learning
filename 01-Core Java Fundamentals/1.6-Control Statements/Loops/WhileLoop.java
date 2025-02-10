// program to find whether a given number is prime or not?
public class WhileLoop{
	public static void main(String[] args) {
		
		int number = 2 + (int) (Math.random()*99); //2-100
		System.out.println(number);

		//prime - just two factors -- 1 and itself
         
         int i = 1;
         int count = 0;

         while(i<=number){
            if(number % i == 0){  // true - factor
              count++; // keeping the count
            }

         	i++;
         }
         
		if(count == 2)
			System.out.println(number+" is a prime number");
		else
			System.out.println(number+" is not a prime number");
	}
}

