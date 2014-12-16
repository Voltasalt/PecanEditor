package pw.voltasalt.pecan.editor;

public class Selection {
    private PecanEditor editor;
    private int lineFrom;
    private int columnFrom;
    private int lineTo;
    private int columnTo;

    public Selection(PecanEditor editor, int lineFrom, int columnFrom) {
        this.editor = editor;
        this.lineFrom = lineFrom;
        this.columnFrom = columnFrom;
    }

    public int getLineFrom() {
        return lineFrom;
    }

    public void setLineFrom(int lineFrom) {
        this.lineFrom = lineFrom;
    }

    public int getColumnFrom() {
        return columnFrom;
    }

    public void setColumnFrom(int columnFrom) {
        this.columnFrom = columnFrom;
    }

    public int getLineTo() {
        return lineTo;
    }

    public void setLineTo(int lineTo) {
        this.lineTo = lineTo;
    }

    public int getColumnTo() {
        return columnTo;
    }

    public void setColumnTo(int columnTo) {
        this.columnTo = columnTo;
    }

    public int getNormalizedColumn() {
        return Math.min(columnFrom, editor.getDocument().getLine(lineFrom).length() - 1);
    }

    @Override
    public String toString() {
        return "Cursor{" +
                "editor=" + editor +
                ", lineFrom=" + lineFrom +
                ", columnFrom=" + columnFrom +
                ", lineTo=" + lineTo +
                ", columnTo=" + columnTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Selection selection = (Selection) o;

        if (columnFrom != selection.columnFrom) return false;
        if (columnTo != selection.columnTo) return false;
        if (lineFrom != selection.lineFrom) return false;
        if (lineTo != selection.lineTo) return false;
        if (!editor.equals(selection.editor)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = editor.hashCode();
        result = 31 * result + lineFrom;
        result = 31 * result + columnFrom;
        result = 31 * result + lineTo;
        result = 31 * result + columnTo;
        return result;
    }

    public void offset(int lines, int columns, boolean end) {
        int lineOut = (end ? lineTo : lineFrom);
        int columnOut = (end ? columnTo : columnFrom);
        
        lineOut += lines;
        if (lineOut < 1) lineOut = 1;
        if (lineOut > editor.getDocument().getLines().size()) lineOut = editor.getDocument().getLines().size();

        int leftover = columns == 0 ? Math.max(0, columnOut - editor.getDocument().getLine(lineOut).length()) : 0;
        int chars = editor.getDocument().lineAndColumnToChars(lineOut, columnOut - leftover);

        chars += columns;

        if (chars < 0) chars = 0;
        if (chars > editor.getDocument().getContent().length()) chars = editor.getDocument().getContent().length();

        Position position = editor.getDocument().charsToLineAndColumn(chars);
        lineOut = position.line;
        columnOut = position.column + leftover;

        if (end) {
            lineTo = lineOut;
            columnTo = columnOut;

            if (lineTo <= lineFrom) {
                if (columnTo <= lineFrom) {
                     collapse();
                }
            }
        } else {
            lineFrom = lineOut;
            columnFrom = columnOut;
        }
    }

    public void collapse() {
        lineTo = lineFrom;
        columnTo = columnFrom;
    }

    public void up(boolean end) {
        up(1, end);
    }

    public void up(int amount, boolean end) {
        offset(-amount, 0, end);
        collapse();
    }

    public void down(boolean end) {
        down(1, end);
    }

    public void down(int amount, boolean end) {
        offset(amount, 0, end);
        collapse();
    }

    public void left(boolean end) {
        left(1, end);
    }

    public void left(int amount, boolean end) {
        normalizeLeftover();
        offset(0, -amount, end);
        collapse();
    }

    public void right(boolean end) {
        right(1, end);
    }

    public void right(int amount, boolean end) {
        normalizeLeftover();
        offset(0, amount, end);
        collapse();
    }

    public void insertAt(String text) {
        collapse();
        editor.getDocument().insert(text, lineFrom, columnFrom);
        right(text.length(), false);
    }

    public void deleteAt(int amount) {
        collapse();
        editor.getDocument().remove(amount, lineFrom, columnFrom);
    }

    public void backspaceAt(int amount) {
        collapse();
        left(false);
        editor.getDocument().remove(amount, lineFrom, columnFrom);

        normalizeLeftover();
    }

    public void normalizeLeftover() {
        columnFrom = Math.min(columnFrom, editor.getDocument().getLine(lineFrom).length() - 1);
    }

    public boolean isAfter(int line, int column) {
        return this.lineFrom == line ? this.columnFrom > column : this.lineFrom > line;
    }
}
