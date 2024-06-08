package org.avans.sudoko.view.informationpane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.view.SudokoView;

public class InformationPane extends VBox {

    public InformationPane(SudokoView sudokoView, SudokoController controller) {
        super(10);
        TimerLabel timerLabel = new TimerLabel(controller);
        HelpNumbersPane helpNumbersPane = new HelpNumbersPane(controller, sudokoView);
        this.getChildren().addAll(helpNumbersPane, timerLabel);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));

        VBox.setVgrow(helpNumbersPane, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(timerLabel, javafx.scene.layout.Priority.ALWAYS);
    }
}
