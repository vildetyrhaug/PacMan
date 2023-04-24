package no.uib.inf101.sem2.grid;

import no.uib.inf101.sem2.model.PacDirection;

/**
 * @author hentet fra lab 5
 * 
 * En CellPosition består av row og col.
 *
 * @param row  raden til cellen
 * @param col  kollonnen til cellen
 */
public record CellPosition(int row, int col) {

    /**
	 * This method is just for convenience.
	 * 
	 * @see PacDirection#getNeighbor(CellPosition)
	 * @param dir direction in which to get neighbouring CellPosition
	 * @return a neighbour CellPosition
     * @author hentet fra lab 5
	 */
	public CellPosition getNeighbor(PacDirection dir) {
		return dir.getNeighbor(this);
	}
}


