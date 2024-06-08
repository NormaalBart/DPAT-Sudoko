package org.avans.sudoko.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoko;
import org.avans.sudoko.view.informationpane.InformationPane;
import org.avans.sudoko.view.menubar.SudokoMenuBar;
import org.avans.sudoko.view.other.NoSudokoPane;
import org.avans.sudoko.view.sudoko.SudokoGridPane;

public class SudokoView extends BorderPane {

    private final SudokoController controller;

    private final ObjectProperty<Cell> interestedCell = new SimpleObjectProperty<Cell>();

    public SudokoView(SudokoController sudokoController) {
        this.controller = sudokoController;

        var menuBar = new SudokoMenuBar(controller);

        this.setPadding(new Insets(10));
        this.setTop(menuBar);
        this.setRight(this.createInformationPane());

        this.controller.getSudokoProperty().subscribe(sudoko -> {
            this.interestedCell.set(null);
            if (sudoko != null) {
                this.setCenter(this.createSudokoGridPane(sudoko));
            } else {
                this.setCenter(this.createNoSudokoPane());
            }
        });
    }

    private InformationPane createInformationPane() {
        InformationPane informationPane = new InformationPane(this, controller);
        informationPane.prefHeightProperty().bind(this.heightProperty());
        informationPane.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        BorderPane.setMargin(informationPane, new Insets(10));
        return informationPane;
    }

    private NoSudokoPane createNoSudokoPane() {
        return new NoSudokoPane(controller);
    }

    private SudokoGridPane createSudokoGridPane(Sudoko sudoko) {
        return new SudokoGridPane(this, sudoko);
    }

    public void setInterestedCell(Cell cell) {
        this.interestedCell.set(cell);
    }

    public ObjectProperty<Cell> interestedCellProperty() {
        return this.interestedCell;
    }
}
