package no.uib.inf101.sem2.controller;

public interface ControllablePacManModel {
     /* 
     * brukes for å flytte brikken rundt på brettet 
     * Metoden skal returnere en boolean som forteller 
     * hvorvidt flyttingen faktisk ble gjennomført eller ikke.
     */
    public boolean movePacMan(int deltaRow, int deltaCol);

    public void movePacManLeft();

    public void movePacManRight();

    public void movePacManUp();

    public void movePacManDown();
    
}
