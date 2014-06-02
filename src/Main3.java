import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String u1 = in.nextLine();
		String u2 = in.nextLine();
		String u3 = in.nextLine();
		String u4 = in.nextLine();
		
		p(u1);
		p(u2);
		p(u3);
		p(u4);
	}
	
	public static void p(String username) {
		if ((username == null) || 
				(username.length() < 5) || 
				(username .length() > 10) || 
				username.contains(" ") || 
				!username.matches("^[A-Za-z][A-Za-z0-9@#*=].+$")) {
			System.out.println("FAIL");
		} else {
			System.out.println("PASS");
		}
	}
}
