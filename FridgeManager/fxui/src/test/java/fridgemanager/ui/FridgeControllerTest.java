package fridgemanager.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;

/**
 * Unit test for simple App.
 */
public class FridgeControllerTest extends ApplicationTest {

  private FridgeController controller;
  private FridgeManager fridgemanager;

  Food eple;

  /**
   * Initialize the JavaFX App used for testing.
   */
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("FridgeApp.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    this.fridgemanager = this.controller.getFridgeManager();
    stage.setScene(new Scene(root));
    stage.setTitle("FridgeManager");
    stage.centerOnScreen();
    stage.show();
  }

  /**
   * Initialize a food element.
   */
  @BeforeEach
  public void init() {
    eple = new Food("Eple", 1, "15.02.2022", "Halvor");
  }

  /**
   * Test the controller and the content.
   */
  @Test
  public void testControllerFridgeManager() {
    assertNotNull(this.controller);
    assertNotNull(this.fridgemanager);
  }

  /**
   * Test adding food to the fridge.
   */
  @Test
  public void testAddToFridge() {

    addEple();
    clickOn("#fridgeButton");

    // Verifing added element
    Food lastItem = fridgemanager.getFridgeContents().get(fridgemanager.getFridgeContents().size() - 1);
    checkItem(lastItem, eple);
  }

  /** Test adding food to the freezer */
  @Test
  public void testAddToFreezer() {
    addEple();
    clickOn("#freezerButton");

    // Verifing added element
    Food lastItem = fridgemanager.getFreezerContents().get(fridgemanager.getFreezerContents().size() - 1);
    checkItem(lastItem, eple);
  }

  /** Test removing content from fridge */
  @Test
  public void testHandleRemoveFridge() {
    ListView<Food> listview = lookup("#fridgecontent").query();
    int lastItemInFridgeInteger = listview.getItems().size() - 1;

    // Select the last element
    listview.getSelectionModel().select(lastItemInFridgeInteger);
    clickOn("#fridgecontent");

    // Remove last element
    clickOn("#removebutton");

    // Verify that element is removed
    assertNotEquals(listview.getItems().size() - 1, lastItemInFridgeInteger);
  }

  /** Test removing content from freezer */
  @Test
  public void testHandleRemoveFreezer() {
    ListView<Food> listview = lookup("#freezercontent").query();
    int lastItemInFreezerInteger = listview.getItems().size() - 1;

    // Select the last element
    listview.getSelectionModel().select(lastItemInFreezerInteger);
    clickOn("#freezercontent");

    // Remove last element
    clickOn("#removebutton");

    // Verify that element is removed
    assertNotEquals(listview.getItems().size() - 1, lastItemInFreezerInteger);
  }

  /** Compare if two elements are equal */
  public void checkItem(Food food1, Food food2) {
    assertEquals(food1.getName(), food2.getName());
    assertEquals(food1.getQuantity(), food2.getQuantity());
    assertEquals(food1.getExpirationDate(), food2.getExpirationDate());
    assertEquals(food1.getOwner(), food2.getOwner());
  }

  /** Add Eple to fridge or freezer */
  public void addEple() {
    clickOn("#textfieldFood").write("Eple");
    clickOn("#textfieldQuantity").write("1");
    clickOn("#textfieldExpiration").write("15.02.2022");
    clickOn("#textfieldOwner").write("Halvor");
  }
}
