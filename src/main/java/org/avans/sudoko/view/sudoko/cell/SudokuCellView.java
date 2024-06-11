package org.avans.sudoko.view.sudoko.cell;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.avans.sudoko.command.ICommand;
import org.avans.sudoko.command.RemoveValueCommand;
import org.avans.sudoko.command.SetValueCommand;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.view.SudokuView;

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
        this.label.setText(value.intValue() == 0 ? "" : String.valueOf(value));
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
