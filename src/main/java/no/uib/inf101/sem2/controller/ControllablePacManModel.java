package no.uib.inf101.sem2.controller;

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
    
}
