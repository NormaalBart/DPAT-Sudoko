package org.avans.sudoko.view.sudoko;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import org.avans.sudoko.model.Sudoko;
import org.avans.sudoko.view.SudokoView;

public class SudokoGridPane extends GridPane {

    private final SudokoView sudokoView;
    private final Sudoko sudokoModel;

    public SudokoGridPane(SudokoView sudokoView, Sudoko sudokoModel) {
        this.sudokoView = sudokoView;
        this.sudokoModel = sudokoModel;
        this.setAlignment(Pos.CENTER);
        createGrid();
    }

    private void createGrid() {
        int size = this.sudokoModel.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SudokoCellView cellView = new SudokoCellView(this.sudokoView, this.sudokoModel.getCell(i, j));
                this.add(cellView, j, i);
            }
        }
    }
}
