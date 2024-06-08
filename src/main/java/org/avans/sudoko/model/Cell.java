package org.avans.sudoko.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cell {

    private IntegerProperty value = new SimpleIntegerProperty();

    public Cell(int value) {
        this.value.set(value);
    }

    public IntegerProperty getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
