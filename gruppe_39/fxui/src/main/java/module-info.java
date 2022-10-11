module fridge_manager.fxui {
    requires fridge_manager.core;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens fridge_manager.fxui to javafx.graphics, javafx.fxml;
    exports fridge_manager.fxui;
}
