package org.avans.sudoko.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.avans.sudoko.controller.SudokoController;

public class SudokoMenuBar extends MenuBar {

    public SudokoMenuBar(SudokoController controller) {
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Sudoku openen");
        openItem.setOnAction(event -> controller.loadSudoku());
        fileMenu.getItems().add(openItem);
        this.getStyleClass().add("menu-bar");
        this.getMenus().add(fileMenu);

    }
}
