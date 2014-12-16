package pw.voltasalt.pecan.editor;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Document {
    private String content;

    public Document() {
        content = "";
    }

    public Document(List<String> lines) {
        content = StringUtils.join(lines, "\n");
    }

    public Document(String... lines) {
        content = StringUtils.join(lines, "\n");
    }

    public Document(String content) {
        this.content = content;
    }

    public List<String> getLines() {
        String[] lines = StringUtils.splitPreserveAllTokens(content, "\n");
        return lines.length > 0 ? Arrays.asList(lines) : Arrays.asList("");
    }

    public String getLine(int line) {
        return getLines().get(line - 1);
    }

    public void setLine(int line, String lineStr) {
        List<String> tmp = getLines();
        tmp.set(line - 1, lineStr);
        setLines(tmp);
    }

    public void insert(String text, int line, int column) {
        int chars = lineAndColumnToChars(line, column);

        String before = content.substring(0, chars);
        String after = content.substring(chars, content.length());

        content = before + text + after;
    }

    public void setLines(List<String> lines) {
        content = StringUtils.join(lines, "\n");
    }

    public void setLines(String... lines) {
        content = StringUtils.join(lines, "\n");
    }

    public int lineAndColumnToChars(int line, int column) {
        int cols = 0;
        if (line <= getLines().size()) {
            for (String lineStr : getLines().subList(0, line - 1)) {
                cols += lineStr.length() + 1;
            }
        } else {
            return content.length();
        }
        cols += Math.min(column, getLine(line).length());
        return cols;
    }

    public Position charsToLineAndColumn(int chars) {
        int line = 1;
        while (chars >= getLine(line).length() + 1) {
            if (line < getLines().size()) {
                chars -= getLine(line).length() + 1;
                line++;
            } else {
                break;
            }
        }
        return new Position(line, chars);
    }

    public void remove(int count, int line, int column) {
        int chars = lineAndColumnToChars(line, column);

        int removeFrom;
        int removeTo;

        removeFrom = chars;
        removeTo = chars + count;

        String before = content.substring(0, removeFrom);
        String after = content.length() == 0 ? "" : content.substring(removeTo, content.length());
        content = before + after;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Document{" +
                "content='" + content + '\'' +
                '}';
    }
}
