package com.gui;

import com.models.User;
import com.models.Users;
import com.models.enums.UserType;
import com.security.StringHash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class LoginController {
    private final StringHash stringHash;
    @FXML
    private TextField usernameEmailField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        this.stringHash = new StringHash();
    }

    @FXML
    public void login(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        User user = Users.findUser(this.usernameEmailField.getText());
        if (user == null) {
            AlertBox.display("Error", "Incorrect username or email.");
            this.usernameEmailField.setText("");
            this.passwordField.setText("");
            return;
        }

        String hashedPassword = this.stringHash.hash(this.passwordField.getText());
        if (!Objects.equals(user.getPassword(), hashedPassword)) {
            AlertBox.display("Error", "Incorrect password.");
            this.passwordField.setText("");
        } else {
            Session.setUser(user);
            if (user.getUserType() == UserType.Student) {
                Navigator.navigateToStudentView(event);
            } else if (user.getUserType() == UserType.CourseAdmin) {
                Navigator.navigateToCourseAdministratorView(event);
            } else if (user.getUserType() == UserType.Instructor) {
                Navigator.navigateToInstructorView(event);
            }
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
