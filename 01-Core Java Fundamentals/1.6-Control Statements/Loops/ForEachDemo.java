public class ForEachDemo{
	public static void main(String[] args) {
		
		int[] arr = {1,4,7,9,45,67,23,89,123,456,7,12};
		System.out.println("size: "+arr.length); // 

		for(int i:arr) // completely scan / fetch everything from the group
			System.out.println(i);

	}
}