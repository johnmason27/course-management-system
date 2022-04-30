package com.gui;

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

public class AddRemoveModulesBox {
    private static final ObservableList<Module> observableModules = FXCollections.observableArrayList();
    private static TextField moduleNameInput;
    private static TableView<Module> table;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Modules");
        window.setMinWidth(800);
        window.setMinHeight(600);

        TableColumn<Module, UUID> moduleIdColumn = new TableColumn<>("Id");
        moduleIdColumn.setMinWidth(400);
        moduleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Module, String> moduleNameColumn = new TableColumn<>("Module name");
        moduleNameColumn.setMinWidth(400);
        moduleNameColumn.setCellValueFactory(new PropertyValueFactory<>("moduleName"));

        table = new TableView<>();
        table.getStylesheets().add(String.valueOf(AddRemoveModulesBox.class.getResource("/com/styles/table-styles.css")));
        table.setItems(FXCollections.observableList(observableModules));
        table.getColumns().addAll(moduleIdColumn, moduleNameColumn);
        table.setPlaceholder(new Label("No modules"));

        moduleNameInput = new TextField();
        moduleNameInput.setPromptText("Module name...");
        moduleNameInput.setStyle("-fx-border-color: #6D6D6D; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-width: 4px; -fx-background-color: transparent; -fx-min-width: 200px; -fx-max-width: 400px; -fx-min-height: 50px; -fx-text-fill: #FFFFFF; -fx-font-size: 18px;");
        Button addModuleButton = new Button("Add");
        addModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        addModuleButton.setOnAction(e -> addModuleButtonClicked());
        Button removeModuleButton = new Button("Remove");
        removeModuleButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        removeModuleButton.setOnAction(e -> removeModuleButtonClicked());
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 100px;");
        backButton.setOnAction(e -> {
            Session.setCachedModules(new ArrayList<>(observableModules));
            window.close();
        });

        HBox inputContainer = new HBox();
        inputContainer.getChildren().addAll(moduleNameInput, addModuleButton, removeModuleButton, backButton);
        inputContainer.setPadding(new Insets(10, 10, 10, 10));
        inputContainer.setSpacing(10);
        inputContainer.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(table, inputContainer);
        layout.setStyle("-fx-background-color: #171717;");
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
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

        Module module = new Module(UUID.randomUUID(), moduleName);
        table.getItems().add(module);
        moduleNameInput.clear();
    }

    private static void removeModuleButtonClicked() {
        ObservableList<Module> moduleSelected, allModules;
        allModules = table.getItems();
        moduleSelected = table.getSelectionModel().getSelectedItems();

        moduleSelected.forEach(allModules::remove);
    }
}
