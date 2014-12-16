package pw.voltasalt.pecan.editor;

import javafx.scene.paint.Color;

public class ColorTheme {
    private Color background;
    private Color backgroundHighlight;
    private Color gutterBackground;
    private Color textColor;
    private Color gutterTextColor;
    private Color cursorColor;

    public ColorTheme() {
        background = Color.BLACK;
        backgroundHighlight = Color.GRAY;
        gutterBackground = Color.GRAY;
        textColor = Color.WHITE;
        gutterTextColor = Color.WHITE;
        cursorColor = Color.BLACK;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getBackgroundHighlight() {
        return backgroundHighlight;
    }

    public void setBackgroundHighlight(Color backgroundHighlight) {
        this.backgroundHighlight = backgroundHighlight;
    }

    public Color getGutterBackground() {
        return gutterBackground;
    }

    public void setGutterBackground(Color gutterBackground) {
        this.gutterBackground = gutterBackground;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getGutterTextColor() {
        return gutterTextColor;
    }

    public void setGutterTextColor(Color gutterTextColor) {
        this.gutterTextColor = gutterTextColor;
    }

    public Color getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(Color cursorColor) {
        this.cursorColor = cursorColor;
    }

    public static final ColorTheme solarized_dark;

    static {
        solarized_dark = new ColorTheme();
        solarized_dark.background = Color.web("#002b36");
        solarized_dark.backgroundHighlight = solarized_dark.gutterBackground = Color.web("#073642");
        solarized_dark.textColor = solarized_dark.gutterTextColor = solarized_dark.cursorColor = Color.web("#839496");
    }
}
