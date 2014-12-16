package pw.voltasalt.pecan.editor;

import javafx.scene.text.Font;

public class PecanOptions {
    private Font font;
    private int minimimGutterWidth;
    private int gutterMargin;
    private boolean boldLineNumbers;
    private boolean resetCursorFlashOnInput;

    // Milliseconds between full opacity and next full opacity
    private double cursorFlashSpeed;

    private ColorTheme colorTheme;

    public PecanOptions() {
        font = Font.font("Monospace", 12);
        minimimGutterWidth = 3;
        gutterMargin = 4;
        colorTheme = ColorTheme.solarized_dark;
        boldLineNumbers = false;
        cursorFlashSpeed = 1000;
        resetCursorFlashOnInput = true;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getMinimimGutterWidth() {
        return minimimGutterWidth;
    }

    public void setMinimimGutterWidth(int minimimGutterWidth) {
        this.minimimGutterWidth = minimimGutterWidth;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public int getGutterMargin() {
        return gutterMargin;
    }

    public void setGutterMargin(int gutterMargin) {
        this.gutterMargin = gutterMargin;
    }

    public boolean isBoldLineNumbers() {
        return boldLineNumbers;
    }

    public void setBoldLineNumbers(boolean boldLineNumbers) {
        this.boldLineNumbers = boldLineNumbers;
    }

    public double getCursorFlashSpeed() {
        return cursorFlashSpeed;
    }

    public void setCursorFlashSpeed(double cursorFlashSpeed) {
        this.cursorFlashSpeed = cursorFlashSpeed;
    }

    public boolean isResetCursorFlashOnInput() {
        return resetCursorFlashOnInput;
    }

    public void setResetCursorFlashOnInput(boolean resetCursorFlashOnInput) {
        this.resetCursorFlashOnInput = resetCursorFlashOnInput;
    }
}
