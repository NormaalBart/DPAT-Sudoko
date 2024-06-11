package org.avans.sudoko.view.other;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.avans.sudoko.command.RestartGameCommand;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.util.TimeUtil;

public class FinishPane extends VBox {

    public FinishPane() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        Label messageLabel = new Label("Congratulations! De sudoku is klaar!");
        Label timeLabel = new Label(String.format("Geklaard in: %s", TimeUtil.formatSeconds(SudokuController.getInstance().secondsElapsedProperty().get())));

        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(event -> restartGame());

        this.getChildren().addAll(messageLabel, timeLabel, restartButton);
    }

    private void restartGame() {
        SudokuController.getInstance().executeCommand(new RestartGameCommand());
    }
}
