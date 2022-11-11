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
   * format: { "Name": "String", "Unit": "String", "Quantity": "int", "Owner": "String",
   * "ExpirationDate": "String" }
  */
  /**
   * The method for converting Food objects to a json format,
   * with the expected outcome above this coment.
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

    jgenerator.writeEndObject();
  }
}
