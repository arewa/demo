package demo1;

import java.awt.Point;
import java.util.List;

public class Direction7 extends Direction {

	public Direction7(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		super(playerSpeed, data, avaiableForTurn);
	}

	/**
	 * - - - - -
	 * - - - - -
	 * - - P - -
	 * - x - - -
	 * x - - - - 
	 */
	
	@Override
	Point computeNextPosition(Point currentPosition) {
		return new Point(currentPosition.x + 1, currentPosition.y - 1);
	}
}
