package org.avans.sudoko.controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.avans.sudoko.command.ICommand;
import org.avans.sudoko.model.Sudoku;

import java.util.Timer;
import java.util.TimerTask;

public class SudokuController {

    private final ObjectProperty<Sudoku> sudokuModel = new SimpleObjectProperty<>();
    private final IntegerProperty secondsElapsed;
    private final Timer timer;

    public SudokuController() {
        this.secondsElapsed = new SimpleIntegerProperty(0);
        this.timer = new Timer(true);
        this.startTimer();
    }

    public void startGame(Sudoku sudoku) {
        this.sudokuModel.set(sudoku);
        this.secondsElapsed.set(0);
    }

    public IntegerProperty getSecondsElapsed() {
        return this.secondsElapsed;
    }

    public ObjectProperty<Sudoku> getSudokuProperty() {
        return sudokuModel;
    }

    public Sudoku getSudoko() {
        return this.sudokuModel.get();
    }

    public void executeCommand(ICommand command) {
        command.execute();
        //TODO CHECK IF SUDOKO IS ALL GOOD!
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    secondsElapsed.set(secondsElapsed.get() + 1);
                });
            }
        }, 1000, 1000);
    }
}
