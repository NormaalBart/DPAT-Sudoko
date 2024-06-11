package org.avans.sudoko.view.informationpane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.util.TimeUtil;

public class TimerLabel extends Label {

    private final String timerText = "Timer: %s";

    public TimerLabel() {
        this.setText(String.format(timerText, 0));
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("label-centered");
        SudokuController.getInstance().secondsElapsedProperty().addListener((observable, oldValue, newValue) ->
                TimerLabel.this.setText(String.format(timerText, TimeUtil.formatSeconds(newValue.intValue()))));
    }
}
