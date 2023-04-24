package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;

public class ResetBoardTest {
    
    @Test 
    public void testResetBoard() {
        PacManBoard board = new PacManBoard(new String[]{
            "###",
            "ooo",
            "ooo",
            "###"
        });
        PacManModel model = new PacManModel(board);

        // Adds the characters from the board to a list
        Iterable<GridCell<Character>> tilesOnBoard = model.getTilesOnBoard();

        List<Character> valuesBeforeReset = new ArrayList<>();
        for (GridCell<Character> cell : tilesOnBoard) {
            valuesBeforeReset.add(cell.value());
        }

        // Reset the board
        model.resetBoard();
        
        // Adds the characters from the reset board to a list
        Iterable<GridCell<Character>> tilesOnBoardAfterReset = model.getTilesOnBoard();

        List<Character> valuesAfterReset = new ArrayList<>();
        for (GridCell<Character> cell : tilesOnBoardAfterReset) {
            valuesAfterReset.add(cell.value());
        }

        // Test that the values on the board before and after reset are not equal
        assertNotEquals(valuesBeforeReset, valuesAfterReset);
        
    }

}
