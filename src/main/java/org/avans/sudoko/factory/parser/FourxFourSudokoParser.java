package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Sudoko;
import org.avans.sudoko.model.Cell;
import org.avans.sudoko.model.GroupCell;

public class FourxFourSudokoParser implements ISudokoParser {

    @Override
    public Sudoko parse(String text) {
        if (text.length() != 16) {
            throw new IllegalArgumentException("Invalid input for 4x4 Sudoku.");
        }

        Sudoko sudoko = new Sudoko(4);
        for (int i = 0; i < text.length(); i++) {
            int value = Character.getNumericValue(text.charAt(i));
            int x = i / 4;
            int y = i % 4;
            sudoko.setValue(x, y, value);
        }

        return sudoko;
    }
}
