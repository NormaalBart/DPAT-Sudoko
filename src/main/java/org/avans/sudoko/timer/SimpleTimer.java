package org.avans.sudoko.timer;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer implements ITimerComponent {
    private final IntegerProperty secondsElapsed;
    private boolean running = false;

    public SimpleTimer() {
        this.secondsElapsed = new SimpleIntegerProperty(0);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    Platform.runLater(() -> secondsElapsed.set(secondsElapsed.get() + 1));
                }
            }
        }, 1000, 1000);
    }

    public IntegerProperty secondsElapsedProperty() {
        return secondsElapsed;
    }

    public void start() {
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    public void reset() {
        Platform.runLater(() -> secondsElapsed.set(0));
    }

    @Override
    public void accept(ITimerVisitor visitor) {
        visitor.visit(this);
    }
}
