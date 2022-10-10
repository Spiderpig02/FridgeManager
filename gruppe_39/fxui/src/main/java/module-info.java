module fridgemanager.fxui {
    requires com.fasterxml.jackson.databind;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires fridgemanager.core;
    
    opens fridgemanager.fxui to javafx.graphics, javafx.fxml;
}