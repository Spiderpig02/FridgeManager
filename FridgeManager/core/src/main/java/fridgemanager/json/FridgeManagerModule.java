package fridgemanager.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;

/**
 * A Jackson module for configuring JSON-serialization and deserialization of FridgeManager-objects.
*/
@SuppressWarnings("serial")
public class FridgeManagerModule extends SimpleModule {

  public static final String NAME = "FridgeManagerModule";
  public static final Version VERSION =
      new Version(1, 0, 1, "SNAPSHOT", "fridge_manager", "gruppe_39");

  /**
   * Initializes object with the correspondig serialization and deserialization-classes.
  */
  public FridgeManagerModule() {
    super(NAME, VERSION);
    addSerializer(Food.class, new FoodSerializer());
    addDeserializer(Food.class, new FoodDeserializer());

    addSerializer(FridgeManager.class, new FridgeManagerSerializer());
    addDeserializer(FridgeManager.class, new FridgeManagerDeserializer());
  }
}
