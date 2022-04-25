package com.gui;

import com.consts.FileNames;
import com.io.IOManager;
import com.models.Users;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ForgotPasswordController {
    private final IOManager ioManager;
    @FXML
    private TextField emailUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    public ForgotPasswordController() {
        this.ioManager = new IOManager();
    }

    @FXML
    public void resetPassword() throws IOException {
        if (!Objects.equals(this.passwordField.getText(), this.confirmPasswordField.getText())) {
            AlertBox.display("Error", "Password's don't match! Please make sure they match.");
            this.passwordField.setText("");
            this.confirmPasswordField.setText("");
        } else {
            ArrayList<String> usersData = this.ioManager.readFile(FileNames.Users);
            Users users = new Users();
            users.processRawUsers(usersData);
            this.ioManager.writeFile(usersData, FileNames.Users);
            AlertBox.display("Success", "Password reset.");
        }
    }

    @FXML
    public void goBack() {
        Navigator.navigateBack();
    }
}
