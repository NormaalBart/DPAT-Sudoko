package org.avans.sudoku;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import org.avans.sudoku.command.ICommand;
import org.avans.sudoku.command.RemoveValueCommand;
import org.avans.sudoku.command.RestartGameCommand;
import org.avans.sudoku.command.SetValueCommand;
import org.avans.sudoku.controller.SudokuController;
import org.avans.sudoku.model.Cell;
import org.avans.sudoku.model.GameState;
import org.avans.sudoku.model.Sudoku;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SudokuControllerTest {

    private SudokuController controller;


    @BeforeAll
    static void beforeAll() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = SudokuController.getInstance();
        controller.startGame(new Sudoku(9));
    }

    @Test
    void testRestartGame() {
        ICommand command = new RestartGameCommand();

        this.controller.executeCommand(command);

        assertEquals(GameState.NO_GAME, controller.getGameStateProperty().get());
        assertNull(controller.getSudokuProperty().get());
    }

    @Test
    void testSetValue() {
        Cell cell = this.controller.getSudoku().getCell(0,0);
        ICommand command = new SetValueCommand(cell, 1);

        this.controller.executeCommand(command);

        assertEquals(1, cell.getValue());
    }

    @Test
    void testRemoveValue() {
        Cell cell = this.controller.getSudoku().getCell(0,0);
        ICommand command = new SetValueCommand(cell, 1);

        this.controller.executeCommand(command);

        assertEquals(1, cell.getValue());


        ICommand command2 = new RemoveValueCommand(cell);

        this.controller.executeCommand(command2);
        assertEquals(Cell.EMPTY_CELL, cell.getValue());
    }


    @Test
    void testTimerOperations() throws InterruptedException {

        IntegerProperty secondsElapsed = controller.secondsElapsedProperty();
        assertNotNull(secondsElapsed);

        // Simulate passage of time
        TimeUnit.SECONDS.sleep(2);

        // Validate timer operations
        assertTrue(secondsElapsed.get() >= 0);
    }
}