package com.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18px;");

        Button closeButton = new Button("Close the window");
        closeButton.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #2076C6; -fx-text-fill: #FFFFFF; -fx-font-size: 18px; -fx-min-width: 200px;");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #171717;");

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
