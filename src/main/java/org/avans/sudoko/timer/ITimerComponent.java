package org.avans.sudoko.timer;

public interface ITimerComponent {
    void accept(ITimerVisitor visitor);
}
