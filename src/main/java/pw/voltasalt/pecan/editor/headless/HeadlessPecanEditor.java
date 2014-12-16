package pw.voltasalt.pecan.editor.headless;

import pw.voltasalt.pecan.editor.PecanEditor;

public class HeadlessPecanEditor extends PecanEditor {
    public HeadlessPecanEditor() {
        this.renderer = new HeadlessRenderer();
    }
}
