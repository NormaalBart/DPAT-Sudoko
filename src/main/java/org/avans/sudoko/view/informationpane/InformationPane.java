package org.avans.sudoko.view.informationpane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.avans.sudoko.controller.SudokoController;

public class InformationPane extends VBox {

    public InformationPane(SudokoController controller) {
        super(10);
        var timerLabel = new TimerLabel(controller);
        var helpNumbersPane = new HelpNumbersPane();
        this.getChildren().addAll(helpNumbersPane, timerLabel);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));

        // Zorg ervoor dat de kinderen gelijk verdeeld worden
        VBox.setVgrow(helpNumbersPane, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(timerLabel, javafx.scene.layout.Priority.ALWAYS);
    }
}
