package org.avans.sudoko.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.avans.sudoko.model.DataModel;

public class MainController {
    private TextField inputField;
    private Button submitButton;
    private Label displayLabel;

    private DataModel model;

    public MainController(TextField inputField, Button submitButton, Label displayLabel) {
        this.inputField = inputField;
        this.submitButton = submitButton;
        this.displayLabel = displayLabel;
        this.model = new DataModel();

        initialize();
    }

    private void initialize() {
        submitButton.setOnAction(event -> updateLabel());
    }

    private void updateLabel() {
        String input = inputField.getText();
        model.setData(input);
        displayLabel.setText(model.getData());
    }
}
