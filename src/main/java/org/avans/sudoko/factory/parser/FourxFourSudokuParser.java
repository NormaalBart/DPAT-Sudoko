package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.model.ValidatorGroup;

public class FourxFourSudokuParser implements ISudokuParser {

    @Override
    public Sudoku parse(String text) {
        if (text.length() != 16) {
            throw new IllegalArgumentException("Invalid input for 4x4 Sudoku.");
        }

        Sudoku sudoku = new Sudoku(4);
        for (int i = 0; i < text.length(); i++) {
            int value = Character.getNumericValue(text.charAt(i));
            int x = i / 4;
            int y = i % 4;
            if (value != 0) {
                sudoku.setValue(x, y, value, true);
            }
        }

        this.createRowValidators(sudoku);
        this.createColumnValidators(sudoku);
        this.createSubgridValidators(sudoku);
        return sudoku;
    }

    private void createRowValidators(Sudoku sudoku) {
        for (int x = 0; x < sudoku.getSize(); x++) {
            ValidatorGroup rowValidator = new ValidatorGroup(sudoku.getSize(), false);
            for (int y = 0; y < sudoku.getSize(); y++) {
                Cell cell = sudoku.getCell(x, y);
                rowValidator.addCell(cell);
            }
        }
    }

    private void createColumnValidators(Sudoku sudoku) {
        for (int y = 0; y < sudoku.getSize(); y++) {
            ValidatorGroup columnValidator = new ValidatorGroup(sudoku.getSize(), false);
            for (int x = 0; x < sudoku.getSize(); x++) {
                Cell cell = sudoku.getCell(x, y);
                columnValidator.addCell(cell);
            }
        }
    }

    private void createSubgridValidators(Sudoku sudoku) {
        int subGridSize = (int) Math.sqrt(sudoku.getSize());
        for (int subGridRow = 0; subGridRow < subGridSize; subGridRow++) {
            for (int subGridCol = 0; subGridCol < subGridSize; subGridCol++) {
                ValidatorGroup subGridValidator = new ValidatorGroup(sudoku.getSize(), true);
                for (int row = 0; row < subGridSize; row++) {
                    for (int col = 0; col < subGridSize; col++) {
                        int x = subGridRow * subGridSize + row;
                        int y = subGridCol * subGridSize + col;
                        Cell cell = sudoku.getCell(x, y);
                        subGridValidator.addCell(cell);
                    }
                }
            }
        }
    }
}
