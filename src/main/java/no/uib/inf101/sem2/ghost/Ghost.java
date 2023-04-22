package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.GhostDirection;

public class Ghost {
    
    CellPosition pos; 
    GhostDirection direction;

    public Ghost(CellPosition pos) {
        // Oppretter en pacman
        this.pos = pos;
        this.direction = GhostDirection.CENTER;

    }

    static Ghost newGhost(int row, int col){
        return new Ghost(new CellPosition((row/2), col/2));
    }

    public Ghost shiftedBy(int dx, int dy) {
        // Returnerer en ny pacman som er flyttet dx rader nedover og dy kolonner til høyre
        CellPosition newPos = new CellPosition(pos.row() + dx, pos.col() + dy);
        Ghost copy = new Ghost(newPos);
        return copy;
    }

    public CellPosition getPos() {
        return pos;
    }

    public void setPosition(CellPosition pos) {
        this.pos = pos;
    }

    public GhostDirection getDirection() {
        return direction;
    }

    public void setDirection(GhostDirection direction) {
        this.direction = direction;
    }
}
