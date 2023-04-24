package no.uib.inf101.sem2.view;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

public class CellPositionToPixelConverter {

  // har en parameter av typen CellPosition og
  // returnerer et Rectangle2D -objekt.  
  // innefor hvilket område rutenettet befinner seg
  // hvor mange rader og kolonner det er i rutenettet som helhet, og
  // hvor stor avstanden mellom rutene skal være.

  // beskriver innenfor hvilket område rutenettet skal tegnes
  Rectangle2D box;
  // beskriver størrelsen til rutenettet rutene vil være en del av
  GridDimension gd;
  // beskriver hvor stor avstanden skal være mellom rutene
  Double margin; 

  // Konstruktør
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

      // beregner størrelsen til cellen
  public double getCellWidth(){
    double cellWidth = ((box.getWidth() - ((gd.cols()+1) * margin))/ gd.cols());
    return cellWidth;
  }
  public double getCellHeight() {
    double cellHeight = ((box.getHeight() - ((gd.rows()+1) * margin))/ gd.rows());
    return cellHeight;
  }    
    // beregner posisjonen til cellen
  public double getCellX(CellPosition cellPos) {
    double cellX = box.getX() + margin + ((getCellWidth() + margin) * cellPos.col());
    return cellX;
  }
  public double getCellY(CellPosition cellPos) {
    double cellY = box.getY() + margin + ((getCellHeight() + margin) * cellPos.row());
    return cellY;
  }



  public Rectangle2D getBoundsForCell(CellPosition cellPos) {
    // Tegner cellen
    Rectangle2D cell = new Rectangle2D.Double(
                getCellX(cellPos), 
                getCellY(cellPos), 
                getCellWidth(), 
                getCellHeight()); 
    return cell;
  }

  public Ellipse2D getBoundsForPacMan(CellPosition cellPos) {
    // Tegner cellen
    Ellipse2D pacMan = new Ellipse2D.Double(
              getCellX(cellPos), 
              getCellY(cellPos), 
              getCellHeight(), 
              getCellWidth()); 
    return pacMan;
  }
  public Ellipse2D getBoundsForGhosts(CellPosition cellPos) {
    // Tegner cellen
    Ellipse2D Ghost = new Ellipse2D.Double(
              getCellX(cellPos), 
              getCellY(cellPos), 
              getCellHeight(), 
              getCellWidth()); 
    return Ghost;
  }

  public Ellipse2D getBoundsForPellet(CellPosition cellPos) {
    // Tegner pelleten
    Ellipse2D pellet = new Ellipse2D.Double(
              getCellX(cellPos)+getCellWidth()/3, 
              getCellY(cellPos)+getCellHeight()/3, 
              getCellWidth()/3, 
              getCellHeight()/3); 
    return pellet;
  }

  public Ellipse2D getBoundsForFruit(CellPosition cellPos){
    // Tegner frukten
    Ellipse2D fruit = new Ellipse2D.Double(
              getCellX(cellPos)+getCellWidth()/4, 
              getCellY(cellPos)+getCellHeight()/4, 
              getCellWidth()/2, 
              getCellHeight()/2); 
    return fruit;
  }

}

