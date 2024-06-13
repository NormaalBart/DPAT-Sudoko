package org.avans.sudoku.command;

import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.Sudoku;

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
