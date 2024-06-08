package org.avans.sudoko.view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.avans.sudoko.controller.SudokoController;

public class SudokoMenuBar extends MenuBar {

    public SudokoMenuBar(SudokoController controller) {
        System.out.println("SudokoMenuBar");
        Menu fileMenu = new Menu("File");

        MenuItem openItem = new MenuItem("Sudoko openen");

        openItem.setOnAction(event -> {
            new SudokoFileChooser().openSudokuFile((Stage) this.getScene().getWindow());
        });

        fileMenu.getItems().add(openItem);
        this.getMenus().add(fileMenu);
    }
}
