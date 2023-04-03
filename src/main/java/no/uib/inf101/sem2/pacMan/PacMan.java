package no.uib.inf101.sem2.pacMan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

public class PacMan implements Iterable<GridCell<Character>> {
    // Representerer en pacman

    CellPosition pos; 

    public PacMan(CellPosition pos) {
        // Oppretter en pacman
        this.pos = pos;
    }

    static PacMan newPacMan(){
        return new PacMan(new CellPosition(7, 10));
    }

    public PacMan shiftedBy(int dx, int dy) {
        // Returnerer en ny pacman som er flyttet dx rader nedover og dy kolonner til høyre
        CellPosition newPos = new CellPosition(pos.row() + dx, pos.col() + dy);
        PacMan copy = new PacMan(newPos);
        return copy;
    }

    public void movePacMan() {
        // Flytter pacman

    }

    public void eatDot() {
        // Spiser en dot
    }

    public void eatPowerPellet() {
        // Spiser en powerpellet
    }

    public void die() {
        // Dør
    }

    public void win() {
        // Vinner
    }

    public void lose() {
        // Taper
    }

    public void reset() {
        // Resetter
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        // returnerer en iterator over alle cellene i pacman
        
        List<GridCell<Character>> list = new ArrayList<>();
        list.add(new GridCell<Character>(pos, 'P'));
        return list.iterator();
        
    }



}
