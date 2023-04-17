package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewablePacManModel {
        
        /*  returnerer dimensjonene til brettet. 
        Mer presist, en metode som returnerer et objekt som 
        har metoder for å finne antall rader og kolonner. */
    GridDimension getDimension();
        

        /*  itererer over alle flisene på brettet. 
            Mer presist, en metode som returnerer et objekt som, 
            når det itereres over, 
            gir alle posisjonene på brettet med tilhørende symbol. */
    Iterable<GridCell<Character>> getTilesOnBoard();
            
    
        /* itererer over alle flisene på pacman 
            Mer presist, en metode som returnerer et objekt som, 
            når det itereres over, 
            gir alle posisjonene på pacman
            */
    Iterable<GridCell<Character>> getTileOnMovingPacMan();
    
        /* Itererer over alle flisene på brettet 
         * Mer presist, en metode som returnerer et objekt som, 
            når det itereres over, 
            gir alle posisjonene på brettet som er tomme.
         */
    Iterable<GridCell<Character>> getTilesOnPellets();
        /* 
         * @return tilstanden til spillet
         */
    //GameState getGameState();
}
