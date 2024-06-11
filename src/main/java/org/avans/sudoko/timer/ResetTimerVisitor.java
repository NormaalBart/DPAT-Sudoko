package org.avans.sudoko.timer;

public class ResetTimerVisitor implements ITimerVisitor {
    @Override
    public void visit(SimpleTimer timer) {
        timer.reset();
    }
}
