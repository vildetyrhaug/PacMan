package no.uib.inf101.sem2.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class MovePacDirectionTest {

    @Test
    public void testMovePacMan() {
        PacManBoard board = new PacManBoard(new String[]{
            "##########",
            "#        #",
            "#        #",
            "#        #",
            "#   P    #",
            "#        #",
            "#        #",
            "#        #",
            "#        #",
            "##########"
        });

        PacManFactory pacManFactory = new RandomPacManFactory();
        GhostFactory ghostFactory = new RandomGhostFactory();
    
        PacManModel model = new PacManModel(board, pacManFactory, ghostFactory, 0);        

        // Set initial PacMan position
        CellPosition initialPos = model.getTileOnMovingPacMan();

        // Move PacMan to the right. PacMan should be one to the right from starting position
        model.movePacDirection(PacDirection.RIGHT);
        CellPosition newPos = new CellPosition(initialPos.row(), initialPos.col() + 1);

        // Check if PacMan moved to the right place
        assertEquals(newPos, model.getTileOnMovingPacMan());
        
        // Move PacMan Down. PacMan should be one down and one to the right from starting position
        model.movePacDirection(PacDirection.DOWN);
        newPos = new CellPosition(initialPos.row() + 1, initialPos.col() + 1);

        // Check if PacMan moved to the right place
        assertEquals(newPos, model.getTileOnMovingPacMan());

        // Move PacMan Left. PacMan should be one down from starting position
        model.movePacDirection(PacDirection.LEFT);
        newPos = new CellPosition(initialPos.row() + 1, initialPos.col());
        
        // Check if PacMan moved to the right place
        assertEquals(newPos, model.getTileOnMovingPacMan());

        // Move PacMan Up. PacMan should be at starting position again
        model.movePacDirection(PacDirection.UP);
        newPos = initialPos;

        // Check if PacMan moved to the right place
        assertEquals(newPos, model.getTileOnMovingPacMan());
       
    }}
