package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.ghost.Ghost;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.GhostDirection;
import no.uib.inf101.sem2.model.PacDirection;

public interface ControllablePacManModel {
     

    /* 
     * brukes for å gi retningskommando til movePacMan
     * tar inn en direction og kaller movePacMan med riktige parametre
     */
    //public void move(PacDirection pacdirection, GhostDirection ghostDirection);
    public void movePacDirection(PacDirection pacdirection);
    public void moveGhostDirection();
    
    /* 
     * brukes for å flytte pacman rundt på brettet 
     */
    public void movePacMan(int dx, int dy);

    /* 
     * flytter brikken rundt på brettet
     */
    public void moveGhost(Ghost ghost, int dx, int dy);

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
     */
    void setDirection(PacDirection pacDirection);


    /* 
     * setter retningen til Ghost
     */
    void setDirectionGhost(Ghost ghost, GhostDirection ghostDirection);

    /* 
     * endrer gameState baser på input
     */
    public void setGameState(GameState gameState);

    
}
