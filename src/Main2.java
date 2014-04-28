import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int hops1 = in.nextInt();
		int hops2 = in.nextInt();
		int hops3 = in.nextInt();
		int hops4 = in.nextInt();
		
		p(hops1);
		p(hops2);
		p(hops3);
		p(hops4);
	}
	
	public static void p(int hops) {
		int distance = 0;
		int currentHop = 1;
		for (int i = 0; i < hops; i ++) {
			switch (currentHop) {
				case 1: distance += 20; break;
				case 2: distance += 10; break;
				case 3: distance += 5; break;
			}
			currentHop ++;
			if (currentHop > 3) {
				currentHop = 1;
			}
		}
		System.out.println(distance);
	}
}
