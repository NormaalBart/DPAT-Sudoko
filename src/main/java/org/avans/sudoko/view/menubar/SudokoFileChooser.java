package org.avans.sudoko.view.menubar;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokoController;
import org.avans.sudoko.factory.SudokoFactory;
import org.avans.sudoko.model.Sudoko;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SudokoFileChooser {

    private final SudokoController sudokoController;
    private FileChooser fileChooser;

    public SudokoFileChooser(SudokoController controller) {
        this.sudokoController = controller;
        this.fileChooser = new FileChooser();
        this.configureFileChooser();
    }

    private void configureFileChooser() {
        this.fileChooser.setTitle("Open Sudoku File");

        SudokoFactory factory = SudokoFactory.getInstance();
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

                SudokoFactory factory = SudokoFactory.getInstance();
                Sudoko sudoko = factory.parseSudoko(file.getName(), content);
                this.sudokoController.startGame(sudoko);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
