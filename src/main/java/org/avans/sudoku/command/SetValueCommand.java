package org.avans.sudoku.command;

import org.avans.sudoku.model.Cell;

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
        cell.setValue(value);
    }
}
