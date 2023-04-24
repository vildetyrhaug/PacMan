package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.GhostDirection;

public class Ghost {
    
    CellPosition pos; 
    GhostDirection direction;
    private boolean vulnerable = false;

    public Ghost(CellPosition pos) {
        // Oppretter en pacman
        this.pos = pos;
        this.direction = GhostDirection.CENTER;

    }

    static Ghost newGhost(CellPosition pos) {
        return new Ghost(pos);
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
    
  /*   public int getDx() {
        return direction.getDx();
    }

    public int getDy() {
        return direction.getDy();
    } */

    public void setPosition(CellPosition pos) {
        this.pos = pos;
    }

    public GhostDirection getDirection() {
        return direction;
    }

    public void setDirection(GhostDirection direction) {
        this.direction = direction;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }
    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }
}
