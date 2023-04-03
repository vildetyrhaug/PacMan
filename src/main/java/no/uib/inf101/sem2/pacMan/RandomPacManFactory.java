package no.uib.inf101.sem2.pacMan;

public class RandomPacManFactory implements PacManFactory {

    @Override
    public PacMan getNext() {
        // Lager nytt pac-man objekt
        PacMan nextPacMan = PacMan.newPacMan();
        return nextPacMan;
    }
    
}
