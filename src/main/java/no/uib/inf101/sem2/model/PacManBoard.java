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
    // 'o' is a pellet
    // 'G' is the ghosts area

    long timeFruitEaten = 0;

    public PacManBoard(int rows, int cols) {
        
        super(rows, cols);

        // initialiserer brettet med vegger og tomme plasser
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                    set(new CellPosition(row, col), '#');
                }  else {
                    set(new CellPosition(row, col), 'o');
                }  
            }
        }

        // lager labyrinten med vegger, frukt, og plass for spøkelser. 
        String[] maze = {
            "###################",
            "#     #     #     #",
            "# ### # ### # ### #",
            "#   f    #    f   #",
            "## ## ##   ## ## ##",
            "#     ### ###     #",
            "# ### ##   ## ### #",
            "# ##           ## #",
            "# #  # ## ## #  # #",
            "       #GGG#       ",
            "# #  # ##### #  # #",
            "# ##           ## #",
            "# ### ##   ## ### #",
            "#     ### ###     #",
            "## ## ##   ## ## ##",
            "#   f    #    f   #",
            "# ### # ### # ### #",
            "#     #     #     #",
            "###################"
        };
        
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
                } else if (mazeChar == 'f') {
                    set(new CellPosition(row, col), 'f');
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

}


    
   