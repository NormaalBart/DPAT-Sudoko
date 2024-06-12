package org.avans.sudoko.command;

import org.avans.sudoko.controller.SudokuController;

public class RestartGameCommand implements ICommand {

    @Override
    public void execute() {
        SudokuController.getInstance().restartGame();
    }
}
