package org.avans.sudoko.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cell {

    private boolean set = false;

    private IntegerProperty value = new SimpleIntegerProperty();
    private ListProperty<Integer> hulpValue = new SimpleListProperty<>(FXCollections.observableArrayList());

    public Cell(int value) {
        this.value.set(value);
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.setValue(value, false);
    }

    public void setValue(int value, boolean set) {
        this.value.set(value);
        this.set = set;
    }

    public boolean isSet() {
        return set;
    }

    public ObservableList<Integer> getHulpValue() {
        return hulpValue.get();
    }

    public void toggleHulpValue(int number) {
        if (hulpValue.contains(number)) {
            hulpValue.remove(Integer.valueOf(number));
        } else {
            hulpValue.add(number);
        }
    }

    public ListProperty<Integer> hulpValueProperty() {
        return hulpValue;
    }
}
