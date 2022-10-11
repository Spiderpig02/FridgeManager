module fridge_manager.fxui {
    requires com.fasterxml.jackson.databind;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires fridge_manager.core;

    opens fridge_manager.fxui to javafx.graphics, javafx.fxml;
}
