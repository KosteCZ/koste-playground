
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Start");
		
		if(args == null){
			System.out.println("args is null!");
		} else {
			System.out.println("args length: " + args.length);
			System.out.println();
			System.out.println(args);
			System.out.println();
			System.out.println("args'" + args + "'");
			System.out.println();
		}
		
		System.out.println();
		
	    for(String s : args) {
	        System.out.println(s);
	    }
	    
	    System.out.println("End");

	}

}
