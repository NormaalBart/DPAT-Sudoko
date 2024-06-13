package org.avans.sudoku.timer;

public class StopTimerVisitor implements ITimerVisitor {
    @Override
    public void visit(SimpleTimer timer) {
        timer.stop();
    }
}
