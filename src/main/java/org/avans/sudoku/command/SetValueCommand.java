package org.avans.sudoku.command;

import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.Cell;
import org.avans.sudoku.model.Sudoku;

public class SetValueCommand implements ICommand {

    private final Cell cell;
    private final int value;

    public SetValueCommand(Cell cell, int value) {
        this.cell = cell;
        this.value = value;
    }

    @Override
    public void execute() {
        if (cell.isSet()) {
            return;
        }
        if(SudokuController.getInstance().shouldCheck() && value > SudokuController.getInstance().getSudoku().getSize()) {
            return;
        }
        cell.setValue(value);
    }
}
