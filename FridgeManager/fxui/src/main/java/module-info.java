module fridgemanager.ui {
  requires transitive fridgemanager.core;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires transitive javafx.graphics;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;

  opens fridgemanager.ui to
      javafx.graphics,
      javafx.fxml;

  exports fridgemanager.ui;

  requires java.net.http;
}
