module com.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.gui to javafx.fxml;
    exports com.gui;

    exports com.tests.models;
    exports com.tests.security;
}