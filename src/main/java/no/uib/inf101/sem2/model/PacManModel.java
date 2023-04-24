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

    public boolean ghostsAreVulnerable = false;
    public boolean pacManHasEatenFruit = false;


    public PacManModel(PacManBoard board, PacManFactory pacManFactory, GhostFactory ghostFactory,int numGhosts) {
        this.board = board;
        this.pacManFactory = pacManFactory;
        this.ghostFactory = ghostFactory;

        this.gameState = GameState.START_GAME;

        this.pacDirection = PacDirection.CENTER;

        this.movingPacMan = pacManFactory.getNext(board.getPacManStartPosition());
        this.ghosts = new ArrayList<>();
        for (int i = 0; i < numGhosts; i++) {
            this.ghosts.add(ghostFactory.getNext(board.getGhostStartPosition));
        }
    }
    
    public PacManModel(PacManBoard board) {
        this.board = board;

        this.gameState = GameState.START_GAME;

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
        return board.getPellets();
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
            case DOWN -> movePacMan(1,0);
            case UP -> movePacMan(-1,0);
            case CENTER -> movePacMan(0,0);
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
            }
        }
    }

    public void randomizeGhostDirection(Ghost ghost){
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

    @Override
    public void interactWith(CellPosition pos) {
        // sjekker hva som er på posisjonen til pacman
        // hvis det er en pellet -> fjern pellet, oppdater score og sjekk om alle pellets er spist
        // hvis det er frukt -> fjern frukt, oppdater score og gjør spøkelsene sårbare i ti sekunder 
        // hvis spøkelsene og pacman kolliderer -> sjekk om pacman har spist frukt og om spøkelsene er sårbare
        // hvis ja -> fjern spøkelsene og oppdater score
        // hvis nei -> game over

        if (board.get(pos) == 'o') {
            board.removePelletAndFruit(pos);
            // update score
            updateScore(10);
            
            checkIfAllPelletsAreEaten();
        }
        if (board.get(pos) == 'f') {
            board.removePelletAndFruit(pos);
            updateScore(50);
            ghostsAreVulnerable = true;
            pacManHasEatenFruit = true;
            checkIfAllPelletsAreEaten();
        }
        if (!timeElapsedIsWithinBounds()){
            ghostsAreVulnerable = false;
            pacManHasEatenFruit = false;
        }
        if (ghostsAndPacCollide()) {
            if (pacManHasEatenFruit && ghostsAreVulnerable && timeElapsedIsWithinBounds() ) {
                        for (Ghost ghost : ghosts) {
                            if (ghost.getPos().equals(pos)) {
                                ghosts.remove(ghost);
                                updateScore(200);
                                ghostsAreVulnerable = false;
                                pacManHasEatenFruit = false;
                                break;
                            }
                        }
            } else {
                setGameState(GameState.GAME_OVER);
            }
        }
    }
    
    // Sjekker om ti sekunder er gått siden pacman spiste frukt
    private boolean timeElapsedIsWithinBounds() {
        long timeElapsed = System.currentTimeMillis() - board.getTimeFruitEaten();

        if (timeElapsed < 10000) {
            return true;
        } else {
            pacManHasEatenFruit = false;
            ghostsAreVulnerable = false;
            return false;
        }
    }    

    public Ghost getVulnerableGhost() {
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
        // sjekker om alle pellets er spist
        // hvis ja -> game won
        if (allPelletsEaten()) {
            setGameState(GameState.GAME_WON);
        }
    }

    private boolean allPelletsEaten() {
        for (GridCell<Character> cell : getTilesOnPellets()) {
            if (cell.value() == 'o') {
                return false;
            }
        }
        return true;
    }

    private void updateScore(int points) {
        this.score += points;
    }
    
    private PacDirection getPacDirection() {
        return pacDirection.currentDirection();
    }
    
    private boolean legalPlacementPac(PacMan newPac) {
        if (!board.positionIsOnGrid(newPac.getPos())) {
                return false;
            }
        else if (board.get(newPac.getPos()) != ' ' 
            && board.get(newPac.getPos()) != 'o' 
            && board.get(newPac.getPos()) != 'f') {
                return false;
            }
        return true;
    }

    public boolean legalPlacementGhost(Ghost newGhost) {
        // sjekker om en ghost kan plasseres på brettet
        // returnerer true hvis det er lovlig, false ellers
        if (!board.positionIsOnGrid(newGhost.getPos())) {
                return false;
            }
        else if (board.get(newGhost.getPos()) != ' ' 
            && board.get(newGhost.getPos()) != 'o' 
            && board.get(newGhost.getPos()) != 'f'
            && board.get(newGhost.getPos()) != 'G' ) {
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
        // sjekker om det er lovlig å plassere pacman på ny posisjon
        // hvis ja -> oppdaterer retningen til pacman
        if (legalPlacementPac(this.movingPacMan.shiftedBy(direction.getDx(), direction.getDy()))){
            this.pacDirection = direction;   
    }}

    @Override
    public void setDirectionGhost(Ghost ghost, GhostDirection direction) {
        // sjekker om det er lovlig å plassere spøkelse på ny posisjon
        // hvis ja -> oppdaterer retningen til spøkelse
        if ((legalPlacementGhost(ghost.shiftedBy(direction.getDx(), direction.getDy()))) 
        && (!newGhostDirectionIsOppositeDirection(ghost, direction) 
        || ghostLockedInCorner(ghost))){
            ghost.setDirection(direction);
        }}
    
    private boolean newGhostDirectionIsOppositeDirection(Ghost ghost, GhostDirection direction) {
        // hvis ny retning er motsatt av retning spøkelse har, returner true
        // hvis ikke, returner false
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
        // sjekker om spøkelse er låst i hjørne
        // hvis ja -> returner true
        // hvis nei -> returner false
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
        this.gameState = gameState;
    }

    @Override
    public int getScore() {
        return this.score;    
    }

    private void getNewGhost() {
        CellPosition ghostPosition; 
        if (board.getGhostStartPosition() != null){
            ghostPosition = board.getGhostStartPosition();
        }
        else {
            ghostPosition = new CellPosition(board.rows()/2-1, board.cols()/2);
        }
        this.ghosts.add(ghostFactory.getNext(ghostPosition));
    }

    @Override
    public void resetPacMan(){
        PacManFactory pacManFactory = new RandomPacManFactory();
        CellPosition pacManPosition;
        if (board.getPacManStartPosition() != null){
            pacManPosition = board.getPacManStartPosition();
        }
        else {
            pacManPosition = new CellPosition(board.rows()/2, board.cols()/2);
        }
        this.movingPacMan = pacManFactory.getNext(pacManPosition);
    }

    @Override
    public void resetGhosts(){
        GhostFactory ghostFactory = new RandomGhostFactory();
        this.ghosts = new ArrayList<>();
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
        this.board = new PacManBoard(
            new String[]{
            "###################",
            "#     #     #     #",
            "# ### # ### # ### #",
            "#   f    #    f   #",
            "## ## ##   ## ## ##",
            "#     ### ###     #",
            "# ### ##   ## ### #",
            "# ##           ## #",
            "# #  # ## ## #  # #",
            "       #GGG#       ",
            "# #  # ##### #  # #",
            "# ##     P     ## #",
            "# ### ##   ## ### #",
            "#     ### ###     #",
            "## ## ##   ## ## ##",
            "#   f    #    f   #",
            "# ### # ### # ### #",
            "#     #     #     #",
            "###################"}
            );
    }

    @Override
    public boolean isInvincible() {
        if (pacManHasEatenFruit){        
            return true;
        }
        return false;
    }

    public void setPacManPosition(CellPosition position) {
        this.movingPacMan.setPosition(position);
    }
}