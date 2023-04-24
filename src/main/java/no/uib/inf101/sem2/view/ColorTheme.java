package no.uib.inf101.sem2.view;

import java.awt.Color;

public interface ColorTheme {
       
        /* 
        * Returnerer fargen til cellen med gitt verdi.
        * @param c  karakteren i cellen
        * @return verdi kan ikke være null
        */
    Color getCellColor(Character c);
        
        /* 
        * @return fargen til rammen rundt brettet 
        * retur-verdien skal være blå
        */
    Color getFrameColor(); 
        
        /* 
        * @return fargen til veggen 
        * farge skal være blå
        */
    Color getWallColor();
    
        /* 
        * @return fargen til bakgrunnen 
        * farge skal være svart
        */
    Color getBackgroundColor(); 

        /* 
         * @return fargen til pacman
         * farge skal være gul
         */
    Color getPacManColor();
    
        /* 
        * @return fargen til pellets
        */
    Color getPelletColor();

        /* 
         * @return fargen til ghosten
         */
    Color getGhostColor();

        /* 
        * @return fargen til den gjennomsiktige overlayen når spillet er game over
        * farge bør være new Color(0, 0, 0, 128)
        */
    Color getGameOverBackgroundColor();

        /* 
        * @return fargen på Game over teksten 
        * farge skal ikke være null eller gjennomsiktig.
        */
    Color getGameOverColor();

        /* 
         * @return fargen på pause teksten
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getPauseColor();

        /* 
         * @return fargen på pause bakgrunnen
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getPauseBackgroundColor();

        /* 
         * @return fargen på start teksten
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getStartBackgroundColor();

        /* 
         * @return fargen på start bakgrunnen
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getStartColor();

        /* 
         * @return fargen på frukt
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getFruitColor();

        /* 
         * @return fargen på scoren
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getScoreColor();

        /* 
         * @return fargen på bakgrunnen når man har vunnet
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getWonBackgroundColor();

        /* 
         * @return fargen på teksten når man har vunnet
         * farge skal ikke være null eller gjennomsiktig.
         */
    Color getWonColor();
 
}
