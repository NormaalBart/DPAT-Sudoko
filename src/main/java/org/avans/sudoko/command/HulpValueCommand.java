package org.avans.sudoko.command;

import org.avans.sudoko.model.Cell;

public abstract class HulpValueCommand implements ICommand {

    protected final Cell cell;
    protected final int value;

    public HulpValueCommand(Cell cell, int value) {
        this.cell = cell;
        this.value = value;
    }

    @Override
    public abstract void execute();

}
