package no.uib.inf101.sem2.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {
    
    private Color frameColor = Color.BLACK;
    private Color wallColor = Color.BLUE;
    private Color backgroundColor = Color.BLUE;
    private Color pacManColor = Color.YELLOW;
    private Color pelletColor = new Color(255, 0, 175);
    private Color ghostColor = Color.CYAN, ghostColor2 = Color.GREEN;
    private Color fruitColor = Color.RED;

    private Color gameOverColor = Color.WHITE;
    private Color gameOverBackgroundColor = new Color(0, 100, 255, 175);

    private Color pauseColor = Color.WHITE;
    private Color pauseBackgroundColor = new Color(0, 100, 255, 200);

    private Color startColor = Color.WHITE;
    private Color startBackgroundColor = new Color(0, 50, 255, 200);

    private Color wonColor = Color.WHITE;
    private Color wonBackgroundColor = new Color(0, 100, 255, 175);

    private Color scoreColor = Color.BLUE.darker();

    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case '#' -> Color.BLUE;
            case ' ' -> Color.BLACK;

            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");
          };
        return color;}

    @Override
    public Color getFrameColor() {
        return frameColor;
        }

    @Override
    public Color getWallColor() {
        return wallColor;
        }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public Color getPacManColor() {
        return pacManColor;
    }

    @Override
    public Color getPelletColor() {
        return pelletColor;}
    
    @Override
    public Color getGhostColor() {
        // randomize ghost color
        int random = (int) (Math.random() * 2);
        Color color = switch(random) {
            case 0 -> ghostColor;
            case 1 -> ghostColor2;
            default -> throw new IllegalArgumentException(
                "No available color for '" + random + "'");
          };
          return color;
    }

    @Override
    public Color getGameOverBackgroundColor() {
        return gameOverBackgroundColor;    
    }

    @Override
    public Color getGameOverColor() {
        return gameOverColor;    
    }

    @Override
    public Color getPauseColor() {
        return pauseColor;
    }

    @Override
    public Color getPauseBackgroundColor() {
        return pauseBackgroundColor;
    }

    @Override
    public Color getStartBackgroundColor() {
        return startBackgroundColor;
    }

    @Override
    public Color getStartColor() {
        return startColor;
    }

    @Override
    public Color getFruitColor() {
        return fruitColor;
    }

    @Override
    public Color getScoreColor() {
        return scoreColor;
    }

    @Override
    public Color getWonBackgroundColor() {
        return wonBackgroundColor;   
    }

    @Override
    public Color getWonColor() {
        return wonColor;
    }
}
