package pw.voltasalt.pecan.editor;

import java.util.*;

public class Selections {
    private PecanEditor editor;
    private SortedSet<Selection> selections;

    public Selections(PecanEditor editor) {
        this.editor = editor;

        selections = new TreeSet<Selection>(new Comparator<Selection>() {
            @Override
            public int compare(Selection o1, Selection o2) {
                if (o1.getLineFrom() == o2.getLineFrom()) {
                    return Integer.compare(o1.getColumnFrom(), o2.getColumnFrom());
                } else {
                    return Integer.compare(o1.getLineFrom(), o2.getColumnFrom());
                }
            }
        });
        selections.add(new Selection(editor, 1, 0));
    }

    public SortedSet<Selection> getSelections() {
        return selections;
    }

    public void offset(int lines, int cols, boolean end) {
        for (Selection selection : selections) {
            selection.offset(lines, cols, end);
        }
    }

    public void up(boolean end) {
        for (Selection selection : selections) {
            selection.up(end);
        }
    }

    public void up(int amount, boolean end) {
        for (Selection selection : selections) {
            selection.up(amount, end);
        }
    }

    public void down(boolean end) {
        for (Selection selection : selections) {
            selection.down(end);
        }
    }

    public void down(int amount, boolean end) {
        for (Selection selection : selections) {
            selection.down(amount, end);
        }
    }

    public void left(boolean end) {
        for (Selection selection : selections) {
            selection.left(end);
        }
    }

    public void left(int amount, boolean end) {
        for (Selection selection : selections) {
            selection.left(amount, end);
        }
    }

    public void right(boolean end) {
        for (Selection selection : selections) {
            selection.right(end);
        }
    }

    public void right(int amount, boolean end) {
        for (Selection selection : selections) {
            selection.right(amount, end);
        }
    }

    public void insertAt(String text) {
        for (Selection selection : selections) {
            selection.insertAt(text);
        }
    }

    public void backspaceAt(int amount) {
        for (Selection selection : selections) {
            selection.backspaceAt(amount);
        }
    }

    public boolean hasMultipleCursors() {
        return selections.size() > 1;
    }

    public List<Selection> getCursorsOnLine(int lineNo) {
        List<Selection> ret = new ArrayList<Selection>();
        for (Selection selection : selections) {
            if (selection.getLineFrom() >= lineNo && selection.getLineTo() <= lineNo) {
                ret.add(selection);
            }
        }
        return ret;
    }

    public void deleteAt(int amount) {
        for (Selection selection : selections) {
            selection.deleteAt(amount);
        }
    }
}
