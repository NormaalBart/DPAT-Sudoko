package org.avans.sudoko.timer;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer implements ITimerComponent {
    private final IntegerProperty secondsElapsed;
    private final Timer timer;

    public SimpleTimer() {
        this.secondsElapsed = new SimpleIntegerProperty(0);
        this.timer = new Timer(true);
    }

    public IntegerProperty secondsElapsedProperty() {
        return secondsElapsed;
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> secondsElapsed.set(secondsElapsed.get() + 1));
            }
        }, 1000, 1000);
    }

    public void stop() {
        timer.cancel();
    }

    public void reset() {
        Platform.runLater(() -> secondsElapsed.set(0));
    }

    @Override
    public void accept(ITimerVisitor visitor) {
        visitor.visit(this);
    }
}
