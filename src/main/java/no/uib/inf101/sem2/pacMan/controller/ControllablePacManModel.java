package no.uib.inf101.sem2.pacMan.controller;

public interface ControllablePacManModel {
    
    /* 
     * moves pac-man around on the board
     * @return boolean to indicate whether the move was successful or not
     */
    boolean movePacMan(int deltaRow, int deltaCol);



}
