package org.avans.sudoko.factory;

import org.avans.sudoko.factory.parser.FourxFourSudokuParser;
import org.avans.sudoko.factory.parser.ISudokuParser;
import org.avans.sudoko.model.Sudoku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuFactory {

    private static SudokuFactory instance;
    private Map<String, ISudokuParser> parsers;

    private SudokuFactory() {
        parsers = new HashMap<>();
        // Register parsers
        parsers.put(".4x4", new FourxFourSudokuParser());
        // Voeg hier andere parsers toe
        // parsers.put(".6x6", new SixxSixSudokoParser());
        // parsers.put(".9x9", new NinexNineSudokoParser());
        // parsers.put(".jigsaw", new JigsawSudokoParser());
        // parsers.put(".samurai", new SamuraiSudokoParser());
    }

    public static SudokuFactory getInstance() {
        if (instance == null) {
            instance = new SudokuFactory();
        }
        return instance;
    }

    public List<String> getSupportedExtensions() {
        return List.copyOf(parsers.keySet());
    }

    public Sudoku parseSudoko(String fileName, String content) {
        for (String ext : parsers.keySet()) {
            if (fileName.endsWith(ext)) {
                ISudokuParser parser = parsers.get(ext);
                return parser.parse(content);
            }
        }
        throw new UnsupportedOperationException("Unsupported file extension: " + fileName);
    }
}
