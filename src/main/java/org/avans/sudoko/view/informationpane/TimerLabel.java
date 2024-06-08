package org.avans.sudoko.view.informationpane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.avans.sudoko.controller.SudokuController;

public class TimerLabel extends Label {

    private String timerText = "Timer: %d s";

    public TimerLabel(SudokuController controller) {
        this.setText(String.format(timerText, 0));
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("label-centered");
        controller.getSecondsElapsed().addListener((observable, oldValue, newValue) ->
                TimerLabel.this.setText(String.format(timerText, newValue.intValue())));
    }
}
