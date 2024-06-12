package org.avans.sudoko;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.avans.sudoko.view.SudokuView;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SudokuView sudokuView = new SudokuView();
        Scene scene = new Scene(sudokuView, 1200, 800);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Sudoku App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
