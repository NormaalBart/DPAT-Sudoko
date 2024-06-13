package org.avans.sudoku.timer;

public class ResetTimerVisitor implements ITimerVisitor {
    @Override
    public void visit(SimpleTimer timer) {
        timer.reset();
    }
}
