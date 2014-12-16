package pw.voltasalt.pecan.editor.javafx;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import pw.voltasalt.pecan.editor.*;

import java.util.ArrayList;
import java.util.List;

public class JavaFXCanvasRenderer implements PecanRenderer {
    private PecanEditor editor;

    private SnapshotParameters snapshotParameters;

    int gutterWidthPixels;
    int lineX;

    int charWidth;
    int charHeight;

    int cursorTime;
    long lastRenderTime;

    public JavaFXCanvasRenderer(PecanEditor editor) {
        this.editor = editor;
    }

    public void render() {
        cursorTime += System.currentTimeMillis() - lastRenderTime;

        GraphicsContext ctx = editor.getCanvas().getGraphicsContext2D();
        ColorTheme theme = editor.getOptions().getColorTheme();

        // Clear screen
        ctx.setFill(theme.getBackground());
        ctx.fillRect(0, 0, editor.getCanvas().getWidth(), editor.getCanvas().getHeight());

        Image hash = rasterizeText("#", Color.WHITE, FontWeight.NORMAL);
        charWidth = (int) hash.getWidth();
        charHeight = (int) hash.getHeight();
        gutterWidthPixels = charWidth * editor.getOptions().getMinimimGutterWidth();

        lineX = gutterWidthPixels + editor.getOptions().getGutterMargin() * 2;

        // Draw gutter
        ctx.setFill(theme.getGutterBackground());
        ctx.fillRect(0, 0, gutterWidthPixels + editor.getOptions().getGutterMargin(), editor.getCanvas().getHeight());

        int currentY = 0;
        int lineNo = 1;

        // Make sure we get our text on a transparent background
        snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);

        for (String line : editor.getDocument().getLines()) {
            // Rasterize text to images
            Image lineNoImage = rasterizeText(lineNo+"", theme.getGutterTextColor(), editor.getOptions().isBoldLineNumbers() ? FontWeight.BOLD : FontWeight.NORMAL);
            Image lineImage = rasterizeText(line, theme.getTextColor(), FontWeight.NORMAL);

            // Check if background needs to be highlighted
            List<Selection> selections = new ArrayList<Selection>();
            for (Selection selection : editor.getSelections().getCursorsOnLine(lineNo)) {
                selections.add(selection);
            }

            // Highlight background and draw cursor(s)
            if (!selections.isEmpty()) {
                ctx.setFill(theme.getBackgroundHighlight());
                ctx.fillRect(0, currentY, editor.getCanvas().getWidth(), lineImage.getHeight());

                for (Selection selection : selections) {
                    int cursorCol = Math.min(selection.getColumnFrom(), line.length());
                    PixelPosition pos = toPixelPosition(selection.getLineFrom(), cursorCol);

                    ctx.setFill(new Color(
                            theme.getCursorColor().getRed(),
                            theme.getCursorColor().getGreen(),
                            theme.getCursorColor().getBlue(),
                            Math.sin(cursorTime / (editor.getOptions().getCursorFlashSpeed() / 2) * Math.PI) / 2 + 0.5));
                    ctx.fillRect(pos.getX(), currentY, 1, lineNoImage.getHeight());
                }
            }

            int lineNoX = (int) (gutterWidthPixels - lineNoImage.getWidth());

            // Draw our text
            ctx.drawImage(lineNoImage, lineNoX, currentY);
            ctx.drawImage(lineImage, lineX, currentY);

            // Increment the Y
            currentY += lineImage.getHeight();

            // Increment line number
            lineNo++;
        }

        lastRenderTime = System.currentTimeMillis();
    }

    public static int round(double number) {
        return (int) number;
    }

    public Image rasterizeText(String str, Paint color, FontWeight weight) {
        Text text = new Text(str);
        text.setFill(color);
        text.setFont(Font.font(editor.getOptions().getFont().getFamily(), weight, editor.getOptions().getFont().getSize()));
        text.setFontSmoothingType(FontSmoothingType.LCD);
        return rasterizeText(text);
    }

    public Image rasterizeText(Text text) {
        return text.snapshot(snapshotParameters, null);
    }

    public PixelPosition toPixelPosition(int line, int column) {
        int pixelX = lineX + (charWidth * column);
        int pixelY = charHeight * line;

        return new PixelPosition(pixelX, pixelY);
    }

    public void resetCursorTime() {
        cursorTime = (int) ((Math.PI/4 * 3) * 1000);
    }
}
