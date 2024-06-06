package org.avans.sudoko.view.informationpane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HelpNumbersPane extends GridPane {
    public HelpNumbersPane() {
        super();
        Label helpLabel = new Label("Hulpgetallen");
        helpLabel.getStyleClass().add("label-centered");
        this.getChildren().add(helpLabel);
        this.setAlignment(Pos.CENTER);
    }
}
