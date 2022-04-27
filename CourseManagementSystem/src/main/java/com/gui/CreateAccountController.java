package com.gui;

import com.consts.FileNames;
import com.io.IOManager;
import com.models.User;
import com.models.Users;
import com.models.enums.UserType;
import com.security.StringHash;
import com.security.StringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class CreateAccountController {
    private final IOManager ioManager;
    private final StringHash stringHash;
    @FXML
    private ToggleGroup userTypeGroup;
    private UserType selectedUserType;
    @FXML
    private TextField forenameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordConfirmField;

    public CreateAccountController() {
        this.ioManager = new IOManager();
        this.stringHash = new StringHash();
    }

    @FXML
    public void initialize() {
        this.userTypeGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            RadioButton activeButton = (RadioButton)userTypeGroup.getSelectedToggle();

            if (activeButton != null) {
                String activeButtonText = activeButton.getText();

                if (Objects.equals(activeButtonText, "Student")) {
                    this.selectedUserType = UserType.Student;
                } else if (Objects.equals(activeButtonText, "Instructor")) {
                    this.selectedUserType = UserType.Instructor;
                } else if (Objects.equals(activeButtonText, "CourseAdmin")) {
                    this.selectedUserType = UserType.CourseAdmin;
                }
            }
        });
    }

    @FXML
    public void createAccount(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        ArrayList<String> rawUsers = this.ioManager.readFile(FileNames.Users);
        Users users = new Users();
        users.processRawUsers(rawUsers);

        String forename = this.forenameField.getText();
        if (forename.length() == 0) {
            AlertBox.display("Error", "You can't leave the forename field blank.");
            return;
        } else if (forename.contains(" ")) {
            AlertBox.display("Error", "You can't have whitespace characters in the forename field.");
            return;
        }

        String surname = this.surnameField.getText();
        if (surname.length() == 0) {
            AlertBox.display("Error", "You can't leave the surname field blank.");
            return;
        } else if (forename.contains(" ")) {
            AlertBox.display("Error", "You can't have whitespace characters in the surname field.");
            return;
        }

        String username = this.usernameField.getText();
        if (username.length() == 0) {
            AlertBox.display("Error", "You can't leave the username field blank.");
            return;
        } else if (forename.contains(" ")) {
            AlertBox.display("Error", "You can't have whitespace characters in the username field.");
            return;
        }

        String email = this.emailField.getText();
        if (email.length() == 0) {
            AlertBox.display("Error", "You can't leave the email field blank.");
            return;
        }

        if (!StringValidator.isValidEmail(email)) {
            AlertBox.display("Error", "Invalid email address");
            this.emailField.setText("");
            return;
        }

        User existingUserByUsername = users.findUser(username);
        User existingUserByEmail = users.findUser(email);
        if (existingUserByUsername != null) {
            AlertBox.display("Error", "You cannot create a user with this username as it already exists.");
            this.forenameField.setText("");
            this.surnameField.setText("");
            this.emailField.setText("");
            this.usernameField.setText("");
            this.passwordField.setText("");
            this.passwordConfirmField.setText("");
            return;
        } else if (existingUserByEmail != null) {
            AlertBox.display("Error", "You cannot create a user with this email as it already exists.");
            this.forenameField.setText("");
            this.surnameField.setText("");
            this.emailField.setText("");
            this.usernameField.setText("");
            this.passwordField.setText("");
            this.passwordConfirmField.setText("");
            return;
        }

        String password = this.passwordField.getText();

        if (!Objects.equals(password, this.passwordConfirmField.getText())) {
            AlertBox.display("Error", "Password's don't match! Please make sure they match.");
            this.passwordField.setText("");
            this.passwordConfirmField.setText("");
            return;
        }

        if (!StringValidator.isValidPassword(password)) {
            AlertBox.display("Error", "Password must contain a number, lower case letter, upper case letter, contain no whitespace and be between 8 and 64 characters long");
            this.passwordField.setText("");
            this.passwordConfirmField.setText("");
            return;
        }

        String hashedPassword = this.stringHash.hash(password);

        User user = new User(this.selectedUserType, forename, surname, email, username, hashedPassword);
        users.addUser(user);
        ArrayList<String> updatedUsers = users.getStringUsers();
        this.ioManager.writeFile(updatedUsers, FileNames.Users);
        this.goBack();
    }

    @FXML
    public void goBack() {
        Navigator.navigateBack();
    }
}
