import java.awt.Point;

public class Demo6 {
	
	final static int speed = 4;
	final static int step = 1;
	final static Point startPoint = new Point(8, 8);
	final static String available = "x ";
	
	static int size = 20;
	static String[][] data = new String[size][size];

	public static void main(String[] args) {
		
		// Init data
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j ++) {
				data[i][j] = "- ";
			}
		}
		
		int x1 = startPoint.x + speed - 1;
		int x2 = startPoint.x - speed + 1;
		int y1 = startPoint.y + speed - 1;
		int y2 = startPoint.y - speed + 1;
		
		for (int i = 0; i < step * 2 + 1; i ++) {
			int t1 = y1 + i;
			if (t1 < size) {
				data[startPoint.x][t1] = available;
			}
			
			t1 = y2 - i;
			if (t1 >= 0) {
				data[startPoint.x][t1] = available;
			}
			
			t1 = x1 + i;
			if (t1 < size) {
				data[t1][startPoint.y] = available;
			}
			
			t1 = x2 - i;
			if (t1 >= 0) {
				data[t1][startPoint.y] = available;
			}
			
			t1 = x1 + i;
			int t2 = y1 + i;
			if ((t1 < size) && (t2 < size)) {
				data[t1][t2] = available;
			}
			
			t1 = x2 - i;
			t2 = startPoint.y - speed + 1 - i;
			if ((t1 >= 0) && (t2 >= 0)) {
				data[t1][t2] = available;
			}
			
			t1 = x1 + i;
			t2 = y2 - i;
			if ((t1 < size) && (t2 >= 0)) {
				data[t1][t2] = available;
			}
			
			t1 = x2 - i;
			t2 = y1 + i;
			if ((t1 >= 0) && (t2 < size)) {
				data[t1][t2] = available;
			}
		}
		
		data[startPoint.x][startPoint.y] = "P "; 
		
		// Print result
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j ++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
	}

}
