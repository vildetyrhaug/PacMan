package no.uib.inf101.sem2.model.pacManModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class InteractWithTest {
    
    private PacManModel pacManModel;

    @BeforeEach
    void setUp() {
        PacManBoard board =  new PacManBoard(
                new String[] { 
                "       ", 
                " f     ",
                "       ",
                "       ",
                "    G  ",
                "       "
            });
        pacManModel = new PacManModel(board, new RandomPacManFactory(), new RandomGhostFactory(), 1);
    }
    
    @Test
    void testInteractWithPellet() {
        // Choose a cell position with a pellet
        CellPosition pelletPos = null;
        Iterable<GridCell<Character>> pelletsBeforeInteraction = pacManModel.getTilesOnPellets();
        for (GridCell<Character> cell : pelletsBeforeInteraction) {
            if (cell.value() == 'o') {
                pelletPos = cell.pos();
                break;
            }
            break;
        }

        // Get the score before interacting with the pellet
        int scoreBefore = pacManModel.getScore();

        // Interact with the pellet
        pacManModel.interactWith(pelletPos);

        Iterable<GridCell<Character>> pelletsAfterInteraction = pacManModel.getTilesOnPellets();

        // Check that the pellet was removed
        assertNotEquals(pelletsBeforeInteraction, pelletsAfterInteraction);

        // Check that the score was updated correctly
        assertEquals(scoreBefore + 10, pacManModel.getScore());
    }

    @Test
    void testInteractWithFruit() {
        // Choose a cell position with a fruit
        CellPosition fruitPos = null; 

        for (GridCell<Character> cell : pacManModel.getTilesOnBoard()) {
            if (cell.value() == 'f') {
                fruitPos = cell.pos();
                break;
            }
        }

        // Get the score before interacting with the fruit
        int scoreBefore = pacManModel.getScore();

        // Set pacManHasEatenFruit to true
        pacManModel.pacManHasEatenFruit = true;

        // Interact with the fruit
        pacManModel.interactWith(fruitPos);

        // Check that the fruit was removed
        for (GridCell<Character> cell : pacManModel.getTilesOnBoard()) {
            if (cell.value() == 'f') {
                assert false;
            }
        }

        // Check that ghostsAreVulnerable = true. If isInvincible returns true then ghostsAreVulnerable = true.
        assertTrue(pacManModel.isInvincible());
            
        // Check that the score was updated correctly
        assertEquals(scoreBefore + 50, pacManModel.getScore());
    }

    @Test
    void testInteractWithGhost() {
        // Choose a cell position with a ghost
        CellPosition ghostPos = null; 

        for (GridCell<Character> cell : pacManModel.getTilesOnBoard()) {
            if (cell.value() == 'G') {
                ghostPos = cell.pos();
                break;
            }
        }

        // Get the score before interacting with the ghost
        int scoreBefore = pacManModel.getScore();
        
        pacManModel.setPacManPosition(ghostPos);

        // Set ghostsAreVulnerable and pacManHasEatenFruit to true
        pacManModel.ghostsAreVulnerable = true;
        pacManModel.pacManHasEatenFruit = true;
        
        // Interact with the ghost
        pacManModel.interactWith(pacManModel.getTileOnMovingPacMan());

        // Check that the score was updated correctly
        assertEquals(scoreBefore + 200, pacManModel.getScore());

        // Check that the ghost was removed
        for (GridCell<Character> cell : pacManModel.getTilesOnBoard()) {
            if (cell.value() == 'G') {
                assert false;
            }
        }
    }
}

