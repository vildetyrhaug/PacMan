package no.uib.inf101.sem2.ghost;

import no.uib.inf101.sem2.grid.CellPosition;

public class RandomGhostFactory implements GhostFactory {
    
    @Override
    public Ghost getNext(CellPosition pos) {
        // Lager nytt ghost objekt
        Ghost nextGhost = Ghost.newGhost(pos);
        return nextGhost;
    }
    
}
