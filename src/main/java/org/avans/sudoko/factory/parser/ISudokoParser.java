package org.avans.sudoko.factory.parser;

import org.avans.sudoko.model.Sudoko;

public interface ISudokoParser {

    public Sudoko parse(String text);

}
