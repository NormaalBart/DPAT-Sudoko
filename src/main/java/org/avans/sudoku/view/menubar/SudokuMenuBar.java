package org.avans.sudoku.view.menubar;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.avans.sudoku.controller.SudokuController;

public class SudokuMenuBar extends MenuBar {

    public SudokuMenuBar() {
        Menu fileMenu = new Menu("Sudoku");

        MenuItem openItem = new MenuItem("Sudoku openen");

        openItem.setOnAction(event -> {
            new SudokuFileChooser().openSudokuFile((Stage) this.getScene().getWindow());
        });

        fileMenu.getItems().add(openItem);

        CheckMenuItem toggleCheck = new CheckMenuItem("Controlleer getal");

        toggleCheck.selectedProperty().bindBidirectional(SudokuController.getInstance().checkProperty());
        fileMenu.getItems().add(toggleCheck);


        this.getMenus().add(fileMenu);
    }
}
