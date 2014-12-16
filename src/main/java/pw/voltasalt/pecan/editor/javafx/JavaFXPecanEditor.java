package pw.voltasalt.pecan.editor.javafx;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import pw.voltasalt.pecan.editor.PecanEditor;

public class JavaFXPecanEditor extends PecanEditor {
    public JavaFXPecanEditor(Canvas canvas) {
        this.canvas = canvas;
        this.renderer = new JavaFXCanvasRenderer(this);

        EventHandler<KeyEvent> evtHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handleKeyEvent(event);
            }
        };

        canvas.setFocusTraversable(true);
        canvas.setOnKeyTyped(evtHandler);
        canvas.setOnKeyPressed(evtHandler);
        canvas.setOnKeyReleased(evtHandler);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
            }
        };
        timer.start();
        render();
    }

    private void handleKeyEvent(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (event.getCode()) {
                case UP:
                    selections.up(false);
                    break;
                case DOWN:
                    selections.down(false);
                    break;
                case LEFT:
                    selections.left(false);
                    break;
                case RIGHT:
                    selections.right(false);
                    break;
                case BACK_SPACE:
                    selections.backspaceAt(1);
                    break;
                case DELETE:
                    selections.deleteAt(1);
                    break;
            }
        } else if (event.getEventType() == KeyEvent.KEY_TYPED) {
            String character = event.getCharacter();

            if (StringUtils.isAsciiPrintable(character)) {
                selections.insertAt(character);
            } else if (character.equals("\r") || character.equals("\n")) {
                selections.insertAt("\n");
            }
        }

        ((JavaFXCanvasRenderer) renderer).resetCursorTime();
    }
}