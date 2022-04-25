package com.gui;

import com.consts.FileNames;
import com.io.IOManager;
import com.models.User;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {
    private final Users users;
    private final IOManager ioManager;
    @FXML
    private TextField usernameEmailField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        this.users = new Users();
        this.ioManager = new IOManager();
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        ArrayList<String> users = this.ioManager.readFile(FileNames.Users);
        this.users.processRawUsers(users);

        User user = this.users.findUser(this.usernameEmailField.getText());
        if (user == null) {
            AlertBox.display("Error", "Incorrect username or email.");
            this.usernameEmailField.setText("");
            this.passwordField.setText("");
            return;
        }

        if (!Objects.equals(user.getPassword(), this.passwordField.getText())) {
            AlertBox.display("Error", "Incorrect password.");
            this.passwordField.setText("");
        } else {
            Navigator.navigateToStudentView(event);
        }
    }

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        Navigator.navigateToCreateAccountView(event);
    }

    @FXML
    public void forgotPassword(ActionEvent event) throws IOException {
        Navigator.navigateToForgotPasswordView(event);
    }
}
