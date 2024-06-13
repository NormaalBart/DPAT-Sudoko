package org.avans.sudoku;

import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.GameState;
import org.avans.sudoku.model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

    private SudokuController controller;
    private Sudoku sudoku;
    private final int SUDOKU_SIZE = 9;

    @BeforeEach
    public void setUp() {
        this.controller = SudokuController.getInstance();
        this.sudoku = new Sudoku(this.SUDOKU_SIZE);
        this.controller.startGame(this.sudoku);
    }

    @Test
    public void testLoadNewGame() {
        assertNotNull(this.controller.getSudoku(), "Sudoku game should be loaded");
        assertEquals(this.controller.getGameStateProperty().get(), GameState.STARTED);
    }

    @Test
    public void testGameFlow() {
        this.sudoku.getCell(0, 0).setValue(5);
        this.sudoku.getCell(0, 1).setValue(3);
        this.sudoku.getCell(1, 0).setValue(6);

        assertEquals(5, this.sudoku.getCell(0, 0).getValue());
        assertEquals(3, this.sudoku.getCell(0, 1).getValue());
        assertEquals(6, this.sudoku.getCell(1, 0).getValue());

        assertNotEquals(this.controller.getGameStateProperty().get(), GameState.FINISHED, "Sudoku should not be complete");

        this.fillBoard(this.sudoku);

        // Now the game should be complete
        assertEquals(this.controller.getGameStateProperty().get(), GameState.FINISHED, "Sudoku should be complete");
    }

    private void fillBoard(Sudoku sudoku) {
        int[][] solution = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.getCell(i, j).setValue(solution[i][j]);
            }
        }
    }
}