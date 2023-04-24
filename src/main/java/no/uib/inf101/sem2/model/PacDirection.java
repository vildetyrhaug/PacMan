package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;

/**
 * This class represents the 4 different directions
 * (plus the direction of no movement) in a grid
 * which one can move by changing either x or y coordinate by at most 1
 */
public enum PacDirection {
	RIGHT(0, 1),
	LEFT(0, -1),
	DOWN(1, 0),
	UP(-1,0),

	CENTER(0, 0);

	private final int dx;
	private final int dy;

	PacDirection(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	
	//get the current direction
	public PacDirection currentDirection() {
		return this;
	}

	/**
	 * Finds the CellPosition adjacent in the given direction
	 * 
	 * @param pos position to start at
	 * @return neighbouring position
	 * @author hentet fra lab 5
	 */
	public CellPosition getNeighbor(CellPosition pos) {
		return new CellPosition(pos.row() + dy, pos.col() + dx);
	}

}
