package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Sudoko;

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
            if(value != 0) {
                sudoko.setValue(x, y, value, true);
            }
        }

        return sudoko;
    }
}
