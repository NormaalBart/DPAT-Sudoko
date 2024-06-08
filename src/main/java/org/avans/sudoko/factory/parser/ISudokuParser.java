package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Sudoku;

public interface ISudokuParser {

    public Sudoku parse(String text);

}
