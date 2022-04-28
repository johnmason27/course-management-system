package com.gui;

import com.models.User;
import com.models.Users;
import com.security.StringHash;
import com.security.StringValidator;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class ForgotPasswordController {
    private final StringHash stringHash;
    @FXML
    private TextField emailUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    public ForgotPasswordController() {
        this.stringHash = new StringHash();
    }

    @FXML
    public void resetPassword() throws IOException, NoSuchAlgorithmException {
        User user = Users.findUser(this.emailUsername.getText());

        if (user == null) {
            AlertBox.display("Error", "Couldn't find an account with that email or username");
            this.emailUsername.setText("");
            this.passwordField.setText("");
            this.confirmPasswordField.setText("");
            return;
        }

        String password = this.passwordField.getText();

        if (!Objects.equals(password, this.confirmPasswordField.getText())) {
            AlertBox.display("Error", "Password's don't match! Please make sure they match.");
            this.passwordField.setText("");
            this.confirmPasswordField.setText("");
            return;
        }

        if (!StringValidator.isValidPassword(password)) {
            AlertBox.display("Error", "Password must contain a number, lower case letter, upper case letter, contain no whitespace and be between 8 and 64 characters long");
            this.passwordField.setText("");
            this.confirmPasswordField.setText("");
            return;
        }

        String hashedPassword = this.stringHash.hash(password);
        user.setPassword(hashedPassword);
        Users.updateUser(user);
        Users.saveUsers();
        AlertBox.display("Success", "Password reset.");
        this.emailUsername.setText("");
        this.passwordField.setText("");
        this.confirmPasswordField.setText("");
        this.goBack();
    }

    @FXML
    public void goBack() {
        Navigator.navigateBack();
    }
}
