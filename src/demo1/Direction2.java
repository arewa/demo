package demo1;

import java.awt.Point;
import java.util.List;

public class Direction2 extends Direction {

	public Direction2(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		super(playerSpeed, data, avaiableForTurn);
	}

	/**
	 * - - - - -
	 * - - - - -
	 * - - P x x
	 * - - - - -
	 * - - - - - 
	 */
	
	@Override
	Point computeNextPosition(Point currentPosition) {
		return new Point(currentPosition.x, currentPosition.y + 1);
	}
}
