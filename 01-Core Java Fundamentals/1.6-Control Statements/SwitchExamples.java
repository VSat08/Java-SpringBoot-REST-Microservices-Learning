// switch - given day number, print day name 
// 1 - Mon, 7 - Sun
public class SwitchExamples{
	public static void main(String[] args) {
	int dayNo = Integer.parseInt(args[0]); // "5" - 5
	
	switch(dayNo){
		case 1:System.out.println("Its Monday");
		       break; 
	    case 2: System.out.println("Its Tuesday");	 
	           //break; 
	    case 3:System.out.println("Its Wednesday"); 
	           break;
	    case 4: System.out.println("Its Thursday");      
	           break;     
	    case 5: System.out.println("Its Friday");
	           break;
	    case 6: System.out.println("Its Saturday");      
	           break;     
	    case 7: System.out.println("Its Sunday");
	           break;
	    default: System.out.println("Please enter 1-7 numbers only!");
	}

	}
}