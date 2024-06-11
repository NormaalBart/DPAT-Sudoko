package org.avans.sudoko.model;

public class Sudoku {

    private final int size;
    private final Cell[][] grid;

    public Sudoku(int size) {
        this.grid = new Cell[size][size];
        this.size = size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Cell(0);
            }
        }
    }

    public Sudoku(int size, Cell[][] grid) {
        this.grid = grid;
        this.size = size;
    }

    public void setValue(int row, int col, int value, boolean set) {
        Cell cell = grid[row][col];
        cell.setValue(value, set);
    }

    public Cell getCell(int row, int col) {
        return this.grid[row][col];
    }

    public int getSize() {
        return this.size;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }
}
