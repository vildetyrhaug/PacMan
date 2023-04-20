package no.uib.inf101.sem2.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;



public class PacManView extends JPanel {
    
    private ViewablePacManModel pacManModel;
    private ColorTheme color;
    
    private static final double OUTERMARGIN = 10;


    // KONSTRUKTØR
    public PacManView(ViewablePacManModel pacManModel) {
      this.setFocusable(true);
      this.setPreferredSize(new Dimension(600, 600));

      this.pacManModel = pacManModel;
      this.color = new DefaultColorTheme();

      this.setBackground(getBackground());
    }
  
    // METODER

  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      drawGame(g2);
      }


  // Tegner brettet 
  private void drawGame(Graphics2D canvas){
      
    // Setter fast størrelse i forhold til sidene på brettet 
    double cellWidth = this.getWidth() - 2 * OUTERMARGIN;
    double cellHeight = this.getHeight() - 2 * OUTERMARGIN;
    
    // Tegner bakgrunnen
    Rectangle2D rectangle = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
    canvas.setColor(color.getFrameColor());
    canvas.fill(rectangle);
    
    // Tegner cellene og pellets
    CellPositionToPixelConverter position = new CellPositionToPixelConverter(rectangle, pacManModel.getDimension(), 1);
    drawCellsAndPellets(canvas, pacManModel.getTilesOnBoard(), position, color);

    // Tegner movingPacMan piece 
    drawPacMan(canvas, pacManModel.getTileOnMovingPacMan(), position, color);

    // Tegner movingGhost piece
    drawGhost(canvas, pacManModel.getTileOnMovingGhost(), position, color);

    // Startskjerm 
    if (pacManModel.getGameState() == GameState.START_GAME){
      canvas.setColor(color.getStartBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getStartColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "PAC-MAN", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight-400);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 15));
      Inf101Graphics.drawCenteredString(canvas, "Målet er å samle opp alle gule pellets på brettet", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight-200);
      Inf101Graphics.drawCenteredString(canvas, "Pass deg for spøkelsene. Hvis de kommer borti deg", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight-100);
      Inf101Graphics.drawCenteredString(canvas, "så mister du ett liv. Du har tre liv.", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
      Inf101Graphics.drawCenteredString(canvas, "Plukk opp frukten for å få ekstra poeng.", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight+100);
      Inf101Graphics.drawCenteredString(canvas, "Benytt piltastene for å bevege pac-man", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight+200);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'mellomrom' for å starte spillet", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight+300);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'p' for å pause spillet", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight+400);
    }

    // Pauseskjerm
    if (pacManModel.getGameState() == GameState.PAUSE_GAME){
      
      canvas.setColor(color.getPauseBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getPauseColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 30));
      Inf101Graphics.drawCenteredString(canvas, "Spillet er satt på pause", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight-200);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 20));
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'p' for å fortsette spillet", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å avslutte spillet", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight+100);


    }
    if (pacManModel.getGameState() == GameState.GAME_OVER) {
      // Tegner en rute med gjennomsiktig farge som dekker hele skjermen,
      // og tegner teksten "GAME OVER" oppå
      canvas.setColor(color.getGameOverBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getGameOverColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "GAME OVER", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight-200);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 20));
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'space' for å starte på nytt", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å avslutte", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight + 100);
    
    } 
  }

    private static void drawCellsAndPellets(Graphics2D canvas, Iterable<GridCell<Character>> gridCellCharacter, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      // itererer over cellene og tegner dem
      for(GridCell<Character> cell : gridCellCharacter){
        // draw the cells 
          if (cell.value() == ' ' || cell.value() == '#' || cell.value() == 'P'){
              drawCells(canvas, cell, cellPosToPixConvert, colorTheme);
            }
          if (cell.value() == 'o'){
              drawPellets(canvas, cell, cellPosToPixConvert, colorTheme);
            }   
      }}

    private static void drawCells(Graphics2D canvas, GridCell<Character> cell, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
        // itererer over cellene og tegner dem
            Rectangle2D rectangle = cellPosToPixConvert.getBoundsForCell(cell.pos());
            Color cellColor = colorTheme.getCellColor(cell.value());
              
            canvas.setColor(cellColor);
            canvas.fill(rectangle);
        }
    
    private static void drawPellets(Graphics2D canvas, GridCell<Character> cell, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
            Ellipse2D pellet = cellPosToPixConvert.getBoundsForPellet(cell.pos());
            Color cellColor = colorTheme.getPelletColor();
            
            canvas.setColor(cellColor);
            canvas.fill(pellet);

        }

    private static void drawPacMan(Graphics2D canvas, CellPosition posOfPacMan, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
        // itererer over cellene og tegner dem
        Ellipse2D pacMan = cellPosToPixConvert.getBoundsForPacManOrGhost(posOfPacMan);
        Color cellColor = colorTheme.getPacManColor();
          
        canvas.setColor(cellColor);
        canvas.fill(pacMan);
            }
    
    private static void drawGhost(Graphics2D canvas, CellPosition posOfGhost, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      Ellipse2D ghost = cellPosToPixConvert.getBoundsForPacManOrGhost(posOfGhost);
      Color cellColor = colorTheme.getGhostColor();
      
      canvas.setColor(cellColor);
      canvas.fill(ghost);
      }
    }



    /* public void updateScore() {


      }  */

