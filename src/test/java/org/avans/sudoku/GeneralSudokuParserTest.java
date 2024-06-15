package org.avans.sudoku;

import org.avans.sudoku.factory.parser.GeneralSudokuParser;
import org.avans.sudoku.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralSudokuParserTest {

    private GeneralSudokuParser parser;

    @BeforeEach
    void setUp() {
        parser = new GeneralSudokuParser(9);
    }

    @Test
    void testParseValidSudoku() {
        String content = "530070000600195000098000060800060003400803001700020006060000280000419005000080079";
        Sudoku sudoku = parser.parse(content);
        assertNotNull(sudoku);
        assertEquals(9, sudoku.getSize());
        assertEquals(5, sudoku.getCell(0, 0).getValue());
        assertEquals(3, sudoku.getCell(0, 1).getValue());
        assertEquals(0, sudoku.getCell(0, 2).getValue());
    }

    @Test
    void testParseInvalidSudokuLength() {
        String content = "123456789";
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(content);
        });
    }
}