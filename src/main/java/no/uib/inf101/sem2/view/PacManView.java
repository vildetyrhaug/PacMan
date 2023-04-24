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
import no.uib.inf101.sem2.model.PacManModel;

public class PacManView extends JPanel {
    
    private static ViewablePacManModel pacManModel;
    PacManModel model;
    private ColorTheme color;
    
    private static final double TOPMARGIN = 40;
    private static final double OUTERMARGIN = 5;

    // KONSTRUKTØR
    public PacManView(ViewablePacManModel pacManModel) {
      this.setFocusable(true);
      this.setPreferredSize(new Dimension(800, 800));

      this.pacManModel = pacManModel;
      this.color = new DefaultColorTheme();

      this.setBackground(getBackground());
    }

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
      double cellHeight = this.getHeight() - OUTERMARGIN - TOPMARGIN;
      
      // Tegner bakgrunnen
      Rectangle2D rectangle = new Rectangle2D.Double(OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight);
      canvas.setColor(color.getFrameColor());
      canvas.fill(rectangle);
      
      // Tegner cellene og pellets
      CellPositionToPixelConverter position = new CellPositionToPixelConverter(rectangle, pacManModel.getDimension(), 1);
      drawBoard(canvas, pacManModel.getTilesOnBoard(), position, color);

      // Tegner movingPacMan piece 
      drawPacMan(canvas, pacManModel.getTileOnMovingPacMan(), position, color);

      // Tegner movingGhost piece
      drawGhost(canvas, pacManModel.getTilesOnMovingGhosts(), position, color);

      // Tegner score
      drawScore(canvas, pacManModel.getScore(), color);

      // Tegner skjermene basert på GameState-tilstand
      switch(pacManModel.getGameState()){
        case START_GAME:
          drawStartScreen(canvas, cellWidth, cellHeight);
          break;
        case PAUSE_GAME:
          drawPauseScreen(canvas, cellWidth, cellHeight);
          break;
        case GAME_OVER:
          drawGameOverScreen(canvas, cellWidth, cellHeight);
          break;
        case GAME_WON:
          drawWonGameScreen(canvas, cellWidth, cellHeight);
          break;
      }
    }

    private void drawWonGameScreen(Graphics2D canvas, double cellWidth, double cellHeight) {
      canvas.setColor(color.getWonBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getWonColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 70));
      Inf101Graphics.drawCenteredString(canvas, "GRATULERER!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-300);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 30));
      Inf101Graphics.drawCenteredString(canvas, "Du har vunnet spillet!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-100);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'mellomrom' for å starte på nytt", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+100);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å lukke spillet", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+200);
    }
  

    private void drawStartScreen(Graphics2D canvas, double cellWidth, double cellHeight) {
      canvas.setColor(color.getStartBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getStartColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 100));
      Inf101Graphics.drawCenteredString(canvas, "PAC-MAN", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-500);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 25));
      Inf101Graphics.drawCenteredString(canvas, "Du vinner når alle rosa pellets er samlet opp.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-300);
      Inf101Graphics.drawCenteredString(canvas, "Pass deg for spøkelsene.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-200);
      Inf101Graphics.drawCenteredString(canvas, "Kommer du borti et spøkelse, er det GAME OVER.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-100);
      Inf101Graphics.drawCenteredString(canvas, "Spis den røde frukten og få ekstra poeng,", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight);
      Inf101Graphics.drawCenteredString(canvas, "og ti sekunder til å spise et spøkelse!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+100);
      Inf101Graphics.drawCenteredString(canvas, "Benytt piltastene for å bevege pac-man rundt.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+300);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'mellomrom' for å starte spillet.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+400);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'p' for å pause spillet.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+500);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å lukke spillet.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+600);
    }

    private void drawPauseScreen(Graphics2D canvas, double cellWidth, double cellHeight) {
      canvas.setColor(color.getPauseBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getPauseColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "Spillet er satt på pause!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-200);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 30));
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'p' eller 'mellomrom'", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight);
      Inf101Graphics.drawCenteredString(canvas, "for å fortsette spillet.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+100);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å lukke spillet.", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+200);
    }

    private void drawGameOverScreen(Graphics2D canvas, double cellWidth, double cellHeight){
      canvas.setColor(color.getGameOverBackgroundColor());  
      canvas.fill(new Rectangle2D.Double(OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight));
      canvas.setColor(color.getGameOverColor()); 
      canvas.setFont(new Font("Monospaced", Font.BOLD, 110));
      Inf101Graphics.drawCenteredString(canvas, "GAME OVER!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight-300);
      canvas.setFont(new Font("Monospaced", Font.BOLD, 50));
      Inf101Graphics.drawCenteredString(canvas, "Du fikk " + pacManModel.getScore() + " poeng!", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight);
      
      canvas.setFont(new Font("Monospaced", Font.BOLD, 30));
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'mellomrom' for å starte på nytt", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight+200);
      Inf101Graphics.drawCenteredString(canvas, "Trykk på 'esc' for å lukke spillet", OUTERMARGIN, TOPMARGIN, cellWidth, cellHeight + 300);
    }

    private void drawScore(Graphics2D canvas, Object score, ColorTheme color) {
      canvas.setColor(color.getScoreColor());
      canvas.setFont(new Font("Monospaced", Font.BOLD, 20));
      canvas.drawString("Score: " + score, 15, 25);
    }

    private static void drawBoard(Graphics2D canvas, Iterable<GridCell<Character>> gridCellCharacter, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      for(GridCell<Character> cell : gridCellCharacter){
          if (cell.value() == ' ' || cell.value() == '#' || cell.value() == 'P'){
              drawCells(canvas, cell, cellPosToPixConvert, colorTheme);
            }
          if (cell.value() == 'o'){
              drawPellets(canvas, cell, cellPosToPixConvert, colorTheme);
            }   
          if (cell.value() == 'f'){
              drawFruit(canvas, cell, cellPosToPixConvert, colorTheme);
          }
      }
    }

    private static void drawCells(Graphics2D canvas, GridCell<Character> cell, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      Rectangle2D rectangle = cellPosToPixConvert.getBoundsForCell(cell.pos());
      Color cellColor = colorTheme.getCellColor(cell.value());
        
      canvas.setColor(cellColor);
      canvas.draw(rectangle);
    }
    
    private static void drawPellets(Graphics2D canvas, GridCell<Character> cell, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      Ellipse2D pellet = cellPosToPixConvert.getBoundsForPellet(cell.pos());
      Color cellColor = colorTheme.getPelletColor();
      
      canvas.setColor(cellColor);
      canvas.fill(pellet);
    }
    
    private static void drawFruit(Graphics2D canvas, GridCell<Character> cell, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      Ellipse2D fruit = cellPosToPixConvert.getBoundsForFruit(cell.pos());
      Color cellColor = colorTheme.getFruitColor();
      
      canvas.setColor(cellColor);
      canvas.fill(fruit);
    }
    

    private static void drawPacMan(Graphics2D canvas, CellPosition posOfPacMan, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      Ellipse2D pacMan = cellPosToPixConvert.getBoundsForPacMan(posOfPacMan);
      Color pacManColor = pacManModel.isInvincible() ? Color.BLUE : colorTheme.getPacManColor();
      
      canvas.setColor(pacManColor);
      canvas.fill(pacMan);
    }
    
    private static void drawGhost(Graphics2D canvas, Iterable<CellPosition> posOfGhost, CellPositionToPixelConverter cellPosToPixConvert, ColorTheme colorTheme){
      for(CellPosition cell : posOfGhost){
        Ellipse2D ghost = cellPosToPixConvert.getBoundsForGhosts(cell);
        Color cellColor = colorTheme.getGhostColor();
        
        canvas.setColor(cellColor);
        canvas.fill(ghost);
      }
    }
}

