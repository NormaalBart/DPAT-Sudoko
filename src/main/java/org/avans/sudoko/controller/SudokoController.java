package org.avans.sudoko.controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.avans.sudoko.model.Sudoko;

import java.util.Timer;
import java.util.TimerTask;

public class SudokoController {
    private Sudoko model;
    private IntegerProperty secondsElapsed;
    private Timer timer;

    public SudokoController() {
        this.secondsElapsed = new SimpleIntegerProperty(0);
        timer = new Timer(true);
        startTimer();
    }

    public void loadSudoku() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Sudoku File");
//        File file = fileChooser.showOpenDialog(null);
//
//        if (file != null) {
//            try {
//                model = FileReaderFactory.createReader(file).read(file);
//                view.getFileContentArea().setText(model.toString());
//                updateGrid();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void updateGrid() {
//        view.getSudokuGrid().getChildren().clear();
        // Logica om de grid te updaten met de Sudoku data
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    secondsElapsed.set(secondsElapsed.get() + 1);
                });
            }
        }, 1000, 1000);
    }

    public IntegerProperty getSecondsElapsed() {
        return secondsElapsed;
    }
}
