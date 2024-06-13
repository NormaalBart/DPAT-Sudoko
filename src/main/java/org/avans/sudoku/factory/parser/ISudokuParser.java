package org.avans.sudoku.factory.parser;

import org.avans.sudoku.model.Sudoku;

public interface ISudokuParser {

    public Sudoku parse(String text);

}
