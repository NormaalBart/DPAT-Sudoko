package org.avans.sudoko.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.view.informationpane.InformationPane;
import org.avans.sudoko.view.menubar.SudokuMenuBar;
import org.avans.sudoko.view.other.NoSudokuPane;
import org.avans.sudoko.view.sudoko.SudokuGridPane;

public class SudokuView extends BorderPane {

    private final SudokuController controller;

    private final ObjectProperty<Cell> interestedCell = new SimpleObjectProperty<Cell>();

    public SudokuView(SudokuController sudokuController) {
        this.controller = sudokuController;

        var menuBar = new SudokuMenuBar(controller);

        this.setPadding(new Insets(10));
        this.setTop(menuBar);
        this.setRight(this.createInformationPane());

        this.controller.getSudokuProperty().subscribe(sudoko -> {
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

    private NoSudokuPane createNoSudokoPane() {
        return new NoSudokuPane(controller);
    }

    private SudokuGridPane createSudokoGridPane(Sudoku sudoku) {
        return new SudokuGridPane(this, sudoku);
    }

    public void setInterestedCell(Cell cell) {
        this.interestedCell.set(cell);
    }

    public ObjectProperty<Cell> interestedCellProperty() {
        return this.interestedCell;
    }
}
