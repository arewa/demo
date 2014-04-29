import java.util.ArrayList;
import java.util.List;


public class Main12 {
	public static void main(String[] args) {
		Object[] input = new Object[] { 1, 2, 3, new Object[]{ new Object[] { new Object[] { new Object[] {4} }, 5, null, null } } };
		
		System.out.println(norm(input));
		
	}
	// should print {1, 2, 3, 4, 5}
	
	private static List<Object> norm(Object[] input) {
		List<Object> result = new ArrayList<Object>();
		
		if (input == null) {
			return result;
		}
		
		for (Object o : input) {
			if (o instanceof Object[]) {
				result.addAll(norm((Object[])o));
			} else if (o != null) {
				result.add(o);
			}
		}
		
		return result;
	}
}
