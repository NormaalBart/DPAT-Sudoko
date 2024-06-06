module Sudoko {

    requires javafx.fxml;
    requires javafx.controls;
    opens org.avans.sudoko to javafx.graphics;
    exports org.avans.sudoko;

}