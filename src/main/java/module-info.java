module Sudoko {

    requires javafx.fxml;
    requires javafx.controls;
    requires jdk.jconsole;
    opens org.avans.sudoko to javafx.graphics;
    exports org.avans.sudoko;

}