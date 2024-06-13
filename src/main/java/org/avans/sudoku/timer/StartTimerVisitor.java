package org.avans.sudoku.timer;

public class StartTimerVisitor implements ITimerVisitor {
    @Override
    public void visit(SimpleTimer timer) {
        timer.start();
    }
}
