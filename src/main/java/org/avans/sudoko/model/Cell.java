package org.avans.sudoko.model;

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

    private boolean set = false;

    private IntegerProperty value = new SimpleIntegerProperty();
    private ListProperty<Integer> hulpValue = new SimpleListProperty<>(FXCollections.observableArrayList());

    private List<ValidatorGroup> validatorGroups = new ArrayList<>();

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

    public void addValidator(ValidatorGroup validatorGroup) {
        if (validatorGroup.showVisual() && validatorGroups.stream().anyMatch(ValidatorGroup::showVisual)) {
            return;
        }
        this.validatorGroups.add(validatorGroup);
    }

    public boolean hasValidator(ValidatorGroup validatorGroup) {
        return this.validatorGroups.contains(validatorGroup);
    }

    public Optional<ValidatorGroup> getVisualValidatorGroup() {
        return this.validatorGroups.stream().filter(ValidatorGroup::showVisual).findFirst();
    }
}
