package org.avans.sudoko.view.sudoko.cell;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokuView;

public class SudokuCellView extends BaseCellView {

    private final Label label;
    private final GridPane helpGrid;
    private final SudokuView sudokuView;

    public SudokuCellView(SudokuView sudokuView, Cell cell, Color backgroundColor) {
        super(backgroundColor);
        this.sudokuView = sudokuView;
        label = new Label();
        helpGrid = new GridPane();
        helpGrid.setAlignment(Pos.TOP_LEFT);
        helpGrid.setVgap(1);
        helpGrid.setHgap(1);

        this.setValue(cell.getValue());
        cell.valueProperty().addListener((obs, oldVal, newVal) -> setValue(newVal));
        cell.hulpValueProperty().addListener((obs, oldVal, newVal) -> updateHelpValues(newVal));

        this.getChildren().addAll(helpGrid, label);

        this.setOnMouseClicked(mouseEvent -> onClick(mouseEvent, cell));
        this.setOnKeyTyped(keyEvent -> handleKeyTyped(keyEvent, cell));

        this.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                background.setFill(Color.GOLD);
            } else {
                background.setFill(backgroundColor);
            }
        });

        updateHelpValues(cell.getHulpValue());
        updateBoldStyle(cell.isSet());
        updateBackgroundColor(backgroundColor);
    }

    private void handleKeyTyped(KeyEvent keyEvent, Cell cell) {
        String character = keyEvent.getCharacter();
        if (character.equals("\b")) { // Check for backspace
            cell.setValue(0);
            updateBoldStyle(cell.isSet());
        } else if (!cell.isSet() && character.matches("[0-9]")) {
            int value = Integer.parseInt(character);
            cell.setValue(value);
            updateBoldStyle(cell.isSet());
        }
    }

    private void onClick(MouseEvent mouseEvent, Cell cell) {
        this.sudokuView.setInterestedCell(cell);
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
        int size = (int) Math.sqrt(helpValues.size() + 1);

        for (Integer helpValue : helpValues) {
            Label helpLabel = new Label(String.valueOf(helpValue));
            helpLabel.setStyle("-fx-font-size: 10;");
            int index = helpValue - 1;
            helpGrid.add(helpLabel, index % size, index / size);
        }
    }

    private void updateBoldStyle(boolean isSet) {
        if (isSet) {
            label.getStyleClass().add("extrabold");
        } else {
            label.getStyleClass().remove("extrabold");
        }
    }
}
