public class ReturnDemo{
	public static void main(String[] args) {
		
		int n1 = (int) (Math.random()*10); //0-9
		int n2 = (int) (Math.random()*10); //0-9
		System.out.println(n1+"   "+n2);

        if(n2==0)
        	return; // exit from the program

		int res = n1/n2;  // n2=0  crash
		System.out.println(res);

		int prod = n1*n2;
		System.out.println(prod);

	}
}