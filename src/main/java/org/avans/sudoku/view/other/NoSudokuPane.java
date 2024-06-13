package org.avans.sudoku.view.other;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.avans.sudoku.view.menubar.SudokuFileChooser;

public class NoSudokuPane extends BorderPane {


    public NoSudokuPane() {
        createCenterLabel();
    }

    private void createCenterLabel() {
        Label label = new Label("Laat een sudoko!");
        label.getStyleClass().add("label-style");

        label.setOnMouseClicked(event -> {
            new SudokuFileChooser().openSudokuFile((Stage) this.getScene().getWindow());
        });

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(label);
        this.setCenter(centerPane);
    }
}
