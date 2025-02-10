// need for methods - demo
public class MethodsExample{

    // method definition - with return type
    static int add(int x, int y){  // formal parameters
    	int sum=0;
    	for(int i=x; i<=y; i++)
        	sum = sum+i;
        
        return sum;
    }

	public static void main(String[] args) { // starting

       int s = add(1,10); // method call - actual parameters
       System.out.println(s);
       System.out.println(add(21,35));
	   System.out.println(add(51,75));	
       System.out.println(add(101,1111));
           
	}
}

