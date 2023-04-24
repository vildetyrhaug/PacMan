package no.uib.inf101.sem2.pacMan;

import no.uib.inf101.sem2.grid.CellPosition;

public class RandomPacManFactory implements PacManFactory {

    @Override
    public PacMan getNext(CellPosition pos) {
        // Lager nytt pac-man objekt
        PacMan nextPacMan = PacMan.newPacMan(pos);
        return nextPacMan;
    }
    
}
