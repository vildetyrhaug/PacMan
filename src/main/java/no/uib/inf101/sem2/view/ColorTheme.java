package no.uib.inf101.sem2.view;

import java.awt.Color;

public interface ColorTheme {
       
        /* 
        * Returnerer fargen til cellen med gitt verdi.
        * @param c  karakteren i cellen
        * return verdi kan ikke være null
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
        * @return fargen til den gjennomsiktige overlayen når spillet er game over
        * farge bør være new Color(0, 0, 0, 128)
        */
    //Color getSeethroughColor();

        /* 
        * @return fargen på Game over teksten 
        * farge skal ikke være null eller gjennomsiktig.
        */
    //Color getGameOverColor();
 
}