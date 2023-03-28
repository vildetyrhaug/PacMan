package no.uib.inf101.sem2.pacMan.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class PacManBoard extends Grid<Character> {
    /* Objekter i denne klassen representerer et tetrisbrett. */

    public PacManBoard(int rows, int cols) {
        
        // konstruktør gir kall til super-konstruktøren
        super(rows, cols);

        // initierer alle cellene med '-'
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.set(new CellPosition(i, j), '-');
        }
    }}
    
    public String prettyString() {
       String prettyString = "";

        for (int i = 0; i < this.rows(); i++){
            for (int j = 0; j < this.cols(); j++){
                prettyString += this.get(new CellPosition(i, j));
            }
            if (i < rows()-1){
                prettyString += "\n";
            }
        }
        //returnerer en string representasjon av brettet på en fin måte
        // hver rad adskilt med linjeskift, og bokstavene i hver rad limt sammen.
        return prettyString;
    }
}


