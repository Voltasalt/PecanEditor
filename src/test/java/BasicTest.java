import org.junit.Before;
import org.junit.Test;
import pw.voltasalt.pecan.editor.PecanEditor;
import pw.voltasalt.pecan.editor.headless.HeadlessPecanEditor;

public class BasicTest {
    PecanEditor editor;

    @Before
    public void setUp() {
        editor = new HeadlessPecanEditor();
    }

    @Test
    public void renderShouldNotError() {
        editor.render();
    }
}
