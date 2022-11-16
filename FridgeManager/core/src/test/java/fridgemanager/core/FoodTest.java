package fridgemanager.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Food.
*/
public class FoodTest {

  Food paprika;

  /**
   * Initialize food-object.
  */
  @BeforeEach
  void init() {
    paprika = new Food("Paprika","stk", 4, LocalDate.of(2022,1,28),"Ola","a75ce814-8f3d-4011-a92d-878dbc7af2c2");
  }

  /**
   * Tests constructor.
  */
  @Test
  public void testConstructor() {

    //Checking if the initialized variables are correct.
    assertEquals(4, paprika.getQuantity());
    assertEquals("Paprika", paprika.getName());
    assertEquals("stk", paprika.getUnit());
    assertEquals("Ola", paprika.getOwner());
    assertEquals("a75ce814-8f3d-4011-a92d-878dbc7af2c2", paprika.getId());
    assertEquals(LocalDate.of(2022,1,28), paprika.getExpirationDate());

    //Testing if IllegalArgumentException is correctly thrown in case of negative input.
    assertThrows(IllegalArgumentException.class, () -> {
      Food laks = new Food("Laks","stk",-4, LocalDate.of(2022,1,28),"Ola");
    });

    //Testing if IllegalArgumentException is correctly thrown in case of negative input.
    assertThrows(IllegalArgumentException.class, () -> {
      Food laks = new Food("Laks","stk",-4, LocalDate.of(2022,1,28),"Ola","a75ce814-8f3d-4011-a92d-878dbc7af2c2");
    });
  }

  /**
  * Test setQuantity.
  */
  @Test
  public void testsetQuantity() {
  
    paprika.setQuantity(1500);

    //Check if the quantity is changed.
    assertEquals(1500, paprika.getQuantity());

    //Test IllegalArgumentException for negative input.
    assertThrows(IllegalArgumentException.class,() -> {
    paprika.setQuantity(-2);
    });
  }
  

  /**
  * Test setUnit.
  */
  @Test
  public void testSetUnit() {
    paprika.setUnit("kg");

    //Check if the quantity is changed.
    assertEquals("kg", paprika.getUnit());
  }
  
  /**
  * Test toString-method.
  */
  @Test
  public void testToString() {
    assertEquals(paprika.toString(),paprika.getQuantity()+ " " + paprika.getUnit() + " " +paprika.getName()+", "+paprika.getOwner()+" sin, går ut: "+paprika.getExpirationDate().toString());
  }
}
