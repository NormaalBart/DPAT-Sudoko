package org.avans.sudoko.timer;

import java.util.ArrayList;
import java.util.List;

public class TimerComposite implements ITimerComponent {
    private final List<ITimerComponent> children = new ArrayList<>();

    public void add(ITimerComponent component) {
        children.add(component);
    }

    public void remove(ITimerComponent component) {
        children.remove(component);
    }

    @Override
    public void accept(ITimerVisitor visitor) {
        for (ITimerComponent child : children) {
            child.accept(visitor);
        }
    }
}
