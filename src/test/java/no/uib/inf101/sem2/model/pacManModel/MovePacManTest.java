package no.uib.inf101.sem2.model.pacManModel;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.PacDirection;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class MovePacManTest {
    private PacManBoard board;
    private PacManFactory pacManFactory;
    private PacManModel pacManModel;
    
    @BeforeEach
    public void setUp() {
        // create a 2x2 board and a PacMan model with a PacMan at (1,1)
        this.board = new PacManBoard(new String[]{
            "   ",
            " P ",
            "   "
        });
        this.pacManFactory = new RandomPacManFactory();
        this.pacManModel = new PacManModel(this.board, this.pacManFactory, new RandomGhostFactory(), 0);
    }
    
    @Test
    public void testMovePacMan() {
        // move PacMan to (1,0)
        pacManModel.movePacMan(0, -1);
        assertEquals(new CellPosition(1, 0), pacManModel.getTileOnMovingPacMan(), "PacMan should be at (1,0)");
        
        // move PacMan to (0,0)
        pacManModel.movePacMan(-1, 0);
        assertEquals(new CellPosition(0, 0), pacManModel.getTileOnMovingPacMan(), "PacMan should be at (0,0)");
        
        // move PacMan to (0,1)
        pacManModel.movePacMan(0, 1);
        assertEquals(new CellPosition(0, 1), pacManModel.getTileOnMovingPacMan(), "PacMan should be at (0,1)");
        
        // move PacMan to (1,1)
        //pacManModel.movePacMan(1, 0);
        //assertEquals(new CellPosition(1, 1), pacManModel.getTileOnMovingPacMan(), "PacMan should be at (1,1)");
        
        // try to move PacMan to (1,2), which is an illegal move
        //pacManModel.movePacMan(0, 1);
        //assertEquals(new CellPosition(1, 1), pacManModel.getTileOnMovingPacMan(), "PacMan should still be at (1,1)");
    }   

    @Test
    public void testMovePacDirection() {
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
        //model.movePacDirection(PacDirection.UP);
        //newPos = initialPos;

        // Check if PacMan moved to the right place
        //assertEquals(newPos, model.getTileOnMovingPacMan());
       
    }
}

