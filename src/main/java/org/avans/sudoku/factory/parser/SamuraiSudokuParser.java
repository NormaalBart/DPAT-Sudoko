package org.avans.sudoku.factory.parser;

import org.avans.sudoku.model.Cell;
import org.avans.sudoku.model.Sudoku;
import org.avans.sudoku.model.ValidatorGroup;

import java.util.Arrays;

public class SamuraiSudokuParser implements ISudokuParser {

    private static final int GRID_AMOUNT = 5;
    private static final int GRID_SIZE = 21;
    private static final int GRID_PER_SUDOKU = 9;

    @Override
    public Sudoku parse(String text) {
        String[] grids = text.split(System.lineSeparator());
        assert grids.length == GRID_AMOUNT;
        assert Arrays.stream(grids).anyMatch(grid -> grid.length() != GRID_PER_SUDOKU * GRID_PER_SUDOKU);

        Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];

        fillGrid(cells, grids[0], 0, 0);    // Top-left
        fillGrid(cells, grids[1], 0, 12);  // Top-right
        fillGrid(cells, grids[2], 6, 6);  // Center
        fillGrid(cells, grids[3], 12, 0); // Bottom-left
        fillGrid(cells, grids[4], 12, 12);// Bottom-right

        return new Sudoku(9, cells);
    }

    private void fillGrid(Cell[][] cells, String grid, int startRow, int startCol) {
        for (int i = 0; i < GRID_PER_SUDOKU * GRID_PER_SUDOKU; i++) {
            int row = startRow + i / GRID_PER_SUDOKU;
            int col = startCol + i % GRID_PER_SUDOKU;
            char c = grid.charAt(i);
            int value = Integer.parseInt(c + "");
            Cell cell = new Cell();
            if (value != 0) {
                cell.setValue(value, true);
            }
            cells[row][col] = cell;
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
        int gridSize = (int) Math.sqrt(GRID_PER_SUDOKU);
        for (int subGridRow = 0; subGridRow < GRID_PER_SUDOKU; subGridRow += gridSize) {
            for (int subGridCol = 0; subGridCol < GRID_PER_SUDOKU; subGridCol += gridSize) {
                ValidatorGroup subGridValidator = new ValidatorGroup(GRID_PER_SUDOKU, true);
                for (int row = 0; row < gridSize; row++) {
                    for (int col = 0; col < gridSize; col++) {
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