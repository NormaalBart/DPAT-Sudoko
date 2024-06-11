package org.avans.sudoko.view.sudoko.cell;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseCellView extends StackPane {
    protected final Rectangle background;
    protected final static double CELL_SIZE = 20;

    public BaseCellView(Color backgroundColor) {
        background = new Rectangle(CELL_SIZE, CELL_SIZE);
        this.updateBackgroundColor(backgroundColor);
        this.getChildren().add(background);

        this.setPrefSize(CELL_SIZE, CELL_SIZE);
    }

    protected void updateBackgroundColor(Color backgroundColor) {
        background.setFill(backgroundColor);
        if (backgroundColor != Color.TRANSPARENT) {
            this.getStyleClass().remove("transparent-background");
            this.getStyleClass().add("base-cell");
        } else {
            this.getStyleClass().add("transparent-background");
            this.getStyleClass().remove("base-cell");
        }
    }
}