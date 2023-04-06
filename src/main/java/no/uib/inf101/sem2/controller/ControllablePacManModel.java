package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;

public interface ControllablePacManModel {
     /* 
     * brukes for å flytte brikken rundt på brettet 
     * Metoden skal returnere en boolean som forteller 
     * hvorvidt flyttingen faktisk ble gjennomført eller ikke.
     */
    public boolean movePacMan(int deltaRow, int deltaCol);

    /* 
     * brukes for å flytte brikken til venstre på brettet
     */
    public void movePacManLeft();

    /* 
     * brukes for å flytte brikken til høyre på brettet
     */
    public void movePacManRight();

    /* 
     * brukes for å flytte brikken oppover på brettet
     */
    public void movePacManUp();

    /* 
     * brukes for å flytte brikken nedover på brettet
     */
    public void movePacManDown();

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
