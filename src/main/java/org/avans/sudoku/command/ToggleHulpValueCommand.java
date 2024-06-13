package org.avans.sudoku.command;

import org.avans.sudoku.model.Cell;

public class ToggleHulpValueCommand extends HulpValueCommand {

    public ToggleHulpValueCommand(Cell cell, int value) {
        super(cell, value);
    }

    @Override
    public void execute() {
        this.cell.toggleHulpValue(this.value);
    }
}
