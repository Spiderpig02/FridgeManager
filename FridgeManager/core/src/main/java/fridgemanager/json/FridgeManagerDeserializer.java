package fridgemanager.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import java.io.IOException;

/**
 * FridgeManagerDeserializer class handles the deserializing of json to a fridgeManager object.
*/
public class FridgeManagerDeserializer extends JsonDeserializer<FridgeManager> {

  private FoodDeserializer foodDeserializer = new FoodDeserializer();

  /*
   * format: { "FridgeMaxSize": "Int", "FridgeContents": [Food,...,...,...],
   * "FreezerMaxSize":
   * "Int",
   * "FreezerContents": [Food,...,...,...] }
  */
  /**
   * The methode takes inn json text and returns a FridgeManager object if sucsessfull,
   * else null.
  */
  @Override
  public FridgeManager deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * This is a private helping methode for spliting up the complexety.
   * ReturnsFridgeManager object if sucsessfull else null.
   */
  private FridgeManager deserialize(JsonNode treeNode) {
    if (treeNode instanceof ObjectNode) {
      FridgeManager fridgeManager = null;
      JsonNode fridgeMaxSizeNode = treeNode.get("FridgeMaxSize");
      JsonNode freezerMaxSizeNode = treeNode.get("FreezerMaxSize");
      if (!(fridgeMaxSizeNode instanceof ValueNode) && !(freezerMaxSizeNode instanceof ValueNode)) {
        return null;
      } else {
        fridgeManager = new FridgeManager(fridgeMaxSizeNode.asInt(), freezerMaxSizeNode.asInt());
      }

      JsonNode fridgeContentsNode = treeNode.get("FridgeConters");
      JsonNode freezerContentsNode = treeNode.get("FreezerContents");

      boolean hasFridgeContents = fridgeContentsNode instanceof ArrayNode;
      boolean hasFreezerContents = freezerContentsNode instanceof ArrayNode;

      if (hasFridgeContents) {
        for (JsonNode elementNode : ((ArrayNode) fridgeContentsNode)) {
          Food food = foodDeserializer.deserializer(elementNode);
          if (food != null) {
            fridgeManager.addFridgeContent(food);
          }
        }
      }
      if (hasFreezerContents) {
        for (JsonNode elementNode : ((ArrayNode) freezerContentsNode)) {
          Food food = foodDeserializer.deserializer(elementNode);
          if (food != null) {
            fridgeManager.addFreezerContent(food);
          }
        }
      }
      return fridgeManager;
    }
    return null;
  }
}
