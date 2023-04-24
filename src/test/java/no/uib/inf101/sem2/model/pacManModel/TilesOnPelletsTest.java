package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;

public class TilesOnPelletsTest {

    private PacManBoard board;
    private PacManModel model;

    @BeforeEach
    public void setUp() {
        String[] maze = { 
            "###", 
            "ooo",
            "ooo",
            "###" };
        board = new PacManBoard(maze);
        model = new PacManModel(board);
    }

    @Test
    public void testGetTilesOnPellets() {
        List<GridCell<Character>> pellets = (List<GridCell<Character>>) model.getTilesOnPellets();
        assertEquals(6, pellets.size());
        assertEquals(new CellPosition(1, 0), pellets.get(0).pos());
        assertEquals(new CellPosition(1, 1), pellets.get(1).pos());
        assertEquals(new CellPosition(1, 2), pellets.get(2).pos());
        assertEquals(new CellPosition(2, 0), pellets.get(3).pos());
        assertEquals(new CellPosition(2, 1), pellets.get(4).pos());
        assertEquals(new CellPosition(2, 2), pellets.get(5).pos());

    }

}
