package demo1;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Demo6 {
	
	final static int speed = 3;
	final static int step = 1;
	final static Point startPoint = new Point(12, 1);
	final static String available = "x ";
	static List<Point> avaiableForTurn = new ArrayList<Point>();
	static List<Direction> directions = new ArrayList<Direction>();
	
//	static Map<Integer, Stack<Point>> directionsData = new HashMap<Integer, Stack<Point>>();
	
	static int size = 20;
	static String[][] data = new String[size][size];

	public static void main(String[] args) {
		
		// Init data
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j ++) {
				data[i][j] = "- ";
			}
		}
		
		// Manually set few "walls"
		data[3][3] = "0 ";
		data[3][4] = "0 ";
		data[3][5] = "0 ";
		data[3][6] = "0 ";
		data[3][7] = "0 ";
		data[3][8] = "0 ";
		data[3][9] = "0 ";
		data[3][10] = "0 ";
		
		data[3][10] = "0 ";
		data[4][10] = "0 ";
		data[5][10] = "0 ";
		data[6][10] = "0 ";
		data[7][10] = "0 ";
		data[8][10] = "0 ";
		data[9][10] = "0 ";
		data[10][10] = "0 ";
		
		directions.add(new Direction1(speed, data, avaiableForTurn));
		directions.add(new Direction2(speed, data, avaiableForTurn));
		directions.add(new Direction3(speed, data, avaiableForTurn));
		directions.add(new Direction4(speed, data, avaiableForTurn));
		directions.add(new Direction5(speed, data, avaiableForTurn));
		directions.add(new Direction6(speed, data, avaiableForTurn));
		directions.add(new Direction7(speed, data, avaiableForTurn));
		directions.add(new Direction8(speed, data, avaiableForTurn));
		
		for (Direction d : directions) {
			d.computeAvailableForTurn(startPoint);
		}
		
		for (Point p : avaiableForTurn) {
			data[p.x][p.y] = available;
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
