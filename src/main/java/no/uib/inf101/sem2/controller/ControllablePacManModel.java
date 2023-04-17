package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.PacDirection;

public interface ControllablePacManModel {
     

    /* 
     * brukes for å gi retningskommando til movePacMan
     * tar inn en direction og kaller movePacMan med riktige parametre
     */
    public void move(PacDirection direction);
    
    /* 
     * brukes for å flytte brikken rundt på brettet 
     * Metoden skal returnere en boolean som forteller 
     * hvorvidt flyttingen faktisk ble gjennomført eller ikke.
     */
    public void movePacMan(int dx, int dy);

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
    void setDirection(PacDirection left);


    
}
