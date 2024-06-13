package org.avans.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.avans.sudoku.view.SudokuView;

public class Main extends Application {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SudokuView sudokuView = new SudokuView();
        Scene scene = new Scene(sudokuView, WIDTH, HEIGHT);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Sudoku App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
