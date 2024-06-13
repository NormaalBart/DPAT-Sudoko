package org.avans.sudoku.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidatorGroup {

    private final List<Cell> cells;
    private final int maxNumber;
    private boolean showVisual = false;

    public ValidatorGroup(int maxNumber, boolean showVisual) {
        this.cells = new ArrayList<>(maxNumber);
        this.maxNumber = maxNumber;
        this.showVisual = showVisual;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
        cell.addValidator(this);
    }

    public boolean showVisual() {
        return this.showVisual;
    }

    public boolean isValid() {
        Set<Integer> seenNumbers = new HashSet<>();
        for (Cell cell : cells) {
            int value = cell.getValue();
            if (value < 1 || value > maxNumber || seenNumbers.contains(value)) {
                return false;
            }
            seenNumbers.add(value);
        }
        return true;
    }
}
