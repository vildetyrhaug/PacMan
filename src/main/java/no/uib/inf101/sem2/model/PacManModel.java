package no.uib.inf101.sem2.model;

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
    public Iterable<GridCell<Character>> getTilesOnPellets() {
        return this.board.getPellets();
    }

    @Override
    public Iterable<GridCell<Character>> getTileOnMovingPacMan() {
        return this.movingPacMan;
    }

    public void move(PacDirection direction){
        switch(direction){
            case LEFT -> movePacMan(0,-1);
            case RIGHT -> movePacMan(0,1);
            case UP -> movePacMan(-1,0);
            case DOWN -> movePacMan(1,0);
        }
    }

    @Override
    public void movePacMan(int dx, int dy) {
        // moves the piece around on the board
        // returns a boolean indicating whether the move was successful or not.
 
            PacMan newPiece = this.movingPacMan.shiftedBy(dx, dy);
            if (this.legalPlacement(newPiece)) {
                this.movingPacMan = newPiece;
                this.direction = this.getDirection();
                // check what is in the position of the pacman
                interactWith(this.movingPacMan.getPos());

        }}          

    public void interactWith(CellPosition pos) {
        // checks what is in the position of the pacman
        // if it is a pellet, it is removed from the board
        // if it is a ghost, the game is over
        if (board.get(pos) == 'o') {
            board.removePellet(pos);
            // update score
            
        }
        if (board.get(pos) == 'G') {
            gameState = GameState.GAME_OVER;
        }
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
            if (board.get(cell.pos()) != ' ' && board.get(cell.pos()) != 'P' && board.get(cell.pos()) != 'o') {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer getTimerDelay() {
        return 300;
    }

    @Override
    public GameState getGameState() {
        return this.gameState; 
    }

    @Override
    public void clockTick() {
        move(direction);
        pelletInPos();
    }

    @Override
    public void setDirection(PacDirection direction) {
        // check if neighbor is empty 
        // if so, change direction
        // if not, don´t change direction
        
        if (legalPlacement(this.movingPacMan.shiftedBy(direction.getDx(), direction.getDy())))
            this.direction = direction;
        
    }

public void pelletInPos(){
    // run through the list of pellets and check if the movingPacMan is on the same position as a pellet
    // if so, add 10 points and change pellet in board to empty space
    // if not, do nothing
    for (GridCell<Character> pelletCell : board.getPellets()){
        for (GridCell<Character> pacManCell : movingPacMan){

            if (pelletCell.pos() == pacManCell.pos()){
                System.out.println("pelletInPos");

                board.removePellet(pelletCell.pos());
            }
    }
}}} 