module fridge_manager.ui {
    requires fridge_manager.core;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens fridge_manager.ui to javafx.graphics, javafx.fxml;
    exports fridge_manager.ui;

}
