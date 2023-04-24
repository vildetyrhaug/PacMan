package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class ResetPacManTest {
    
    @Test
    public void testResetPacMan() {
        PacManBoard board = new PacManBoard(new String[]{
            "####",
            "oooo",
            "oPoo",
            "oooo",
            "oooo",
            "####"
        });

        PacManModel model = new PacManModel(board, new RandomPacManFactory(), new RandomGhostFactory(), 0);
        
        // Get the position of the pac-man initially
        CellPosition initialPos = model.getTileOnMovingPacMan();

        // move the pac-man to a new position
        model.movePacMan(1, 0);

        CellPosition movedPos = model.getTileOnMovingPacMan();

        // check that the initial position is not the same as the moved position
        assertNotEquals(initialPos, movedPos);

        // reset the pac-man
        model.resetPacMan();

        CellPosition resetPos = model.getTileOnMovingPacMan();

        // check that the reset position is not the same as the moved position
        // check that the initial position is the same as the reset position
        assertNotEquals(movedPos, resetPos);
        assertEquals(initialPos, resetPos);
    }
}
