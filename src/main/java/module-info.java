module Sudoko {

    requires javafx.fxml;
    requires javafx.controls;
    requires jdk.jconsole;
    requires java.naming;
    opens org.avans.sudoku to javafx.graphics;
    exports org.avans.sudoku;

}