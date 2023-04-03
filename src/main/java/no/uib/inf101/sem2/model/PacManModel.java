package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllablePacManModel;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.view.ViewablePacManModel;

public class PacManModel implements ViewablePacManModel, ControllablePacManModel{

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

    @Override
    public boolean movePacMan(int deltaRow, int deltaCol) {
        // moves the piece around on the board
        // returns a boolean indicating whether the move was successful or not.
        PacMan newPiece = this.movingPacMan.shiftedBy(deltaRow, deltaCol);
        if (this.legalPlacement(newPiece)) {
            this.movingPacMan = newPiece;
            return true;
        }
        return false;
    }
    
    public boolean legalPlacement(PacMan newPiece) {
        // sjekker om en tetromino kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        for (GridCell<Character> cell : newPiece) {
            if (!board.positionIsOnGrid(cell.pos())) {
                return false;
            }
            if (board.get(cell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }


}
