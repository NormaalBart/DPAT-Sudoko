package org.avans.sudoko.view.sudoko;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokuView;

public class SudokuCellView extends StackPane {

    private final Label label;
    private final GridPane helpGrid;
    private final SudokuView sudokuView;
    private final Rectangle background;

    public SudokuCellView(SudokuView sudokuView, Cell cell, Color backgroundColor) {
        this.sudokuView = sudokuView;
        label = new Label();
        label.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Duidelijkere cijfers
        helpGrid = new GridPane();
        helpGrid.setAlignment(Pos.TOP_LEFT);
        helpGrid.setVgap(1);
        helpGrid.setHgap(1);

        background = new Rectangle(50, 50);
        background.setFill(backgroundColor);

        this.setValue(cell.getValue());
        cell.valueProperty().addListener((obs, oldVal, newVal) -> setValue(newVal));
        cell.hulpValueProperty().addListener((obs, oldVal, newVal) -> updateHelpValues(newVal));

        this.getChildren().addAll(background, helpGrid, label);
        this.setStyle("-fx-border-color: black; -fx-alignment: center;");
        this.setPrefSize(50, 50);

        this.setOnMouseClicked(mouseEvent -> onClick(mouseEvent, cell));
        this.setOnKeyTyped(keyEvent -> handleKeyTyped(keyEvent, cell));

        // Change background color when focused
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
            label.setStyle("-fx-font-weight: bold;");
        } else {
            label.setStyle("-fx-font-weight: normal;");
        }
    }

    private void updateBackgroundColor(Color backgroundColor) {
        background.setFill(backgroundColor);

    }
}
