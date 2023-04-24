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
    // 'P' is where Pac-Man starts the game
    // 'o' is a pellet
    // 'G' is the ghosts area

    long timeFruitEaten = 0;
    CellPosition pacManStartPosition;

    public PacManBoard(String[] maze) {
        
        super(maze.length, maze[0].length());

        // legger til tegn basert på maze input
        for (int row = 0; row < maze.length; row++) {
            String mazeRow = maze[row];
            for (int col = 0; col < mazeRow.length(); col++) {
                char mazeChar = mazeRow.charAt(col);
                if (mazeChar == '#') {
                    set(new CellPosition(row, col), '#');
                } else if (mazeChar == ' ') {
                    set(new CellPosition(row, col), 'o'); 
                } else if (mazeChar == 'G') {
                    set(new CellPosition(row, col), 'G');
                } else if (mazeChar == 'P') {
                    set(new CellPosition(row, col), 'P');
                    pacManStartPosition = new CellPosition(row, col);
                } else if (mazeChar == 'f') {
                    set(new CellPosition(row, col), 'f');
                } 
            }
        }
    }

    public Iterable<GridCell<Character>> getPellets() {
        List<GridCell<Character>> list = new ArrayList<>();
        for (GridCell<Character> pos : this) {
            if (pos.value() == 'o') {
                list.add(pos);
            }
        }
        return list;
    }
 
    public void removePelletAndFruit(CellPosition pos) {
        if (get(pos) == 'f') {
            timeFruitEaten = System.currentTimeMillis();
        }
        this.set(pos, ' ');
    }

    // tidspunktet frukten ble spist
    public long getTimeFruitEaten() {
        return timeFruitEaten;
    }


    public void setTimeFruitEaten(long currentTimeMillis) {
        this.timeFruitEaten = currentTimeMillis;
    }

    public CellPosition getPacManStartPosition() {
        //CellPosition pos = new CellPosition(this.rows()/2+2, this.cols()/2);
        
        return pacManStartPosition;     
    }
}


    
   