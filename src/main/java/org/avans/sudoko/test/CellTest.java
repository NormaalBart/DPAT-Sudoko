package org.avans.sudoko.test;

import static org.junit.jupiter.api.Assertions.*;

import org.avans.sudoko.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(cell.getValue() == 5);
        cell.setValue(0);
        assertFalse(cell.isSet());
    }
}
