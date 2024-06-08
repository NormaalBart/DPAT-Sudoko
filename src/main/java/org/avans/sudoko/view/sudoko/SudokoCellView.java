package org.avans.sudoko.view.sudoko;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokoView;

public class SudokoCellView extends StackPane {

    private final Label label;
    private final GridPane helpGrid;
    private final SudokoView sudokoView;

    public SudokoCellView(SudokoView sudokoView, Cell cell) {
        this.sudokoView = sudokoView;
        label = new Label();
        helpGrid = new GridPane();
        helpGrid.setAlignment(Pos.TOP_LEFT);
        helpGrid.setVgap(1);
        helpGrid.setHgap(1);

        this.setValue(cell.getValue());
        cell.valueProperty().addListener((obs, oldVal, newVal) -> setValue(newVal));
        cell.hulpValueProperty().addListener((obs, oldVal, newVal) -> updateHelpValues(newVal));

        this.getChildren().addAll(helpGrid, label);
        this.setStyle("-fx-border-color: black; -fx-alignment: center;");
        this.setPrefSize(50, 50);

        this.setOnMouseClicked(mouseEvent -> onClick(mouseEvent, cell));
        this.setOnKeyTyped(keyEvent -> handleKeyTyped(keyEvent, cell));

        // Change background color when focused
        this.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                this.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-background-color: gold;");
            } else {
                this.setStyle("-fx-border-color: black; -fx-alignment: center;");
            }
        });

        updateHelpValues(cell.getHulpValue());
        updateBoldStyle(cell.isSet());
    }

    private void handleKeyTyped(KeyEvent keyEvent, Cell cell) {
        if (cell.isSet()) {
            return; // Do nothing if the cell is set
        }
        String character = keyEvent.getCharacter();
        System.out.println(character);
        if (character.equals("\b")) {
            cell.setValue(0);
        }
        else if (character.matches("[0-9]")) {
            int value = Integer.parseInt(character);
            cell.setValue(value);
        }
    }

    private void onClick(MouseEvent mouseEvent, Cell cell) {
        this.sudokoView.setInterestedCell(cell);
        this.requestFocus();
    }

    private void setValue(Number value) {
        this.setValue(value.intValue());
    }

    public void setValue(int value) {
        this.label.setText(value == 0 ? "" : String.valueOf(value));
    }

    private void updateHelpValues(javafx.collections.ObservableList<Integer> helpValues) {
        helpGrid.getChildren().clear();
        int size = (int) Math.sqrt(helpValues.size() + 1); // +1 to ensure proper grid size

        for (Integer helpValue : helpValues) {
            Label helpLabel = new Label(String.valueOf(helpValue));
            helpLabel.setStyle("-fx-font-size: 10;");
            int index = helpValue - 1;
            helpGrid.add(helpLabel, index % size, index / size);
        }
    }

    private void updateBoldStyle(boolean isSet) {
        if (isSet) {
            label.setStyle("-fx-font-weight: bold;");
        } else {
            label.setStyle("-fx-font-weight: normal;");
        }
    }
}
