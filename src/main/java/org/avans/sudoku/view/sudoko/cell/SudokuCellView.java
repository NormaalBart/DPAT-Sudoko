package org.avans.sudoku.view.sudoko.cell;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.avans.sudoku.command.ICommand;
import org.avans.sudoku.command.RemoveValueCommand;
import org.avans.sudoku.command.SetValueCommand;
import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.Cell;
import org.avans.sudoku.view.SudokuView;

public class SudokuCellView extends BaseCellView {

    private final Cell cell;
    private final Label label;
    private final SudokuView sudokuView;

    public SudokuCellView(SudokuView sudokuView, Cell cell, Color backgroundColor) {
        super(backgroundColor);
        this.cell = cell;
        this.sudokuView = sudokuView;
        label = new Label();
        this.getChildren().add(label);

        this.setValue(cell.getValue());
        cell.valueProperty().addListener((obs, oldVal, newVal) -> setValue(newVal));

        this.setOnMouseClicked(mouseEvent -> onClick());
        this.setOnKeyTyped(this::handleKeyTyped);

        this.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                background.setFill(Color.GOLD);
            } else {
                background.setFill(backgroundColor);
            }
        });

        updateBoldStyle(cell.isSet());
        updateBackgroundColor(backgroundColor);
    }

    private void handleKeyTyped(KeyEvent keyEvent) {
        String character = keyEvent.getCharacter();
        ICommand command = null;
        if (character.equals("\b")) {
            command = new RemoveValueCommand(cell);
        } else if (character.matches("[0-9]")) {
            int value = Integer.parseInt(character);
            command = new SetValueCommand(cell, value);
        }

        if (command != null) {
            SudokuController.getInstance().executeCommand(command);
        }
    }

    private void onClick() {
        this.sudokuView.setInterestedCell(this.cell);
        this.requestFocus();
    }

    private void setValue(Number value) {
        this.label.setText(value.intValue() == Cell.EMPTY_CELL ? "" : String.valueOf(value));
        updateBoldStyle(cell.isSet());
    }

    private void updateBoldStyle(boolean isSet) {
        if (isSet) {
            label.getStyleClass().add("extrabold");
        } else {
            label.getStyleClass().remove("extrabold");
        }
    }
}
