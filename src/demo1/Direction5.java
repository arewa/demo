package demo1;

import java.awt.Point;
import java.util.List;

public class Direction5 extends Direction {

	public Direction5(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		super(playerSpeed, data, avaiableForTurn);
	}

	/**
	 * - - - - x
	 * - - - x -
	 * - - P - -
	 * - - - - -
	 * - - - - - 
	 */
	
	@Override
	Point computeNextPosition(Point currentPosition) {
		return new Point(currentPosition.x - 1, currentPosition.y + 1);
	}
}
