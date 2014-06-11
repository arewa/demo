package memorytest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NonHeapSpace2 {
	private static int waitTime = 15000;

	// run on java 1.6 u45
	// run with: -Xmx8m -XX:PermSize=8m -XX:MaxPermSize=8m

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Before");
		Thread.sleep(waitTime);
		
		Random rnd = new Random();
	    List<String> interned = new ArrayList<String>();
	    while(true) {
	        int length = rnd.nextInt(100);
	        StringBuilder builder = new StringBuilder();
	        String chars = "abcdefghijklmnopqrstuvwxyz";
	        for ( int i = 0; i < length; i++ ) {
	            builder.append(chars.charAt(rnd.nextInt(chars.length())));
	        }
	        interned.add(builder.toString().intern());
	        Thread.sleep(10);
	    }
	}
}
