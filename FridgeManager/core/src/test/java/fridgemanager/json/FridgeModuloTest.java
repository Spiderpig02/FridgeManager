package fridgemanager.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FridgeModuloTest {

  private static ObjectMapper mapper;
  Food paprika;
  Food banan;
  FridgeManager fridgemanager;
  String fridgeManagerWithTwoItems;

  /*
   * Setting up mapping from JSON.
   */
  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new FridgeManagerModule());
  }

  /*
   * Initializing all variables.
   */
  @BeforeEach
  void init() {
    paprika = new Food("Paprika","stk", 4, LocalDate.of(2022,1,30),"Ola");
    banan = new Food("Banan","stk", 8, LocalDate.of(2022,2,10), "Halvor");
    fridgemanager = new FridgeManager(3, 3);

    /*
     * format:
     * {
     * "FridgeMaxSize": "Int", "FridgeConters": [Food,...,...,...],
     *
     * "FreezerMaxSize":, "Int", "FreezerContents": [Food,...,...,...]
     * }
     */
    fridgeManagerWithTwoItems =
      "{\"FridgeMaxSize\":3,"
        + "\"FridgeContents\":[{\"Name\":\"Banan\",\"Quantity\":8,\"Unit\":\"stk\",\"Owner\":\"Halvor\",\"ExpirationDate\":\"2022-02-10\"}],"
        + "\"FreezerMaxSize\":3,"
        + "\"FreezerContents\":[{\"Name\":\"Paprika\",\"Quantity\":4,\"Unit\":\"stk\",\"Owner\":\"Ola\",\"ExpirationDate\":\"2022-01-30\"}]}";
  }

  /*
   * Testing the Serializer.
   */
  @Test
  public void testSerializers() throws JsonProcessingException {
    fridgemanager.addFreezerContent(paprika);
    fridgemanager.addFridgeContent(banan);

    // Verifing that fridgeManagerWithTwoItems is equal to content written into JSON.
    try {
      assertEquals(fridgeManagerWithTwoItems, mapper.writeValueAsString(fridgemanager));

    } catch (JsonProcessingException e) {
      fail();
    }
  }

  /*
   * Help function. Verifing food element.
   */
  public void checkItem(Food food, String name, String unit, int quantity, LocalDate localDate, String owner) {
    assertEquals(name, food.getName());
    assertEquals(unit, food.getUnit());
    assertEquals(quantity, food.getQuantity());
    assertEquals(localDate, food.getExpirationDate());
    assertEquals(owner, food.getOwner());
  }
}

