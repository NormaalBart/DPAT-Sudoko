package org.avans.sudoku;

import org.avans.sudoku.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CellTest {

    private Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void testSetValue() {
        cell.setValue(5);
        assertEquals(5, cell.getValue());
    }

    @Test
    public void testIsValid() {
        cell.setValue(5);
        assertEquals(cell.getValue(), 5);
        cell.setValue(0);
        assertFalse(cell.isSet());
    }
}