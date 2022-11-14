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
    eple=new Food("Banan", "stk",3, LocalDate.now(), "Halvor");
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
  * Test adding food to the fridge
  */
  @Test
  public void testAddToFridge() {

    //Add Eple to the fridge
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Banan",3,"stk",LocalDate.now().toString(),"Halvor");

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
    addFoodItem("Banan",3,"stk",LocalDate.now().toString(),"Halvor");

    //Verifing added element
    Food lastItem = fridgemanager.getFreezerContents().get(fridgemanager.getFreezerContents().size()-1);
    checkItem(lastItem, eple);

  }

  /**
  * Test removing content from fridge
  */
  @Test
  public void testHandleRemoveFridge() {
    ListView<Food> listview = lookup("#fridgeContent").query();
    int lastItemInFridgeInteger = listview.getItems().size()-1;

    //Select the last element
    listview.getSelectionModel().select(lastItemInFridgeInteger);
    clickOn("#fridgeContent");

    //Remove last element
    clickOn("#trashcanFridge1");

    //Verify that element is removed
    assertNotEquals(listview.getItems().size()-1, lastItemInFridgeInteger);
  }

  /**
  * Test removing content from freezer
  */
  @Test
  public void testHandleRemoveFreezer() {
    ListView<Food> listview = lookup("#freezerContent").queryListView();
    int lastItemInFreezerInteger = listview.getItems().size()-1;

    //Select the last element
    listview.getSelectionModel().select(lastItemInFreezerInteger);
    clickOn("#freezerContent");

    //Remove last element
    clickOn("#trashcanFridge1");

    //Verify that element is removed
    assertNotEquals(listview.getItems().size()-1, lastItemInFreezerInteger);
  }

  /**
  * Test removing specific amount from fridge
  */
  @Test
  public void testHandleRemoveSpecificAmountFridge() {
      
    //Add Eple to the freezer
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Eple",3,"stk",LocalDate.now().toString(),"Halvor");

    //Remove 2 of 3 Eple
    clickOn("#textFieldFoodRemove").write("Eple");
    clickOn("#textFieldQuantityRemove").write("2");
    clickOn("#dropDownMenuRemove").clickOn("fridge");
    clickOn("#removeButton");

    //Verify that it is 1 Eple left
    ListView<Food> listview = lookup("#fridgeContent").query();
    int epleLeft = listview.getItems().get(listview.getItems().size()-1).getQuantity();
    assertEquals(epleLeft,1);             
  }

  /**
  * Test removing specific amount from freezer
  */
  @Test
  public void testHandleRemoveSpecificAmountFreezer() {
      
    //Add Eple to the freezer
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Eple",3,"stk",LocalDate.now().toString(),"Halvor");

    //Remove 2 of 3 Eple
    clickOn("#textFieldFoodRemove").write("Eple");
    clickOn("#textFieldQuantityRemove").write("2");
    clickOn("#dropDownMenuRemove").clickOn("freezer");
    clickOn("#removeButton");

    //Verify that it is 1 Eple left
    ListView<Food> listview = lookup("#freezerContent").query();
    int epleLeft = listview.getItems().get(listview.getItems().size()-1).getQuantity();
    assertEquals(epleLeft,1);             
  }

  /**
  * Remove over the amount in the Fridge.
  */
  @Test
  public void testHandleRemoveToMuchAmountFridge() {

    //Add Ostepopp to the fridge. It is placed last
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Ostepopp",3,"stk",LocalDate.now().toString(),"Halvor");
    
    //Remove 4 of 3 ostepopp
    clickOn("#textFieldFoodRemove").write("Ostepopp");
    clickOn("#textFieldQuantityRemove").write("4");
    clickOn("#dropDownMenuRemove").clickOn("fridge").
    clickOn("#removeButton");
  }

  /**
  * Remove over the amount in the Fridge
  */
  @Test
  public void testHandleRemoveToMuchAmountFreezer() {

    //Add Ostepopp to the fridge. It is placed last
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Ostepopp",3,"stk",LocalDate.now().toString(),"Halvor");
    
    //Remove 4 of 3 ostepopp
    clickOn("#textFieldFoodRemove").write("Ostepopp");
    clickOn("#textFieldQuantityRemove").write("4");
    clickOn("#dropDownMenuRemove").clickOn("freezer").
    clickOn("#removeButton");
  }

  /**
  * Validate input in textfield
  */
  @Test
  public void testValidateInput() {

      //Add Eple to the freezer with negative quantity
      clickOn("#dropDownMenuAdd").clickOn("freezer");
      addFoodItem("Eple",-4,"stk",LocalDate.now().toString(),"Halvor");

      //Add Eple to the freezer with name including numbers
      clickOn("#dropDownMenuAdd").clickOn("freezer");
      addFoodItem("Ep9e", 3, "stk", LocalDate.now().toString(), "Halvor");

      //Må teste dato også

      //Add Eple to the freezer with owner-name including numbers
      clickOn("#dropDownMenuAdd").clickOn("freezer");
      addFoodItem("Eple", 3, "stk", LocalDate.now().toString(), "Halv9r");
  }

  /**
  * Validate input in textfield for special amount
  */
  @Test
  public void testValidateRemovalInput() {

      //Add Pære to the freezer
      clickOn("#dropDownMenuAdd").clickOn("freezer");
      addFoodItem("Pære", 3, "stk", LocalDate.now().toString(), "Halvor");
      
      //Fail to remove object due to wrong food input
      clickOn("#textFieldFoodRemove").write("Pp24");
      clickOn("#textFieldQuantityRemove").write("3");
      clickOn("#dropDownMenuRemove").clickOn("freezer").
      clickOn("#removeButton");

      //Fail to remove object due to wrong quantity input
      clickOn("#textFieldFoodRemove").write("Pære");
      clickOn("#textFieldQuantityRemove").write("-3");
      clickOn("#dropDownMenuRemove").clickOn("freezer").
      clickOn("#removeButton");
  }


  /**
  * Compare if two elements are equal
  */
  public void checkItem(Food food1, Food food2){
    assertEquals(food1.getName(), food2.getName());
    assertEquals(food1.getQuantity(), food2.getQuantity());
    assertEquals(food1.getUnit(), food2.getUnit());
    assertEquals(food1.getExpirationDate(), food2.getExpirationDate());
    assertEquals(food1.getOwner(), food2.getOwner());
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
