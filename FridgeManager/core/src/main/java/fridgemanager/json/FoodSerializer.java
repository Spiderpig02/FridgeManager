package fridgemanager.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fridgemanager.core.Food;
import java.io.IOException;

/**
 * FoodSerializer class handles the serializing of Food-objects into JSON-text.
*/
public class FoodSerializer extends JsonSerializer<Food> {

  /*
   * format: { "Name": "String", "Unit": "String", "Quantity": "int", "Owner": "String",
   * "ExpirationDate": "String", "UUID": "String" }
   */

  /**
   * Converts Food-object to JSON-text, formatted as specified above.

   * @param food food to be serialized
   * @param jgenerator generator for json
   * @param sprovider provider for json
   */
  @Override
  public void serialize(Food food, JsonGenerator jgenerator, SerializerProvider sprovider)
      throws IOException {

    jgenerator.writeStartObject();

    jgenerator.writeStringField("Name", food.getName());
    jgenerator.writeNumberField("Quantity", food.getQuantity());
    jgenerator.writeStringField("Unit", food.getUnit());
    jgenerator.writeStringField("Owner", food.getOwner());
    jgenerator.writeStringField("ExpirationDate", food.getExpirationDate().toString());
    jgenerator.writeStringField("UUID", food.getId());

    jgenerator.writeEndObject();
  }
}
