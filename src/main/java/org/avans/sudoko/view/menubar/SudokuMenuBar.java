package org.avans.sudoko.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokuController;

public class SudokuMenuBar extends MenuBar {

    public SudokuMenuBar(SudokuController controller) {
        Menu fileMenu = new Menu("File");

        MenuItem openItem = new MenuItem("Sudoku openen");

        openItem.setOnAction(event -> {
            new SudokuFileChooser(controller).openSudokuFile((Stage) this.getScene().getWindow());
        });

        fileMenu.getItems().add(openItem);
        this.getMenus().add(fileMenu);
    }
}