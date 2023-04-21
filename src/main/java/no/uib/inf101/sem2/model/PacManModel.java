package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

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
    //Ghost movingGhost;
    List<Ghost> ghosts;

    GameState gameState;
    PacDirection pacDirection;    
    GhostDirection ghostDirection;

    PacManController controller;
    int score = 0;

    public PacManModel(PacManBoard board, PacManFactory pacManFactory, GhostFactory ghostFactory,int numGhosts) {
        this.board = board;
        this.pacManFactory = pacManFactory;
        this.ghostFactory = ghostFactory;

        this.gameState = GameState.START_GAME;

        this.pacDirection = PacDirection.CENTER;
        this.ghostDirection = GhostDirection.CENTER;

        this.movingPacMan = pacManFactory.getNext();
        this.ghosts = new ArrayList<>();
        for (int i = 0; i < numGhosts; i++) {
            this.ghosts.add(ghostFactory.getNext());
        }
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
    public Iterable<CellPosition> getTilesOnMovingGhosts() {
        List<CellPosition> positions = new ArrayList<>();
        for (Ghost ghost : ghosts) {
            positions.add(ghost.getPos());
        }
        return positions;
    }

    @Override
    public void movePacDirection(PacDirection pacDirection) {
        switch(pacDirection){
            case LEFT -> movePacMan(0,-1);
            case RIGHT -> movePacMan(0,1);
            case UP -> movePacMan(-1,0);
            case DOWN -> movePacMan(1,0);
        }
    }
    
    @Override 
    public void moveGhostDirection(GhostDirection ghostDirection) {
        for (Ghost ghost : ghosts) {
            randomizeGhostDirection(ghost);
            switch(ghostDirection){
                case LEFT -> moveGhost(0,-1);
                case RIGHT -> moveGhost(0,1);
                case UP -> moveGhost(-1,0);
                case DOWN -> moveGhost(1,0);
            }}
    }

    private void randomizeGhostDirection(Ghost ghost){
        int random = (int) (Math.random() * 4);
        switch(random){
            case 0 -> setDirectionGhost(ghost, GhostDirection.LEFT);
            case 1 -> setDirectionGhost(ghost, GhostDirection.RIGHT);
            case 2 -> setDirectionGhost(ghost, GhostDirection.UP);
            case 3 -> setDirectionGhost(ghost, GhostDirection.DOWN);
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
        }            
}       
        
 /*    public void moveGhost(Ghost ghost, int dx, int dy) {
        Ghost newGhost = ghost.shiftedBy(dx, dy);
        if (legalPlacementGhost(newGhost)){
            ghost.setPos(newGhost.getPos());
        }
    } */
    @Override
    public void moveGhost(int dx, int dy) {
        for (Ghost ghost : this.ghosts) {
            Ghost newGhost = ghost.shiftedBy(dx, dy);
            if (legalPlacementGhost(newGhost)) {
                ghost.setPosition(newGhost.getPos());
            }
        }
}
   /*  @Override
    public void moveGhost(int dx, int dy) {
        
        Ghost newGhost = this.movingGhost.shiftedBy(dx, dy);
        if (legalPlacementGhost(newGhost)){
            movingGhost = newGhost;
            ghostDirection = this.getGhostDirection();
        }
    }
 */

    private void interactWith(CellPosition pos) {
        // checks what is in the position of the pacman
        // if it is a pellet, it is removed from the board
        // if it is a ghost, the game is over
        
        if (board.get(pos) == 'o') {
            board.removePelletAndFruit(pos);
            // update score
            updateScore(10);
            
            checkIfAllPelletsAreEaten();
        }
        
        if (ghostsAndPacCollide()) {
            setGameState(GameState.GAME_OVER); 
                }

        // if pac hits fruit -> update score
        if (board.get(pos) == 'f') {
            board.removePelletAndFruit(pos);
            // update score
            updateScore(50);
        }
    }

    private boolean ghostsAndPacCollide(){
        for (Ghost ghost : ghosts) {
            if (ghost.getPos().equals(movingPacMan.getPos())) {
                return true;
            }
        }
        return false;
    }

    private void checkIfAllPelletsAreEaten() {
        // checks if all pellets are eaten
        // if so, the game is won
        if (allPelletsEaten()) {
            setGameState(GameState.GAME_WON);
        }
    }
    public boolean allPelletsEaten() {
        for (GridCell<Character> cell : getTilesOnPellets()) {
            if (cell.value() == 'o') {
                return false;
            }
        }
        return true;
    }

    public void updateScore(int points) {
            this.score += points;
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
        else if (board.get(newPac.getPos()) != ' ' && board.get(newPac.getPos()) != 'o' && board.get(newPac.getPos()) != 'f') {
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
        else if (board.get(newGhost.getPos()) != ' ' && board.get(newGhost.getPos()) != 'o' && board.get(newGhost.getPos()) != 'f' ) {
                return false;
            }
        return true;
    }


    @Override
    public Integer getTimerDelay() {
        return 100;
    }

    @Override
    public GameState getGameState() {
        return this.gameState; 
    }

    @Override
    public void clockTick() {
        movePacDirection(pacDirection);
        interactWith(getTileOnMovingPacMan());
        moveGhostDirection(ghostDirection);
    }

    @Override
    public void setDirection(PacDirection direction) {
        // check if neighbor is empty 
        // if so, change direction
        // if not, don´t change direction

        if (legalPlacementPac(this.movingPacMan.shiftedBy(direction.getDx(), direction.getDy()))){
            this.pacDirection = direction;   
    }}

    @Override
    public void setDirectionGhost(Ghost ghost, GhostDirection direction) {
        if ((legalPlacementGhost(ghost.shiftedBy(direction.getDx(), direction.getDy()))) && (!newGhostDirectionIsOppositeDirection(direction) || ghostLockedInCorner(ghost))){
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

    private boolean ghostLockedInCorner(Ghost ghost) {
        // if all the surrounding directions except for backwards are walls, return true
        // if not, return false
        switch(ghostDirection){
            case LEFT -> {
                if (!legalPlacementGhost(ghost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(ghost.shiftedBy(1,0)) 
                && !legalPlacementGhost(ghost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case RIGHT -> {
                if (!legalPlacementGhost(ghost.shiftedBy(0,1)) 
                && !legalPlacementGhost(ghost.shiftedBy(1,0)) 
                && !legalPlacementGhost(ghost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case UP -> {
                if (!legalPlacementGhost(ghost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(ghost.shiftedBy(0,1)) 
                && !legalPlacementGhost(ghost.shiftedBy(-1,0))){
                    return true;
                }
            }
            case DOWN -> {
                if (!legalPlacementGhost(ghost.shiftedBy(0,-1)) 
                && !legalPlacementGhost(ghost.shiftedBy(0,1)) 
                && !legalPlacementGhost(ghost.shiftedBy(1,0))){
                    return true;
                }
            }
        }
        return false;  
    }

    @Override
    public void setGameState(GameState gameState) {
        System.out.println("Game state changed to " + gameState);
        this.gameState = gameState;
    }


    @Override
    public Object getScore() {
        return this.score;    
    }

    private void getNewGhost() {
        this.ghosts.add(ghostFactory.getNext());
    }

}