package org.avans.sudoko.view.sudoko;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.avans.sudoko.model.Cell;

public class SudokoCellView extends StackPane {

    private Label label;

    public SudokoCellView(Cell cell) {
        label = new Label();
        this.setValue(cell.getValue().get());
        cell.getValue().subscribe(this::setValue);

        this.getChildren().add(label);
        this.setStyle("-fx-border-color: black; -fx-alignment: center;");
        this.setPrefSize(50, 50);
    }

    private void setValue(Number value) {
        this.setValue(value.intValue());
    }

    public void setValue(int value) {
        label.setText(value == 0 ? "" : String.valueOf(value));
    }
}