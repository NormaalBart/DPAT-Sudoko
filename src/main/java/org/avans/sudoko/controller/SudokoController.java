package org.avans.sudoko.controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.avans.sudoko.model.Sudoko;

import java.util.Timer;
import java.util.TimerTask;

public class SudokoController {

    private ObjectProperty<Sudoko> sudokoModel = new SimpleObjectProperty<>();
    private IntegerProperty secondsElapsed;
    private Timer timer;

    public SudokoController() {
        this.secondsElapsed = new SimpleIntegerProperty(0);
        this.timer = new Timer(true);
        this.startTimer();
    }

    public void startGame(Sudoko sudoko) {
        this.sudokoModel.set(sudoko);
    }

    public IntegerProperty getSecondsElapsed() {
        return this.secondsElapsed;
    }

    public ObjectProperty<Sudoko> getModel() {
        return sudokoModel;
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
