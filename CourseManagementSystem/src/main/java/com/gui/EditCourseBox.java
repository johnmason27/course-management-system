package com.gui;

import com.models.Course;
import com.models.Courses;
import com.models.Module;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.UUID;

public class EditCourseBox {
    private static TextField courseNameInput;
    private static Stage window;
    private static TableView<Module> moduleTable;
    private static TextField moduleNameInput;
    private static TextField moduleLevelInput;

    public static void display(Course existingCourse) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Course");
        window.setMinWidth(800);
        window.setMinHeight(600);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        backButton.setOnAction(e -> window.close());
        HBox topContainer = new HBox();
        topContainer.getChildren().add(backButton);
        topContainer.setAlignment(Pos.TOP_LEFT);

        Label editModalTitle = new Label("Edit Course");
        editModalTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 22px;");
        courseNameInput = new TextField(existingCourse.getCourseName());
        courseNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        Button editCourseNameButton = new Button("Edit Name");
        editCourseNameButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        editCourseNameButton.setOnAction(e -> editCourseNameButtonClicked(existingCourse));

        HBox editCourseNameItems = new HBox();
        editCourseNameItems.getChildren().addAll(courseNameInput, editCourseNameButton);
        editCourseNameItems.setPadding(new Insets(10, 10, 10, 10));
        editCourseNameItems.setSpacing(10);
        editCourseNameItems.setAlignment(Pos.CENTER);

        TableColumn<Module, UUID> moduleUUIDTableColumn = new TableColumn<>("Id");
        moduleUUIDTableColumn.setMinWidth(300);
        moduleUUIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Module, String> moduleNameTableColumn = new TableColumn<>("Module Name");
        moduleNameTableColumn.setMinWidth(300);
        moduleNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        TableColumn<Module, Integer> moduleLevelTableColumn = new TableColumn<>("Module Level");
        moduleLevelTableColumn.setMinWidth(50);
        moduleLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        TableColumn<Module, Boolean> moduleIsRunningTableColumn = new TableColumn<>("Module Running");
        moduleIsRunningTableColumn.setCellValueFactory(new PropertyValueFactory<>("isModuleRunning"));
        moduleTable = new TableView<>(FXCollections.observableList(existingCourse.getCourseModules()));
        moduleTable.getStylesheets().add(String.valueOf(EditCourseBox.class.getResource("/com/styles/table-styles.css")));
        moduleTable.getColumns().addAll(moduleUUIDTableColumn, moduleNameTableColumn, moduleLevelTableColumn, moduleIsRunningTableColumn);
        moduleTable.setPlaceholder(new Label("No Modules"));

        moduleNameInput = new TextField();
        moduleNameInput.setPromptText("Module name...");
        moduleNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        moduleLevelInput = new TextField();
        moduleLevelInput.setPromptText("Module level...");
        moduleLevelInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        Button addModuleButton = new Button("Add Module");
        addModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        addModuleButton.setOnAction(e -> addModuleButtonClicked(existingCourse));
        Button editModuleButton = new Button("Edit Module");
        editModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        editModuleButton.setOnAction(e -> editModuleButtonClicked(existingCourse));
        Button toggleModuleRunningButton = new Button("Toggle Module Running");
        toggleModuleRunningButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        toggleModuleRunningButton.setOnAction(e -> toggleModuleRunningButtonClicked(existingCourse));
        Button removeModuleButton = new Button("Remove Module");
        removeModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        removeModuleButton.setOnAction(e -> removeModuleButtonClicked(existingCourse));

        HBox moduleEditItems = new HBox();
        moduleEditItems.getChildren().addAll(moduleNameInput, moduleLevelInput, addModuleButton, editModuleButton, toggleModuleRunningButton, removeModuleButton);
        moduleEditItems.setPadding(new Insets(10, 10, 10, 10));
        moduleEditItems.setSpacing(10);
        moduleEditItems.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(topContainer, editModalTitle, editCourseNameItems, moduleTable, moduleEditItems);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #171717;");
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        Courses.updateCourseById(existingCourse);
        courseNameInput.clear();
        moduleNameInput.clear();
        moduleLevelInput.clear();
    }

    private static void editCourseNameButtonClicked(Course existingCourse) {
        String courseName = courseNameInput.getText();

        if (courseName.length() == 0) {
            AlertBox.display("Error", "Enter a course name!");
            courseNameInput.clear();
        } else if (!Objects.equals(existingCourse.getCourseName(), courseName) &&
                Courses.findCourse(courseName) != null) {
            AlertBox.display("Error", "A course already exists with that name!");
            courseNameInput.clear();
        } else {
            existingCourse.setCourseName(courseName);
            Courses.updateCourseById(existingCourse);
            AlertBox.display("Success", "Course name edited!");
        }
    }

    private static void addModuleButtonClicked(Course existingCourse) {
        String moduleName = moduleNameInput.getText();
        if (moduleName.length() == 0) {
            AlertBox.display("Error", "Module name can't be empty.");
            return;
        }

        Module existingModule = moduleTable.getItems().stream()
                .filter(m -> m.getModuleName().equals(moduleName))
                .findAny()
                .orElse(null);

        if (existingModule != null) {
            AlertBox.display("Error", "Module name already exists.");
            return;
        }

        int moduleLevel;

        try {
            moduleLevel = Integer.parseInt(moduleLevelInput.getText());
        } catch (NumberFormatException e) {
            AlertBox.display("Error", "Module level can't be a string character or empty, must be either 4, 5, 6.");
            return;
        }

        if (moduleLevel != 4 && moduleLevel != 5 && moduleLevel != 6) {
            AlertBox.display("Error", "Module level must be either 4, 5 or 5.");
            return;
        }

        Module newModule = new Module(UUID.randomUUID(), moduleName, true, moduleLevel);
        existingCourse.addModule(newModule);
        moduleNameInput.clear();
        moduleLevelInput.clear();
        moduleTable.refresh();
    }

    private static void editModuleButtonClicked(Course existingCourse) {
        Module selectedModule = moduleTable.getSelectionModel().getSelectedItem();
        if (selectedModule == null) {
            AlertBox.display("Error", "Please select a module to edit!");
            return;
        }

        String moduleName = moduleNameInput.getText();
        if (moduleName.length() == 0) {
            AlertBox.display("Error", "Module name can't be empty.");
            return;
        }

        Module existingModule = moduleTable.getItems().stream()
                .filter(m -> m.getModuleName().equals(moduleName))
                .findAny()
                .orElse(null);

        if (existingModule != null) {
            AlertBox.display("Error", "Module name already exists.");
            return;
        }

        int moduleLevel;

        try {
            moduleLevel = Integer.parseInt(moduleLevelInput.getText());
        } catch (NumberFormatException e) {
            AlertBox.display("Error", "Module level can't be a string character or empty, must be either 4, 5, 6.");
            return;
        }

        if (moduleLevel != 4 && moduleLevel != 5 && moduleLevel != 6) {
            AlertBox.display("Error", "Module level must be either 4, 5 or 5.");
            return;
        }

        Module newModule = new Module(selectedModule.getId(), moduleName, selectedModule.getIsModuleRunning(), moduleLevel);
        existingCourse.removeModule(selectedModule);
        existingCourse.addModule(newModule);
        moduleNameInput.clear();
        moduleLevelInput.clear();
        moduleTable.refresh();
    }

    private static void toggleModuleRunningButtonClicked(Course existingCourse) {
        Module selectedModule = moduleTable.getSelectionModel().getSelectedItem();
        if (selectedModule == null) {
            AlertBox.display("Error", "Please select a module to edit!");
            return;
        }

        existingCourse.toggleModuleRunning(selectedModule.getId(), !selectedModule.getIsModuleRunning());
        moduleTable.refresh();
    }

    private static void removeModuleButtonClicked(Course existingCourse) {
        Module selectedModule = moduleTable.getSelectionModel().getSelectedItem();
        if (selectedModule == null) {
            AlertBox.display("Error", "Please select a module to edit!");
            return;
        }

        existingCourse.removeModule(selectedModule);
        moduleTable.refresh();
    }
}
