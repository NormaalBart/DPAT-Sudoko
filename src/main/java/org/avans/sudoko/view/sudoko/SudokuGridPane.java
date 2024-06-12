package org.avans.sudoko.view.sudoko;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.model.ValidatorGroup;
import org.avans.sudoko.view.SudokuView;
import org.avans.sudoko.view.sudoko.cell.BaseCellView;
import org.avans.sudoko.view.sudoko.cell.EmptyCellView;
import org.avans.sudoko.view.sudoko.cell.SudokuCellView;

import java.util.*;

public class SudokuGridPane extends GridPane {

    private static final List<Color> BACKGROUND_COLORS = new ArrayList<>(List.of(
            Color.LIGHTCORAL, Color.LIGHTGREEN, Color.LIGHTBLUE, Color.LIGHTPINK, Color.LIGHTYELLOW,
            Color.LIGHTCYAN, Color.LIGHTGOLDENRODYELLOW, Color.LIGHTGRAY, Color.LIGHTSALMON, Color.LIGHTSALMON,
            Color.LIGHTSEAGREEN, Color.LIGHTSKYBLUE, Color.LIGHTSLATEGRAY, Color.LIGHTSTEELBLUE, Color.LIGHTGREEN,
            Color.LIGHTSKYBLUE, Color.LIGHTYELLOW, Color.LIGHTPINK, Color.LIGHTBLUE, Color.LIGHTCORAL
    ));

    private final SudokuView sudokuView;
    private final Sudoku sudokuModel;

    public SudokuGridPane(SudokuView sudokuView, Sudoku sudokuModel) {
        this.sudokuView = sudokuView;
        this.sudokuModel = sudokuModel;
        this.setAlignment(Pos.CENTER);
        createGrid();
    }

    private void createGrid() {
        Map<ValidatorGroup, Color> colorMap = new HashMap<>();
        int size = this.sudokuModel.getGrid().length;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = this.sudokuModel.getCell(x, y);
                BaseCellView cellView;
                if (cell == null) {
                    cellView = new EmptyCellView();
                } else {
                    Optional<ValidatorGroup> optionalGroup = cell.getVisualValidatorGroup();
                    Color backgroundColor;

                    if (optionalGroup.isPresent()) {
                        ValidatorGroup group = optionalGroup.get();
                        backgroundColor = colorMap.computeIfAbsent(group, k -> getNextColor(colorMap.size()));
                    } else {
                        backgroundColor = Color.WHITE;
                    }

                    cellView = new SudokuCellView(this.sudokuView, cell, backgroundColor);
                }
                this.add(cellView, y, x);
            }
        }
    }

    private Color getNextColor(int index) {
        return BACKGROUND_COLORS.get(index % BACKGROUND_COLORS.size());
    }
}
