package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.model.GhostDirection;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class MoveGhostDirectionTest {

    private PacManModel model;

    @BeforeEach
    public void setUp() {
        // Create a new PacManModel with a PacManBoard and a RandomPacManFactory
        // with 2 ghosts and set it as the model for the test
        PacManBoard board = new PacManBoard(new String[]{
            "#####",
            "#   #",
            "# 0 #",
            "#   #",
            "#####"
        });
        model = new PacManModel(board);
    }

    @Test
    public void testMoveGhost() {
        // Get the first ghost from the model
        Ghost ghost = new Ghost(new CellPosition(2, 2));

        // Move the ghost to the right by one cell
        model.moveGhost(ghost, 0, 1);

        // Check that the ghost's position has been updated to (2, 3)
        assertEquals(new CellPosition(2, 3), ghost.getPos());

        // Move the ghost down by one cell
        model.moveGhost(ghost, 1, 0);
        
        // Check that the ghost's position has been updated to (3, 3)
        assertEquals(new CellPosition(3, 3), ghost.getPos());

        // Move the ghost to the left by one cell
        model.moveGhost(ghost, 0, -1);

        // Check that the ghost's position has been updated to (3, 2)
        assertEquals(new CellPosition(3, 2), ghost.getPos());

        // Move the ghost up by one cell
        // model.moveGhost(ghost, -1, 0);

        // Check that the ghost's position has been updated to (2, 2)
        // assertEquals(new CellPosition(2, 2), ghost.getPos());

    }

    @Test
    public void testRandomizeGhostDirection() {
        // Get the first ghost from the model
        Ghost ghost = new Ghost(new CellPosition(2, 2));
        GhostDirection initialDirection = ghost.getDirection();

        // Randomize the ghost's direction
        model.randomizeGhostDirection(ghost);

        // Check that the ghost's direction has been updated
        assertNotEquals(initialDirection, ghost.getDirection());
    }
}