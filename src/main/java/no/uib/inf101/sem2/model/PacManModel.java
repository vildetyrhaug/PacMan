package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.controller.ControllablePacManModel;
import no.uib.inf101.sem2.controller.PacManController;
import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.ghost.GhostFactory;
import no.uib.inf101.sem2.ghost.RandomGhostFactory;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.PacMan;
import no.uib.inf101.sem2.pacMan.PacManFactory;
import no.uib.inf101.sem2.pacMan.RandomPacManFactory;
import no.uib.inf101.sem2.view.ViewablePacManModel;

public class PacManModel implements ViewablePacManModel, ControllablePacManModel {

    private PacManBoard board;
    PacManFactory pacManFactory;
    private PacMan movingPacMan;

    private GhostFactory ghostFactory;
    private List<Ghost> ghosts;

    private GameState gameState;
    private PacDirection pacDirection;    

    PacManController controller;
    private int score = 0;

    private boolean ghostsAreVulnerable = false;
    private boolean pacManHasEatenFruit = false;


    public PacManModel(PacManBoard board, PacManFactory pacManFactory, GhostFactory ghostFactory,int numGhosts) {
        this.board = board;
        this.pacManFactory = pacManFactory;
        this.ghostFactory = ghostFactory;

        this.gameState = GameState.START_GAME;

        this.pacDirection = PacDirection.CENTER;

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
    public void moveGhostDirection() {
        for (Ghost ghost : ghosts) {
            randomizeGhostDirection(ghost);
            switch(ghost.getDirection()){
                case LEFT -> moveGhost(ghost, 0,-1);
                case RIGHT -> moveGhost(ghost, 0,1);
                case UP -> moveGhost(ghost, -1,0);
                case DOWN -> moveGhost(ghost, 1,0);
            }}
    }

    private void randomizeGhostDirection(Ghost ghost){
        int random = (int) (Math.random() * 4);
        switch(random){ 
            case 0:
                setDirectionGhost(ghost, GhostDirection.LEFT);
                break;
            case 1:
                setDirectionGhost(ghost, GhostDirection.RIGHT);
                break;
            case 2:
                setDirectionGhost(ghost, GhostDirection.UP);
                break;
            case 3:
                setDirectionGhost(ghost, GhostDirection.DOWN);
                break;
        }
        if (ghostsAreVulnerable) {
            ghost.setVulnerable(true);
        }
        else {
            ghost.setVulnerable(false);
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

    @Override
    public void moveGhost(Ghost ghost, int dx, int dy) {
        Ghost newGhost = ghost.shiftedBy(dx, dy);
        if (legalPlacementGhost(newGhost)) {
                ghost.setPosition(newGhost.getPos());
        }
        }


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
        // if pac hits fruit -> update score and make ghosts vulnerable
        if (board.get(pos) == 'f') {
            board.removePelletAndFruit(pos);
            // update score
            updateScore(50);
            ghostsAreVulnerable = true;
            pacManHasEatenFruit = true;

            checkIfAllPelletsAreEaten();
        }
        else if (ghostsAndPacCollide()) {
            if (pacManHasEatenFruit && timeElapsedIsWithinBounds() && ghostsAreVulnerable) {
                        for (Ghost ghost : ghosts) {
                            if (ghost.getPos().equals(pos)) {
                                ghosts.remove(ghost);
                                updateScore(50);
                                ghostsAreVulnerable = false;
                                break;
                            }
                        }
            } else {
                setGameState(GameState.GAME_OVER);
            }
        }
        }
        /* Ghost vulnerableGhost = getVulnerableGhost();
                if (vulnerableGhost != null) {
                    ghosts.remove(vulnerableGhost);
                    updateScore(50);
                    ghostsAreVulnerable = false;
                } */
    
        /* if (ghostsAndPacCollide()) {
            setGameState(GameState.GAME_OVER); 
                }
 */
        /* // if pac hits fruit -> update score
        if (board.get(pos) == 'f') {
            board.removePelletAndFruit(pos);
            // update score
            updateScore(50);
        } */
    

    private boolean timeElapsedIsWithinBounds() {
        long timeElapsed = System.currentTimeMillis() - board.getTimeFruitEaten();

        if (timeElapsed < 10000) {
            System.out.println("timeElapsedIsWithinBounds true: Time elapsed: " + timeElapsed);
            return true;
        } else {
            System.out.println("timeElapsedIsWithinBounds false: Time elapsed: " + timeElapsed);
            pacManHasEatenFruit = false;
            ghostsAreVulnerable = false;
            return false;
        }
    }    



    private Ghost getVulnerableGhost() {
        for (Ghost ghost : ghosts) {
            if (ghost.isVulnerable()) {
                return ghost;
            }
        }
        return null;
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
        return 150;
    }

    @Override
    public GameState getGameState() {
        return this.gameState; 
    }

    @Override
    public void clockTick() {
        movePacDirection(pacDirection);
        interactWith(getTileOnMovingPacMan());
        moveGhostDirection();
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
        if ((legalPlacementGhost(ghost.shiftedBy(direction.getDx(), direction.getDy()))) 
        && (!newGhostDirectionIsOppositeDirection(ghost, direction) || ghostLockedInCorner(ghost))){
            ghost.setDirection(direction);
        }}
    
    private boolean newGhostDirectionIsOppositeDirection(Ghost ghost, GhostDirection direction) {
        // if direction is the opposite of current direction, return false
        switch(direction){
            case LEFT -> {
                if (ghost.getDirection() == GhostDirection.RIGHT){
                    return true;
                }
            }
            case RIGHT -> {
                if (ghost.getDirection() == GhostDirection.LEFT){
                    return true;
                }
            }
            case UP -> {
                if (ghost.getDirection() == GhostDirection.DOWN){
                    return true;
                }
            }
            case DOWN -> {
                if (ghost.getDirection() == GhostDirection.UP){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ghostLockedInCorner(Ghost ghost) {
        // if all the surrounding directions except for backwards are walls, return true
        // if not, return false
        switch(ghost.getDirection()){
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
            case CENTER -> {
                return false;
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

    @Override
    public void resetPacMan(){
        PacManFactory pacManFactory = new RandomPacManFactory();
        this.movingPacMan = pacManFactory.getNext();
    }
    @Override
    public void resetGhosts(){
        GhostFactory ghostFactory = new RandomGhostFactory();
        this.ghosts = new ArrayList<>();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();
        getNewGhost();

    }
    @Override
    public void resetScore() {
        this.score = 0;
    }
    @Override
    public void resetBoard() {
        this.board = new PacManBoard(19, 19);
    }



}