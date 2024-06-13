package org.avans.sudoku.command;

import org.avans.sudoku.model.Cell;

public class RemoveValueCommand extends SetValueCommand {

    public RemoveValueCommand(Cell cell) {
        super(cell, Cell.EMPTY_CELL);
    }
}
