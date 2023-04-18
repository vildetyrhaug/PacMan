package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.grid.GridCell;

public class PacManBoard extends Grid<Character> {

    // make a Pac-Man board with walls and empty spaces
    // the board is a grid of characters
    // '#' is a wall
    // ' ' is an empty space
    // 'P' is pac man

    public PacManBoard(int rows, int cols) {
        super(rows, cols);
        
        // initialise the board with walls and empty spaces
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    set(new CellPosition(row, col), '#');
                } else {
                    set(new CellPosition(row, col), 'o');
                }
            }
        }
        
        // add a maze with this structure. Adding the walls (#)
        // [[#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#],
        //  [#, , , , ,#, , , , , , , , , ,#, , , , ,#],
        //  [#, ,#,#, ,#, ,#,#,#,#,#,#,#, ,#, ,#,#, ,#],
        //  [#, ,#, , , , , , , , , , , , , , , ,#, ,#],
        //  [#, ,#, ,#,#, ,#,#,#, ,#,#,#, ,#,#, ,#, ,#],
        //  [#, , , , , , ,#, , , , , ,#, , , , , , ,#],
        //  [#, ,#, ,#,#, ,#,#,#,#,#,#,#, ,#,#, ,#, ,#],
        //  [#, ,#, , , , , , , , , , , , , , , ,#, ,#],
        //  [#, ,#,#, ,#, ,#,#,#,#,#,#,#, ,#, ,#,#, ,#],
        //  [#, , , , ,#, , , , , , , , , ,#, , , , ,#],
        //  [#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#,#],
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                if ((row == 1 || row == 9) && (col == 5 || col == 15)){
                    set(new CellPosition(row, col), '#');
                }
                if ((row == 2 || row == 8) && (col == 2 || col == 3 || col == 5 || col > 6 && col < 14 
                 || col == 15 || col == 17 || col == 18)) {
                    set(new CellPosition(row, col), '#');
                }
                if ((row == 3 || row == 7) && (col == 2 || col == 18)){
                    set(new CellPosition(row, col), '#');
                }
                if (row == 4 && (col == 2 || col == 4 || col == 5 || col > 6 && col < 10 
                 || col > 10 && col < 14 || col == 15 || col == 16 || col == 18)) {
                    set(new CellPosition(row, col), '#');
                }
                if (row == 5 && (col == 7 || col == 13)) {
                    set(new CellPosition(row, col), '#');
                }
                if (row == 6 && (col == 2 || col == 4 || col == 5 || col > 6 && col < 14 
                 || col == 15 || col == 16 || col == 18)) {
                    set(new CellPosition(row, col), '#');
                }
                // set startpos til pacman og startpos til ghost tom
                if ((row == 7 && col == 10) || (row == 5 && col > 7 && col < 13) ) {
                    set(new CellPosition(row, col), ' ');
                }
        }        
    } 
}

    public Iterable<GridCell<Character>> getPellets() {
        List<GridCell<Character>> list = new ArrayList<>();
        for (GridCell<Character> cell : this) {
            if (cell.value() == 'o') {
                list.add(cell);
            }
        }
        return list;
    }

    public void removePellet(CellPosition pos) {
        this.set(pos, ' ');
    }


}

    
   