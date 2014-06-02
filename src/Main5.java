import java.util.Calendar;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] d1 = in.nextLine().split("-");	
		String[] d2 = in.nextLine().split("-");
		
		p(d1);
		p(d2);
	}
	
	public static void p(String[] d) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(d[0]), Integer.parseInt(d[1]), 28);
		
		switch (c.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY: System.out.println("MONDAY"); break;
			case Calendar.THURSDAY: System.out.println("THURSDAY"); break;
			case Calendar.WEDNESDAY: System.out.println("WEDNESDAY"); break;
			case Calendar.TUESDAY: System.out.println("TUESDAY"); break;
			case Calendar.FRIDAY: System.out.println("FRIDAY"); break;
			case Calendar.SATURDAY: System.out.println("SATURDAY"); break;
			case Calendar.SUNDAY: System.out.println("SUNDAY"); break;
		}
	}
}
