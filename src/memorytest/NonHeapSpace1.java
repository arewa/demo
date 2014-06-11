package memorytest;

public class NonHeapSpace1 {
	private static int waitTime = 15000;

	// run on java 1.7 u5 and on java 1.7 u51
	// run with: -Xmx8m -XX:PermSize=16m -XX:MaxPermSize=16m

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Before");
		Thread.sleep(waitTime);
		
		f();
		
		System.out.println("Finished");
	}
	
	public static void f() throws InterruptedException {
		System.out.println("Do something");
		f();
	}
}
