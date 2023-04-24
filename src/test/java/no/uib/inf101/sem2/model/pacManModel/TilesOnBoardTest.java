package no.uib.inf101.sem2.model.pacManModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

import java.util.ArrayList;
import java.util.List;

public class TilesOnBoardTest {
    private PacManBoard board;
    private PacManModel pacManModel;

    @BeforeEach
    public void setUp() {
        board = new PacManBoard(
            new String[]{
            "###",
            "#.#",
            "#.#",
            "###"
        });
        pacManModel = new PacManModel(board, new RandomPacManFactory(), new RandomGhostFactory(), 0);
    }

    @Test
    public void testGetTilesOnBoard() {
        Iterable<GridCell<Character>> tilesOnBoard = pacManModel.getTilesOnBoard();

        List<Character> actualValues = new ArrayList<>();
        for (GridCell<Character> cell : tilesOnBoard) {
            actualValues.add(cell.value());
        }

        // Manually set the expected values
        List<Character> expectedValues = new ArrayList<>();
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add(null);
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add(null);
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add('#');
        expectedValues.add('#');
       
        Assertions.assertEquals(expectedValues, actualValues, "The values on the board are not as expected");
    }
}