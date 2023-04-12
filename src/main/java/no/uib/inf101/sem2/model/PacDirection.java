package no.uib.inf101.sem2.model;

import java.util.Arrays;

import no.uib.inf101.sem2.grid.CellPosition;

/**
 * This class represents the 4 different directions
 * (plus the direction of no movement) in a grid
 * which one can move by changing either x or y coordinate by at most 1
 */
public enum PacDirection {
	RIGHT(0, 1),
	UP(-1, 0),
	LEFT(0, -1),
	DOWN(1, 0), 
	CENTER(0, 0);

	/**
	 * The four cardinal directions: {@link #UP}, {@link #DOWN}, {@link #RIGHT},
	 * {@link #LEFT}.
	 */
	public static final Iterable<PacDirection> FOUR_DIRECTIONS = Arrays.asList(
			RIGHT,
			UP,
			LEFT,
			DOWN
	);
	
	/**
	 * The 5 cardinal and inter-cardinal directions ({@link #FOUR_DIRECTIONS}),
	 * plus {@link #CENTER}.
	 */
	public static final Iterable<PacDirection> FIVE_DIRECTIONS = Arrays.asList(
			RIGHT,
			UP,
			LEFT,
			DOWN,
			CENTER
	);

	private final int dx;
	private final int dy;

	PacDirection(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	// get the x and y coordinates of the direction
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
	 */
	public CellPosition getNeighbor(CellPosition pos) {
		return new CellPosition(pos.row() + dy, pos.col() + dx);
	}

	public PacDirection turnLeft() {
		return switch (this) {
			case UP -> PacDirection.LEFT;
			case DOWN -> PacDirection.LEFT;
			case RIGHT -> PacDirection.LEFT;
			case LEFT -> PacDirection.LEFT;
			case CENTER -> PacDirection.LEFT;
		};
	}


	public PacDirection turnRight() {
		return switch (this) {
			case UP -> PacDirection.RIGHT;
			case DOWN -> PacDirection.RIGHT;
			case RIGHT -> PacDirection.RIGHT;
			case LEFT -> PacDirection.RIGHT;
			case CENTER -> PacDirection.RIGHT;
		};
	}

	public PacDirection turnDown() {
		return switch (this) {
			case UP -> PacDirection.DOWN;
			case DOWN -> PacDirection.DOWN;
			case RIGHT -> PacDirection.DOWN;
			case LEFT -> PacDirection.DOWN;
			case CENTER -> PacDirection.DOWN;

		};
	}

	public PacDirection turnUP() {
		return switch (this) {
			case UP -> PacDirection.UP;
			case DOWN -> PacDirection.UP;
			case RIGHT -> PacDirection.UP;
			case LEFT -> PacDirection.UP;
			case CENTER -> PacDirection.UP;
		};
	}

	/* 
	 * A method that will call move at a specified time interval,
	 * and give different arguments to movePacMan depending on what
	 * the instance variable is (which has type PacDirection)
	 */
	
}
