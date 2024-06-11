package org.avans.sudoko.timer;

public class StartTimerVisitor implements ITimerVisitor {
    @Override
    public void visit(SimpleTimer timer) {
        timer.start();
    }
}
