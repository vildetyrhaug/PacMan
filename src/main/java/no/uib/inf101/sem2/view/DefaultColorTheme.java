package no.uib.inf101.sem2.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {
    
    private Color frameColor = Color.BLACK;
    private Color wallColor = Color.BLUE;
    private Color backgroundColor = Color.BLUE;
    private Color pacManColor = Color.YELLOW;
    private Color pelletColor = Color.ORANGE;
    private Color ghostColor = Color.RED, ghostColor2 = Color.CYAN, ghostColor3 = Color.GREEN;

    private Color gameOverBackgroundColor = Color.LIGHT_GRAY;
    private Color gameOverColor = Color.RED;

    private Color pauseColor = Color.RED;
    private Color pauseBackgroundColor = Color.LIGHT_GRAY;

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
        int random = (int) (Math.random() * 3);
        Color color = switch(random) {
            case 0 -> ghostColor;
            case 1 -> ghostColor2;
            case 2 -> ghostColor3;
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
}
