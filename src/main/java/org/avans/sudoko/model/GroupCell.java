package org.avans.sudoko.model;

import java.util.ArrayList;
import java.util.List;

public class GroupCell {
    private final List<Object> children;

    public GroupCell() {
        this.children = new ArrayList<>();
    }

    public void add(Object component) {
        children.add(component);
    }
}
