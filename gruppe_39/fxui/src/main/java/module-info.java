module fridge_manager.fxui {
    requires fridge_manager.core;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens fridge_manager.fxui to javafx.graphics, javafx.fxml;
    exports fridge_manager.fxui;

}
