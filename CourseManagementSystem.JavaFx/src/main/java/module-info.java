module com.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires junit;
    requires com.google.gson;

    opens com.gui to javafx.fxml, com.google.gson, javafx.base;
    exports com.gui;

    opens com.models to com.google.gson, javafx.base;
    opens com.models.enums to com.google.gson, javafx.base;

    exports com.models;
    exports com.models.enums;
    exports com.tests.models;
    exports com.tests.security;
}