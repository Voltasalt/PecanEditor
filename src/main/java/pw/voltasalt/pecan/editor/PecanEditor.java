package pw.voltasalt.pecan.editor;

import javafx.scene.canvas.Canvas;

public abstract class PecanEditor {
    protected PecanRenderer renderer;
    protected PecanOptions options;
    protected Canvas canvas;

    protected Document document;

    protected Selections selections;

    public PecanEditor() {
        renderer = null;
        options = new PecanOptions();
        document = new Document();
        selections = new Selections(this);
    }

    public void render() {
        renderer.render();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public PecanRenderer getRenderer() {
        return renderer;
    }

    public PecanOptions getOptions() {
        return options;
    }

    public Document getDocument() {
        return document;
    }

    public void setOptions(PecanOptions options) {
        this.options = options;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Selections getSelections() {
        return selections;
    }
}
