import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] words = in.nextLine().split(",");
		int[] values = new int[] {0, 0};
		for (int i = 0; i < words.length; i ++) {
			for (int j = 0; j < words[i].length(); j ++) {
				values[i] += Character.getNumericValue(words[i].charAt(j));
			}
		}
		System.out.println(values[0] - values[1]);
	}
}
