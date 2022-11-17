package fridgemanager.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import fridgemanager.core.Food;
import java.io.IOException;
import java.time.LocalDate;

/**
 * FoodDeserializer handles the task of deserializing json-text to Food-objects.
*/
public class FoodDeserializer extends JsonDeserializer<Food> {

  /*
   * format: { "Name": "String","Unit": "String", "Quantity": int, "Owner": "String",
   * "ExpirationDate": "String", "UUID": "String" }
   */

  /**
   * Deserializes JSON-text into treeNodes and calls deserializer() to create a Food-object.
   * @param parser 
   * @param ctxt
   * @return Food if it exists, null otherwise
   */
  @Override
  public Food deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserializer((JsonNode) treeNode);
  }

  /**
  * Converts content of JSON-text into a Food-object.
  * @param jsonNode
  * @return Food if it exists, null otherwise.
  */
  public Food deserializer(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      String name = null;
      int quantity = 0;
      String owner = null;
      String expirationDate = null;
      String Uuid = null;
      String unit = null;
      JsonNode nameNode = jsonNode.get("Name");
      if (nameNode instanceof TextNode) {
        name = nameNode.asText();
      }
      JsonNode unitNode = jsonNode.get("Unit");
      if (unitNode instanceof TextNode) {
        unit = unitNode.asText();
      }
      JsonNode quantityNode = jsonNode.get("Quantity");
      if (quantityNode instanceof ValueNode) {
        quantity = quantityNode.asInt();
      }
      JsonNode ownerNode = jsonNode.get("Owner");
      if (ownerNode instanceof TextNode) {
        owner = ownerNode.asText();
      }
      JsonNode expirationDateNode = jsonNode.get("ExpirationDate");
      if (expirationDateNode instanceof TextNode) {
        expirationDate = expirationDateNode.asText();
      }
      JsonNode UuidNode = jsonNode.get("UUID");
      if (UuidNode instanceof TextNode) {
        Uuid = UuidNode.asText();
      }

      return new Food(name, unit, quantity, LocalDate.parse(expirationDate), owner, Uuid);
    }
    return null;
  }
}
