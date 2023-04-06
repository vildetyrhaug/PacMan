package no.uib.inf101.sem2.model;

import java.awt.event.KeyEvent;

import no.uib.inf101.sem2.controller.ControllablePacManModel;
import no.uib.inf101.sem2.controller.PacManController;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.view.ViewablePacManModel;

public class PacManModel implements ViewablePacManModel, ControllablePacManModel{

    PacManBoard board;
    PacManFactory factory;
    PacMan movingPacMan;
    GameState gameState;

    PacManController controller;

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
        // LEFT:
        if(deltaRow == 0 && deltaCol == -1){
            PacMan newPiece = this.movingPacMan.shiftedBy(0, -1);
            while (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
                newPiece = this.movingPacMan.shiftedBy(0, -1);
        }            return true;
} 
        // RIGHT:
        if(deltaRow == 0 && deltaCol == 1){
            PacMan newPiece = this.movingPacMan.shiftedBy(0, 1);
            while (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
                newPiece = this.movingPacMan.shiftedBy(0, 1);

        }            return true;
}
        // UP:
        if(deltaRow == -1 && deltaCol == 0){
            PacMan newPiece = this.movingPacMan.shiftedBy(-1, 0);
            while (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
                newPiece = this.movingPacMan.shiftedBy(-1, 0);
        } return true;
    } 
        // DOWN:
        if(deltaRow == 1 && deltaCol == 0){
            PacMan newPiece = this.movingPacMan.shiftedBy(1, 0);
            while (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
                newPiece = this.movingPacMan.shiftedBy(1, 0);
        } return true;
    }
        return false;
    }
    
    public boolean legalPlacement(PacMan newPiece) {
        // sjekker om en pacman kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        for (GridCell<Character> cell : newPiece) {
            if (!board.positionIsOnGrid(cell.pos())) {
                return false;
            }
            if (board.get(cell.pos()) != ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer getTimerDelay() {
        return 1000;
    }

    @Override
    public GameState getGameState() {
        return this.gameState; 
    }

    @Override
    public void clockTick() {
        // move pac-man to the next position
        
      }
}
