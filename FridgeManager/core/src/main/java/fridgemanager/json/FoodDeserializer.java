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

/**
 * FoodDeserializier handles the task of deserializing json text to a Food
 * class.
 */
public class FoodDeserializer extends JsonDeserializer<Food> {

  /*
   * format: { "Name": "String", "Quantity": int, "Owner": "String",
   * "ExpirationDate": "String", "UUID": "String" }
   */
  /**
   * Get json text inn and returning a Food object.
   * If object not found, return null.
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
      String uUID = null;
      JsonNode nameNode = jsonNode.get("Name");
      if (nameNode instanceof TextNode) {
        name = nameNode.asText();
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
      JsonNode uUIDNode = jsonNode.get("UUID");
      if (uUIDNode instanceof TextNode) {
        uUID = uUIDNode.asText();
      }

      return new Food(name, quantity, expirationDate, owner, uUID);
    }
    return null;
  }
}
