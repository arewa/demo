
public class RecursionTest {
	
	static int calls = 0;
	
	public static void main(String [] args) 
    {
		f();
    }
	
	public static void f() {
		calls ++;
		
		if (calls % 1000 == 0) {
			System.out.println(calls);
		}
		// By default recursive call might be done about 12500 times...
		// We are allowed to increase this value using -Xss flag
		// Examples:
		// -Xss1024k
		// -Xss64m
		f();
		
	}
}
