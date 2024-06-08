package org.avans.sudoko.view;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.model.Sudoko;
import org.avans.sudoko.view.informationpane.InformationPane;
import org.avans.sudoko.view.menubar.SudokoMenuBar;
import org.avans.sudoko.view.other.NoSudokoPane;
import org.avans.sudoko.view.sudoko.SudokoGridPane;

public class SudokoView extends BorderPane {

    private final SudokoController controller;

    public SudokoView(SudokoController sudokoController) {
        this.controller = sudokoController;

        var menuBar = new SudokoMenuBar(controller);


        this.setPadding(new Insets(10));
        this.setTop(menuBar);
        this.setRight(this.createInformationPane());

        this.controller.getModel().subscribe(sudoko -> {
            if (sudoko != null) {
                this.setCenter(this.createSudokoGridPane(sudoko));
            } else {
                this.setCenter(this.createNoSudokoPane());
            }
        });
    }

    private InformationPane createInformationPane() {
        InformationPane informationPane = new InformationPane(controller);
        informationPane.prefHeightProperty().bind(this.heightProperty());
        informationPane.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        BorderPane.setMargin(informationPane, new Insets(10));
        return informationPane;
    }

    private NoSudokoPane createNoSudokoPane() {
        return new NoSudokoPane(controller);
    }

    private SudokoGridPane createSudokoGridPane(Sudoko sudoko) {
        SudokoGridPane sudokoGridPane = new SudokoGridPane(sudoko);
        sudokoGridPane.prefHeightProperty().bind(this.heightProperty());
        sudokoGridPane.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        BorderPane.setMargin(sudokoGridPane, new Insets(10));
        return sudokoGridPane;
    }
}
