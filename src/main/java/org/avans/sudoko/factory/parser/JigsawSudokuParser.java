package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.Sudoku;
import org.avans.sudoko.model.ValidatorGroup;

import java.util.HashMap;
import java.util.Map;

public class JigsawSudokuParser implements ISudokuParser {

    private static final int SIZE = 9;

    @Override
    public Sudoku parse(String text) {
        if (!text.startsWith("SumoCueV1=")) {
            throw new IllegalArgumentException("Invalid input format for Jigsaw Sudoku.");
        }
        text = text.substring("SumoCueV1=".length());

        Sudoku sudoku = new Sudoku(SIZE);

        String[] entries = text.split("=");
        if (entries.length != SIZE * SIZE) {
            throw new IllegalArgumentException("Invalid number of entries for 9x9 Sudoku.");
        }

        Map<Integer, ValidatorGroup> groupMap = new HashMap<>();

        for (int index = 0; index < entries.length; index++) {
            String entry = entries[index];
            int value = Character.getNumericValue(entry.charAt(0));
            int groupIndex = entry.charAt(2);
            int x = index / SIZE;
            int y = index % SIZE;

            if (!groupMap.containsKey(groupIndex)) {
                groupMap.put(groupIndex, new ValidatorGroup(SIZE, true));
            }
            ValidatorGroup group = groupMap.get(groupIndex);
            Cell cell = sudoku.getCell(x, y);
            if (value != 0) {
                sudoku.setValue(x, y, value, true);
            }
            group.addCell(cell);
        }

        this.createRowColumnValidators(sudoku);
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
}
