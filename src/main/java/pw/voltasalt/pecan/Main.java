package pw.voltasalt.pecan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pw.voltasalt.pecan.editor.Document;
import pw.voltasalt.pecan.editor.PecanEditor;
import pw.voltasalt.pecan.editor.javafx.JavaFXPecanEditor;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Canvas canvas = new Canvas(500, 500);

        GraphicsContext ctx = canvas.getGraphicsContext2D();

        PecanEditor editor = new JavaFXPecanEditor(canvas);
        editor.getOptions().setFont(Font.font("Inconsolata", 14));
        editor.setDocument(new Document("One", "Two", "Three", "Four", "Five!"));

        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
