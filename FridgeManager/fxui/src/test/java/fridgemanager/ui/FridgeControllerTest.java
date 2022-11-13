package fridgemanager.ui;

import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.assertj.core.internal.bytebuddy.asm.Advice.Enter;
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
    stage.show();
  }
  
  /**
  * Initialize a food element.
  */
  @BeforeEach
  public void init() {
    eple=new Food("Eple", "stk",3, LocalDate.of(2022,2,15), "Halvor");
  }
  
  /**
  * Test the controller and the content.
  */
  @Test
  public void testControllerFridgeManager(){
    assertNotNull(this.controller);
    assertNotNull(this.fridgemanager);
  }
  
  /**
  * Test adding food to the fridge.
  
  @Test
  public void testAddToFridge() {
    //Add Eple to the fridge
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Eple", "stk",3, "02.11.2022", "Halvor");
  }*/

  /**
  * Test adding food to the fridge
  */
  @Test
  public void testAddToFridge() {

    //Add Eple to the fridge
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Eple",3,"stk","12/12/2022","Halvor");

    //Verifing added element
    Food lastItem = fridgemanager.getFridgeContents().get(fridgemanager.getFridgeContents().size()-1);
    checkItem(lastItem, eple);

  }

  /**
  * Test adding food to the freezer
  */
  @Test
  public void testAddToFreezer() {

    //Add Eple to the fridge
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Eple",3,"stk","12/12/2022","Halvor");

    //Verifing added element
    Food lastItem = fridgemanager.getFreezerContents().get(fridgemanager.getFreezerContents().size()-1);
    checkItem(lastItem, eple);

  }

  /**
  * Compare if two elements are equal
  */
  public void checkItem(Food food1, Food food2){
    //assertEquals(food1.getName(), food2.getName());
    //assertEquals(food1.getQuantity(), food2.getQuantity());
    //assertEquals(food1.getExpirationDate(), food2.getExpirationDate());
    //assertEquals(food1.getOwner(), food2.getOwner());
  }

  /**
  * Add Food-items into the textfields
  */
  public void addFoodItem(String name, int quantity, String unit, String expirationdate, String owner) {
    clickOn("#textfieldFood").write(name);
    clickOn("#textfieldQuantity").write(Integer.toString(quantity));
    clickOn("#dropDownMenuQuantity").clickOn(unit);
    clickOn("#datePickerExpiration").write(expirationdate);
    clickOn("#textfieldOwner").write(owner).type(KeyCode.ENTER);
  }


}
