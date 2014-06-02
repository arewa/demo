import java.util.Scanner;

public class Main6 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t1 = in.nextInt();
		int t2 = in.nextInt();
		
		p(t1);
		p(t2);
	}
	
	public static void p(int t) {
		int d = 10 * t + ((5 * t * t) / 2);
		System.out.println(d);
	}
}
