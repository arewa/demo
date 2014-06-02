import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> students = new HashMap<String, Integer>();
		for (int i = 0; i < 10; i ++) {
			String[] s = in.nextLine().split("-");
			String number = s[0];
			Integer score = Integer.valueOf(s[1]);
			if (students.containsKey(number)) {
				Integer previousScore = students.get(number);
				if (score.intValue() > previousScore.intValue()) {
					students.put(number, score);
				}
			} else {
				students.put(number, score);
			}
		}
		
		ValueComparator bvc =  new ValueComparator(students);
        Map<String, Integer> sortedStudents = new TreeMap<String, Integer>(bvc);
        sortedStudents.putAll(students);
        Iterator<String> it = sortedStudents.keySet().iterator();
        while(it.hasNext()) {
        	String k = it.next();
        	System.out.println(k + "-" + students.get(k));
        }
	}
	
	static class ValueComparator implements Comparator<String> {
	    Map<String, Integer> base;
	    public ValueComparator(Map<String, Integer> base) {
	        this.base = base;
	    }
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}
}
