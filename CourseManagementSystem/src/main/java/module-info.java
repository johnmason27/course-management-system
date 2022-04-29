module com.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires com.google.gson;

    opens com.gui to javafx.fxml, com.google.gson;
    exports com.gui;

    opens com.models to com.google.gson;
    opens com.models.enums to com.google.gson;

    exports com.tests.models;
    exports com.tests.security;
}