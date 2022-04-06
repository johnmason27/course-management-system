package com.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameInput;
    @FXML
    private Button submitButton;

    @FXML
    public void onSubmit() {
        System.out.println("Submitted name");
        System.out.println(this.nameInput.getText());
    }
}
