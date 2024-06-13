package org.avans.sudoku.command;

import org.avans.sudoku.controller.SudokuController;

public class RestartGameCommand implements ICommand {

    @Override
    public void execute() {
        SudokuController.getInstance().restartGame();
    }
}
