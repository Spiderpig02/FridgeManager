package fridgemanager.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;

/**
 * Class for testing FridgeManager.
*/
public class FridgeManagerTest {

  FridgeManager fridgemanager;

  Food banan;
  Food eple;
  Food tomat;
  Food mugg;

  /**
   * Initialize a FridgeManager-object and Food-variables.
  */
  @BeforeEach
  void init() {
    fridgemanager = new FridgeManager(3, 3);

    banan=new Food("Banan", "stk", 8, LocalDate.of(2022,02,10), "Halvor");
    eple=new Food("Eple", "stk",1, LocalDate.of(2022,02,15), "Halvor");
    tomat=new Food("Tomat", "stk",8, LocalDate.of(2022,02,12), "Halvor");
    mugg=new Food("Mugg", "stk", 100, LocalDate.of(2050,02,28), "Naturen");

  }

  /**
   * Test of Constructor.
  */
  @Test
  public void testConstructor() {

    //Check the size of the Freezer and the Fridge.
    assertEquals(3, fridgemanager.getFreezerMaxsize());
    assertEquals(3, fridgemanager.getFridgeMaxsize());

    //Testing if IllegalArgumentException is correctly thrown in case of negative input.
    assertThrows(IllegalArgumentException.class,() -> {
      FridgeManager fridgeNeg = new FridgeManager(-2, 7);
    });
    assertThrows(IllegalArgumentException.class,() -> {
        FridgeManager freezerNeg = new FridgeManager(7, -2);
    });
  }

  /**
  * Test that addFridgeContent works and returns a copy of the content-list 
  * instead of the actual list.
  */
  @Test
  public void testAddFridgeContents() {

    fridgemanager.addFridgeContent(banan);
    fridgemanager.addFridgeContent(eple);

    List<Food> fridgecontent = new ArrayList<>();
    fridgecontent.add(banan);
    fridgecontent.add(eple);

    //Check if the list fridgecontent equals the content in the Fridge.
    assertEquals(fridgecontent, fridgemanager.getFridgeContents());

    fridgemanager.addFridgeContent(tomat);

    //Check if the list fridgecontent is different from the content in the Fridge.
    assertFalse(fridgecontent.equals(fridgemanager.getFridgeContents()));

    //Trigger the IllegalArgumentException. Too many object are added to the Fridge.
    assertThrows(IllegalArgumentException.class,() -> {
      fridgemanager.addFridgeContent(mugg);
    });
  }

  /**
  * Test that addfreezercontent works, and that it returns a copy of the list and not the real list.
  */
  @Test
  public void testAddFreezerContents() {

    fridgemanager.addFreezerContent(banan);
    fridgemanager.addFreezerContent(eple);

    List<Food> freezercontent = new ArrayList<>();
    freezercontent.add(banan);
    freezercontent.add(eple);

    //Check if the list freezercontent equals the content in the Freezer.
    assertEquals(freezercontent, fridgemanager.getFreezerContents());

    fridgemanager.addFreezerContent(tomat);

    //Check if the list fridgecontent is different from the content in the Fridge.
    assertFalse(freezercontent.equals(fridgemanager.getFreezerContents()));

    //Testing if IllegalArgumentException is thrown correctly when freezer is full.
    assertThrows(IllegalArgumentException.class,() -> {
      fridgemanager.addFreezerContent(mugg);
    });
  }

  /**
   * Test for removeFridgecontent.
  */
  @Test
  public void testRemoveFridgeContents() {

    fridgemanager.addFridgeContent(banan);
    fridgemanager.addFridgeContent(eple);
    fridgemanager.removeFridgeContent(eple);

    List<Food> fridgecontent = new ArrayList<>();
    fridgecontent.add(banan);
    fridgecontent.add(eple);

    //Check if the list fridgecontent is different from the content in the Fridge.
    assertFalse(fridgemanager.getFridgeContents().equals(fridgecontent));

    //Try to delete an object that is not in the fridge.
    fridgemanager.removeFridgeContent(mugg);
  }

  /**
   * Test for removeFreezercontent.
  */
  @Test
  public void testRemoveFreezerContents() {

    fridgemanager.addFreezerContent(banan);
    fridgemanager.addFreezerContent(eple);
    fridgemanager.removeFreezerContent(eple);

    List<Food> freezercontent = new ArrayList<>();
    freezercontent.add(banan);
    freezercontent.add(eple);

    //Check if the list freezercontent is different from the content in the Freezer.
    assertFalse(fridgemanager.getFreezerContents().equals(freezercontent));

    //Try to delete an object that is not in the freezer.
    fridgemanager.removeFreezerContent(mugg);
  }
}
