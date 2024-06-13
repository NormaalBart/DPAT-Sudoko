package org.avans.sudoku.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.avans.sudoku.command.ICommand;
import org.avans.sudoku.model.GameState;
import org.avans.sudoku.model.Sudoku;
import org.avans.sudoku.timer.*;

public class SudokuController {

    private static SudokuController instance;
    private final ObjectProperty<Sudoku> sudokuModel = new SimpleObjectProperty<>();
    private final ObjectProperty<GameState> gameState = new SimpleObjectProperty<>();
    private final TimerComposite timerComposite;
    private final SimpleTimer simpleTimer;

    private SudokuController() {
        timerComposite = new TimerComposite();
        simpleTimer = new SimpleTimer();
        timerComposite.add(simpleTimer);
        this.gameState.set(GameState.NO_GAME);
    }

    public static SudokuController getInstance() {
        if (instance == null) {
            instance = new SudokuController();
        }
        return instance;
    }

    public void startGame(Sudoku sudoku) {
        this.sudokuModel.set(sudoku);
        this.timerComposite.accept(new StartTimerVisitor());
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

    public void restartGame() {
        this.gameState.set(GameState.NO_GAME);
        this.sudokuModel.set(null);
        this.timerComposite.accept(new ResetTimerVisitor());
    }
}
