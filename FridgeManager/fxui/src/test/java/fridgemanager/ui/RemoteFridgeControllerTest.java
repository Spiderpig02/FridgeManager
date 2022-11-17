package fridgemanager.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;

/**
 * Class for testing Controller.
 */
public class RemoteFridgeControllerTest extends ApplicationTest {

  private RemoteFridgeController controller;
  private FridgeManager fridgemanager;
  
  Food eple;
  
  /**
  * Initialize the JavaFX-App used for testing.
  */
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FridgeRemoteApp.fxml"));
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
    eple=new Food("Banan", "stk",3, LocalDate.now(), "Halvor");
    
  }
  
  /**
  * Test the controller and its contents.
  */
  @Test
  public void testControllerFridgeManager(){
    assertNotNull(this.controller);
    assertNotNull(this.fridgemanager);
  }

  /**
  * Test adding food to the fridge.
  */
  @Test
  public void testAddToFridge() {
    //Add "Eple" to the fridge.
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Banan",3,"stk",LocalDate.now().toString(),"Halvor");

    //Verifying added element.
    Food lastItem = fridgemanager.getFridgeContents().get(fridgemanager.getFridgeContents().size()-1);
    checkItem(lastItem, eple);
  }

  /**
  * Test adding food to the freezer.
  */
  @Test
  public void testAddToFreezer() {

    //Add "Eple" to the fridge.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Banan",3,"stk",LocalDate.now().toString(),"Halvor");

    //Verifying added element.
    Food lastItem = fridgemanager.getFreezerContents().get(fridgemanager.getFreezerContents().size()-1);
    checkItem(lastItem, eple);

  }

  /**
  * Test removing content from fridge.
  */
  @Test
  public void testHandleRemoveFridge() {
    ListView<Food> listview = lookup("#fridgeContent").query();
    int lastItemInFridgeInteger = listview.getItems().size()-1;

    //Select the last element.
    listview.getSelectionModel().select(lastItemInFridgeInteger);
    clickOn("#fridgeContent");

    //Remove last element.
    clickOn("#trashcanFridge");

    //Verify that element was removed.
    assertNotEquals(listview.getItems().size()-1, lastItemInFridgeInteger);
  }

  /**
  * Test removing content from freezer.
  */
  @Test
  public void testHandleRemoveFreezer() {
    ListView<Food> listview = lookup("#freezerContent").queryListView();
    int lastItemInFreezerInteger = listview.getItems().size()-1;

    //Select the last element.
    listview.getSelectionModel().select(lastItemInFreezerInteger);
    clickOn("#freezerContent");

    //Remove last element.
    clickOn("#trashcanFreezer");

    //Verify that element was removed.
    assertNotEquals(listview.getItems().size()-1, lastItemInFreezerInteger);
  }

  /**
  * Test removing specific amount from fridge.
  */
  @Test
  public void testHandleRemoveSpecificAmountFridge() {
      
    //Add Eple to the freezer.
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Popcorn",3,"stk",LocalDate.now().toString(),"Halvor");

    //Remove 2 out of 3 "Eple".
    clickOn("#textFieldFoodRemove").write("Popcorn");
    clickOn("#textFieldQuantityRemove").write("2");
    clickOn("#dropDownMenuRemove").clickOn("fridge");
    clickOn("#removeButton");

    //Verify that "Eple" now has quantity = 1.
    ListView<Food> listview = lookup("#fridgeContent").query();
    int epleLeft = listview.getItems().get(listview.getItems().size()-1).getQuantity();
    assertEquals(epleLeft,1);             
  }

  /**
  * Test removing specific amount from freezer.
  */
  @Test
  public void testHandleRemoveSpecificAmountFreezer() {
      
    //Add Eple to the freezer.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Storfe",3,"stk",LocalDate.now().toString(),"Halvor");

    //Remove 2 out of 3 "Eple".
    clickOn("#textFieldFoodRemove").write("Storfe");
    clickOn("#textFieldQuantityRemove").write("2");
    clickOn("#dropDownMenuRemove").clickOn("freezer");
    clickOn("#removeButton");

    //Verify that "Eple" now has quantity = 1.
    ListView<Food> listview = lookup("#freezerContent").query();
    int epleLeft = listview.getItems().get(listview.getItems().size()-1).getQuantity();
    assertEquals(epleLeft,1);             
  }

  /**
  * Try to remove a larger quantity of an element than the
  * the actual amount of that element in the fridge. 
  */
  @Test
  public void testHandleRemoveToMuchAmountFridge() {

    //Add "Ostepopp" to the fridge. It is placed last.
    clickOn("#dropDownMenuAdd").clickOn("fridge");
    addFoodItem("Ostepopp",3,"stk",LocalDate.now().toString(),"Halvor");
    
    //Remove 4 of 3 "ostepopp".
    clickOn("#textFieldFoodRemove").write("Ostepopp");
    clickOn("#textFieldQuantityRemove").write("4");
    clickOn("#dropDownMenuRemove").clickOn("fridge").
    clickOn("#removeButton");
  }

  /**
  * Try to remove a larger quantity of an element than the 
  * the actual amount of that element in the freezer. 
  */
  @Test
  public void testHandleRemoveToMuchAmountFreezer() {

    //Add "Ostepopp" to the freezer. It is placed last.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Ostepopp",3,"stk",LocalDate.now().toString(),"Halvor");
    
    //Remove 4 of 3 "ostepopp".
    clickOn("#textFieldFoodRemove").write("Ostepopp");
    clickOn("#textFieldQuantityRemove").write("4");
    clickOn("#dropDownMenuRemove").clickOn("freezer").
    clickOn("#removeButton");
  }

  /**
  * Validate input in textfields.
  */
  @Test
  public void testValidateInput() {

    //Add "Eple" to the freezer with negative quantity.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Eple",-4,"stk",LocalDate.now().toString(),"Halvor");

    //Add "Eple" to the freezer with a number in its name.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Ep9e", 3, "stk", LocalDate.now().toString(), "Halvor");

    //Add "Eple" to the freezer with a number in name of owner.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Eple", 3, "stk", LocalDate.now().toString(), "Halv9r");
  }

  /**
  * Validate input in textfield for edge-cases.
  */
  @Test
  public void testValidateRemovalInput() {

    //Add "Pære" to the freezer.
    clickOn("#dropDownMenuAdd").clickOn("freezer");
    addFoodItem("Pære", 3, "stk", LocalDate.now().toString(), "Halvor");
    
    //Fail to remove object as it has not been added due to invalid input.
    clickOn("#textFieldFoodRemove").write("Pp24");
    clickOn("#textFieldQuantityRemove").write("3");
    clickOn("#dropDownMenuRemove").clickOn("freezer").
    clickOn("#removeButton");

    //Fail to remove object as it has not been added due to invalid input.
    clickOn("#textFieldFoodRemove").write("Pære");
    clickOn("#textFieldQuantityRemove").write("-3");
    clickOn("#dropDownMenuRemove").clickOn("freezer").
    clickOn("#removeButton");
  }


  /**
  * Compare if two elements are equal.
  *
  * @param food1
  * @param food2
  */
  public void checkItem(Food food1, Food food2){
    assertEquals(food1.getName(), food2.getName());
    assertEquals(food1.getQuantity(), food2.getQuantity());
    assertEquals(food1.getUnit(), food2.getUnit());
    assertEquals(food1.getExpirationDate(), food2.getExpirationDate());
    assertEquals(food1.getOwner(), food2.getOwner());
  }

  /**
  * Write values of food-item into textfields.
  *
  * @param name
  * @param quantity
  * @param unit
  * @param expirationDate
  * @param owner
  */
  public void addFoodItem(String name, int quantity, String unit, String expirationdate, String owner) {
    clickOn("#textfieldFood").write(name);
    clickOn("#textfieldQuantity").write(String.valueOf(quantity));
    clickOn("#dropDownMenuQuantity").clickOn(unit);
    clickOn("#datePickerExpiration").write(expirationdate);
    clickOn("#textfieldOwner").write(owner).type(KeyCode.ENTER);
  }
}