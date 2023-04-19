package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllablePacManModel;
import no.uib.inf101.sem2.controller.PacManController;
import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.view.ViewablePacManModel;

public class PacManModel implements ViewablePacManModel, ControllablePacManModel {

    PacManBoard board;
    PacManFactory pacManFactory;
    PacMan movingPacMan;

    GhostFactory ghostFactory;
    Ghost movingGhost;

    GameState gameState;
    PacDirection pacDirection;    
    GhostDirection ghostDirection;

    PacManController controller;
    int score = 0;

    public PacManModel(PacManBoard board, PacManFactory pacManFactory, GhostFactory ghostFactory) {
        this.board = board;
        this.pacManFactory = pacManFactory;
        this.ghostFactory = ghostFactory;

        this.gameState = GameState.ACTIVE_GAME;

        this.pacDirection = PacDirection.CENTER;
        this.ghostDirection = GhostDirection.CENTER;

        this.movingPacMan = pacManFactory.getNext();
        this.movingGhost = ghostFactory.getNext();
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
    public CellPosition getTileOnMovingPacMan() {
        return this.movingPacMan.getPos();
    }

    @Override
    public CellPosition getTileOnMovingGhost() {
        return this.movingGhost.getPos();
    }

    @Override
    public void move(PacDirection pacDirection, GhostDirection ghostDirection) {
        switch(pacDirection){
            case LEFT -> movePacMan(0,-1);
            case RIGHT -> movePacMan(0,1);
            case UP -> movePacMan(-1,0);
            case DOWN -> movePacMan(1,0);
        }
        // randomize the direction of the Ghost by calling setDirectionGhost
        // and then move the ghost
        this.randomizeGhostDirection();
        
    }
    private void randomizeGhostDirection(){
        int random = (int) (Math.random() * 4);
        switch(random){
            case 0 -> setDirectionGhost(GhostDirection.LEFT);
            case 1 -> setDirectionGhost(GhostDirection.RIGHT);
            case 2 -> setDirectionGhost(GhostDirection.UP);
            case 3 -> setDirectionGhost(GhostDirection.DOWN);
        }
        switch(ghostDirection){
            case LEFT -> moveGhost(0,-1);
            case RIGHT -> moveGhost(0,1);
            case UP -> moveGhost(-1,0);
            case DOWN -> moveGhost(1,0);
        }

    }

    @Override
    public void movePacMan(int dx, int dy) {
        // moves the piece around on the board
        // returns a boolean indicating whether the move was successful or not.
 
            PacMan newPiece = this.movingPacMan.shiftedBy(dx, dy);
            if (this.legalPlacementPac(newPiece)) {
                this.movingPacMan = newPiece;
                this.pacDirection = this.getPacDirection();
                // check what is in the position of the pacman
                interactWith(this.movingPacMan.getPos());

        }}       
        
    
    @Override
    public void moveGhost(int dx, int dy) {
        
        Ghost newGhost = this.movingGhost.shiftedBy(dx, dy);
        if (legalPlacementGhost(newGhost)){
            movingGhost = newGhost;
            ghostDirection = this.getGhostDirection();
        }
    }


    private void interactWith(CellPosition pos) {
        // checks what is in the position of the pacman
        // if it is a pellet, it is removed from the board
        // if it is a ghost, the game is over
        if (board.get(pos) == 'o') {
            board.removePellet(pos);
            // update score
            //controller.updateScore();
        }
        if (movingPacMan.getPos().equals(movingGhost.getPos())) {
            System.out.println("GAME OVER");
            gameState = GameState.GAME_OVER;
        }
    }

    

    private PacDirection getPacDirection() {
        return pacDirection.currentDirection();
    }
    private GhostDirection getGhostDirection() {
        return ghostDirection.currentDirection();
    }
    
    private boolean legalPlacementPac(PacMan newPac) {
        // sjekker om en pacman kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        if (!board.positionIsOnGrid(newPac.getPos())) {
                return false;
            }
        if (board.get(newPac.getPos()) != ' ' && board.get(newPac.getPos()) != 'P' && board.get(newPac.getPos()) != 'o') {
                return false;
            }
        
        return true;
    }

    private boolean legalPlacementGhost(Ghost newGhost) {
        // sjekker om en ghost kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        if (!board.positionIsOnGrid(newGhost.getPos())) {
                return false;
            }
        else if (board.get(newGhost.getPos()) != ' ' && board.get(newGhost.getPos()) != 'P' && board.get(newGhost.getPos()) != 'o' ) {
                return false;
            }

        return true;
    }


    @Override
    public Integer getTimerDelay() {
        return 200;
    }

    @Override
    public GameState getGameState() {
        return this.gameState; 
    }

    @Override
    public void clockTick() {
        move(pacDirection, ghostDirection);
    }

    @Override
    public void setDirection(PacDirection direction) {
        // check if neighbor is empty 
        // if so, change direction
        // if not, don´t change direction

        if (legalPlacementPac(this.movingPacMan.shiftedBy(direction.getDx(), direction.getDy()))){
            this.pacDirection = direction;
        
    } }

    private void setDirectionGhost(GhostDirection direction) {
        if ((legalPlacementGhost(this.movingGhost.shiftedBy(direction.getDx(), direction.getDy()))) && (!newGhostDirectionIsOppositeDirection(direction) || ghostLockedInCorner())){
            this.ghostDirection = direction;
        
    }}
    
    private boolean newGhostDirectionIsOppositeDirection(GhostDirection direction) {
        // if direction is the opposite of current direction, return false
        switch(direction){
            case LEFT -> {
                if (ghostDirection == GhostDirection.RIGHT){
                    return true;
                }
            }
            case RIGHT -> {
                if (ghostDirection == GhostDirection.LEFT){
                    return true;
                }
            }
            case UP -> {
                if (ghostDirection == GhostDirection.DOWN){
                    return true;
                }
            }
            case DOWN -> {
                if (ghostDirection == GhostDirection.UP){
                    return true;
                }
            }
        }
        
        return false;
}


    private boolean ghostLockedInCorner() {
        // if all the surrounding directions except for backwards are walls, return true
        // if not, return false
        switch(ghostDirection){
            case LEFT -> {
                if (!legalPlacementGhost(movingGhost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(1,0)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case RIGHT -> {
                if (!legalPlacementGhost(movingGhost.shiftedBy(0,1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(1,0)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case UP -> {
                if (!legalPlacementGhost(movingGhost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(0,1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case DOWN -> {
                if (!legalPlacementGhost(movingGhost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(0,1)) 
                && !legalPlacementGhost(movingGhost.shiftedBy(1,0))){
                    return true;
                }
            }
        }
        return false;  
    }}