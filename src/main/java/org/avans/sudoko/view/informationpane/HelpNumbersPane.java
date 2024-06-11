package org.avans.sudoko.view.informationpane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.avans.sudoko.command.ToggleHulpValueCommand;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokuView;

public class HelpNumbersPane extends GridPane {

    public HelpNumbersPane(SudokuView sudokuView) {
        super();
        this.setAlignment(Pos.CENTER);

        sudokuView.interestedCellProperty().addListener((obs, oldCell, newCell) -> {
            this.getChildren().clear();
            if (newCell != null) {
                int size = SudokuController.getInstance().getSudokuProperty().get().getSize();
                createHelpGrid(newCell, size);
            }
        });
    }

    private void createHelpGrid(Cell cell, int size) {
        int gridSize = (int) Math.sqrt(size);

        for (int i = 1; i <= size; i++) {
            Label numberLabel = createNumberLabel(cell, i);
            this.add(numberLabel, (i - 1) % gridSize, (i - 1) / gridSize);
        }
    }

    private Label createNumberLabel(Cell cell, int number) {
        Label numberLabel = new Label(String.valueOf(number));
        numberLabel.getStyleClass().add("number-label");
        updateLabelStyle(numberLabel, cell, number);

        cell.hulpValueProperty().subscribe((list) -> {
            if (list.contains(number)) {
                numberLabel.getStyleClass().add("selected-label");
            } else {
                numberLabel.getStyleClass().remove("selected-label");

            }
        });

        numberLabel.setOnMouseClicked(event -> {
            SudokuController.getInstance().executeCommand(new ToggleHulpValueCommand(cell, number));
        });

        return numberLabel;
    }

    private void updateLabelStyle(Label label, Cell cell, int number) {
        if (cell.getHulpValue().contains(number)) {
            label.getStyleClass().add("selected-label");
        } else {
            label.getStyleClass().remove("selected-label");
        }
    }
}
