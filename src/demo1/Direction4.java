package demo1;

import java.awt.Point;
import java.util.List;

public class Direction4 extends Direction {

	public Direction4(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		super(playerSpeed, data, avaiableForTurn);
	}

	/**
	 * - - - - -
	 * - - - - -
	 * x x P - -
	 * - - - - -
	 * - - - - - 
	 */
	
	@Override
	Point computeNextPosition(Point currentPosition) {
		return new Point(currentPosition.x, currentPosition.y - 1);
	}
}
