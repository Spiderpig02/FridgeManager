package fridgemanager.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fridgemanager.core.Food;
import java.io.IOException;

/**
 * FoodSerializer class handles the serializing of the Food object.
 */
public class FoodSerializer extends JsonSerializer<Food> {

  /*
   * format: { "Name": "String", "Quantity": int, "Owner": "String",
   * "ExpirationDate": "String", "UUID": "String" }
   */
  /**
   * The method for converting Food objects to a jason format,
   * with the expected outcome above this coment.
   */
  @Override
  public void serialize(Food food, JsonGenerator jgenerator, SerializerProvider sprovider)
      throws IOException {

    jgenerator.writeStartObject();

    jgenerator.writeStringField("Name", food.getName());
    jgenerator.writeNumberField("Quantity", food.getQuantity());
    jgenerator.writeStringField("Owner", food.getOwner());
    jgenerator.writeStringField("ExpirationDate", food.getExpirationDate());
    jgenerator.writeStringField("UUID", food.id());

    jgenerator.writeEndObject();
  }
}
