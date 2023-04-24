package no.uib.inf101.sem2.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;

public class GridDimensionTest {
    
    @Test
    public void testGetDimension() {
        PacManBoard dimension = new PacManBoard(new String[]{
            "##########",
            "#........#",
            "#........#",
            "#........#",
            "#........#",
            "#........#",
            "#........#",
            "#........#",
            "#........#",
            "##########"
        });
        PacManModel model = new PacManModel(dimension, new RandomPacManFactory(), new RandomGhostFactory(), 4);
        Assertions.assertEquals(dimension, model.getDimension());
    }

}

