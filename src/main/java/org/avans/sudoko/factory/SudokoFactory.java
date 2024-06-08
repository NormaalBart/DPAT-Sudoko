package org.avans.sudoko.factory;

import org.avans.sudoko.factory.parser.FourxFourSudokoParser;
import org.avans.sudoko.factory.parser.ISudokoParser;
import org.avans.sudoko.model.Sudoko;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokoFactory {

    private static SudokoFactory instance;
    private Map<String, ISudokoParser> parsers;

    private SudokoFactory() {
        parsers = new HashMap<>();
        // Register parsers
        parsers.put(".4x4", new FourxFourSudokoParser());
        // Voeg hier andere parsers toe
        // parsers.put(".6x6", new SixxSixSudokoParser());
        // parsers.put(".9x9", new NinexNineSudokoParser());
        // parsers.put(".jigsaw", new JigsawSudokoParser());
        // parsers.put(".samurai", new SamuraiSudokoParser());
    }

    public static SudokoFactory getInstance() {
        if (instance == null) {
            instance = new SudokoFactory();
        }
        return instance;
    }

    public List<String> getSupportedExtensions() {
        return List.copyOf(parsers.keySet());
    }

    public Sudoko parseSudoko(String fileName, String content) {
        for (String ext : parsers.keySet()) {
            if (fileName.endsWith(ext)) {
                ISudokoParser parser = parsers.get(ext);
                return parser.parse(content);
            }
        }
        throw new UnsupportedOperationException("Unsupported file extension: " + fileName);
    }
}
