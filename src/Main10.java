import java.util.Scanner;

public class Main10 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		
		String[] a1 = s1.split(",");
		String[] a2 = s2.split(",");
		
		int c = 0;
		
		for (int i = 0; i < a1.length; i ++) {
			for (int j = 0; j < a2.length; j ++) {
				if (a2[j].equals(a1[i])) {
					c ++;
				}
			}
		}
		
		System.out.println(c);
	}
}
