package no.uib.inf101.sem2.pacMan;

import no.uib.inf101.sem2.grid.CellPosition;

public interface PacManFactory {
    
    /* 
     * @return en ny Pac-man
     */
    PacMan getNext(CellPosition pos);
}
