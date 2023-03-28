package no.uib.inf101.sem2.pacMan.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pacMan.controller.ControllablePacManModel;
import no.uib.inf101.sem2.pacMan.view.ViewablePacManModel;


public class PacManModel implements ViewablePacManModel, ControllablePacManModel {
    
    PacManBoard board;
    GameState gameState;
    private int score;
    private int level;

    public PacManModel(PacManBoard board) {
        this.board = board;

        this.gameState = GameState.ACTIVE_GAME;
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
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public boolean movePacMan(int deltaRow, int deltaCol) {
        return true;
    }
}
