package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.PacDirection;

public interface ControllablePacManModel {
     /* 
     * brukes for å flytte brikken rundt på brettet 
     * Metoden skal returnere en boolean som forteller 
     * hvorvidt flyttingen faktisk ble gjennomført eller ikke.
     */
    public boolean movePacMan(PacDirection direction);

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


    
}
