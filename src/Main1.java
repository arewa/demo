import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		
		BigDecimal a = new BigDecimal("929");
		BigDecimal b = new BigDecimal("31");
		BigDecimal c = a.divide(b, 3, RoundingMode.HALF_UP);
		BigDecimal c1 = a.divide(b, 2, RoundingMode.HALF_UP);
		//BigDecimal c = a.divide(b);
		
		System.out.println(c);
		System.out.println(c1);
		
//		Scanner in = new Scanner(System.in);
//		List<String> words = new ArrayList<String>();
//		for (int i = 0; i < 3; i ++) {
//			String[] wordsInLine = in.nextLine().split(" ");
//			for (int j = 0; j < wordsInLine.length; j ++) {
//				if (wordsInLine != null && Character.isUpperCase(wordsInLine[j].charAt(0))) {
//					words.add(wordsInLine[j]);
//				}
//			}
//		}
//		
//		Iterator<String> it = words.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
	}

}
