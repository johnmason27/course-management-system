package com.gui;

import com.models.Course;
import com.models.Courses;
import com.models.Module;
import com.models.Modules;
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
import java.util.Objects;
import java.util.UUID;

public class EditCourseBox {
    private static ArrayList<Module> removedModulesCache = new ArrayList<>();
    private static ObservableList<Module> observableModules;
    private static TextField moduleNameInput;
    private static TextField moduleLevelInput;
    private static TableView<Module> table;
    private static Course course = null;

    public static Course display(Course existingCourse) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Course");
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

        Label modalTitle = new Label("Edit Course");
        modalTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 22px;");
        TextField courseNameInput = new TextField();
        courseNameInput.setText(existingCourse.getCourseName());
        courseNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");

        Button editCourseButton = new Button("Edit Course");
        editCourseButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        editCourseButton.setOnAction(e -> {
            String courseName = courseNameInput.getText();

            if (courseName.length() == 0) {
                AlertBox.display("Error", "Enter a course name");
                courseNameInput.setText("");
            } else if (!Objects.equals(existingCourse.getCourseName(), courseName) && Courses.findCourse(courseName) != null) {
                AlertBox.display("Error", "A course already exists with that name");
                courseNameInput.setText("");
            } else {
                ArrayList<Module> updatedModules = new ArrayList<>(observableModules);
                Modules.removeModules(removedModulesCache);
                Modules.addModules(updatedModules);
                course = new Course(existingCourse.getId(), courseName, updatedModules, true);
                AlertBox.display("Success", "Course edited");
                window.close();
            }
        });

        TableColumn<Module, UUID> moduleIdColumn = new TableColumn<>("Id");
        moduleIdColumn.setMinWidth(300);
        moduleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Module, String> moduleNameColumn = new TableColumn<>("Module name");
        moduleNameColumn.setMinWidth(400);
        moduleNameColumn.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        TableColumn<Module, Integer> moduleLevelColumn = new TableColumn<>("Module Level");
        moduleLevelColumn.setMinWidth(100);
        moduleLevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        table = new TableView<>();
        table.getStylesheets().add(String.valueOf(EditCourseBox.class.getResource("/com/styles/table-styles.css")));
        observableModules = FXCollections.observableList(existingCourse.getCourseModules());
        table.setItems(observableModules);
        table.getColumns().addAll(moduleIdColumn, moduleNameColumn, moduleLevelColumn);
        table.setPlaceholder(new Label("No modules"));

        moduleNameInput = new TextField();
        moduleNameInput.setPromptText("Module name...");
        moduleNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        moduleLevelInput = new TextField();
        moduleLevelInput.setPromptText("Module level...");
        moduleLevelInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        Button addModuleButton = new Button("Add");
        addModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        addModuleButton.setOnAction(e -> addModuleButtonClicked());
        Button editModuleButton = new Button("Edit");
        editModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        editModuleButton.setOnAction(e -> editModuleButtonClicked());
        Button toggleModuleStateButton = new Button("Activate/Deactivate Module");
        toggleModuleStateButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        toggleModuleStateButton.setOnAction(e -> toggleModuleStateButtonClicked());
        Button removeModuleButton = new Button("Remove");
        removeModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        removeModuleButton.setOnAction(e -> removeModuleButtonClicked());

        HBox inputContainer = new HBox();
        inputContainer.getChildren().addAll(moduleNameInput, moduleLevelInput, addModuleButton, editModuleButton, toggleModuleStateButton, removeModuleButton);
        inputContainer.setPadding(new Insets(10, 10, 10, 10));
        inputContainer.setSpacing(10);
        inputContainer.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(topContainer, modalTitle, courseNameInput, table, inputContainer, editCourseButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #171717;");
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        observableModules = FXCollections.observableArrayList();
        removedModulesCache = new ArrayList<>();
        moduleNameInput.clear();
        moduleLevelInput.clear();
        Course returnData = course;
        course = null;
        return returnData;
    }

    private static void addModuleButtonClicked() {
        String moduleName = moduleNameInput.getText();
        Module existingModule = table.getItems().stream()
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
            AlertBox.display("Error", "Module level can't be a string, must be either 4, 5 or 6");
            return;
        }

        if (moduleLevel != 4 && moduleLevel != 5 && moduleLevel != 6) {
            AlertBox.display("Error", "Module level must be either 4, 5 or 6");
            return;
        }

        Module module = new Module(UUID.randomUUID(), moduleName, true, moduleLevel);
        table.getItems().add(module);
        moduleNameInput.clear();
        moduleLevelInput.clear();
    }

    private static void editModuleButtonClicked() {
        Module selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a module to edit!");
            return;
        }

        String moduleName = moduleNameInput.getText();
        Module existingModule = table.getItems().stream()
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

        Module module = new Module(selectedItem.getId(), moduleName, selectedItem.getIsModuleRunning(), moduleLevel);
        removedModulesCache.add(selectedItem);
        observableModules.remove(selectedItem);
        observableModules.add(module);
        moduleNameInput.clear();
        moduleLevelInput.clear();
    }

    private static void removeModuleButtonClicked() {
        Module selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a module to remove!");
            return;
        }
        removedModulesCache.add(selectedItem);
        table.getItems().remove(selectedItem);
    }

    private static void toggleModuleStateButtonClicked() {
        Module selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a module to activate/deactivate!");
            return;
        }
        selectedItem.setIsModuleRunning(!selectedItem.getIsModuleRunning());
        Modules.updateModule(selectedItem);
        table.refresh();
    }
}
