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
 * FoodDeserializier handles the task of deserializing json text to a Food class.
*/
public class FoodDeserializer extends JsonDeserializer<Food> {

  /*
   * format: { "Name": "String","Unit": "String", "Quantity": int, "Owner": "String",
   * "ExpirationDate": "String" }
  */
  /**
   * This method handles the main task of getting json text inn and returning a Food object.
   * If no Food object exist, return null.
  */
  @Override
  public Food deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserializer((JsonNode) treeNode);
  }

  /**
   * Deserialize jsonNode objects and returns Food object if sucsessfull,
   * else null.
  */
  public Food deserializer(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      String name = null;
      int quantity = 0;
      String owner = null;
      String expirationDate = null;
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
      if (name != null && unit != null && quantity < 1 && expirationDate != null && owner != null) {
        return new Food(name, unit, quantity, LocalDate.parse(expirationDate), owner);
      }
    }
    return new Food(null, null, 0, null, null);
  }
}
