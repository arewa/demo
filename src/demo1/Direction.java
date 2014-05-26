package demo1;

import java.awt.Point;
import java.util.List;

public abstract class Direction {
	
	protected int playerSpeed;
	protected List<Point> avaiableForTurn;
	protected String[][] data;
	protected int maxHops;
	
	abstract Point computeNextPosition(Point currentPosition);
	
	public Direction(int playerSpeed, String[][] data, List<Point> avaiableForTurn) {
		this.playerSpeed = playerSpeed;
		this.data = data;
		this.avaiableForTurn = avaiableForTurn;
		this.maxHops = playerSpeed + 1;
	}
	
	public void computeAvailableForTurn(Point startPosition) {
		if (maxHops == 0) {
			return;
		}
		
		Point nextPosition = computeNextPosition(startPosition);
		
		if (nextPosition.x < 0 || nextPosition.x >= Demo6.size) {
			return;
		}
		
		if (nextPosition.y < 0 || nextPosition.y >= Demo6.size) {
			return;
		}
		
		if (data[nextPosition.x][nextPosition.y].equals("0 ")) {
			return;
		}
		
		if (maxHops <= 3) {
			avaiableForTurn.add(nextPosition);
		}
		
		maxHops --;
		computeAvailableForTurn(nextPosition);
	}
}