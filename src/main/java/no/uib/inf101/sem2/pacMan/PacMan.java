package no.uib.inf101.sem2.pacMan;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.PacDirection;
import no.uib.inf101.sem2.model.PacManBoard;
import no.uib.inf101.sem2.model.PacManModel;

public class PacMan {
    // Representerer en pacman

    CellPosition pos; 
    PacDirection direction;
    PacManBoard board;
    PacManModel model;

    public PacMan(CellPosition pos) {
        // Oppretter en pacman
        this.pos = pos;
        this.direction = PacDirection.CENTER;
    }

    static PacMan newPacMan(CellPosition pos){
        // Returnerer en ny pacman
        return new PacMan(pos);
    } 

    public PacMan shiftedBy(int dx, int dy) {
        // Returnerer en ny pacman som er flyttet dx rader nedover og dy kolonner til høyre
        CellPosition newPos = new CellPosition(pos.row() + dx, pos.col() + dy);
        PacMan copy = new PacMan(newPos);
        return copy;
    }

    public CellPosition getPos() {
        return pos;
    }

    public void setPosition(CellPosition pos) {
        this.pos = pos;
    }
}
