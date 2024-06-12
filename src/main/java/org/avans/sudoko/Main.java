package org.avans.sudoko;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.avans.sudoko.view.SudokuView;

public class Main extends Application {
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SudokuView sudokuView = new SudokuView();
        Scene scene = new Scene(sudokuView, this.WIDTH, this.HEIGHT);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Sudoku App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
