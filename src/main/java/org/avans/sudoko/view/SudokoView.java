package org.avans.sudoko.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.view.informationpane.InformationPane;
import org.avans.sudoko.view.menubar.SudokoMenuBar;

public class SudokoView extends BorderPane {

    private final SudokoController controller;
    private final GridPane sudokuGrid;
    private final InformationPane informationPane;

    public SudokoView(SudokoController sudokoController) {
        this.controller = sudokoController;
        this.sudokuGrid = new GridPane();
        this.informationPane = new InformationPane(controller);

        var menuBar = new SudokoMenuBar(controller);

        this.setPadding(new Insets(10));
        this.setTop(menuBar);
        this.setCenter(sudokuGrid);
        this.setRight(informationPane);

        informationPane.prefHeightProperty().bind(this.heightProperty());
        informationPane.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
        BorderPane.setMargin(informationPane, new Insets(10));
    }
}
