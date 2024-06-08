package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.model.ValidatorGroup;

public class GeneralSudokuParser implements ISudokuParser {

    private final int size;
    private final int subGridRows;
    private final int subGridCols;

    public GeneralSudokuParser(int size) {
        this(size, (int) Math.sqrt(size), (int) Math.sqrt(size));
    }

    public GeneralSudokuParser(int size, int subGridRows, int subGridCols) {
        this.size = size;
        this.subGridRows = subGridRows;
        this.subGridCols = subGridCols;
    }

    @Override
    public Sudoku parse(String text) {
        int expectedLength = size * size;
        if (text.length() != expectedLength) {
            throw new IllegalArgumentException("Invalid input for " + size + "x" + size + " Sudoku.");
        }

        Sudoku sudoku = new Sudoku(size);
        for (int i = 0; i < text.length(); i++) {
            int value = Character.getNumericValue(text.charAt(i));
            int x = i / size;
            int y = i % size;
            if (value != 0) {
                sudoku.setValue(x, y, value, true);
            }
        }

        createRowColumnValidators(sudoku);
        createSubgridValidators(sudoku);
        return sudoku;
    }

    private void createRowColumnValidators(Sudoku sudoku) {
        for (int x = 0; x < sudoku.getSize(); x++) {
            ValidatorGroup rowValidator = new ValidatorGroup(sudoku.getSize(), false);
            ValidatorGroup columnValidator = new ValidatorGroup(sudoku.getSize(), false);
            for (int y = 0; y < sudoku.getSize(); y++) {
                rowValidator.addCell(sudoku.getCell(x, y));
                columnValidator.addCell(sudoku.getCell(y, x));
            }
        }
    }

    private void createSubgridValidators(Sudoku sudoku) {
        for (int subGridRow = 0; subGridRow < size; subGridRow += subGridRows) {
            for (int subGridCol = 0; subGridCol < size; subGridCol += subGridCols) {
                ValidatorGroup subGridValidator = new ValidatorGroup(sudoku.getSize(), true);
                for (int row = 0; row < subGridRows; row++) {
                    for (int col = 0; col < subGridCols; col++) {
                        int x = subGridRow + row;
                        int y = subGridCol + col;
                        Cell cell = sudoku.getCell(x, y);
                        subGridValidator.addCell(cell);
                    }
                }
            }
        }
    }
}
