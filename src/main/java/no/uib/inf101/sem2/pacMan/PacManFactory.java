package no.uib.inf101.sem2.pacMan;

import no.uib.inf101.sem2.grid.CellPosition;

public interface PacManFactory {
    
    /* 
     * @return en ny Pac-man
     * @param pos er celleposisjonen til den nye pac-man
     */
    PacMan getNext(CellPosition pos);
}
