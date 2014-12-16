package pw.voltasalt.pecan.editor.headless;

import pw.voltasalt.pecan.editor.PecanRenderer;
import pw.voltasalt.pecan.editor.PixelPosition;

public class HeadlessRenderer implements PecanRenderer {
    @Override
    public void render() {
    }

    @Override
    public PixelPosition toPixelPosition(int line, int column) {
        return new PixelPosition(0, 0);
    }
}
