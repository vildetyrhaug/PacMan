package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.view.ViewablePacManModel;

public class PacManModel implements ViewablePacManModel {

    PacManBoard board;
    PacManFactory factory;
    PacMan movingPacMan;
    

    public PacManModel(PacManBoard board, PacManFactory factory) {
        this.board = board;
        this.factory = factory;

        this.movingPacMan = factory.getNext();
    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTileOnMovingPacMan() {
    
        return this.movingPacMan;
    }
    


}
