package org.avans.sudoko.view.informationpane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.view.SudokuView;

public class InformationPane extends VBox {

    public InformationPane(SudokuView sudokuView) {
        super(10);
        TimerLabel timerLabel = new TimerLabel();
        HelpNumbersPane helpNumbersPane = new HelpNumbersPane(sudokuView);
        this.getChildren().addAll(helpNumbersPane, timerLabel);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));

        VBox.setVgrow(helpNumbersPane, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(timerLabel, javafx.scene.layout.Priority.ALWAYS);
    }
}
