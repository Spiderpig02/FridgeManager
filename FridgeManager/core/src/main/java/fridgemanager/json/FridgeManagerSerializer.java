package fridgemanager.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import java.io.IOException;

/**
 * FridgeManagerSerializer class handles the serializing of FridgeManager-objects.
*/
public class FridgeManagerSerializer extends JsonSerializer<FridgeManager> {

  /*
   * format: { "FridgeMaxSize": "Int", "FridgeContents": [Food,...,...,...],
   * "FreezerMaxSize":
   * "Int",
   * "FreezerContents": [Food,...,...,...] }
  */

  /**
   * Converts FridgeManager-object to JSON-text, formatted as specified above.
   * @param fmanager
   * @param jgenerator
   * @param sprovider
   */
  @Override
  public void serialize(
      FridgeManager fmanager, JsonGenerator jgenerator, SerializerProvider sprovider)
      throws IOException {

    jgenerator.writeStartObject();

    jgenerator.writeNumberField("FridgeMaxSize", fmanager.getFridgeMaxsize());

    jgenerator.writeArrayFieldStart("FridgeContents");
    for (Food element : fmanager.getFridgeContents()) {
      jgenerator.writeObject(element);
    }
    jgenerator.writeEndArray();

    jgenerator.writeNumberField("FreezerMaxSize", fmanager.getFreezerMaxsize());

    jgenerator.writeArrayFieldStart("FreezerContents");
    for (Food element : fmanager.getFreezerContents()) {
      jgenerator.writeObject(element);
    }
    jgenerator.writeEndArray();

    jgenerator.writeEndObject();
  }
}
