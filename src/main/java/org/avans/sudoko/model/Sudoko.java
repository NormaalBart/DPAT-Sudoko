package org.avans.sudoko.model;

public class Sudoko {

    private final Cell[][] grid;

    public Sudoko(int size) {
        this.grid = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Cell(0);
            }
        }
    }

    public void setValue(int row, int col, int value) {
        this.grid[row][col].setValue(value);
    }

    public Cell getCell(int row, int col) {
        return this.grid[row][col];
    }

    public int getSize() {
        return grid.length;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }
}
