package com.gui;

import com.models.Courses;
import com.models.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/gui/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Course Management System");
        stage.setScene(scene);
        stage.show();
        new Users();
        new Courses();
    }

    public static void main(String[] args) {
        launch();
    }
}
