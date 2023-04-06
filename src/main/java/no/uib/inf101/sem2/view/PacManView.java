package no.uib.inf101.sem2.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

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
    
    // Tegner cellene
    CellPositionToPixelConverter position = new CellPositionToPixelConverter(rectangle, pacManModel.getDimension(), 2);
    drawCells(canvas, pacManModel.getTilesOnBoard(), position, color);

    // Tegner movingPacMan piece
    drawCells(canvas, pacManModel.getTileOnMovingPacMan(), position, color);

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


    private static void drawCells(Graphics2D canvas, Iterable<GridCell<Character>> gridCellCharacter, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      // itererer over cellene og tegner dem
      for(GridCell<Character> cell : gridCellCharacter){
          Rectangle2D rectangle = cellPosToPixConvert.getBoundsForCell(cell.pos());
          Color cellColor = colorTheme.getCellColor(cell.value());
          
          canvas.setColor(cellColor);
          canvas.fill(rectangle);
          }
      }
  }