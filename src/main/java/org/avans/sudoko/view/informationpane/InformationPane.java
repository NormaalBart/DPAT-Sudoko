package org.avans.sudoko.view.informationpane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.avans.sudoko.view.SudokuView;

public class InformationPane extends VBox {

    private static final int INSET = 10;

    public InformationPane(SudokuView sudokuView) {
        super(INSET);
        TimerLabel timerLabel = new TimerLabel();
        HelpNumbersPane helpNumbersPane = new HelpNumbersPane(sudokuView);
        this.getChildren().addAll(helpNumbersPane, timerLabel);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(INSET));

        VBox.setVgrow(helpNumbersPane, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(timerLabel, javafx.scene.layout.Priority.ALWAYS);
    }
}
