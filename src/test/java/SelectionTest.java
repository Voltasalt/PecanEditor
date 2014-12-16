import org.junit.Before;
import org.junit.Test;
import pw.voltasalt.pecan.editor.Selection;
import pw.voltasalt.pecan.editor.Selections;
import pw.voltasalt.pecan.editor.PecanEditor;
import pw.voltasalt.pecan.editor.headless.HeadlessPecanEditor;

import static org.junit.Assert.*;

public class SelectionTest {
    PecanEditor editor;

    @Before
    public void setUp() {
        editor = new HeadlessPecanEditor();
    }

    @Test
    public void movementTest() {
        assertEquals(1, editor.getSelections().getSelections().size());
        assertFalse(editor.getSelections().hasMultipleCursors());

        Selections selections = editor.getSelections();

        assertEquals(new Selection(editor, 1, 0), selections.getSelections().first());

        selections.offset(2, 3, false);

        assertEquals(new Selection(editor, 1, 0), selections.getSelections().first());

        editor.getDocument().setLines("One", "Two", "Three", "Four", "Five!");

        assertEquals(new Selection(editor, 1, 0), selections.getSelections().first());

        selections.offset(2, 3, false);

        assertEquals(new Selection(editor, 3, 3), selections.getSelections().first());

        selections.right(3, false);

        assertEquals(new Selection(editor, 4, 0), selections.getSelections().first());

        selections.left(2, false);

        assertEquals(new Selection(editor, 3, 4), selections.getSelections().first());

        selections.right(1, false);
        selections.down(1, false);

        assertEquals(new Selection(editor, 4, 5), selections.getSelections().first());
        assertEquals(3, selections.getSelections().first().getNormalizedColumn());

        selections.getSelections().first().normalizeLeftover();

        assertEquals(new Selection(editor, 4, 3), selections.getSelections().first());

        selections.up(1, false);
        selections.right(2, false);
        selections.down(1, false);

        assertEquals(new Selection(editor, 4, 5), selections.getSelections().first());

        selections.down(1, false);

        assertEquals(new Selection(editor, 5, 5), selections.getSelections().first());
    }
}
