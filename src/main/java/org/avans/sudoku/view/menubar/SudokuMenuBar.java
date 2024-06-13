package org.avans.sudoku.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class SudokuMenuBar extends MenuBar {

    public SudokuMenuBar() {
        Menu fileMenu = new Menu("File");

        MenuItem openItem = new MenuItem("Sudoku openen");

        openItem.setOnAction(event -> {
            new SudokuFileChooser().openSudokuFile((Stage) this.getScene().getWindow());
        });

        fileMenu.getItems().add(openItem);
        this.getMenus().add(fileMenu);
    }
}