module fridge_manager.ui {
    requires com.fasterxml.jackson.databind;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires fridge_manager.core;

    opens fridge_manager.ui to javafx.graphics, javafx.fxml;
}
