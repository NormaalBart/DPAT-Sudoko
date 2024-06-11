package org.avans.sudoko.view.other;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.view.menubar.SudokuFileChooser;

public class NoSudokuPane extends BorderPane {

    private final SudokuController sudokuController;

    public NoSudokuPane(SudokuController controller) {
        this.sudokuController = controller;
        createCenterLabel();
    }

    private void createCenterLabel() {
        Label label = new Label("Laat een sudoko!");
        label.getStyleClass().add("label-style");

        label.setOnMouseClicked(event -> {
            new SudokuFileChooser(this.sudokuController).openSudokuFile((Stage) this.getScene().getWindow());
        });

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(label);
        this.setCenter(centerPane);
    }
}
