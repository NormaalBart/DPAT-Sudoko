package org.avans.sudoku.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.Cell;
import org.avans.sudoku.model.Sudoku;
import org.avans.sudoku.view.informationpane.InformationPane;
import org.avans.sudoku.view.menubar.SudokuMenuBar;
import org.avans.sudoku.view.other.FinishPane;
import org.avans.sudoku.view.other.NoSudokuPane;
import org.avans.sudoku.view.sudoko.SudokuGridPane;

public class SudokuView extends BorderPane {

    private static final double PREF_WIDTH_INFORMATION_PANE = 0.25;
    private static final int INSET_INFORMATION_PANE = 10;

    private final ObjectProperty<Cell> interestedCell = new SimpleObjectProperty<Cell>();

    public SudokuView() {

        var menuBar = new SudokuMenuBar();

        this.setPadding(new Insets(10));
        this.setTop(menuBar);
        this.setRight(this.createInformationPane());

        SudokuController.getInstance().getGameStateProperty().subscribe(gameState -> {
            Pane pane;
            switch (gameState) {
                case STARTED -> pane = this.createSudokoGridPane(SudokuController.getInstance().getSudoku());
                case FINISHED -> pane = this.createFinishedPane();
                default -> pane = this.createNoSudokoPane();
            }
            this.setCenter(pane);
            this.interestedCell.set(null);
        });
    }

    private FinishPane createFinishedPane() {
        return new FinishPane();
    }

    private InformationPane createInformationPane() {
        InformationPane informationPane = new InformationPane(this);
        informationPane.prefHeightProperty().bind(this.heightProperty());
        informationPane.prefWidthProperty().bind(this.widthProperty().multiply(PREF_WIDTH_INFORMATION_PANE));
        BorderPane.setMargin(informationPane, new Insets(INSET_INFORMATION_PANE));
        return informationPane;
    }

    private NoSudokuPane createNoSudokoPane() {
        return new NoSudokuPane();
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
