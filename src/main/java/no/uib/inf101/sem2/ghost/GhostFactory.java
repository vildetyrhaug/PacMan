package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;

public interface GhostFactory {
    
    /* 
     * @return en ny ghost
     */
    Ghost getNext(CellPosition pos);

}
