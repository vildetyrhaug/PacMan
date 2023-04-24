package no.uib.inf101.sem2.model.pacManModel;


import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TilesOnMovingGhostsTest {
    
    @Test
    void getTilesOnMovingGhostsReturnsExpectedPositions() {
        // Arrange
        PacManBoard board = new PacManBoard(new String[]{
                "##########",
                "#        #",
                "#        #",
                "#        #",
                "#    G   #",
                "#        #",
                "#        #",
                "# G      #",
                "#        #",
                "##########"
        });
        PacManFactory pacManFactory = new RandomPacManFactory();
        GhostFactory ghostFactory = new RandomGhostFactory();
        int numGhosts = 2;
        PacManModel model = new PacManModel(board, pacManFactory, ghostFactory, numGhosts);

        // Act
        Iterable<CellPosition> positions = model.getTilesOnMovingGhosts();

        // Assert
        assertNotNull(positions);
        List<CellPosition> positionsList = new ArrayList<>();
        positions.forEach(positionsList::add);
        assertEquals(numGhosts, positionsList.size());
        for (CellPosition position : positionsList) {
            Ghost newGhost = new Ghost(position);
            assertTrue(model.legalPlacementGhost(newGhost));
        }
    }
}
