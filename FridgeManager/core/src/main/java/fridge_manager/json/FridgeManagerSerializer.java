package fridge_manager.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;
import java.io.IOException;

/** FridgeManagerSerializer class handles the serializing of the FridgeManager object */
public class FridgeManagerSerializer extends JsonSerializer<FridgeManager> {

  /*
   * format: { "FridgeMaxSize": "Int", "FridgeConters": [Food,...,...,...],
   * "FreezerMaxSize":
   * "Int",
   * "FreezerContents": [Food,...,...,...] }
   */
  /**
   * This methode creates a json string with the FileManager object Methode formats the text as the
   * coment above
   */
  @Override
  public void serialize(
      FridgeManager fManager, JsonGenerator jGenerator, SerializerProvider sProvider)
      throws IOException {

    jGenerator.writeStartObject();

    jGenerator.writeNumberField("FridgeMaxSize", fManager.getFridgeMaxsize());

    jGenerator.writeArrayFieldStart("FridgeConters");
    for (Food element : fManager.getFridgeContents()) {
      jGenerator.writeObject(element);
    }
    jGenerator.writeEndArray();

    jGenerator.writeNumberField("FreezerMaxSize", fManager.getFreezerMaxsize());

    jGenerator.writeArrayFieldStart("FreezerContents");
    for (Food element : fManager.getFreezerContents()) {
      jGenerator.writeObject(element);
    }
    jGenerator.writeEndArray();

    jGenerator.writeEndObject();
  }
}
