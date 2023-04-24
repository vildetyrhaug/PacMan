package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;

public interface GhostFactory {
    
    /* 
     * @return en ny ghost
     * @param pos er celleposisjonen til den nye ghost
     */
    Ghost getNext(CellPosition pos);

}
