package com.gui;

import com.models.Course;
import com.models.Courses;
import com.models.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

public class AddCourseBox {
    private static ObservableList<Module> observableModules = FXCollections.observableArrayList();
    private static TextField courseNameInput;
    private static TextField moduleNameInput;
    private static TextField moduleLevelInput;
    private static TableView<Module> moduleTable;
    private static Course course = null;
    private static Stage window;

    public static Course display() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Course");
        window.setMinWidth(800);
        window.setMinHeight(600);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        backButton.setOnAction(e -> window.close());
        HBox topContainer = new HBox();
        topContainer.getChildren().add(backButton);
        topContainer.setAlignment(Pos.TOP_LEFT);

        Label addModalTitle = new Label("Add Course");
        addModalTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 22px;");
        courseNameInput = new TextField();
        courseNameInput.setPromptText("Course Name...");
        courseNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");

        TableColumn<Module, UUID> moduleUUIDTableColumn = new TableColumn<>("Id");
        moduleUUIDTableColumn.setMinWidth(300);
        moduleUUIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Module, String> moduleNameTableColumn = new TableColumn<>("Module name");
        moduleNameTableColumn.setMinWidth(400);
        moduleNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        TableColumn<Module, Integer> moduleLevelTableColumn = new TableColumn<>("Module Level");
        moduleLevelTableColumn.setMinWidth(100);
        moduleLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        moduleTable = new TableView<>();
        moduleTable.getStylesheets().add(String.valueOf(AddCourseBox.class.getResource("/com/styles/table-styles.css")));
        moduleTable.setItems(FXCollections.observableList(observableModules));
        moduleTable.getColumns().addAll(moduleUUIDTableColumn, moduleNameTableColumn, moduleLevelTableColumn);
        moduleTable.setPlaceholder(new Label("No modules"));

        moduleNameInput = new TextField();
        moduleNameInput.setPromptText("Module name...");
        moduleNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        moduleLevelInput = new TextField();
        moduleLevelInput.setPromptText("Module level...");
        moduleLevelInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        Button addModuleButton = new Button("Add");
        addModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        addModuleButton.setOnAction(e -> addModuleButtonClicked());
        Button removeModuleButton = new Button("Remove");
        removeModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        removeModuleButton.setOnAction(e -> removeModuleButtonClicked());

        HBox moduleInputItems = new HBox();
        moduleInputItems.getChildren().addAll(moduleNameInput, moduleLevelInput, addModuleButton, removeModuleButton);
        moduleInputItems.setPadding(new Insets(10, 10, 10, 10));
        moduleInputItems.setSpacing(10);
        moduleInputItems.setAlignment(Pos.CENTER);

        Button createCourseButton = new Button("Create Course");
        createCourseButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        createCourseButton.setOnAction(e -> createCourseButtonClicked());

        VBox layout = new VBox();
        layout.getChildren().addAll(topContainer, addModalTitle, courseNameInput, moduleTable, moduleInputItems, createCourseButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #171717;");
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        observableModules = FXCollections.observableArrayList();
        moduleNameInput.clear();
        moduleLevelInput.clear();
        Course returnData = course;
        course = null;
        return returnData;
    }

    private static void createCourseButtonClicked() {
        String courseName = courseNameInput.getText();

        if (courseName.length() == 0) {
            AlertBox.display("Error", "Enter a course name");
            courseNameInput.clear();
        } else if (Courses.findCourse(courseName) != null) {
            AlertBox.display("Error", "A course already exists with that name");
            courseNameInput.clear();
        } else {
            ArrayList<Module> modules = new ArrayList<>(observableModules);
            course = new Course(UUID.randomUUID(), courseName, modules, true);
            AlertBox.display("Success", "Course created");
            window.close();
        }
    }

    private static void addModuleButtonClicked() {
        String moduleName = moduleNameInput.getText();
        Module existingModule = moduleTable.getItems().stream()
                .filter(i -> i.getModuleName().equals(moduleName))
                .findAny()
                .orElse(null);

        if (moduleName.length() == 0) {
            AlertBox.display("Error", "Module name can't be empty!");
            return;
        } else if (existingModule != null) {
            AlertBox.display("Error", "Module name already added");
            return;
        }

        int moduleLevel;

        try {
            moduleLevel = Integer.parseInt(moduleLevelInput.getText());
        } catch (NumberFormatException e) {
            AlertBox.display("Error", "Module level can't be a string or empty, must be either 4, 5 or 6");
            return;
        }

        if (moduleLevel != 4 && moduleLevel != 5 && moduleLevel != 6) {
            AlertBox.display("Error", "Module level must be either 4, 5 or 6");
            return;
        }

        Module module = new Module(UUID.randomUUID(), moduleName, true, moduleLevel);
        moduleTable.getItems().add(module);
        moduleNameInput.clear();
        moduleLevelInput.clear();
    }

    private static void removeModuleButtonClicked() {
        Module selectedItem = moduleTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a module to remove!");
            return;
        }
        moduleTable.getItems().remove(selectedItem);
    }
}
