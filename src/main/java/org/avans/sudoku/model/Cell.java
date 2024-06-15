package org.avans.sudoku.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cell {

    public static final int EMPTY_CELL = 0;
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final ListProperty<Integer> hulpValue = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final List<ValidatorGroup> validatorGroups = new ArrayList<>();
    private boolean set = false;

    public Cell() {
        this(EMPTY_CELL);
    }

    public Cell(int value) {
        this.setValue(value);
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

    public void addValidator(ValidatorGroup validatorGroup) {
        if (validatorGroup.showVisual() && validatorGroups.stream().anyMatch(ValidatorGroup::showVisual)) {
            return;
        }
        this.validatorGroups.add(validatorGroup);
    }

    public Optional<ValidatorGroup> getVisualValidatorGroup() {
        return this.validatorGroups.stream().filter(ValidatorGroup::showVisual).findFirst();
    }

    public List<ValidatorGroup> getValidatorGroups() {
        return validatorGroups;
    }
}
