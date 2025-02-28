import java.io.*;
public class ExceptDemo{
	
	public static void main(String[] args) throws IOException{  // declaring an exception
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter name and age");
		String name = null;
		int age = 0;
		try{
		name = br.readLine(); //  it reads a line of text
		age = Integer.parseInt(br.readLine()); // thirty ->throw new NumberFormatException()
	    }
	    catch(Exception e){
	    	System.err.println(e);
	    }
	    finally{
	    	// closing resources 
	    	if(br != null)
	    		br.close();
	    	System.out.println("resource closed!");
	    }
        System.out.println(name+" "+age);
        System.out.println("rest of the code...");
	}

}