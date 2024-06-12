package org.avans.sudoko.command;

import org.avans.sudoko.model.Cell;

public class RemoveValueCommand extends SetValueCommand {

    public RemoveValueCommand(Cell cell) {
        super(cell, Cell.EMPTY_CELL);
    }
}
