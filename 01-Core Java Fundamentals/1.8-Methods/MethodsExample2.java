// var args - passing variable arguments - 
public class MethodsExample2{
  
	static void add(int... x){  // it allows variable number of arguments to be passed
    int s = 0;
    for(int i=0;i<x.length; i++)
     s += x[i];
    System.out.println(s);
	}

	public static void main(String... args){ 
     
     add(10,20); // two args
     add(10,20,30); // 3 args
     add(1,2,3,4,5,6,7,8,9); // 9
     add(45,67,89,34,56,78,12);
     
	}
} 

// variable arguments
// x...
// var args is undelyingly is an array
//it must only one or it must be the last argument