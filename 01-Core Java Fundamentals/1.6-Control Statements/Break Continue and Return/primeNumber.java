// program to find whether a given number is prime or not? break demo
public class primeNumber{
	public static void main(String[] args) {
		
		int number = 2 + (int) (Math.random()*99); //2-100
		System.out.println(number);

		//prime - just one factor
         
         int i = 1;
         int count = 0;

         while(i<=number/2){
            if(number % i == 0){  // true - factor
              count++; // keeping the count  

              if(count>1)
              break; // jump       
            }

         	i++;
         }
         
		if(count == 1)
			System.out.println(number+" is a prime number");
		else
			System.out.println(number+" is not a prime number");
	}
}

