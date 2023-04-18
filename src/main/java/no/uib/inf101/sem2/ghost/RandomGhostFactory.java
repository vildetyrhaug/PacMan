package no.uib.inf101.sem2.ghost;

public class RandomGhostFactory implements GhostFactory {
    
    @Override
    public Ghost getNext() {
        // Lager nytt ghost objekt
        Ghost nextGhost = Ghost.newGhost();
        return nextGhost;
    }
    
}
