package no.uib.inf101.sem2.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {
    
    private Color frameColor = Color.BLACK;
    private Color wallColor = Color.BLUE;
    private Color backgroundColor = Color.BLUE;
    
    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case '#' -> Color.BLUE;
            case 'o' -> Color.YELLOW;
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
    
}
