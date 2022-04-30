package com.gui;

import com.models.Course;
import com.models.Courses;
import com.models.Module;
import com.models.Modules;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

public class AddCourseBox {
    private static Course course = null;

    public static Course display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Course");
        window.setMinWidth(800);
        window.setMinHeight(600);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        backButton.setOnAction(e -> {
            window.close();
        });
        HBox topContainer = new HBox();
        topContainer.getChildren().add(backButton);
        topContainer.setAlignment(Pos.TOP_LEFT);
        topContainer.setPadding(new Insets(20));

        Label modalTitle = new Label("Add Course");
        modalTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 22px;");
        Label courseNameLabel = new Label("Course Name");
        courseNameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        TextField courseNameInput = new TextField();
        courseNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");

        Button addRemoveModulesButton = new Button("Add/Remove modules");
        addRemoveModulesButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        addRemoveModulesButton.setOnAction(e -> {
           AddRemoveModulesBox.display();
        });

        Button createCourseButton = new Button("Create Course");
        createCourseButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        createCourseButton.setOnAction(e -> {
            String courseName = courseNameInput.getText();

            if (courseName.length() == 0) {
                AlertBox.display("Error", "Enter a course name");
                courseNameInput.setText("");
            } else if (Courses.findCourse(courseName) != null) {
                AlertBox.display("Error", "A course already exists with that name");
                courseNameInput.setText("");
            } else {
                ArrayList<Module> modules = new ArrayList<>();
                if (Session.cachedModules != null) {
                    modules = Session.cachedModules;

                    for (Module module :
                            modules) {
                        Modules.addModule(module);
                    }

                    Modules.saveModules();
                }

                course = new Course(UUID.randomUUID(), courseName, modules);
                AlertBox.display("Success", "Course created");
                window.close();
            }
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(topContainer, modalTitle, courseNameLabel, courseNameInput, addRemoveModulesButton, createCourseButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #171717;");
        layout.setSpacing(10);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return course;
    }
}
