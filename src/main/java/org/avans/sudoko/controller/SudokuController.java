package org.avans.sudoko.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.avans.sudoko.command.ICommand;
import org.avans.sudoko.model.GameState;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.timer.*;

public class SudokuController {

    private static SudokuController instance;

    public static SudokuController getInstance() {
        if (instance == null) {
            instance = new SudokuController();
        }
        return instance;
    }

    private final ObjectProperty<Sudoku> sudokuModel = new SimpleObjectProperty<>();
    private final ObjectProperty<GameState> gameState = new SimpleObjectProperty<>();
    private final TimerComposite timerComposite;
    private final SimpleTimer simpleTimer;

    private SudokuController() {
        timerComposite = new TimerComposite();
        simpleTimer = new SimpleTimer();
        timerComposite.add(simpleTimer);
        this.timerComposite.accept(new StartTimerVisitor());
        this.gameState.set(GameState.NO_GAME);
    }

    public void startGame(Sudoku sudoku) {
        this.sudokuModel.set(sudoku);
        this.timerComposite.accept(new ResetTimerVisitor());
        this.gameState.set(GameState.STARTED);
    }

    public IntegerProperty secondsElapsedProperty() {
        return simpleTimer.secondsElapsedProperty();
    }

    public ObjectProperty<Sudoku> getSudokuProperty() {
        return sudokuModel;
    }

    public Sudoku getSudoku() {
        return this.sudokuModel.get();
    }

    public ObjectProperty<GameState> getGameStateProperty() {
        return gameState;
    }

    public void executeCommand(ICommand command) {
        command.execute();
        if (this.sudokuModel.get() != null) {
            if (!this.sudokuModel.get().isValid()) {
                return;
            }
            this.gameState.set(GameState.FINISHED);
            timerComposite.accept(new StopTimerVisitor());
        }
    }
}
