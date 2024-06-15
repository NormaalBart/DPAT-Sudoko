package org.avans.sudoku;

import org.avans.sudoku.factory.SudokuFactory;
import org.avans.sudoku.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFactoryTest {

    private SudokuFactory sudokuFactory;

    @BeforeEach
    void setUp() {
        sudokuFactory = SudokuFactory.getInstance();
    }

    @Test
    void testGetSupportedExtensions() {
        List<String> supportedExtensions = sudokuFactory.getSupportedExtensions();
        assertNotNull(supportedExtensions);
        assertTrue(supportedExtensions.contains(".4x4"));
        assertTrue(supportedExtensions.contains(".6x6"));
        assertTrue(supportedExtensions.contains(".9x9"));
        assertTrue(supportedExtensions.contains(".jigsaw"));
        assertTrue(supportedExtensions.contains(".samurai"));
    }

    @Test
    void testParseSudokuWithValidExtension() {
        String fileName = "puzzle.9x9";
        String content = "530070000600195000098000060800060003400803001700020006060000280000419005000080079";
        Sudoku sudoku = sudokuFactory.parseSudoku(fileName, content);
        assertNotNull(sudoku);
        assertEquals(9, sudoku.getSize());
    }

    @Test
    void testParseSudokuWithInvalidExtension() {
        String fileName = "puzzle.invalid";
        String content = "123456789";
        assertThrows(UnsupportedOperationException.class, () -> {
            sudokuFactory.parseSudoku(fileName, content);
        });
    }
}
