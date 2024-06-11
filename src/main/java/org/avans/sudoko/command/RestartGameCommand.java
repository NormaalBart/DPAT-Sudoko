package org.avans.sudoko.command;

import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.model.GameState;

public class RestartGameCommand implements ICommand {

    @Override
    public void execute() {
        SudokuController.getInstance().startGame(null);
        SudokuController.getInstance().getGameStateProperty().set(GameState.NO_GAME);
    }
}
