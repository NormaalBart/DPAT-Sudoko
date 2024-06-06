package org.avans.sudoko.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.avans.sudoko.controller.MainController;

public class MainView {
    private VBox view;

    public MainView() {
        TextField inputField = new TextField();
        Button submitButton = new Button("Submit");
        Label displayLabel = new Label();

        view = new VBox(10, inputField, submitButton, displayLabel);

        // Initialize the controller with view components
        new MainController(inputField, submitButton, displayLabel);
    }

    public VBox getView() {
        return view;
    }
}
