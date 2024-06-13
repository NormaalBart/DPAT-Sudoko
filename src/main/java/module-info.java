module Sudoko {

    requires javafx.fxml;
    requires javafx.controls;
    requires jdk.jconsole;
    requires java.naming;
    requires org.junit.jupiter.api;
    opens org.avans.sudoko to javafx.graphics;
    exports org.avans.sudoko;

}