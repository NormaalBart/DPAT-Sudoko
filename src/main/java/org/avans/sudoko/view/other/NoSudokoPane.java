package org.avans.sudoko.view.other;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.view.menubar.SudokoFileChooser;

public class NoSudokoPane extends BorderPane {

    private final SudokoController sudokoController;

    public NoSudokoPane(SudokoController controller) {
        this.sudokoController = controller;
        createCenterLabel();
    }

    private void createCenterLabel() {
        Label label = new Label("Laat een sudoko!");
        label.setStyle("-fx-font-size: 20px; -fx-cursor: hand;");

        label.setOnMouseClicked(event -> {
            new SudokoFileChooser(this.sudokoController).openSudokuFile((Stage) this.getScene().getWindow());
        });

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(label);
        this.setCenter(centerPane);
    }
}
