package org.avans.sudoko.command;

import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.model.Sudoku;

public class LoadNewGameCommand implements ICommand {

    private final Sudoku sudoku;

    public LoadNewGameCommand(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public void execute() {
        SudokuController.getInstance().startGame(this.sudoku);
    }
}
