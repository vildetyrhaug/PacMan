package no.uib.inf101.sem2.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;



public class PacManView extends JPanel {
    
    private ViewablePacManModel pacManModel;
    private ColorTheme color;
    
    private static final double OUTERMARGIN = 10;


    // KONSTRUKTØR
    public PacManView(ViewablePacManModel pacManModel) {
      this.setFocusable(true);
      this.setPreferredSize(new Dimension(700, 300));

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

    /* if (model.getGameState()== GameState.GAME_OVER) {
      // Lager en grå firkant over hele brettet
      canvas.setColor(Color.LIGHT_GRAY.darker().darker());
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      // Tegner en rute med gjennomsiktig farge som dekker hele skjermen,
      // og tegner teksten "GAME OVER" oppå
      canvas.setColor(colorTheme.getSeethroughColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight));
      canvas.setColor(colorTheme.getGameOverColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "GAME OVER", OUTERMARGIN, OUTERMARGIN, cellWidth, cellHeight);
    }  */

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

