package memorytest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HeapSpace2 {
	
	private static int waitTime = 15000;

	// run on java 1.7 u5 and on java 1.7 u51
	// run with: -Xmx8m

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.out.println("Before read big string");
		Thread.sleep(waitTime);
		
		String[] smallStringsArray = new String[5];
		
		for (int i = 0; i < 5; i ++) {
		
			String bigString = new String(Files.readAllBytes(FileSystems.getDefault().getPath("BigFile.html")));
	
			// smallStringsArray[i] = bigString.substring(0, 10);
			// smallStringsArray[i] = bigString.substring(0, 10).intern();
			smallStringsArray[i] = new String(bigString.substring(0, 10));
			
			bigString = null;
			
			System.out.println("Small string has been read");
			Thread.sleep(5000);
		}
		
		System.gc();
		
		Thread.sleep(waitTime);
		
		System.out.println("Finished");
	}
}
