package org.avans.sudoko.view.menubar;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokuController;
import org.avans.sudoko.factory.SudokuFactory;
import org.avans.sudoko.model.Sudoku;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SudokuFileChooser {

    private final SudokuController sudokoController;
    private FileChooser fileChooser;

    public SudokuFileChooser(SudokuController controller) {
        this.sudokoController = controller;
        this.fileChooser = new FileChooser();
        this.configureFileChooser();
    }

    private void configureFileChooser() {
        this.fileChooser.setTitle("Open Sudoku File");

        SudokuFactory factory = SudokuFactory.getInstance();
        List<String> extensions = factory.getSupportedExtensions();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Sudoku Files",
                extensions.stream().map(ext -> "*" + ext).toArray(String[]::new));
        this.fileChooser.getExtensionFilters().add(extFilter);
    }

    public void openSudokuFile(Stage stage) {
        File file = this.fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

                SudokuFactory factory = SudokuFactory.getInstance();
                Sudoku sudoku = factory.parseSudoko(file.getName(), content);
                this.sudokoController.startGame(sudoku);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
