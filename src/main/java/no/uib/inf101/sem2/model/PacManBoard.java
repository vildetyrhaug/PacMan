package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class PacManBoard extends Grid<Character> {

    // make a Pac-Man board with walls and empty spaces
    // the board is a grid of characters
    // '#' is a wall
    // ' ' is an empty space
    // 'o' is a dot

    
    public PacManBoard(int rows, int cols) {
        super(rows, cols);
        
        // initialise the board with walls and empty spaces
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    set(new CellPosition(row, col), '#');
                } else {
                    set(new CellPosition(row, col), ' ');
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
        }
    }
    
    
}}
