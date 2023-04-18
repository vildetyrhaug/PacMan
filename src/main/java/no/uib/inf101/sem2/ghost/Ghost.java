package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;

public class Ghost {
    
    
    CellPosition pos; 

    public Ghost(CellPosition pos) {
        // Oppretter en pacman
        this.pos = pos;
    }

    static Ghost newGhost(){
        return new Ghost(new CellPosition(5, 10));
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



}
