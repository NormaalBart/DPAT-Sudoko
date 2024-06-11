package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.model.ValidatorGroup;

public class SamuraiSudokuParser implements ISudokuParser {

    private static final int GRID_SIZE = 21;
    private static final int GRID_PER_SUDOKU = 9;

    @Override
    public Sudoku parse(String text) {
        if (text.length() != 413) {
            throw new IllegalArgumentException("Puzzle must be exactly 413 characters long.");
        }

        Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];

        fillGrid(cells, text.substring(0, 82), 0, 0);    // Top-left
        fillGrid(cells, text.substring(82, 164), 0, 12);  // Top-right
        fillGrid(cells, text.substring(164, 246), 6, 6);  // Center
        fillGrid(cells, text.substring(246, 328), 12, 0); // Bottom-left
        fillGrid(cells, text.substring(328, 410), 12, 12);// Bottom-right

        return new Sudoku(9, cells);
    }

    private void fillGrid(Cell[][] cells, String grid, int startRow, int startCol) {
        for (int i = 0; i < 81; i++) {
            int row = startRow + i / 9;
            int col = startCol + i % 9;
            char c = grid.charAt(i);
            cells[row][col] = new Cell(Character.getNumericValue(c));
        }
        this.createRowColumnValidators(cells, startRow, startCol);
        this.createSubgridValidators(cells, startRow, startCol);
    }

    private void createRowColumnValidators(Cell[][] cells, int startRow, int startCol) {
        for (int x = 0; x < GRID_PER_SUDOKU; x++) {
            ValidatorGroup rowValidator = new ValidatorGroup(GRID_PER_SUDOKU, false);
            ValidatorGroup columnValidator = new ValidatorGroup(GRID_PER_SUDOKU, false);
            for (int y = 0; y < GRID_PER_SUDOKU; y++) {
                Cell cell = cells[startRow + y][startCol + x];
                rowValidator.addCell(cell);
                columnValidator.addCell(cell);
            }
        }
    }

    private void createSubgridValidators(Cell[][] cells, int startRow, int startCol) {
        for (int subGridRow = 0; subGridRow < GRID_PER_SUDOKU; subGridRow += 3) {
            for (int subGridCol = 0; subGridCol < GRID_PER_SUDOKU; subGridCol += 3) {
                ValidatorGroup subGridValidator = new ValidatorGroup(9, true);
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        int x = subGridRow + row + startRow;
                        int y = subGridCol + col + startCol;
                        Cell cell = cells[x][y];
                        subGridValidator.addCell(cell);
                    }
                }
            }
        }
    }
}