package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllablePacManModel;
import no.uib.inf101.sem2.controller.PacManController;
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
    PacDirection direction;    

    PacManController controller;

    public PacManModel(PacManBoard board, PacManFactory factory) {
        this.board = board;
        this.factory = factory;
        this.gameState = GameState.ACTIVE_GAME;

        this.direction = PacDirection.CENTER;

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
    public boolean movePacMan(PacDirection direction) {
        // moves the piece around on the board
        // returns a boolean indicating whether the move was successful or not.
        int deltaRow = direction.getDx();
        int deltaCol = direction.getDy();
        System.out.println("movePacMan, direction:" + direction + " deltaRow:" 
        + deltaRow + " deltaCol:" + deltaCol);
        // LEFT:
        if(deltaRow == 0 && deltaCol == -1){
            PacMan newPiece = this.movingPacMan.shiftedBy(0, -1);
            if (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
        }          
        return true;
} 
        // RIGHT:
        if(deltaRow == 0 && deltaCol == 1){
            PacMan newPiece = this.movingPacMan.shiftedBy(0, 1);
            if (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
        }            
        return true;
}
        // UP:
        if(deltaRow == -1 && deltaCol == 0){
            PacMan newPiece = this.movingPacMan.shiftedBy(-1, 0);
            if (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
        } 
        return true;
    } 
        // DOWN:
        if(deltaRow == 1 && deltaCol == 0){
            PacMan newPiece = this.movingPacMan.shiftedBy(1, 0);
            while (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
        } 
        return true;
    }
        return false;
    }

    public PacDirection getDirection() {
        return direction.currentDirection();
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
        // interact with pacDirection to move the piece
        System.out.println("clockTick called");
        System.out.println("clocktick: direction: " + this.getDirection());
        movePacMan(this.getDirection());
      }
}
