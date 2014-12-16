package pw.voltasalt.pecan.editor;

public interface PecanRenderer {
    void render();

    PixelPosition toPixelPosition(int line, int column);
}
