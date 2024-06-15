package org.avans.sudoku;

import org.avans.sudoku.model.Cell;
import org.avans.sudoku.model.ValidatorGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorGroupTest {

    private ValidatorGroup validatorGroup;

    @BeforeEach
    void setUp() {
        // Initialize ValidatorGroup with a max number of 9 (standard for Sudoku)
        validatorGroup = new ValidatorGroup(9, true);
    }

    @Test
    void testAddCell() {
        Cell cell = new Cell(5);
        validatorGroup.addCell(cell);
        assertTrue(validatorGroup.getCells().contains(cell), "ValidatorGroup should contain the added cell.");
    }

    @Test
    void testShowVisual() {
        assertTrue(validatorGroup.showVisual(), "ValidatorGroup should show visual as set in constructor.");
    }

    @Test
    void testIsValidWithValidCells() {
        for (int i = 1; i <= 9; i++) {
            Cell cell = new Cell(i);
            validatorGroup.addCell(cell);
        }
        assertTrue(validatorGroup.isValid(), "ValidatorGroup should be valid with unique numbers from 1 to 9.");
    }

    @Test
    void testIsValidWithInvalidCellsDuplicate() {
        for (int i = 1; i <= 8; i++) {
            Cell cell = new Cell(i);
            validatorGroup.addCell(cell);
        }
        Cell duplicateCell = new Cell(8);
        validatorGroup.addCell(duplicateCell);
        assertFalse(validatorGroup.isValid(), "ValidatorGroup should be invalid with duplicate numbers.");
    }

    @Test
    void testIsValidWithInvalidCellsOutOfBounds() {
        for (int i = 1; i <= 8; i++) {
            Cell cell = new Cell(i);
            validatorGroup.addCell(cell);
        }
        Cell outOfBoundsCell = new Cell(10);
        validatorGroup.addCell(outOfBoundsCell);
        assertFalse(validatorGroup.isValid(), "ValidatorGroup should be invalid with a number out of bounds.");
    }

    @Test
    void testIsValidWithEmptyCell() {
        for (int i = 1; i <= 8; i++) {
            Cell cell = new Cell(i);
            validatorGroup.addCell(cell);
        }
        Cell emptyCell = new Cell(Cell.EMPTY_CELL);
        validatorGroup.addCell(emptyCell);
        assertFalse(validatorGroup.isValid(), "ValidatorGroup should be invalid with an empty cell.");
    }
}
