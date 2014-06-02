import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n1 = in.nextInt();
		int n2 = in.nextInt();
		int n3 = in.nextInt();
		
		p(n1);
		p(n2);
		p(n3);
	}
	
	public static void p(int n) {
		int spaces = n - 1;
		for (int i = 1; i <= n; i ++) {
			for (int j = 0; j < n; j ++) {
				if (j < spaces) {
					System.out.print(" ");
				} else {
					System.out.print(i + " ");
				}
			}
			System.out.println();
			spaces --;
		}
	}
}
