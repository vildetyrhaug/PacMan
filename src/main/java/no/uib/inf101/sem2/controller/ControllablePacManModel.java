package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.GhostDirection;
import no.uib.inf101.sem2.model.PacDirection;

public interface ControllablePacManModel {
     

    /* 
     * brukes for å gi retningskommando til movePacMan
     * @param pacdirection er hvilken retning pacman skal flyttes
     */
    void movePacDirection(PacDirection pacdirection);

    /* 
     * brukes for å gi retningskommando til moveGhost
     * tildeler hvert ghost en retning og kaller moveGhost med riktige parametre
     */
    void moveGhostDirection();
    
    /* 
     * brukes for å flytte pacman rundt på brettet 
     * @param dx er hvor mange rader pacman skal flyttes
     * @param dy er hvor mange kolonner pacman skal flyttes
     */
    void movePacMan(int dx, int dy);

    /* 
     * flytter ghosts rundt på brettet
     * param ghost er hvilken ghost som skal flyttes
     * @param dx er hvor mange rader ghost skal flyttes
     * @param dy er hvor mange kolonner ghost skal flyttes
     */
    void moveGhost(Ghost ghost, int dx, int dy);

    /* 
     * @return en GameState som indikerer om spillet er aktivt, eller GameOver
     */
    GameState getGameState();

    /* 
     * en metode som henter ut hvor mange millisekunder (som integer) 
     * det skal være mellom hvert klokkeslag 
     */
    Integer getTimerDelay();

    /* 
     * kalles hver gang klokken slår.
     */
    void clockTick();


    /* 
     * setter retningen til PacMan
     * @param pacDirection er hvilken retning pacman skal flyttes
     */
    void setDirection(PacDirection pacDirection);


    /* 
     * setter retningen til Ghost
     * @param ghost er hvilken ghost som skal flyttes
     * @param ghostDirection er hvilken retning ghost skal flyttes
     */
    void setDirectionGhost(Ghost ghost, GhostDirection ghostDirection);

    /* 
     * endrer gameState baser på input
     * @param gameState er hvilken gameState som skal settes
     */
    void setGameState(GameState gameState);

    /* 
     * resetter pacMan til startposisjon
     */
    void resetPacMan();

    /* 
     * resetter score til 0
     */
    void resetScore();

    /* 
     * resetter ghosts til startposisjon
     */
    void resetGhosts();

    /* 
     * resetter brettet til startposisjon
     */
    void resetBoard();

    /* 
     * sjekker hva som er i posisjonen pacman er flyttet til 
     * @param pos er posisjonen pacman er flyttet til
     */
    void interactWith(CellPosition pos);
    
}
