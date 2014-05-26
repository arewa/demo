package demo1;

import java.awt.Point;
import java.util.List;

public class Direction1 extends Direction {

	public Direction1(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		super(playerSpeed, data, avaiableForTurn);
	}

	/**
	 * - - õ - -
	 * - - õ - -
	 * - - P - -
	 * - - - - -
	 * - - - - - 
	 */
	
	@Override
	Point computeNextPosition(Point currentPosition) {
		return new Point(currentPosition.x - 1, currentPosition.y);
	}
}
