package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class Navigator {
    private static Stage stage;
    private static Scene scene;
    private static Scene previousScene;

    private Navigator(){}

    public static void navigateToStudentView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource("/com/gui/main-student-view.fxml"));
        Navigator.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigator.scene = new Scene(root);
        Navigator.previousScene = Navigator.stage.getScene();
        Navigator.stage.setScene(Navigator.scene);
        Navigator.stage.show();
    }

    public static void navigateToCreateAccountView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource("/com/gui/create-account-view.fxml"));
        Navigator.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigator.scene = new Scene(root);
        Navigator.previousScene = Navigator.stage.getScene();
        Navigator.stage.setScene(Navigator.scene);
        Navigator.stage.show();
    }

    public static void navigateToForgotPasswordView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Navigator.class.getResource("/com/gui/forgot-password-view.fxml"));
        Navigator.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigator.scene = new Scene(root);
        Navigator.previousScene = Navigator.stage.getScene();
        Navigator.stage.setScene(Navigator.scene);
        Navigator.stage.show();
    }

    public static void navigateBack() {
        Navigator.stage.setScene(Navigator.previousScene);
        Navigator.stage.show();
    }
}

