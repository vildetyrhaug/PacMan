package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.PacManBoard;

public class TilesOnPelletsTest {
    
    PacManBoard board;
    PacManModel model;
    
    @BeforeAll
    public void setUp() {
        // Create a PacManBoard object for testing
        String[] maze = {
                "#####",
                "# o #",
                "# o #",
                "# P #",
                "#####"
        };
        board = new PacManBoard(maze);
    }
    
    @Test
    public void testGetTilesOnPellets() {
        // Get the tiles on pellets using the method being tested
        Iterable<GridCell<Character>> pellets = model.getTilesOnPellets();
        
        // Create a list of the expected pellet positions
        List<CellPosition> expectedPelletPositions = new ArrayList<>();
        expectedPelletPositions.add(new CellPosition(1, 2));
        expectedPelletPositions.add(new CellPosition(2, 2));
        
        // Check that the positions of the pellets are as expected
        for (GridCell<Character> pellet : pellets) {
            assertTrue(expectedPelletPositions.contains(pellet.pos()));
        }
    }
}
