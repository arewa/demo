import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main11 {
	public static void main(String[] args) throws ParseException {
		Date today = new Date();
		System.out.println(today.getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy:HH");
		System.out.println(dateFormat.format(new Date()));
		System.out.println(dateFormat.parse(dateFormat.format(new Date())).getTime());
	}
}
