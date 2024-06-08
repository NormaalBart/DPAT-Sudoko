package org.avans.sudoko.view.sudoko;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import org.avans.sudoko.model.Sudoko;

public class SudokoGridPane extends GridPane {

    private Sudoko sudokoModel;

    public SudokoGridPane(Sudoko sudokoModel) {
        this.sudokoModel = sudokoModel;
        this.setAlignment(Pos.CENTER);
        createGrid();
    }

    private void createGrid() {
        int size = this.sudokoModel.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SudokoCellView cellView = new SudokoCellView(sudokoModel.getCell(i, j));
                this.add(cellView, j, i);
            }
        }
    }

    public void updateGrid() {
        this.getChildren().clear();
        createGrid();
    }
}
