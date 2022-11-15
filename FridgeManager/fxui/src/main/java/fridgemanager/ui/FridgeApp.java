package fridgemanager.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class FridgeApp extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("FridgeApp.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setScene(new Scene(parent));
    stage.setTitle("FridgeManager");
    stage.setResizable(false);
    stage.centerOnScreen();
    // setRes(stage);
    stage.show();
  }

  private void setRes(Stage stage) {

    int localScreenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int localScreenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    System.out.println(localScreenWidth);
    System.out.println(localScreenHeight);

    int sceneWidth = 0;
    int sceneHeight = 0;
    if (localScreenWidth < 800 && localScreenHeight < 600) {
      sceneWidth = 600;
      sceneHeight = 350;
      System.out.println("hei1");
    } else if (localScreenWidth < 1280 && localScreenHeight < 768) {
      sceneWidth = 800;
      sceneHeight = 450;
      System.out.println("hei2");
    } else {
      sceneWidth = 1200;
      sceneHeight = 850;
      System.out.println("hei3");
    }
    stage.setHeight(sceneHeight);
    stage.setWidth(sceneWidth);
  }

  public static void main(String[] args) {
    launch();
  }
}
