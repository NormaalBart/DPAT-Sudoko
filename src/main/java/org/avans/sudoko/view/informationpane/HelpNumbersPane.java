package org.avans.sudoko.view.informationpane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokoView;

public class HelpNumbersPane extends GridPane {

    public HelpNumbersPane(SudokoController controller, SudokoView sudokoView) {
        super();
        this.setAlignment(Pos.CENTER);

        sudokoView.interestedCellProperty().addListener((obs, oldCell, newCell) -> {
            this.getChildren().clear();
            if (newCell != null) {
                int size = controller.getSudokoProperty().get().getSize();
                createHelpGrid(newCell, size);
            }
        });
    }

    private void createHelpGrid(Cell cell, int size) {
        int gridSize = (int) Math.sqrt(size); // Determine the grid size based on the Sudoku size

        for (int i = 1; i <= size; i++) {
            Label numberLabel = createNumberLabel(cell, i);
            this.add(numberLabel, (i - 1) % gridSize, (i - 1) / gridSize);
        }
    }

    private Label createNumberLabel(Cell cell, int number) {
        Label numberLabel = new Label(String.valueOf(number));
        numberLabel.setPrefSize(30, 30);
        numberLabel.setAlignment(Pos.CENTER);
        numberLabel.setStyle(cell.getHulpValue().contains(number) ? "-fx-background-color: green;" : "-fx-background-color: red;");

        numberLabel.setOnMouseClicked(event -> {
            cell.toggleHulpValue(number);
            numberLabel.setStyle(cell.getHulpValue().contains(number) ? "-fx-background-color: green;" : "-fx-background-color: red;");
        });

        return numberLabel;
    }
}
